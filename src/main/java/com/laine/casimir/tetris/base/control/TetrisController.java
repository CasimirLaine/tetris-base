package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.model.TetrisGame;

public class TetrisController {

    private final TetrisGame tetrisGame;
    private TetrisGameListener tetrisGameListener;

    public TetrisController(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
    }

    public void leftShift() {

    }

    public void rightShift() {

    }

    public void rotateClockwise() {

    }

    public void rotateCounterclockwise() {

    }

    public void hardDrop() {

    }

    public void softDrop() {

    }

    public void hold() {

    }

    public void pause() {
        tetrisGame.setPaused(!tetrisGame.isPaused());
        tetrisGameListener.onEvent(tetrisGame.isPaused() ? TetrisEvent.PAUSE : TetrisEvent.RESUME);
    }

    public void setTetrisGameListener(TetrisGameListener tetrisGameListener) {
        this.tetrisGameListener = tetrisGameListener;
    }

    public TetrisGame getTetrisGame() {
        return tetrisGame;
    }
}
