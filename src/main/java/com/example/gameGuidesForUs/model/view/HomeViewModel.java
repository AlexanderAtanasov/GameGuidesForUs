package com.example.gameGuidesForUs.model.view;

public class HomeViewModel {

    private Long gameId;
    private String gameTitle;
    private String screenshotUrl;

    public String getScreenshotUrl() {
        return screenshotUrl;
    }

    public HomeViewModel setScreenshotUrl(String screenshotUrl) {
        this.screenshotUrl = screenshotUrl;
        return this;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public HomeViewModel setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
        return this;
    }

    public Long getGameId() {
        return gameId;
    }

    public HomeViewModel setGameId(Long gameId) {
        this.gameId = gameId;
        return this;
    }
}
