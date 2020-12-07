package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.model.FallingTetromino;
import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.Tetromino;

public final class TetrisManager {

    private final TetrisGame tetrisGame;

    public TetrisManager(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
    }

    public void rotateClockwise() {
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        final boolean canMove = tetrisGame.getPlayfield().move(0, 0);
        if (fallingTetromino != null && canMove) {
            fallingTetromino.getTetromino().rotateClockwise();
            if (!tetrisGame.getPlayfield().move(0, 0)) {
                fallingTetromino.getTetromino().rotateCounterclockwise();
            }
        }
    }

    public void rotateCounterclockwise() {
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        final boolean canMove = tetrisGame.getPlayfield().move(0, 0);
        if (fallingTetromino != null && canMove) {
            fallingTetromino.getTetromino().rotateCounterclockwise();
            if (!tetrisGame.getPlayfield().move(0, 0)) {
                fallingTetromino.getTetromino().rotateClockwise();
            }
        }
    }

    public void nextTetromino() {
        if (tetrisGame.getPlayfield().isPieceLockedOutOfBounds()) {
            tetrisGame.end();
            return;
        }
        final Tetromino nextTetromino = tetrisGame.getTetrominoQueue().pick();
        tetrisGame.getPlayfield().setFallingTetromino(nextTetromino);
    }
}
