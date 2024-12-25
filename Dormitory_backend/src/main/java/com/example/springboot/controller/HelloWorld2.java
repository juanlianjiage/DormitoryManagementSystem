package com.example.springboot.controller;

/**
 * @Author: chenxinjia
 * @Date: 2024/12/19 19:21
 */
public class HelloWorld2
{
    public String sayHello()
    {
        return "测试22222";
    }

    public static void main(String[] args)
    {
        System.out.println(new HelloWorld2().sayHello());
    }
}

