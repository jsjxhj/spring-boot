package com.owen.springboot.swagger.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
    /**
     * Spring4之前，用@Controller + @ResponseBody来配合返回json
     * Spring4开始 @RestController默认返回json
     * @return
     */
    @ApiOperation(value="Hello Spring Boot", notes="根据url的id来指定删除对象")
    @ResponseBody
    @RequestMapping(value="/hello", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String hello(){
        return "Hello Spring Boot!";
    }

    @ApiOperation(value="Hello Spring Boot", notes="根据url的id来指定删除对象", hidden = true)
    @RequestMapping("/")
    public String index(ModelMap map){
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://projects.spring.io/spring-boot/");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

}
