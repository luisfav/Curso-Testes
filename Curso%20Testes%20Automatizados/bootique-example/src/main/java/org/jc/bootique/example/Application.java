/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.bootique.example;

import com.google.inject.Module;
import io.bootique.Bootique;
import io.bootique.jersey.JerseyModule;
import org.jc.bootique.example.hello.HelloResource;

/**
 *
 * @author jean
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Module module = binder
            -> JerseyModule
                .contributeResources(binder)
                    .addBinding()
                        .to(HelloResource.class);

        Bootique.app(args)
            .module(module)
            .autoLoadModules()
            .run();
    }

}
