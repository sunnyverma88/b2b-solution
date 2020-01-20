package com.techieonthenet.config.web;

import com.techieonthenet.interceptors.SessionAttributesInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Web mvc config.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * Session attributes interceptor session attributes interceptor.
     *
     * @return the session attributes interceptor
     */
    @Bean
    public SessionAttributesInterceptor sessionAttributesInterceptor() {
        return new SessionAttributesInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionAttributesInterceptor());
    }
}
