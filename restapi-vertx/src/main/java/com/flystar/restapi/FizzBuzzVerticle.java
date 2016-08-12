package com.flystar.restapi;

import com.flystar.fizzbuzz.api.FizzBuzzStrategyFactory;
import com.flystar.fizzbuzz.impl.Strategy;
import com.flystar.service.api.FizzBuzzService;
import com.flystar.service.impl.FizzBuzzServiceSimpleImpl;
import com.flystar.service.utils.Utils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.TimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zack on 8/12/2016.
 */
public class FizzBuzzVerticle extends AbstractVerticle{
    private final static long TIMEOUT = Duration.ofSeconds(20).toMillis();
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private HttpServer httpServer;

    public final int getPort(){
        final String port = System.getProperty("http.port");
        return Integer.parseInt(Utils.isNullOrEmpty(port)?"9080":port);
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        LOG.info("Starting server....");
        final Router router = Router.router(vertx);
        httpServer = vertx.createHttpServer().requestHandler(router::accept).listen(getPort(), res -> {
            if(res.succeeded()){
                LOG.info("Server started at port "+getPort());
                startFuture.complete();
            }else{
                LOG.info("Server failed to start at port "+getPort());
                startFuture.fail(res.cause());
            }
        });
        router.route().handler(BodyHandler.create());
        router.post("/fizzbuzz/:strategy").consumes("application/json").handler(this::playFizzBuzz).failureHandler(this::errorMessage);
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        LOG.info("Stopping server...");
        if(httpServer != null) httpServer.close(res -> {
            if(res.succeeded()){
                stopFuture.complete();
            }else{
                stopFuture.fail(res.cause());
            }
        });

    }

    @Override
    public void stop() throws Exception {

    }

    void playFizzBuzz(RoutingContext routingContext){
            LOG.info("Receiving request");
            final String strategyName = getParam(routingContext,"strategy");
            final Strategy strategy = Strategy.getStrategy(strategyName);
            LOG.info("Chosen FizzBuzz strategy: "+strategy.toString());
            final FizzBuzzService fbs = new FizzBuzzServiceSimpleImpl(FizzBuzzStrategyFactory.getDefault().getStrategy(strategy));
            final String numbers = getNumbers(routingContext);
            if(LOG.isDebugEnabled()) LOG.debug("Numbers = "+numbers);
            final String result = fbs.evaluateNumbersInString(numbers);
            sendResponse(routingContext,200,new JsonObject().put("result",result));
    }

    String getNumbers(RoutingContext rc){
        final String numbers = rc.getBodyAsJson().getString("numbers");
        if(Utils.isNullOrEmpty(numbers)){
            throw new RuntimeException("Empty payload! The payload should be like {\"numbers\": \"1,3,5,2\"}");
        }
        return numbers;
    }

    private void sendResponse(RoutingContext rc,int statusCode, JsonObject content){
        if(LOG.isDebugEnabled()) LOG.debug(content.encodePrettily());
        rc.response().setStatusCode(statusCode).putHeader("Content-Type","application/json").end(content.encodePrettily());
    }

    private String getParam(RoutingContext rc,String name){
        return rc.request().getParam(name);
    }

    void errorMessage(RoutingContext rc){
        LOG.error("Error when processing request",rc.failure());
        rc.response().setStatusCode(400).putHeader("Content-Type","application/json").end(new JsonObject().put("error",rc.failure().getMessage()).put("statusCode",400).encodePrettily());
    }
}
