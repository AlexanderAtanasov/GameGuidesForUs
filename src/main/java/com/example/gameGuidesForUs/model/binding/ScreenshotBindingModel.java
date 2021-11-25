package com.example.gameGuidesForUs.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class ScreenshotBindingModel {

  private MultipartFile screenshot;

    public MultipartFile getScreenshot() {
        return screenshot;
    }

    public ScreenshotBindingModel setScreenshot(MultipartFile screenshot) {
        this.screenshot = screenshot;
        return this;
    }
}
