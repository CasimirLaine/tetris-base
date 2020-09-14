package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;

public class HoldBox {

    private AbstractTetromino tetromino;

    public AbstractTetromino getTetromino() {
        return tetromino;
    }

    public AbstractTetromino setTetromino(AbstractTetromino tetromino) {
        final AbstractTetromino currentTetromino = getTetromino();
        this.tetromino = tetromino;
        return currentTetromino;
    }
}
