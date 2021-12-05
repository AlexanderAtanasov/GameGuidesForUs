package com.example.gameGuidesForUs.model.view;

import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;


class CommentViewModelTest {

    private Long id = 1L;
    private Guide guideId = new Guide();
    private String comment = "commentForTest";
    private User commentCreatedBy = new User();
    private Instant createdOn = Instant.now();
    private Instant modifiedOn = Instant.now();
    private String screenshot = "screenshotUrl";

    private CommentViewModel toTest = new CommentViewModel();


    @Test
    void testAll() {

        toTest.setId(id)
                .setGuideId(guideId)
                .setComment(comment)
                .setCommentCreatedBy(commentCreatedBy)
                .setCreatedOn(createdOn).setModifiedOn(modifiedOn)
                .setScreenshot(screenshot);

        Assertions.assertEquals(id,toTest.getId());
        Assertions.assertEquals(guideId,toTest.getGuideId());
        Assertions.assertEquals(comment,toTest.getComment());
        Assertions.assertEquals(commentCreatedBy,toTest.getCommentCreatedBy());
        Assertions.assertEquals(createdOn,toTest.getCreatedOn());
        Assertions.assertEquals(modifiedOn,toTest.getModifiedOn());
        Assertions.assertEquals(screenshot,toTest.getScreenshot());
    }
}