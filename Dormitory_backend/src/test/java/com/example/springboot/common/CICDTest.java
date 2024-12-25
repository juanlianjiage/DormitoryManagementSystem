package com.example.springboot.common;

import org.junit.jupiter.api.Test;

/**
 * @Author: chenxinjia
 * @Date: 2024/12/25 14:50
 */
public class CICDTest {
    @Test
    public void cicdTest(){
        CICD cicd = new CICD();
        cicd.say();
    }
}
