package com.owen.springboot.security.service;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {

    /**
     * decide 方法用于判定是否拥有权限
     *
     * @param authentication 是CustomUserService中封装到UserDetails中的GrantedAuthority权限信息集合authorities.
     * @param object 包含客户端发起的请求的request信息，可转换为HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
     * @param configAttributes 为CustomSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果,即资源对应的权限列表
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        //循环url所需要的权限列表,和用户拥有的权限进行比较
        for (Iterator<ConfigAttribute> configAttributeIterator = configAttributes.iterator(); configAttributeIterator.hasNext(); ) {
            ConfigAttribute configAttribute = configAttributeIterator.next();
            String role = configAttribute.getAttribute();
            //循环用户所拥有的权限,去和url需要的role进行比较.
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (role.trim().equals(authority.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
