package com.alevel.deliverit.customers;

import com.alevel.deliverit.customers.gateway.LogisticsGateway;
import com.alevel.deliverit.customers.request.RouteLookupRequest;
import com.alevel.deliverit.customers.verticle.VertxContext;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        @Override
        public <T> EventBus send(String address, Object message, Handler<AsyncResult<Message<T>>> replyHandler) {
            this.address = address;
            this.request = (RouteLookupRequest) message;
            return null;
        }

        @Override
        public EventBus send(String address, Object message) {
            return null;
        }

        @Override
        public EventBus send(String address, Object message, DeliveryOptions options) {
            return null;
        }

        @Override
        public <T> EventBus send(String address, Object message, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler) {
            return null;
        }

        @Override
        public EventBus publish(String address, Object message) {
            return null;
        }

        @Override
        public EventBus publish(String address, Object message, DeliveryOptions options) {
            return null;
        }

        @Override
        public <T> MessageConsumer<T> consumer(String address) {
            return null;
        }

        @Override
        public <T> MessageConsumer<T> consumer(String address, Handler<Message<T>> handler) {
            return null;
        }

        @Override
        public <T> MessageConsumer<T> localConsumer(String address) {
            return null;
        }

        @Override
        public <T> MessageConsumer<T> localConsumer(String address, Handler<Message<T>> handler) {
            return null;
        }

        @Override
        public <T> MessageProducer<T> sender(String address) {
            return null;
        }

        @Override
        public <T> MessageProducer<T> sender(String address, DeliveryOptions options) {
            return null;
        }

        @Override
        public <T> MessageProducer<T> publisher(String address) {
            return null;
        }

        @Override
        public <T> MessageProducer<T> publisher(String address, DeliveryOptions options) {
            return null;
        }

        @Override
        public EventBus registerCodec(MessageCodec codec) {
            return null;
        }

        @Override
        public EventBus unregisterCodec(String name) {
            return null;
        }

        @Override
        public <T> EventBus registerDefaultCodec(Class<T> clazz, MessageCodec<T, ?> codec) {
            return null;
        }

        @Override
        public EventBus unregisterDefaultCodec(Class clazz) {
            return null;
        }

        @Override
        public void start(Handler<AsyncResult<Void>> completionHandler) {

        }

        @Override
        public void close(Handler<AsyncResult<Void>> completionHandler) {

        }

        @Override
        public EventBus addInterceptor(Handler<SendContext> interceptor) {
            return null;
        }

        @Override
        public EventBus removeInterceptor(Handler<SendContext> interceptor) {
            return null;
        }

        @Override
        public boolean isMetricsEnabled() {
            return false;
        }
    }
}
