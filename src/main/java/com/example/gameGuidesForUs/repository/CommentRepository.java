package com.example.gameGuidesForUs.repository;

import com.example.gameGuidesForUs.model.entity.Comment;
import com.example.gameGuidesForUs.model.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.guide.id=?1 order by c.createdOn asc")
    List<Comment> findAllByGuideIdOrderByCreatedOnDesc(Long guideId);


    Optional<Comment> findByScreenshotId(Long screenshot_id);
}
