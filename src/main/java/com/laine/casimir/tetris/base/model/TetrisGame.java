package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.tool.HoldBox;
import com.laine.casimir.tetris.base.tool.TetrominoQueue;

public class TetrisGame {

    private static final float START_INTERVAL = 1_000;
    private static final float MIN_INTERVAL = 25;

    private final Playfield playfield = new Playfield();
    private final HoldBox holdBox = new HoldBox();
    private final TetrominoQueue tetrominoQueue = new TetrominoQueue();

    private float speed = 1F;
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

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getDropInterval() {
        return (int) Math.max(START_INTERVAL / speed, MIN_INTERVAL);
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
