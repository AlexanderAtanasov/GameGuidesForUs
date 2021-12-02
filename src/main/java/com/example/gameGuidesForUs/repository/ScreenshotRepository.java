package com.example.gameGuidesForUs.repository;

import com.example.gameGuidesForUs.model.entity.Screenshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenshotRepository extends JpaRepository<Screenshot, Long> {

    @Query("select s.id FROM Screenshot s")
    List<Long> allScreenshotIds();
}
