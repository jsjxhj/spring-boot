package com.owen.springboot.springboot01.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by owen on 2016/10/19.
 * @SpringBootApplication 相当于 @Configuration、@ComponentScan和@EnableAutoConfiguration
 */
@Controller
@SpringBootApplication
public class SampleController {
    @ResponseBody
    @RequestMapping(value="/hello")
    public String home(){
        return "Hello World!";
    }

    @RequestMapping("/")
    public String index(ModelMap map){
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://projects.spring.io/spring-boot/");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    public static void main(String[] args){
        SpringApplication.run(SampleController.class, args);
    }
}
