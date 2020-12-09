package com.laine.casimir.tetris.base.model;

public final class TetrisScore {

    private int score;
    private int level = 1;
    private int linesCleared;

    private int linesClearedInLevel;
    private int combo;

    private boolean wasDifficultClear;

    public void processLines(int lines, boolean isTSpin) {
        addLinesCleared(lines);
        if (isTSpin) {
            addTSpin(lines);
        }
        wasDifficultClear = lines == 4 || (isTSpin && (lines > 0 || wasDifficultClear));
        if (lines <= 0) {
            resetCombo();
        }
    }

    public void addScore(int score) {
        this.score += score;
    }

    private void addLinesCleared(int lines) {
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
    }

    private void addTSpin(int linesCleared) {
        addScore(getTSpinScore(linesCleared));
    }

    private void resetCombo() {
        combo = 0;
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

    private int getTSpinScore(int lines) {
        switch (lines) {
            default:
            case 0:
                return 400;
            case 1:
                return (int) (800 * (wasDifficultClear ? 3F / 2F : 1));
            case 2:
                return (int) (1200 * (wasDifficultClear ? 3F / 2F : 1));
            case 3:
                return (int) (1600 * (wasDifficultClear ? 3F / 2F : 1));
        }
    }

    private int getLevelGoal() {
        return 5 * level;
    }
}
