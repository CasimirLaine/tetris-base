package com.laine.casimir.tetris.base.model.tetromino;

import com.laine.casimir.tetris.base.model.Position;

public class TetrominoJ extends AbstractTetromino {

    @Override
    public String getColorHex() {
        return "#0000FF";
    }

    @Override
    public Position getRotationPoint() {
        return new Position(1, 1);
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
