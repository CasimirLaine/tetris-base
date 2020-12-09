package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.TetrisConstants;
import com.laine.casimir.tetris.base.tool.HoldBox;
import com.laine.casimir.tetris.base.tool.TetrominoQueue;

public final class TetrisGame {

    private static final float DROP_INTERVAL_START = 1_000;
    private static final float DROP_INTERVAL_MIN = 25;

    private final Playfield playfield = new Playfield(TetrisConstants.WIDTH, TetrisConstants.HEIGHT, TetrisConstants.VISIBLE_HEIGHT);
    private final HoldBox holdBox = new HoldBox();
    private final TetrominoQueue tetrominoQueue = new TetrominoQueue();
    private final TetrisScore tetrisScore = new TetrisScore();

    private boolean gameOver;
    private boolean paused;

    public void end() {
        gameOver = true;
    }

    public Playfield getPlayfield() {
        return playfield;
    }

    public HoldBox getHoldBox() {
        return holdBox;
    }

    public TetrominoQueue getTetrominoQueue() {
        return tetrominoQueue;
    }

    public TetrisScore getTetrisScore() {
        return tetrisScore;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getDropInterval() {
        return (int) Math.max(calculateDropInterval(), DROP_INTERVAL_MIN);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isRunning() {
        return !isPaused() && !isGameOver();
    }

    private double calculateDropInterval() {
        final int level = tetrisScore.getLevel();
        return 1_000 * Math.pow(0.8 - ((level - 1) * 0.007), level - 1);
    }
}
