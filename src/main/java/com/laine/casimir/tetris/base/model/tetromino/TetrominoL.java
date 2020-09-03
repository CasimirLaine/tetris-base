package com.laine.casimir.tetris.base.model.tetromino;

public class TetrominoL extends AbstractTetromino {

    @Override
    public String getColorHex() {
        return "#FFA500";
    }

    @Override
    protected int[] getSquareCoordinates() {
        return new int[]{
                2, 0,
                0, 1,
                1, 1,
                2, 1
        };
    }
}
