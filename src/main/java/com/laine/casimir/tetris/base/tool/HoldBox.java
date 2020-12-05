package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.Tetromino;

public final class HoldBox {

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
