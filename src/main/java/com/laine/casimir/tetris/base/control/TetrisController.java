package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.model.Playfield;
import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.tetromino.FallingTetromino;

public class TetrisController {

    private final TetrisGame tetrisGame;

    public TetrisController(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
    }

    public void start() {

    }

    public void stop() {

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
        tetrisGame.hold();
    }

    public void pause() {
        tetrisGame.setPaused(!tetrisGame.isPaused());
    }

    public void resume() {
        tetrisGame.setPaused(false);
    }
}
