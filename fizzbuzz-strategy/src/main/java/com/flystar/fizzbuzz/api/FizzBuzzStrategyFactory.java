package com.flystar.fizzbuzz.api;

import com.flystar.fizzbuzz.impl.FizzBuzzStrategyFactoryImpl;
import com.flystar.fizzbuzz.impl.Strategy;

/**
 * Created by zack on 8/12/2016.
 */
public interface FizzBuzzStrategyFactory {

    FizzBuzzStrategy getStrategy(Strategy strategyName);

    static FizzBuzzStrategyFactory getDefault(){
        return new FizzBuzzStrategyFactoryImpl();
    }
}
