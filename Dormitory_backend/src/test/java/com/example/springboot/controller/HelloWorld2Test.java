package com.example.springboot.controller;

import com.example.springboot.common.HelloWorld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: chenxinjia
 * @Date: 2024/12/19 19:22
 */
class HelloWorld2Test {

    @BeforeEach
    void setUp()
    {
        System.out.println("@BeforeEach，测试开始");
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("@AfterEach，测试结束");
    }

    @Test
    void sayHello()
    {
        HelloWorld2 helloWorld = new HelloWorld2();
        String result = helloWorld.sayHello();
        System.out.println(result);
        assertEquals("您好，欢迎访问 pan_junbiao的博客",result);
    }
}