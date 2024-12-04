package com.example.springboot.utils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ExecutorUtil {
    @Bean
    public ExecutorService create(){
        return Executors.newFixedThreadPool(10);
    }
}
