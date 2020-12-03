package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.event.TetrisEvent;
import com.laine.casimir.tetris.base.event.TetrisGameListener;
import com.laine.casimir.tetris.base.model.Playfield;
import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;
import com.laine.casimir.tetris.base.model.FallingTetromino;

public final class TetrisController {

    private final TetrisManager tetrisManager;

    private final TetrisGame tetrisGame;

    private TetrisGameListener tetrisGameListener;

    public TetrisController(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        this.tetrisManager = new TetrisManager(tetrisGame);
    }

    public void start() {
        tetrisGame.getHoldBox().setTetromino(null);
        tetrisGame.setPaused(false);
    }

    public void drop() {
        if (tetrisGame.isGameOver()) {
            return;
        }
        if (tetrisGame.getPlayfield().getFallingTetromino() == null) {
            tetrisManager.nextTetromino();
        } else {
            tetrisGame.getPlayfield().fall();
        }
    }

    public void shiftLeft() {
        if (tetrisGame.isGameOver()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftLeft();
    }

    public void shiftRight() {
        if (tetrisGame.isGameOver()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftRight();
    }

    public void rotateClockwise() {
        if (tetrisGame.isGameOver()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            fallingTetromino.getTetromino().rotateClockwise();
        }
    }

    public void rotateCounterclockwise() {
        if (tetrisGame.isGameOver()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            fallingTetromino.getTetromino().rotateCounterclockwise();
        }
    }

    public void hardDrop() {
        if (tetrisGame.isGameOver()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.drop();
    }

    public void softDrop() {
        if (tetrisGame.isGameOver()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.fall();
    }

    public void hold() {
        if (tetrisGame.isGameOver()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino == null || fallingTetromino.isTakenFromHoldBox()) {
            return;
        }
        final AbstractTetromino heldTetromino = tetrisGame.getHoldBox().setTetromino(fallingTetromino.getTetromino());
        tetrisGame.getPlayfield().setFallingTetromino(heldTetromino);
        if (heldTetromino == null) {
            tetrisManager.nextTetromino();
        }
        final FallingTetromino newFallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (newFallingTetromino != null) {
            newFallingTetromino.setTakenFromHoldBox(true);
        }
    }

    public void pause() {
        final boolean changed = !tetrisGame.isPaused();
        tetrisGame.setPaused(true);
        if (changed) {
            tetrisGameListener.onEvent(TetrisEvent.PAUSE);
        }
    }

    public void resume() {
        final boolean changed = tetrisGame.isPaused();
        tetrisGame.setPaused(false);
        if (changed) {
            tetrisGameListener.onEvent(TetrisEvent.RESUME);
        }
    }

    public void setTetrisGameListener(TetrisGameListener tetrisGameListener) {
        this.tetrisGameListener = tetrisGameListener;
    }
}
