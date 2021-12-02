package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.view.StatisticView;
import com.example.gameGuidesForUs.service.StatisticService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    private int anonymousRequests, authRequests;

    @Override
    public void onRequest() {
        Authentication authentication = SecurityContextHolder.
                getContext().
                getAuthentication();


        if (authentication == null || (!(authentication.getPrincipal() instanceof UserDetails))) {
            anonymousRequests++;
        } else {
            authRequests++;
        }
    }

    @Override
    public StatisticView getStats() {
        return new StatisticView(authRequests, anonymousRequests);
    }
}
