package com.example.movie_app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/reviews", "/api/reviews/**", "/phim-yeu-thich");

        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/api/admin/**", "/admin/**", "/phim-bo", "/api/favorites", "/api/favorites/**");
    }
}
