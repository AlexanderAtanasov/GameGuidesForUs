package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Screenshot;
import com.example.gameGuidesForUs.repository.ScreenshotRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.ScreenshotService;
import com.example.gameGuidesForUs.service.cloudinary.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;

@Service
public class ScreenshotServiceImpl implements ScreenshotService {

    private final ScreenshotRepository screenshotRepository;
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;

    public ScreenshotServiceImpl(ScreenshotRepository screenshotRepository, CloudinaryService cloudinaryService, UserRepository userRepository) {
        this.screenshotRepository = screenshotRepository;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
    }

    @Override
    public Screenshot addScreenshot(MultipartFile screenshotFile, String username) throws IOException {

        String screenshotUrl = cloudinaryService.upload(screenshotFile).getUrl();

        Screenshot screenshot = new Screenshot();

        screenshot.setPublicId(screenshotUrl
                        .substring(screenshotUrl.lastIndexOf("/") + 1,
                                screenshotUrl.lastIndexOf(".")))
                .setUrl(screenshotUrl)
                .setUploadedOn(Instant.now())
                .setUploadedBy(userRepository.getByUsername(username));

       return screenshotRepository.save(screenshot);
    }
}
