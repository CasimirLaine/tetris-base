package com.laine.casimir.tetris.base.model;

public final class TetrisScore {

    private int score;
    private int level = 1;
    private int linesCleared;

    private int linesClearedInLevel;
    private int combo;

    private boolean wasDifficultClear;

    public void addScore(int score) {
        this.score += score;
    }

    public void addLinesCleared(int lines) {
        if (lines <= 0) {
            return;
        }
        this.linesCleared += lines;
        this.linesClearedInLevel += getLineValue(lines);
        addScore(getScoreForLines(lines) * this.level + 50 * combo * this.level);
        if (linesClearedInLevel >= getLevelGoal()) {
            this.level++;
            this.linesClearedInLevel = linesClearedInLevel - getLevelGoal();
        }
        combo++;
        wasDifficultClear = lines == 4;
    }

    public void resetCombo() {
        combo = 0;
        wasDifficultClear = false;
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
                return wasDifficultClear ? 1200 : 800;
        }
    }

    private int getLineValue(int lines) {
        switch (lines) {
            default:
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 5;
            case 4:
                return 8;
        }
    }

    private int getLevelGoal() {
        return 5 * level;
    }
}
