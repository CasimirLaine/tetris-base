package com.laine.casimir.tetris.base.model;

public class TetrisScore {

    private static final int LINES_PER_LEVEL = 10;

    private int score;
    private int level = 1;
    private int linesCleared;

    public void addScore(int score) {
        this.score += score;
    }

    public void addLinesCleared(int lines) {
        this.linesCleared += lines;
        addScore(getScoreForLines(lines) * this.level);
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

    private int getScoreForLines(int lines) {
        switch (lines) {
            default:
            case 1:
                return 100;
            case 2:
                return 300;
            case 3:
                return 500;
            case 4:
                return 800;
        }
    }
}
