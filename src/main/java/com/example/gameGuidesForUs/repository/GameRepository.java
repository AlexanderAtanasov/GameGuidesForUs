package com.example.gameGuidesForUs.repository;

import com.example.gameGuidesForUs.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g ORDER BY g.releasedOn DESC")
    List<Game> findAllGamesSortByReleaseDate();

    Optional<Game> findByGameTitle(String gameTitle);

}
