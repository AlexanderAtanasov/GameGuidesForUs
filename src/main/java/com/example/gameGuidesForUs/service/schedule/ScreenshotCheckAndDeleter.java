package com.example.gameGuidesForUs.service.schedule;

import com.example.gameGuidesForUs.service.ScreenshotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScreenshotCheckAndDeleter {

    private final ScreenshotService screenshotService;

    public ScreenshotCheckAndDeleter(ScreenshotService screenshotService) {
        this.screenshotService = screenshotService;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotCheckAndDeleter.class);


    @Scheduled(cron= "0 0 * * * *")
    public void clearScreenshots() {
      screenshotService.findAllScreenshotsWithoutAssignedCommentsAndDeleteThem();

    }
}
