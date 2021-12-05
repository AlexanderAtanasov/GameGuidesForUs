package com.example.gameGuidesForUs.config;


import com.example.gameGuidesForUs.web.interceptor.SessionTimerInterceptor;
import com.example.gameGuidesForUs.web.interceptor.StatisticInterceptor;
import com.example.gameGuidesForUs.web.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StatisticInterceptor statisticInterceptor;
    private final UserInterceptor userInterceptor;
    private final SessionTimerInterceptor sessionTimerInterceptor;

    public WebConfiguration(StatisticInterceptor statisticInterceptor, UserInterceptor userInterceptor, SessionTimerInterceptor sessionTimerInterceptor) {
        this.statisticInterceptor = statisticInterceptor;
        this.userInterceptor = userInterceptor;
        this.sessionTimerInterceptor = sessionTimerInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statisticInterceptor);
        registry.addInterceptor(userInterceptor);
        registry.addInterceptor(sessionTimerInterceptor);
    }
}