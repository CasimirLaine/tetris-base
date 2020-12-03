package com.laine.casimir.tetris.base.model.tetromino;

import com.laine.casimir.tetris.base.model.Position;

public class TetrominoL extends AbstractTetromino {

    @Override
    public String getColorHex() {
        return "#FFA500";
    }

    @Override
    public Position getRotationPoint() {
        return new Position(1, 1);
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
