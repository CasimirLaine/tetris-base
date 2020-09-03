package com.laine.casimir.tetris.base.model.tetromino;

public class TetrominoT extends AbstractTetromino {

    @Override
    public String getColorHex() {
        return "#800080";
    }

    @Override
    protected int[] getSquareCoordinates() {
        return new int[]{
                1, 0,
                0, 1,
                1, 1,
                2, 1
        };
    }
}
