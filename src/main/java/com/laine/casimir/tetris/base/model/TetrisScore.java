package com.laine.casimir.tetris.base.model;

public class TetrisScore {

    private static final int LINES_PER_LEVEL = 5;

    private int score;
    private int level = 1;
    private int linesCleared;

    public void addScore(int score) {
        this.score += score;
    }

    public void addLineCleared() {
        this.linesCleared += 1;
        this.level = linesCleared / LINES_PER_LEVEL + 1;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getLinesCleared() {
        return linesCleared;
    }
}
