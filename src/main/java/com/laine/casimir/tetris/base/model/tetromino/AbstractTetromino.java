package com.laine.casimir.tetris.base.model.tetromino;

import com.laine.casimir.tetris.base.model.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTetromino {

    private static final int ROTATION_COUNT = 4;

    protected int rotationIndex = 0;

    public Position getKick(int kick) {
        switch (kick) {
            case 1:
                switch (rotationIndex) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        return new Position(0, 0);
                }
            case 2:
                switch (rotationIndex) {
                    case 0:
                        return new Position(-1, 0);
                    case 1:
                        return new Position(1, 0);
                    case 2:
                        return new Position(1, 0);
                    case 3:
                        return new Position(-1, 0);
                }
            case 3:
                switch (rotationIndex) {
                    case 0:
                        return new Position(-1, -1);
                    case 1:
                        return new Position(1, 1);
                    case 2:
                        return new Position(1, -1);
                    case 3:
                        return new Position(-1, 1);
                }
            case 4:
                switch (rotationIndex) {
                    case 0:
                        return new Position(0, 2);
                    case 1:
                        return new Position(0, -2);
                    case 2:
                        return new Position(0, 2);
                    case 3:
                        return new Position(0, -2);
                }
            case 5:
                switch (rotationIndex) {
                    case 0:
                        return new Position(-1, 2);
                    case 1:
                        return new Position(1, -2);
                    case 2:
                        return new Position(1, 2);
                    case 3:
                        return new Position(-1, -2);
                }
        }
        return null;
    }

    public Position[] getSquarePositions() {
        final int[] squareCoordinates = getSquareCoordinates();
        final Position[] positions = new Position[squareCoordinates.length / 2];
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

    public abstract String getColorHex();

    public abstract double getSpawnLocation();

    public abstract Position getRotationPoint();

    protected abstract int[] getSquareCoordinates();

    public List<Square> getSquares() {
        final List<Square> squares = new ArrayList<>();
        final Position[] positions = getSquarePositions();
        for (final Position position : positions) {
            final Square square = new Square(position);
            square.setColorHex(getColorHex());
            squares.add(square);
        }
        return squares;
    }
}
