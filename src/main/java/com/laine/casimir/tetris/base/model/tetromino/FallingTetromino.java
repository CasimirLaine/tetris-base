package com.laine.casimir.tetris.base.model.tetromino;

import java.util.ArrayList;
import java.util.List;

public class FallingTetromino {

    private final AbstractTetromino tetromino;
    private final List<Square> squares = new ArrayList<>();

    private boolean takenFromHoldBox;

    public FallingTetromino(AbstractTetromino tetromino) {
        this.tetromino = tetromino;
        squares.addAll(tetromino.getSquares());
    }

    public boolean isTakenFromHoldBox() {
        return takenFromHoldBox;
    }

    public void setTakenFromHoldBox(boolean takenFromHoldBox) {
        this.takenFromHoldBox = takenFromHoldBox;
    }

    public AbstractTetromino getTetromino() {
        return tetromino;
    }
}
