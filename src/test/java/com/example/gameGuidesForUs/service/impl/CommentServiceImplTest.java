package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.*;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import com.example.gameGuidesForUs.model.service.CommentAddServiceModel;
import com.example.gameGuidesForUs.model.view.CommentViewModel;
import com.example.gameGuidesForUs.repository.CommentRepository;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.CommentService;
import com.example.gameGuidesForUs.service.ScreenshotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private CommentAddServiceModel testCommentAddServiceModel;
    private UserRoleEntity adminRole;
    private Guide testGuide;

    @BeforeEach
    void init() {

        serviceToTest = new CommentServiceImpl(mockCommentRepository, mockUserRepository,
                mockGuideRepository, mockModelMapper, mockScreenshotService);

        adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        testGuide = new Guide();
        testGuide.setComments(new ArrayList<>())
                .setDescription("SomeGuideRandomDescr.........")
                .setCreatedOn(Instant.now())
                .setGuideTitle("Guide Title....")
                .setGameId(new Game())
                .setGuideCreatedBy(new User());

        testComment = new Comment();
        testComment.setComment("TestComment")
                .setGuide(new Guide())
                .setCommentCreatedBy(new User())
                .setCreatedOn(Instant.now());

        testCommentAddServiceModel = new CommentAddServiceModel();
        testCommentAddServiceModel.setComment("TESTCOMMENT...............");
        System.out.println();
    }

    @Test
    void testDeleteComment() {

        Comment testComment = this.testComment;

        Mockito.when(mockCommentRepository.getById(testComment.getId())).thenReturn(testComment);
        Comment byId = mockCommentRepository.getById(testComment.getId());
        Assertions.assertEquals(byId, testComment);
        serviceToTest.deleteComment(testComment.getId());
////        Long testScreenshotId = mockCommentRepository.getById(testComment.getId()).getScreenshot().getId();
//        mockCommentRepository.deleteById(testComment.getId());
//        mockScreenshotService.deleteScreenshot(testComment.getScreenshot().getId());
//        Mockito.verify(mockCommentRepository, Mockito.times(1)).deleteById(testComment.getId());
    }

    @Test
    void testIfAdmin() {
        User user = new User();
        user.setRoles(Set.of(adminRole));
        Assertions.assertTrue(serviceToTest.isAdmin(user));
    }

    @Test
    void testIsOwnerOrAdmin() {
        Comment testComment = this.testComment;
        User testUser = new User();
        testUser.setUsername("TestMock");
        testComment.setCommentCreatedBy(testUser);
        testUser.setRoles(Set.of(adminRole));


        Mockito.when(mockCommentRepository.getById(testComment.getId())).thenReturn(testComment);
        Mockito.when(mockUserRepository.getByUsername(testUser.getUsername())).thenReturn(testUser);

        Assertions.assertTrue(serviceToTest.isOwnerOrAdmin(testUser.getUsername(), testComment.getId()));
    }


    @Test
    void testAddComment() {
        CommentAddServiceModel testCommentAddServiceModel = this.testCommentAddServiceModel;
        Mockito.when(mockModelMapper.map(testCommentAddServiceModel, Comment.class)).thenReturn(testComment);

        Comment testComment = this.testComment;

        serviceToTest.addComment(testCommentAddServiceModel, testComment.getGuide().getId(), testComment.getCommentCreatedBy().getUsername());
        Mockito.verify(mockCommentRepository, Mockito.times(1))
                .save(testComment);

    }

    @Test
    void testDeletionOfCommentById() {

        Comment testComment = this.testComment;
        Mockito.when(mockCommentRepository.getById(testComment.getId()))
                .thenReturn(testComment);
        serviceToTest.deleteComment(testComment.getId());
    }

    @Test
    void testFindListOfCommentViewModelsByGuideId() {
        Guide testGuide = this.testGuide;

        List<CommentViewModel> commentViewModels = new ArrayList<>();
//        List<Comment> commentList = new ArrayList<>();
//        Mockito.when(mockCommentRepository.findAllByGuideIdOrderByCreatedOnDesc(testGuide.getId()))
//                .thenReturn(commentList);
//
//        commentViewModels = commentList.stream()
//                .map(comment -> {
//                    CommentViewModel c = mockModelMapper.map(comment, CommentViewModel.class);
//                    if (comment.getScreenshot() != null) {
//                        {
//                            c.setScreenshot(comment.getScreenshot().getUrl());
//                        }
//                    }
//                    return c;
//                }).collect(Collectors.toList());
//
//
        serviceToTest.findByGuideId(testGuide.getId());




    }
}
