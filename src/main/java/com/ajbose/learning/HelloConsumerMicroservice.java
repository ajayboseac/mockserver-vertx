package com.ajbose.learning;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.*;
import io.vertx.rxjava.ext.web.client.*;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import rx.Single;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import  com.ajbose.learning.model.EndPoint;

public class HelloConsumerMicroservice extends AbstractVerticle {

    Map endPointRepository = new HashMap<String, EndPoint>();

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/v1/mockserver/endpoints").handler(this::getAllEndPoints);
        router.post("/v1/mockserver/endpoints").handler(this::addEndPoint);
        router.getWithRegex(".*").handler(this::handleMockCall);
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8081);
    }

    private void handleMockCall(RoutingContext routingContext) {
        System.out.println("URI "+routingContext.request().absoluteURI());
        System.out.println("URI "+routingContext.request().path());
        routingContext.response().end(Json.encode(endPointRepository.get(routingContext.request().path())));
    }

    private void getAllEndPoints(RoutingContext rc){
        Collection values = endPointRepository.values();
        String s = new Gson().toJson(values);
        rc.response().end(s);
    }

    private void addEndPoint(RoutingContext rc){
        EndPoint endPoint = Json.decodeValue(rc.getBodyAsString(),EndPoint.class);
        endPointRepository.put(endPoint.getUrl(),endPoint);
        rc.response().end("ok");
    }

    private void invokeMyFirstMicroservice(RoutingContext rc) {
//        HttpRequest<JsonObject> request1 = client
//                .get(8080, "localhost", "/Luke")
//                .as(BodyCodec.jsonObject());
//        HttpRequest<JsonObject> request2 = client
//                .get(8080, "localhost", "/Leia")
//                .as(BodyCodec.jsonObject());
//        Single<JsonObject> s1 = request1.rxSend()
//                .map(HttpResponse::body);
//        Single<JsonObject> s2 = request2.rxSend()
//                .map(HttpResponse::body);
//        Single
//                .zip(s1, s2, (luke, leia) -> {
//                    // We have the results of both requests in Luke and Leia
//                    return new JsonObject()
//                            .put("Luke", luke.getString("message"))
//                            .put("Leia", leia.getString("message"));
//                })
//                .subscribe(
//                        result -> rc.response().end(result.encodePrettily()),
//                        error -> {
//                            error.printStackTrace();
//                            rc.response()
//                                    .setStatusCode(500).end(error.getMessage());
//                        }
//                );

        rc.response().end("Hello");
    }
}
