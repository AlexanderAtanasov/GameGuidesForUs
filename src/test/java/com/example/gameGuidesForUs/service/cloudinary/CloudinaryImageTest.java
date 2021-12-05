package com.example.gameGuidesForUs.service.cloudinary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CloudinaryImageTest {

    private String url;
    private String publicId;
    private CloudinaryImage testObject;

    @BeforeEach
    void init() {
        testObject = new CloudinaryImage();

        url = "testUrl";
        publicId = "testPublicId";
    }

    @Test
    void test() {
        CloudinaryImage testObject = this.testObject;
        testObject.setUrl(url);
        testObject.setPublicId(publicId);
        Assertions.assertEquals(url, testObject.getUrl());
        Assertions.assertEquals(publicId, testObject.getPublicId());
    }
}