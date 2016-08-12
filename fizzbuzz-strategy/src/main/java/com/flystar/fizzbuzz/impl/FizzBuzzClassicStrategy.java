package com.flystar.fizzbuzz.impl;

import com.flystar.fizzbuzz.api.FizzBuzzStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zack on 8/12/2016.
 */
final class FizzBuzzClassicStrategy implements FizzBuzzStrategy {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String substitute(String number) {
        if (log.isDebugEnabled()) log.debug("Checking number: " + number);
        try {
            final long val = Long.parseLong(number);
            if (val % 15 == 0) {
                return "Fizz Buzz";
            }
            if (val % 3 == 0) {
                return "Fizz";
            }
            if (val % 5 == 0) {
                return "Buzz";
            }
            return number;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
