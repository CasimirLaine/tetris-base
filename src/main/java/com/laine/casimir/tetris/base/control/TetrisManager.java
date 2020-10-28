package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;

final class TetrisManager {

    private final TetrisGame tetrisGame;

    public TetrisManager(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
    }

    public void nextTetromino() {
        final AbstractTetromino nextTetromino = tetrisGame.getTetrominoQueue().pick();
        tetrisGame.getPlayfield().setFallingTetromino(nextTetromino);
    }
}
