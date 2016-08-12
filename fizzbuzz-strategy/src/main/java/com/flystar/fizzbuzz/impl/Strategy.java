package com.flystar.fizzbuzz.impl;

/**
 * Created by zack on 8/12/2016.
 */
public enum Strategy {
    CLASSIC("classic");

    final String name;

    Strategy(String name) {
        this.name = name;
    }

    public static Strategy getStrategy(String name){
        for(Strategy st : values()){
            if(st.name.equalsIgnoreCase(name)) return st;
        }
        throw new RuntimeException("No Strategy found");
    }
}
