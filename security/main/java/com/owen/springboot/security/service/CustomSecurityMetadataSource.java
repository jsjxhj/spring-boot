package com.owen.springboot.security.service;

import com.owen.springboot.security.entity.Role;
import com.owen.springboot.security.entity.Url;
import com.owen.springboot.security.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    UrlRepository urlRepository;

    private Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<>();

    /**
     * 加载所有的url,并封装每个url所需要的权限列表
     */
    @PostConstruct
    public void loadMetadataSource() {
        List<Url> urls = urlRepository.findAll();
        for (Url url : urls) {
            Collection<ConfigAttribute> configAttributes = new ArrayList<>();
            List<Role> roles = url.getRoles();
            for (int i = 0; i<roles.size(); i++){
                ConfigAttribute configAttribute = new SecurityConfig(roles.get(i).getName());
                configAttributes.add(configAttribute);
            }

            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            resourceMap.put(url.getUrl(), configAttributes);
        }
    }

    /**
     * 获取的信息将会作为AccessDecisionManager类的decide的第三个参数。
     * @param object 包含客户端发起的请求的request信息
     * @return 请求的资源所需要的权限列表
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        //循环资源列表中的所有资源,和当前请求的url进行匹配,并返回请求的url对应的
        for (Iterator<String> urlIterator = resourceMap.keySet().iterator(); urlIterator.hasNext(); ) {
            String requestUrl = urlIterator.next();
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(requestUrl);
            if (matcher.matches(request)) {
                return resourceMap.get(requestUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
