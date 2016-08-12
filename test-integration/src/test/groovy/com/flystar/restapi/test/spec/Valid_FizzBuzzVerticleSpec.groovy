package com.flystar.restapi.test.spec

import com.flystar.restapi.test.Request
import com.flystar.restapi.test.Result
import spock.lang.Unroll

/**
 * Created by zack on 8/12/2016.
 */

class Valid_FizzBuzzVerticleSpec extends AbstractSpec {


    @Unroll
    def "test basic case with 1 number which is not divisible by 3 and 5: Test Number = #number"() {
        given:
        println "Test number = $number"
        expect: "The response will be the same as request"
        println "Sending requests"
        Result res = client.getResult(new Request(number));
        println "Receiving response"
        res?.result == number
        where:
        number << ["1", "2", "4", "7", "8", "11", "13", "22", "119"]
    }

    @Unroll
    def "test basic case with 1 number which is divisible by 3 not 5: Test Number = #number"() {
        given:
        println "Test number = $number"
        expect: "Response will be Fizz"
        println "Sending requests"
        Result res = client.getResult(new Request(number));
        println "Receiving response"
        res?.result == "Fizz"
        where:
        number << ["3", "6", "99", "666", "66666666", "669999", "999999999"]
    }

    @Unroll
    def "test basic case with 1 number which is divisible by 5 not 3: Test Number = #number"() {
        given:
        println "Test number = $number"
        expect: "Response will be Buzz"
        println "Sending requests"
        Result res = client.getResult(new Request(number));
        println "Receiving response"
        res?.result == "Buzz"
        where:
        number << ["5", "10", "20", "500", "5555", "55555", "5555555"]
    }

    @Unroll
    def "test basic case with 1 number which is divisible by both 5 and 3: Test Number = #number"() {
        given:
        println "Test number = $number"
        expect: "Response will be Fizz Buzz"
        println "Sending requests"
        Result res = client.getResult(new Request(number));
        println "Receiving response"
        res?.result == "Fizz Buzz"
        where:
        number << ["15", "30", "60", "75", "600", "7500", "999999990"]
    }

    @Unroll
    def "test basic case with multiple numbers which is divisible by 3 not 5"() {
        given:
        println "Test number = $numbers"
        expect:
        println "Sending requests"
        Result res = client.getResult(new Request(numbers));
        println "Receiving response"
        res?.result == result
        where:
        numbers                                || result
        "3,6,9,999,9999,1,2"                   || "Fizz,Fizz,Fizz,Fizz,Fizz,1,2"
        "33,111,66,99,9991,1,2"                || "Fizz,Fizz,Fizz,Fizz,9991,1,2"
        "11,88,77,333333,999917,222222,211111" || "11,88,77,Fizz,999917,Fizz,211111"
    }

    @Unroll
    def "test basic case with multiple numbers which is divisible by 5 not 3"() {
        given:
        println "Test number = $numbers"
        expect:
        println "Sending requests"
        Result res = client.getResult(new Request(numbers));
        println "Receiving response"
        res?.result == result
        where:
        numbers                                       || result
        "5,50,555,77,9998,80000,2000000"              || "Buzz,Buzz,Fizz Buzz,77,9998,Buzz,Buzz"
        "70,110,61,91,9991,10,20"                     || "Buzz,Buzz,61,91,9991,Buzz,Buzz"
        "130,1,2,5555555,32000,777777,8888888,555550" || "Buzz,1,2,Buzz,Buzz,Fizz,8888888,Buzz"
    }

    @Unroll
    def "test basic case with multiple numbers which is divisible by 5 and 3"() {
        given:
        println "Test number = $numbers"
        expect:
        println "Sending requests"
        Result res = client.getResult(new Request(numbers));
        println "Receiving response"
        res?.result == result
        where:
        numbers                                          || result
        "15,150,1111,555571,9999000"                     || "Fizz Buzz,Fizz Buzz,1111,555571,Fizz Buzz"
        "60,6000,120,61,91,9991,270,300"                 || "Fizz Buzz,Fizz Buzz,Fizz Buzz,61,91,9991,Fizz Buzz,Fizz Buzz"
        "180,1,2,66669990,33000,777777,9999900000,42137" || "Fizz Buzz,1,2,Fizz Buzz,Fizz Buzz,Fizz,Fizz Buzz,42137"
    }

    @Unroll
    def "test basic case with multiple numbers that mixing divisible by 3 ,5 and both 3,5"() {
        given:
        println "Test number = $numbers"
        expect:
        println "Sending requests"
        Result res = client.getResult(new Request(numbers));
        println "Receiving response"
        res?.result == result
        where:
        numbers                                          || result
        "3,333333,11,1,1,15,150,1111,555571,9997000"                     || "Fizz,Fizz,11,1,1,Fizz Buzz,Fizz Buzz,1111,555571,Buzz"
        "66,6000,121,11,111111,9999991,27000,3000003"                 || "Fizz,Fizz Buzz,121,11,Fizz,9999991,Fizz Buzz,Fizz"
        "180,1,2,66669995,33003,777777,11,43,9999900001,42137,33,6600" || "Fizz Buzz,1,2,Buzz,Fizz,Fizz,11,43,9999900001,42137,Fizz,Fizz Buzz"
    }


}
