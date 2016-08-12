package com.flystar.restapi.test;


import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * Created by zack on 8/12/2016.
 */
public class RestClient {

    public FizzBuzzClient create(int port){
        return new ResteasyClientBuilder().build().target(getUrl(port)).proxy(FizzBuzzClient.class);
    }

    private String getUrl(int port) {
        String url = System.getProperty("com.flystar.restapi.test.url");
        if(url == null) url= "http://localhost";
        return String.format("%s:%d",url,port);
    }
}
