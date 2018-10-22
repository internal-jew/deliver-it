package com.alevel.deliverit.gateway;

import com.alevel.deliverit.ModuleAPI;
import com.alevel.deliverit.ServiceMethod;
import com.alevel.deliverit.codecs.DefaultCodec;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageCodec;

import java.io.*;
import java.util.Map;
import java.util.Optional;

/**
 * @author Sergey Bogovesov
 */
public class LogisticsVerticle extends AbstractVerticle {
    private DefaultCodec defaultCodec = new DefaultCodec();
    private DeliveryOptions options;

    public LogisticsVerticle() {
        init();
        options = new DeliveryOptions().setCodecName(defaultCodec.name());
    }

    @Override
    public void start() throws Exception {
        Map<String, ServiceMethod> consumersContainer = ModuleAPI.getInstance().getConsumersContainer();

        EventBus eb = vertx.eventBus();
        eb.registerCodec(new MessageCodec() {
            @Override
            public void encodeToWire(Buffer buffer, Object o) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
                    out.writeObject(o);
                    out.flush();
                    final byte[] bytes = bos.toByteArray();
                    buffer.appendBytes(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public Object decodeFromWire(int pos, Buffer buffer) {
                ByteArrayInputStream bis = new ByteArrayInputStream(buffer.getBytes(pos, buffer.length()));
                ObjectInput in = null;
                try {
                    in = new ObjectInputStream(bis);
                    Object o = in.readObject();
                    return o;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    return null;
                }
            }

            @Override
            public Object transform(Object o) {
                return o;
            }

            @Override
            public String name() {
                return "myCodec";
            }

            @Override
            public byte systemCodecID() {
                return -1;
            }
        });

        for (Map.Entry<String, ServiceMethod> e : consumersContainer.entrySet()) {

            String address = e.getKey();
            ServiceMethod value = e.getValue();

            eb.consumer(address, message -> {
                Optional optional = value.invokeConsumer(message.body());
                if (optional.isPresent() && !optional.equals(Optional.empty())) {
                    System.out.println("Sending reply");
                    message.reply(optional.get(), options);
                    System.out.println("Reply sent");
                }
            });
        }
    }

    private void init() {
        ModuleAPI.getInstance().registerConsumers(new RouteLookup());
//        ModuleAPI.getInstance().registerConsumers(new PriceLookup());
        ModuleAPI.getInstance().registerConsumers(new DeliveryTimeLookup());
        ModuleAPI.getInstance().registerConsumers(new TrackNumberLookup());
    }
}
