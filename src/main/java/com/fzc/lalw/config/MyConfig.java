package com.fzc.lalw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 2021/12/19/0019
 * MyConfig
 *
 * @author 帅帅付
 */

@Configuration
public class MyConfig{

    @Bean
    public CorsFilter corsFilter(){

        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOriginPattern("*");

        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        HandlerInterceptor interceptor = new LoginInterceptor();
//
//        String[] path = {"/user/**", "/admin/**", "/staticResource/**",
//                "/novel/**"};
//        registry.addInterceptor(interceptor).addPathPatterns(path);
//    }
}
