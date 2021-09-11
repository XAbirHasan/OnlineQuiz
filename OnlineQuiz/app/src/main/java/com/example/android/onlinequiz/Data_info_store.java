package com.example.android.onlinequiz;

public class Data_info_store {
    private String name;
    private int score;
    Data_info_store(String name, int score)
    {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
