package com.laine.casimir.tetris.base.model.tetromino;

public class TetrominoI extends AbstractTetromino {

    @Override
    public String getColorHex() {
        return "#00FFFF";
    }

    @Override
    protected int[] getSquareCoordinates() {
        return new int[]{
                0, 0,
                1, 0,
                2, 0,
                3, 0
        };
    }
}
