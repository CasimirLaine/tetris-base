package com.laine.casimir.tetris.base.model.tetromino;

public class TetrominoJ extends AbstractTetromino {

    @Override
    public String getColorHex() {
        return "#0000FF";
    }

    @Override
    protected int[] getSquareCoordinates() {
        return new int[]{
                0, 0,
                0, 1,
                1, 1,
                2, 1
        };
    }
}
