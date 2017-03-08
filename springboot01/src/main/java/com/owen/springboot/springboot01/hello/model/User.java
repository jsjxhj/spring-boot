package com.owen.springboot.springboot01.hello.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by owen on 2016/11/3.
 */
public class User {
    @ApiModelProperty(value = "用户编码", example = "0000001")
    private String id;
    @ApiModelProperty(value = "用户姓名", example = "zhangsan")
    private String name;
    @ApiModelProperty(value = "用户年龄", example = "20")
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
