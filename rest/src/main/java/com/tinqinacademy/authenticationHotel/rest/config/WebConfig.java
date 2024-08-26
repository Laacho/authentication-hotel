package com.tinqinacademy.authenticationHotel.rest.config;


import com.tinqinacademy.authenticationHotel.core.service.paths.AuthURLPaths;
import com.tinqinacademy.authenticationHotel.rest.interceptor.Interceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final Interceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns(AuthURLPaths.PROMOTE,
                        AuthURLPaths.DEMOTE,
                        AuthURLPaths.CHANGE_PASSWORD,
                        AuthURLPaths.LOGOUT);
    }
}
