package com.example.gameGuidesForUs.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionTimerInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(SessionTimerInterceptor.class);

    private static final long MAX_INACTIVE_SESSION_TIME = 5 * 1100;

    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        log.info("Pre handle method - check handling start time");
        long startTime = System.currentTimeMillis();
        request.setAttribute("executionTime", startTime);
        if (UserInterceptor.isUserLogged()) {
            session = request.getSession();
            log.info("Time since last request in this session: {} ms", System.currentTimeMillis() - request.getSession()
                    .getLastAccessedTime());
            if (System.currentTimeMillis() - session.getLastAccessedTime() > MAX_INACTIVE_SESSION_TIME) {
                log.warn("Logging out, due to inactive session");
                SecurityContextHolder.clearContext();
                request.logout();
                response.sendRedirect("/users/logout-timeout");
            }
        }
        return true;
    }


}