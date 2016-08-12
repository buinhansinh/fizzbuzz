package com.flystar.restapi.test;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by zack on 8/12/2016.
 */
public interface FizzBuzzClient {

    @POST
    @Path("/fizzbuzz/classic")
    @Produces("application/json")
    @Consumes("application/json")
    Result getResult(Request request);

}
