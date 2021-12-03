package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Comment;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.UserRoleEntity;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import com.example.gameGuidesForUs.model.service.CommentAddServiceModel;
import com.example.gameGuidesForUs.model.view.CommentViewModel;
import com.example.gameGuidesForUs.repository.CommentRepository;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.CommentService;
import com.example.gameGuidesForUs.service.ScreenshotService;
import com.example.gameGuidesForUs.web.exception.ObjectNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final GuideRepository guideRepository;
    private final ModelMapper modelMapper;
    private final ScreenshotService screenshotService;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, GuideRepository guideRepository, ModelMapper modelMapper, ScreenshotService screenshotService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.guideRepository = guideRepository;
        this.modelMapper = modelMapper;
        this.screenshotService = screenshotService;
    }

    @Override
    public List<CommentViewModel> findByGuideId(Long id) {

        return commentRepository.findAllByGuideIdOrderByCreatedOnDesc(id)
                .stream().map(comment -> {
                    CommentViewModel c = modelMapper.map(comment, CommentViewModel.class);
                    if (comment.getScreenshot() != null) {
                        c.setScreenshot(comment.getScreenshot().getUrl());
                    }
                    return c;
                }).collect(Collectors.toList());
    }

    @Override
    public void addComment(CommentAddServiceModel commentAddServiceModel, Long guideId, String username) {

        List<Comment> all = commentRepository.findAll();
        Comment comment = modelMapper.map(commentAddServiceModel, Comment.class);

        comment.setGuide(guideRepository.getById(guideId))
                .setCommentCreatedBy(userRepository.getByUsername(username))
                .setCreatedOn(Instant.now());

        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {

        Comment forDeletion = commentRepository.getById(id);

        if (forDeletion.getScreenshot() == null) {
            commentRepository.delete(forDeletion);
        } else {
            Long screenshotId = commentRepository.getById(id).getScreenshot().getId();
            commentRepository.deleteById(id);
            screenshotService.deleteScreenshot(screenshotId);
        }

        System.out.println();
    }

    @Override
    public boolean isOwnerOrAdmin(String userIdentifier, Long commentId) {
        Comment comment = commentRepository.getById(commentId);
        User user = userRepository.getByUsername(userIdentifier);
        return  comment.getCommentCreatedBy().getId() == user.getId() || isAdmin(user) ;
    }

    @Override
    public CommentViewModel getCurrentComment(Long commentId) {
        return commentRepository.findById(commentId)
                .map(comment -> modelMapper.map(comment,CommentViewModel.class))
                .orElseThrow(ObjectNotFound::new);
    }

    private boolean isAdmin(User user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).anyMatch(r-> r.equals(UserRoleEnum.ADMIN));
    }


}
