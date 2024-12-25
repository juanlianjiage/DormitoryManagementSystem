package com.example.springboot.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: chenxinjia
 * @Date: 2024/12/19 19:22
 */
class HelloWorldTest {

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
        HelloWorld helloWorld = new HelloWorld();
        String result = helloWorld.sayHello();
        System.out.println(result);
        assertEquals("您好，欢迎访问 pan_junbiao的博客",result);
    }
}