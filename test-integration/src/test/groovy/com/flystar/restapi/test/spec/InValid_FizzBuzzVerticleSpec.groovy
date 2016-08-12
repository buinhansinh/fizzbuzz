package com.flystar.restapi.test.spec

import com.flystar.restapi.test.Request
import com.flystar.restapi.test.Result
import jdk.nashorn.internal.runtime.Specialization
import org.spockframework.compiler.model.Spec
import spock.lang.Specification
import spock.lang.Unroll

import javax.ws.rs.BadRequestException

/**
 * Created by zack on 8/12/2016.
 */
class InValid_FizzBuzzVerticleSpec extends AbstractSpec{

    @Unroll
    def "user should receive error when submitting a string which is not a number"() {
        given:
        println "Test number = $number"
        when:
        println "Sending request"
        client.getResult(new Request(number));
        then: "status code 400. Bad request exception"
        BadRequestException ex = thrown()

        where:
        number << ["a", "b", "c", "d", "ea", "f", "g", "2fdafdfdf213", "test132313fdsafdsfds"]
    }
}
