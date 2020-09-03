package com.laine.casimir.tetris.base.model.tetromino;

import com.laine.casimir.tetris.base.model.Position;

public abstract class AbstractTetromino {

    public abstract String getColorHex();

    protected abstract int[] getSquareCoordinates();

    public Position[] getSquarePositions() {
        final int[] squareCoordinates = getSquareCoordinates();
        final Position[] positions = new Position[squareCoordinates.length];
        for (int index = 0; index < positions.length; index++) {
            final Position position = new Position(squareCoordinates[2 * index], squareCoordinates[2 * index + 1]);
            positions[index] = position;
        }
        return positions;
    }
}
