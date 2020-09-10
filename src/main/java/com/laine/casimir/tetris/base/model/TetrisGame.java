package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.tool.HoldBox;
import com.laine.casimir.tetris.base.tool.TetrominoQueue;

public class TetrisGame {

    private final HoldBox holdBox = new HoldBox();
    private TetrominoQueue tetrominoQueue;
    private Playfield playfield;

    private boolean paused;

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
