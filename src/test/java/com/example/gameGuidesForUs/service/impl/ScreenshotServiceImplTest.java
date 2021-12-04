package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Screenshot;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.repository.CommentRepository;
import com.example.gameGuidesForUs.repository.ScreenshotRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.cloudinary.CloudinaryImage;
import com.example.gameGuidesForUs.service.cloudinary.CloudinaryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScreenshotServiceImplTest {

    private ScreenshotServiceImpl serviceToTest;
    private Screenshot screenshotToTest;

    @Mock
    private ScreenshotRepository mockScreenshotRepository;
    @Mock
    private CloudinaryService mockCloudinaryService;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private CommentRepository mockCommentRepository;

    @BeforeEach
    void init() {
        serviceToTest = new ScreenshotServiceImpl(mockScreenshotRepository, mockCloudinaryService,
                mockUserRepository, mockCommentRepository);
        screenshotToTest = new Screenshot();
        screenshotToTest.setUploadedBy(new User())
                .setUrl("TESTURL.....")
                .setUploadedOn(Instant.now())
                .setPublicId("PUBLICID...");

    }




    @Test
    void testAllScreenshotIds() {

        serviceToTest.findAllScreenshotId();
        Mockito.verify(mockScreenshotRepository, Mockito.times(1)).allScreenshotIds();


    }
}