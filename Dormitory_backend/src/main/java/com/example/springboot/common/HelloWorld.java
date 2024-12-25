package com.example.springboot.common;

/**
 * @Author: chenxinjia
 * @Date: 2024/12/19 19:21
 */
/**
 * 业务类
 * @author pan_junbiao
 **/
public class HelloWorld
{
    public String sayHello()
    {
        return "您好，欢迎访问 pan_junbiao的博客";
    }

    public static void main(String[] args)
    {
        System.out.println(new HelloWorld().sayHello());
    }
}

