package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.model.FallingTetromino;
import com.laine.casimir.tetris.base.model.Playfield;
import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;

public final class TetrisController {

    private final TetrisManager tetrisManager;

    private final TetrisGame tetrisGame;

    private long lastDrop;

    public TetrisController(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        this.tetrisManager = new TetrisManager(tetrisGame);
    }

    public void start() {
        tetrisGame.getHoldBox().setTetromino(null);
        tetrisGame.setPaused(false);
    }

    public void update() {
        if (!tetrisGame.isGameOver() && System.currentTimeMillis() - lastDrop >= tetrisGame.getDropInterval()) {
            drop();
            lastDrop = System.currentTimeMillis();
        }
    }

    public void drop() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        if (tetrisGame.getPlayfield().getFallingTetromino() == null) {
            tetrisManager.nextTetromino();
        } else {
            tetrisGame.getPlayfield().fall();
        }
    }

    public void shiftLeft() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftLeft();
    }

    public void shiftRight() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftRight();
    }

    public void rotateClockwise() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            fallingTetromino.getTetromino().rotateClockwise();
        }
    }

    public void rotateCounterclockwise() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            fallingTetromino.getTetromino().rotateCounterclockwise();
        }
    }

    public void hardDrop() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.drop();
    }

    public void softDrop() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.fall();
    }

    public void hold() {
        if (!tetrisGame.isRunning()) {
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
        tetrisGame.setPaused(true);
    }

    public void resume() {
        tetrisGame.setPaused(false);
    }
}
