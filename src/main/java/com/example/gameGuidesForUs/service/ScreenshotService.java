package com.example.gameGuidesForUs.service;

import com.example.gameGuidesForUs.model.entity.Screenshot;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ScreenshotService {

    Screenshot addScreenshot(MultipartFile screenshot, String userIdentifier) throws IOException;

    void deleteScreenshot(Long screenshotId);
}
