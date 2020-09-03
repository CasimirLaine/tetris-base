package com.laine.casimir.tetris.base.model.tetromino;

public class TetrominoO extends AbstractTetromino {

    @Override
    public String getColorHex() {
        return "#FFFF00";
    }

    @Override
    protected int[] getSquareCoordinates() {
        return new int[]{
                0, 0,
                1, 0,
                0, 1,
                1, 1
        };
    }
}
