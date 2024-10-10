package com.example.doit;

public class Category {
    private int imageResId;
    private String title;
    private int highScore;

    public Category(int imageResId, String title, int highScore) {
        this.imageResId = imageResId;
        this.title = title;
        this.highScore = highScore;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
