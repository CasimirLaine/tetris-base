package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.tetromino.Tetromino;

public class HoldBox {

    private Tetromino tetromino;

    public Tetromino getTetromino() {
        return tetromino;
    }

    public Tetromino setTetromino(Tetromino tetromino) {
        final Tetromino currentTetromino = getTetromino();
        this.tetromino = tetromino;
        return currentTetromino;
    }
}
