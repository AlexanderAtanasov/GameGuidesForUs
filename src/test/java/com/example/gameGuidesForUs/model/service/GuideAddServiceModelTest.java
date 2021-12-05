package com.example.gameGuidesForUs.model.service;

import com.example.gameGuidesForUs.model.entity.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class GuideAddServiceModelTest {

    private String guideTitle = "guideTitle";
    private String description = "someGuideDescription......";
    private String guideCreatedBy = "someUsername";
    private Instant createdOn = Instant.now();
    private Instant modifiedOn = Instant.now();
    private List<Comment> comments = new ArrayList<>();
    private Long gameId = 3L;

    private GuideAddServiceModel toTest = new GuideAddServiceModel();

   @Test
    void testAll () {

       toTest.setDescription(description)
               .setGuideTitle(guideTitle)
               .setGuideCreatedBy(guideCreatedBy)
               .setCreatedOn(createdOn)
               .setModifiedOn(modifiedOn)
               .setComments(comments)
               .setGameId(gameId);

       Assertions.assertEquals(guideTitle,toTest.getGuideTitle());
       Assertions.assertEquals(description,toTest.getDescription());
       Assertions.assertEquals(guideCreatedBy,toTest.getGuideCreatedBy());
       Assertions.assertEquals(createdOn,toTest.getCreatedOn());
       Assertions.assertEquals(modifiedOn,toTest.getModifiedOn());
       Assertions.assertEquals(comments,toTest.getComments());
       Assertions.assertEquals(gameId,toTest.getGameId());

   }
}