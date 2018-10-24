package com.alevel.deliverit;


import com.alevel.deliverit.codecs.DefaultCodec;
import com.alevel.deliverit.customers.verticle.CustomersVerticle;
import com.alevel.deliverit.gateway.*;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Unit test for Rest.
 */
@DisplayName("Rest API should:")
class RestTest {

    @Test
    @DisplayName("Parse json to Object")
    void testParseToObject() {
        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setMaxEventLoopExecuteTime(Long.MAX_VALUE);

        Vertx vertx = Vertx.vertx(vertxOptions);
        vertx.eventBus().registerCodec(new DefaultCodec());

        final ModuleAPI instance = ModuleAPI.getInstance();
        instance.registerConsumers(new RouteLookup());
//        instance.registerConsumers(new PriceLookup());
        instance.registerConsumers(new DeliveryTimeLookup());
        instance.registerConsumers(new TrackNumberLookup());

        vertx.deployVerticle(new LogisticsVerticle());
        vertx.deployVerticle(new CustomersVerticle());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ParcelReceptionEndpoint parcelReceptionEndpoint = new ParcelReceptionEndpoint();

        try {
            Path path = getPath();
            File file = new File(path.toUri());
            JSONObject jsonObject = getJsonObject(file);
            String jsonString = jsonObject.toJSONString();
//            parcelReceptionEndpoint.accept(jsonString);
        } catch (IOException | ParseException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJsonObject(File file) throws IOException, ParseException {
        return (JSONObject) new JSONParser().parse(new FileReader(file));
    }

    private Path getPath() throws URISyntaxException {
        return Paths.get(
                getClass()
                        .getClassLoader()
                        .getResource("JsonRequest.json")
                        .toURI());


    }

    @Test
    @DisplayName("Get json from HTTP request")
    void vertexTest() {
        try {
            new RestVerticle().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
