package com.laine.casimir.tetris.base.model.tetromino;

import com.laine.casimir.tetris.base.model.Position;

public abstract class AbstractTetromino {

    private static final int ROTATION_COUNT = 4;

    protected int rotationIndex = 0;

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

    public void rotateClockwise() {
        rotationIndex++;
        rotationIndex %= ROTATION_COUNT;
    }

    public void rotateCounterclockwise() {
        rotationIndex--;
        rotationIndex %= ROTATION_COUNT;
    }
}
