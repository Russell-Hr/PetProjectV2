package com.example.FinalProject.command.configuration;

import com.example.FinalProject.aspect.BenchAspect;
import com.example.FinalProject.aspect.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectJAutoProxyConfig {
    @Bean
    public BenchAspect userService(){
        return new BenchAspect();
    }
    @Bean
    public LoggingAspect loggingAspect(){
        return new LoggingAspect();
    }
}
