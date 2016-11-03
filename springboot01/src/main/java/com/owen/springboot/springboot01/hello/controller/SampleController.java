package com.owen.springboot.springboot01.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
    /**
     * Spring4之前，用@Controller + @ResponseBody来配合返回json
     * Spring4开始 @RestController默认返回json
     * @return
     */
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

}
