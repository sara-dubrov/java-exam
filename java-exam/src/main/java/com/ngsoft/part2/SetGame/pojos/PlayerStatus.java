package com.ngsoft.part2.SetGame.pojos;

public class PlayerStatus {

    private boolean isSuccess;
    private int score;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getScore() {
        return score;
    }

    public void setSuccess() {
        isSuccess = true;
        score++;
    }

    public void setFailure() {
        isSuccess = false;
        score--;
    }

}