package com.vic.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

/**
 * @author qinquan
 * @date 2019/8/6
 */
@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        //SpringApplication.run(AopApplication.class, args);
        StringBuilder validateCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            validateCode.append(random.nextInt(9));
        }
        System.out.println(validateCode);
    }

}
