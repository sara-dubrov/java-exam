package com.ngsoft.part2.SetGame.pojos;

public class GameStatus {

    private PlayerStatus playerStatus = new PlayerStatus();
    private boolean isGameOver;

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void gameOver() {
        this.isGameOver = true;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }
}
