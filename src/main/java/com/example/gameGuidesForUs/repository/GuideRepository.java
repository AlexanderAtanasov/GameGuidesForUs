package com.example.gameGuidesForUs.repository;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {

    @Query("select g from Guide g where g.gameId.id=?1")
    List<Guide> findAllByGameId(Long gameId);
}
