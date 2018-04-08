package com.owen.springboot.security.config;

import com.owen.springboot.security.service.CustomFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
//@EnableWebSecurity springboot项目中会自动开启该注解，参考SecurityAutoConfiguration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService customUserService;

    @Autowired
    CustomFilterSecurityInterceptor filterSecurityInterceptor;
    /**
     * 可以自己指定密码加密算法，也可以利用默认的DelegatingPasswordEncoder,采用默认的则需要在保存密码时加上{xxxx}加密类型前缀
     */
    //    @Bean
    //    PasswordEncoder noOpPasswordEncoder(){
    //        return NoOpPasswordEncoder.getInstance();
    //    }

    /**
     * 身份验证管理生成器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService);
    }

    /**
     * HTTP请求安全处理
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                .and().logout().logoutSuccessUrl("/login").permitAll();
        http.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    /**
     * WEB安全
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
