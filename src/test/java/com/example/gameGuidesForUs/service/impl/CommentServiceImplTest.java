package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Comment;
import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.service.CommentAddServiceModel;
import com.example.gameGuidesForUs.repository.CommentRepository;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.CommentService;
import com.example.gameGuidesForUs.service.ScreenshotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository mockCommentRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private GuideRepository mockGuideRepository;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private ScreenshotService mockScreenshotService;

    private CommentServiceImpl serviceToTest;
    private Comment testComment;

    @BeforeEach
    void init() {

        serviceToTest = new CommentServiceImpl(mockCommentRepository, mockUserRepository,
                mockGuideRepository, mockModelMapper, mockScreenshotService);
        testComment = new Comment();
        testComment.setComment("TestComment")
                .setGuide(new Guide())
                .setCommentCreatedBy(new User())
                .setCreatedOn(Instant.now());

        System.out.println();
    }

    @Test
    void deleteComment() {


    }

    @Test
    void addComment() {


        Comment testComment = this.testComment;
        CommentAddServiceModel commentAddServiceModel = mockModelMapper
                .map(testComment,CommentAddServiceModel.class);
        serviceToTest.addComment(commentAddServiceModel,1L,"TESTUSER");
        Mockito.verify(mockCommentRepository,Mockito.times(1))
                .save(Mockito.any());


    }
}
