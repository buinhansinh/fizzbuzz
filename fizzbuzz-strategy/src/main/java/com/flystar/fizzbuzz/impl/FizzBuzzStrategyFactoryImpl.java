package com.flystar.fizzbuzz.impl;

import com.flystar.fizzbuzz.api.FizzBuzzStrategy;
import com.flystar.fizzbuzz.api.FizzBuzzStrategyFactory;
import com.flystar.fizzbuzz.impl.Strategy;

/**
 * Created by zack on 8/12/2016.
 */
public final class FizzBuzzStrategyFactoryImpl implements FizzBuzzStrategyFactory {
    @Override
    public FizzBuzzStrategy getStrategy(Strategy strategyName) {
        switch (strategyName){
            case CLASSIC: return new FizzBuzzClassicStrategy();
            default: throw new RuntimeException("Not Supported Strategy");
        }
    }
}
