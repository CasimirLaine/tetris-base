package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.event.TetrisEvent;
import com.laine.casimir.tetris.base.event.TetrisGameListener;
import com.laine.casimir.tetris.base.model.Playfield;
import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;
import com.laine.casimir.tetris.base.model.tetromino.FallingTetromino;

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
        tetrisGameListener.onEvent(TetrisEvent.GAME_START);
    }

    public void drop() {
        tetrisGameListener.onEvent(TetrisEvent.DROP);
    }

    public void shiftLeft() {
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftLeft();
    }

    public void shiftRight() {
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftRight();
    }

    public void rotateClockwise() {
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            fallingTetromino.getTetromino().rotateClockwise();
        }
    }

    public void rotateCounterclockwise() {
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            fallingTetromino.getTetromino().rotateCounterclockwise();
        }
    }

    public void hardDrop() {
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.drop();
    }

    public void softDrop() {
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.fall();
    }

    public void hold() {
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino == null || fallingTetromino.isTakenFromHoldBox()) {
            return;
        }
        final AbstractTetromino heldTetromino = tetrisGame.getHoldBox().setTetromino(fallingTetromino.getTetromino());
        tetrisGame.getPlayfield().setFallingTetromino(heldTetromino);
        final FallingTetromino newFallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (newFallingTetromino != null) {
            newFallingTetromino.setTakenFromHoldBox(true);
        }
        tetrisManager.nextTetromino();
        tetrisGameListener.onEvent(TetrisEvent.HOLD_BOX_UPDATE);
    }

    public void pause() {
        final boolean changed = !tetrisGame.isPaused();
        tetrisGame.setPaused(!tetrisGame.isPaused());
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
