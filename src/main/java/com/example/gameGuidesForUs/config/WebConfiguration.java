package com.example.gameGuidesForUs.config;


import com.example.gameGuidesForUs.web.interceptor.StatisticInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StatisticInterceptor statisticInterceptor;

    public WebConfiguration(StatisticInterceptor statisticInterceptor) {
        this.statisticInterceptor = statisticInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statisticInterceptor);
    }
}