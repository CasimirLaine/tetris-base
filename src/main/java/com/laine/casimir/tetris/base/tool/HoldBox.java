package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;

public class HoldBox {

    private AbstractTetromino tetromino;

    public AbstractTetromino getTetromino() {
        return tetromino;
    }

    public void setTetromino(AbstractTetromino tetromino) {
        this.tetromino = tetromino;
    }
}
