package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.Tetromino;

final class TetrisManager {

    private final TetrisGame tetrisGame;

    public TetrisManager(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
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
