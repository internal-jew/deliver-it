package com.alevel.deliverit.gateway;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

import java.io.*;

/**
 * A {@linkplain MessageCodec message codec} that serialized and deserializes Java objects
 * using {@link ObjectOutputStream} and {@link ObjectInputStream}.
 *
 * @author Vladyslav Lubenskyi
 */
public class ByteArrayCodec implements MessageCodec<Object, Object> {

    public static final String CODEC_NAME = "byteArrayCodec";

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
            System.out.println("Trying to decode");
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            System.out.println("Decoded " + ((RouteLookupRequest) o).getStart());
            return o;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception " + e.getLocalizedMessage());
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Object transform(Object o) {
        return o;
    }

    @Override
    public String name() {
        return CODEC_NAME;
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
