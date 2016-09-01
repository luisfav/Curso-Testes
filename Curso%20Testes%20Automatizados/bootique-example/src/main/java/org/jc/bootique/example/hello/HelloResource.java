/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.bootique.example.hello;

import com.google.inject.Inject;
import io.bootique.annotation.Args;
import java.util.Arrays;
import static java.util.stream.Collectors.joining;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author jean
 */
@Path("/")
public class HelloResource {

    @Inject
    @Args
    private String[] args;
    
    

    @GET
    public String hello() {
        String allArgs = Arrays.asList(args).stream().collect(joining(" "));
        return "Hello, world! The app was started with the following arguments: " + allArgs;
    }
}
