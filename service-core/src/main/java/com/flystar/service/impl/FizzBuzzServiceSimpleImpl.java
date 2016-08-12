package com.flystar.service.impl;

import com.flystar.fizzbuzz.api.FizzBuzzStrategy;
import com.flystar.service.api.FizzBuzzService;
import com.flystar.service.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zack on 8/12/2016.
 */
public class FizzBuzzServiceSimpleImpl implements FizzBuzzService {
    private final static String SEPARATOR = ",";

    private final FizzBuzzStrategy strategy;

    public FizzBuzzServiceSimpleImpl(FizzBuzzStrategy strategy) {
        this.strategy = strategy;
    }


    @Override
    public String evaluateNumbersInString(String numbers) {
        if(Utils.isNullOrEmpty(numbers)) throw new RuntimeException("Invalid argument numbers is null or empty");
        final List<String> numberList = evaluateNumbers(Arrays.asList(numbers.split(SEPARATOR)));
        final StringBuilder sb = new StringBuilder();
        for(String number : numberList){
            sb.append(number);
            sb.append(",");
        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    @Override
    public List<String> evaluateNumbers(List<String> numbers) {
        if(Utils.isEmpty(numbers)) throw new RuntimeException("Invalid argument list can not be empty");
        final List<String> results = new ArrayList<>(numbers.size());
        for(String number : numbers){
            results.add(strategy.substitute(number));
        }
        return results;
    }
}
