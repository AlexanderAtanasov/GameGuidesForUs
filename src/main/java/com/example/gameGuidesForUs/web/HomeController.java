package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.view.HomeViewModel;
import com.example.gameGuidesForUs.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final GameService gameService;

    public HomeController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<HomeViewModel> getRandomGame() {
       HomeViewModel randomGame = gameService.getAllGamesForRandomShow();

        return ResponseEntity.
                ok(randomGame);
    }

}
