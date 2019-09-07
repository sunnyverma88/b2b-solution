package com.techieonthenet.config.security;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger LOGGER = LoggerFactory.getLogger(LoggingAccessDeniedHandler.class);

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private UserDetailsService userDetailsService;


    private static final String SALT = "salt"; //Salt should be protected carefully

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
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
                        "/webjars/**").permitAll()
                .antMatchers("/main-page").hasAuthority("PRIV_VIEW_PRODUCT")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main-page")
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