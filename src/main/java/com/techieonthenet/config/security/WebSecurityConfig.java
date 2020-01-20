package com.techieonthenet.config.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.SecureRandom;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private UserDetailsService userDetailsService;


    private static final String SALT = "salt"; //Salt should be protected carefully
    private static final String PRIV_VIEW_PRODUCT = "PRIV_VIEW_PRODUCT";
    private static final String PRIV_ADMIN_CAP = "PRIV_ADMIN_CAP";
    private static final String PRIV_APPROVERS = "PRIV_APPROVERS";



    @Bean
    private static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/login",
                        "/js/**",
                        "/css/**",
                        "/images/**",
                        "/webjars/**",
                        "/user/forgotPwd").permitAll()
                .antMatchers("/main-page").hasAuthority(PRIV_VIEW_PRODUCT)
                .antMatchers("/group/add").hasAuthority(PRIV_ADMIN_CAP)
                .antMatchers("/product/add").hasAuthority(PRIV_ADMIN_CAP)
                .antMatchers("/category/add").hasAuthority(PRIV_ADMIN_CAP)
                .antMatchers("/task/modify").hasAuthority(PRIV_APPROVERS)
                .antMatchers("/user/add").hasAuthority(PRIV_ADMIN_CAP)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}