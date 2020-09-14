package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.event.TetrisEvent;
import com.laine.casimir.tetris.base.event.TetrisGameListener;
import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;
import com.laine.casimir.tetris.base.model.tetromino.FallingTetromino;
import com.laine.casimir.tetris.base.tool.HoldBox;
import com.laine.casimir.tetris.base.tool.TetrominoQueue;

public class TetrisGame {

    public static final int PREVIEW_COUNT = 1;

    private final Playfield playfield = new Playfield();
    private final HoldBox holdBox = new HoldBox();
    private final TetrominoQueue tetrominoQueue = new TetrominoQueue();

    private TetrisGameListener tetrisGameListener;

    private boolean paused;

    public Playfield getPlayfield() {
        return playfield;
    }

    public HoldBox getHoldBox() {
        return holdBox;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        final boolean changed = this.paused == paused;
        this.paused = paused;
        if (tetrisGameListener != null && changed) {
            tetrisGameListener.onEvent(isPaused() ? TetrisEvent.PAUSE : TetrisEvent.RESUME);
        }
    }

    public void hold() {
        final FallingTetromino fallingTetromino = getPlayfield().getFallingTetromino();
        if (fallingTetromino == null || fallingTetromino.isTakenFromHoldBox()) {
            return;
        }
        final AbstractTetromino heldTetromino = getHoldBox().setTetromino(fallingTetromino.getTetromino());
        getPlayfield().setFallingTetromino(heldTetromino);
        final FallingTetromino newFallingTetromino = getPlayfield().getFallingTetromino();
        if (newFallingTetromino != null) {
            newFallingTetromino.setTakenFromHoldBox(true);
        }
        nextTetromino();
        tetrisGameListener.onEvent(TetrisEvent.HOLD_BOX_UPDATE);
    }

    public void nextTetromino() {
        final AbstractTetromino nextTetromino = tetrominoQueue.pick();
        playfield.setFallingTetromino(nextTetromino);
    }

    public void setTetrisGameListener(TetrisGameListener tetrisGameListener) {
        this.tetrisGameListener = tetrisGameListener;
    }
}
