package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.request.RouteLookupRequest;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.*;
import io.vertx.core.streams.WriteStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Eugene Mitrofanov
 */

class CustomersVerticleTest {

    private Vertx vertx;

    @BeforeEach
    void setUp() {
//        VertxContext.instance().setEventBus();
//        vertx = Vertx.vertx();
//        vertx.deployVerticle(CustomersVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @AfterEach
    void tearDown() {
//        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    void testVerticle() {
        TestEventBus eb = new TestEventBus();
        VertxContext.instance().setEventBus(eb);
        RouteLookupRequest request = new RouteLookupRequest(1L, 2L);
        LogisticsGateway.find(request);
        assertEquals(request, eb.request);
    }

    private static class TestEventBus implements EventBus {

        private RouteLookupRequest request;
        private String address;

        /**
         * Sends a message.
         * <p>
         * The message will be delivered to at most one of the handlers registered to the address.
         *
         * @param address the address to send it to
         * @param message the message, may be {@code null}
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public EventBus send(String address, Object message) {
            return null;
        }

        /**
         * Like {@link #send(String, Object)} but specifying a {@code replyHandler} that will be called if the recipient
         * subsequently replies to the message.
         *
         * @param address      the address to send it to
         * @param message      the message, may be {@code null}
         * @param replyHandler reply handler will be called when any reply from the recipient is received, may be {@code null}
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public <T> EventBus send(String address, Object message, Handler<AsyncResult<Message<T>>> replyHandler) {
            this.address = address;
            this.request = (RouteLookupRequest) message;
            return null;
        }

        /**
         * Like {@link #send(String, Object)} but specifying {@code options} that can be used to configure the delivery.
         *
         * @param address the address to send it to
         * @param message the message, may be {@code null}
         * @param options delivery options
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public EventBus send(String address, Object message, DeliveryOptions options) {
            return null;
        }

        /**
         * Like {@link #send(String, Object, DeliveryOptions)} but specifying a {@code replyHandler} that will be called if the recipient
         * subsequently replies to the message.
         *
         * @param address      the address to send it to
         * @param message      the message, may be {@code null}
         * @param options      delivery options
         * @param replyHandler reply handler will be called when any reply from the recipient is received, may be {@code null}
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public <T> EventBus send(String address, Object message, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler) {
            return null;
        }

        /**
         * Publish a message.<p>
         * The message will be delivered to all handlers registered to the address.
         *
         * @param address the address to publish it to
         * @param message the message, may be {@code null}
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public EventBus publish(String address, Object message) {
            return null;
        }

        /**
         * Like {@link #publish(String, Object)} but specifying {@code options} that can be used to configure the delivery.
         *
         * @param address the address to publish it to
         * @param message the message, may be {@code null}
         * @param options the delivery options
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public EventBus publish(String address, Object message, DeliveryOptions options) {
            return null;
        }

        /**
         * Create a message consumer against the specified address.
         * <p>
         * The returned consumer is not yet registered
         * at the address, registration will be effective when {@link MessageConsumer#handler(Handler)}
         * is called.
         *
         * @param address the address that it will register it at
         * @return the event bus message consumer
         */
        @Override
        public <T> MessageConsumer<T> consumer(String address) {
            return null;
        }

        /**
         * Create a consumer and register it against the specified address.
         *
         * @param address the address that will register it at
         * @param handler the handler that will process the received messages
         * @return the event bus message consumer
         */
        @Override
        public <T> MessageConsumer<T> consumer(String address, Handler<Message<T>> handler) {
            return null;
        }

        /**
         * Like {@link #consumer(String)} but the address won't be propagated across the cluster.
         *
         * @param address the address to register it at
         * @return the event bus message consumer
         */
        @Override
        public <T> MessageConsumer<T> localConsumer(String address) {
            return null;
        }

        /**
         * Like {@link #consumer(String, Handler)} but the address won't be propagated across the cluster.
         *
         * @param address the address that will register it at
         * @param handler the handler that will process the received messages
         * @return the event bus message consumer
         */
        @Override
        public <T> MessageConsumer<T> localConsumer(String address, Handler<Message<T>> handler) {
            return null;
        }

        /**
         * Create a message sender against the specified address.
         * <p>
         * The returned sender will invoke the {@link #send(String, Object)}
         * method when the stream {@link WriteStream#write(Object)} method is called with the sender
         * address and the provided data.
         *
         * @param address the address to send it to
         * @return The sender
         */
        @Override
        public <T> MessageProducer<T> sender(String address) {
            return null;
        }

        /**
         * Like {@link #sender(String)} but specifying delivery options that will be used for configuring the delivery of
         * the message.
         *
         * @param address the address to send it to
         * @param options the delivery options
         * @return The sender
         */
        @Override
        public <T> MessageProducer<T> sender(String address, DeliveryOptions options) {
            return null;
        }

        /**
         * Create a message publisher against the specified address.
         * <p>
         * The returned publisher will invoke the {@link #publish(String, Object)}
         * method when the stream {@link WriteStream#write(Object)} method is called with the publisher
         * address and the provided data.
         *
         * @param address The address to publish it to
         * @return The publisher
         */
        @Override
        public <T> MessageProducer<T> publisher(String address) {
            return null;
        }

        /**
         * Like {@link #publisher(String)} but specifying delivery options that will be used for configuring the delivery of
         * the message.
         *
         * @param address the address to publish it to
         * @param options the delivery options
         * @return The publisher
         */
        @Override
        public <T> MessageProducer<T> publisher(String address, DeliveryOptions options) {
            return null;
        }

        /**
         * Register a message codec.
         * <p>
         * You can register a message codec if you want to send any non standard message across the event bus.
         * E.g. you might want to send POJOs directly across the event bus.
         * <p>
         * To use a message codec for a send, you should specify it in the delivery options.
         *
         * @param codec the message codec to register
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public EventBus registerCodec(MessageCodec codec) {
            return null;
        }

        /**
         * Unregister a message codec.
         * <p>
         *
         * @param name the name of the codec
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public EventBus unregisterCodec(String name) {
            return null;
        }

        /**
         * Register a default message codec.
         * <p>
         * You can register a message codec if you want to send any non standard message across the event bus.
         * E.g. you might want to send POJOs directly across the event bus.
         * <p>
         * Default message codecs will be used to serialise any messages of the specified type on the event bus without
         * the codec having to be specified in the delivery options.
         *
         * @param clazz the class for which to use this codec
         * @param codec the message codec to register
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public <T> EventBus registerDefaultCodec(Class<T> clazz, MessageCodec<T, ?> codec) {
            return null;
        }

        /**
         * Unregister a default message codec.
         * <p>
         *
         * @param clazz the class for which the codec was registered
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public EventBus unregisterDefaultCodec(Class clazz) {
            return null;
        }

        /**
         * Start the event bus. This would not normally be called in user code
         *
         * @param completionHandler handler will be called when event bus is started
         */
        @Override
        public void start(Handler<AsyncResult<Void>> completionHandler) {

        }

        /**
         * Close the event bus and release any resources held. This would not normally be called in user code
         *
         * @param completionHandler may be {@code null}
         */
        @Override
        public void close(Handler<AsyncResult<Void>> completionHandler) {

        }

        /**
         * Add an interceptor that will be called whenever a message is sent from Vert.x
         *
         * @param interceptor the interceptor
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public EventBus addInterceptor(Handler<SendContext> interceptor) {
            return null;
        }

        /**
         * Remove an interceptor
         *
         * @param interceptor the interceptor
         * @return a reference to this, so the API can be used fluently
         */
        @Override
        public EventBus removeInterceptor(Handler<SendContext> interceptor) {
            return null;
        }

        /**
         * Whether the metrics are enabled for this measured object
         *
         * @return true if the metrics are enabled
         */
        @Override
        public boolean isMetricsEnabled() {
            return false;
        }
    }
}