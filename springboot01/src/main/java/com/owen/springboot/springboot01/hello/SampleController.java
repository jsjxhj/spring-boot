package com.owen.springboot.springboot01.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by owen on 2016/10/19.
 * @SpringBootApplication 相当于 @Configuration、@ComponentScan和@EnableAutoConfiguration
 */
@Controller
@SpringBootApplication
public class SampleController {
    @ResponseBody
    @RequestMapping(value="/")
    String home(){
        return "Hello World!";
    }

    public static void main(String[] args){
        //SpringApplication.run(SampleController.class, "--server.port=8081");
        SpringApplication.run(SampleController.class, args);
    }
}
