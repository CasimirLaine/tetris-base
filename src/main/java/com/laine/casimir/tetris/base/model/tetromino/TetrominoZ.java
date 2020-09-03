package com.laine.casimir.tetris.base.model.tetromino;

public class TetrominoZ extends AbstractTetromino {

    @Override
    public String getColorHex() {
        return "#FF0000";
    }

    @Override
    protected int[] getSquareCoordinates() {
        return new int[]{
                0, 0,
                1, 0,
                1, 1,
                2, 1
        };
    }
}
