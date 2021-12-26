package com.laine.casimir.tetris.base.model;

public final class TetrisScore {

    public static final int MAX_LINES = 4;
    public static final int START_LEVEL = 1;
    public static final int LINES_PER_LEVEL_MULTIPLIER = 5;
    public static final int DIFFICULT_CLEAR = 4;

    private int score;
    private int level = START_LEVEL;
    private int linesCleared;

    private int linesClearedInLevel;
    private int combo;

    private boolean wasDifficultClear;

    public void processLines(final int lines, boolean isTSpin) {
        if (lines < 0 || lines > MAX_LINES) {
            return;
        }
        addLinesCleared(lines);
        if (isTSpin) {
            addTSpin(lines);
        }
        wasDifficultClear = lines == DIFFICULT_CLEAR || (isTSpin && (lines > 0 || wasDifficultClear));
        if (lines == 0) {
            resetCombo();
        }
    }

    public void addScore(int score) {
        if (score <= 0) {
            return;
        }
        this.score += score;
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

    private void addLinesCleared(int lines) {
        if (lines <= 0 || lines > MAX_LINES) {
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
        return LINES_PER_LEVEL_MULTIPLIER * level;
    }
}
