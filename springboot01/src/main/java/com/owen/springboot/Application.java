package com.owen.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by owen on 2016/11/3.
 * @SpringBootApplication 相当于 @Configuration、@ComponentScan和@EnableAutoConfiguration
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}
