package com.example.FinalProject.command;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class FunctionalEndpoint {
@Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route().GET("/functional", this::getInfo).build();
    }

    public ServerResponse getInfo(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                Collections.singletonMap("aKey", serverRequest.param("value").orElse("")));
    }
}

