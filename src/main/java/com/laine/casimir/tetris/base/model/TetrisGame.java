package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.tool.HoldBox;
import com.laine.casimir.tetris.base.tool.TetrominoQueue;

public class TetrisGame {

    private final Playfield playfield = new Playfield();
    private final HoldBox holdBox = new HoldBox();
    private final TetrominoQueue tetrominoQueue = new TetrominoQueue();

    private boolean paused;

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
}
