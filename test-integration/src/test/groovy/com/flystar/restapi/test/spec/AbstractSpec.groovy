package com.flystar.restapi.test.spec

import com.flystar.restapi.FizzBuzzVerticle
import com.flystar.restapi.test.FizzBuzzClient
import com.flystar.restapi.test.RestClient
import com.flystar.restapi.test.SimpleFuture
import io.vertx.core.AsyncResult
import io.vertx.core.DeploymentOptions
import io.vertx.core.Handler
import io.vertx.core.Vertx
import org.junit.After
import org.junit.Before
import spock.lang.Specification

import java.util.concurrent.TimeUnit

/**
 * Created by zack on 8/12/2016.
 */
class AbstractSpec extends Specification {
    private FizzBuzzVerticle verticle;
    protected FizzBuzzClient client;
    private final Vertx vertx = Vertx.vertx();

    @Before
    def "Setting up server"(){
        println "Starting test"
        verticle = new FizzBuzzVerticle();
        final SimpleFuture sf = new SimpleFuture();
        vertx.deployVerticle(verticle, new DeploymentOptions(),new Handler<AsyncResult<String>>() {
            @Override
            void handle(AsyncResult<String> res) {
                if(res.succeeded()){
                    println "Verticle deployed: "+res.result()
                    sf.complete(res.result());
                }else{
                    println "Failed when starting server"
                    sf.completeExceptionally(res.cause());
                }
            }
        });
        sf.get(20,TimeUnit.SECONDS);
        client = new RestClient().create();
    }


    @After
    def "Stopping Server"(){
        println "Stopping test"
        final SimpleFuture sf = new SimpleFuture();
        vertx.close(new Handler<AsyncResult<Void>>() {
            @Override
            void handle(AsyncResult<Void> res) {
                if(res.succeeded()){
                    println "Test stopped successfully"
                    sf.complete("");
                }
                else{
                    println "Test failed to stop"
                    sf.completeExceptionally(res.cause())
                }
            }
        })
        sf.get();
    }
}
