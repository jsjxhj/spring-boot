package com.owen.springboot.security.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue
    private int id;

    //父节点id
    private Integer parentId;
    //权限名称
    private String name;
    //授权链接
    private String url;
    //权限描述
    private String description;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "role_url", joinColumns = @JoinColumn(name = "url_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
