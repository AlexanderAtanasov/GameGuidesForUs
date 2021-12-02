package com.example.gameGuidesForUs.web.interceptor;

import com.example.gameGuidesForUs.service.StatisticService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StatisticInterceptor implements HandlerInterceptor {

    private final StatisticService statisticService;

    public StatisticInterceptor(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        statisticService.onRequest();
        return true;
    }


}
