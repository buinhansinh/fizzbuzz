package com.flystar.restapi.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountedCompleter;

/**
 * Created by zack on 8/12/2016.
 */
public class SimpleFuture extends CompletableFuture<String>{
    @Override
    public boolean completeExceptionally(Throwable ex) {
        return super.completeExceptionally(ex);
    }
}
