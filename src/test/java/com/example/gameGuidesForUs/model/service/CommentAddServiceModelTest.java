package com.example.gameGuidesForUs.model.service;

import com.example.gameGuidesForUs.model.entity.Screenshot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentAddServiceModelTest {

    private String comment;
    private Screenshot screenshot;
    private CommentAddServiceModel toTest;

    @BeforeEach
    void init() {

        toTest = new CommentAddServiceModel();
        comment = "somecomment";
        screenshot = new Screenshot();
    }


    @Test
    void testAll() {

        CommentAddServiceModel toTest = this.toTest;
        toTest.setComment(comment);
        toTest.setScreenshot(screenshot);


        Assertions.assertEquals(comment,toTest.getComment());
        Assertions.assertEquals(screenshot,toTest.getScreenshot());

    }
}