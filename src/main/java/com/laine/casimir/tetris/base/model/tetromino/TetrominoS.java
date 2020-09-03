package com.laine.casimir.tetris.base.model.tetromino;

public class TetrominoS extends AbstractTetromino {

    @Override
    public String getColorHex() {
        return "#00FF00";
    }

    @Override
    protected int[] getSquareCoordinates() {
        return new int[]{
                1, 0,
                2, 0,
                0, 1,
                1, 1
        };
    }
}
