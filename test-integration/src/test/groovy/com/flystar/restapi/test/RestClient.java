package com.flystar.restapi.test;


import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * Created by zack on 8/12/2016.
 */
public class RestClient {

    public FizzBuzzClient create(){
        return new ResteasyClientBuilder().build().target(getUrl()).proxy(FizzBuzzClient.class);
    }

    private String getUrl() {
        final String url = System.getProperty("com.flystar.restapi.test.url");
        if(url == null) return "http://localhost:9000";
        return url;
    }
}
