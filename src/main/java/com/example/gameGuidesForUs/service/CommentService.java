package com.example.gameGuidesForUs.service;

import com.example.gameGuidesForUs.model.entity.Comment;
import com.example.gameGuidesForUs.model.service.CommentAddServiceModel;
import com.example.gameGuidesForUs.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    List<CommentViewModel> findByGuideId(Long id);


    void addComment(CommentAddServiceModel commentAddServiceModel, Long guideId, String username);

    void deleteComment(Long id);


    boolean isOwnerOrAdmin(String userIdentifier, Long commentId);

    CommentViewModel getCurrentComment(Long commentId);
}
