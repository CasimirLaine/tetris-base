package com.laine.casimir.tetris.base.model;

import java.util.ArrayList;
import java.util.List;

public final class Tetromino {

    private static final int ROTATION_COUNT = 4;
    private static final int ROTATION_0 = 0;
    private static final int ROTATION_90 = 1;
    private static final int ROTATION_180 = 2;
    private static final int ROTATION_270 = 3;

    private final boolean[] data;
    private final String colorHex;

    private int rotation;

    public Tetromino(String colorHex, int dimension) {
        this.colorHex = colorHex;
        this.data = new boolean[dimension * dimension];
    }

    boolean hasSquare(int x, int y) {
        return data[getRotatedIndex(x, y)];
    }

    public void setSquare(int x, int y, boolean enabled) {
        data[getRotatedIndex(x, y)] = enabled;
    }

    public void rotateClockwise() {
        rotation++;
        rotation = Math.abs(rotation + ROTATION_COUNT) % ROTATION_COUNT;
    }

    public void rotateCounterclockwise() {
        rotation--;
        rotation = Math.abs(rotation + ROTATION_COUNT) % ROTATION_COUNT;
    }

    public List<Position> getSquarePositions() {
        final List<Position> positions = new ArrayList<>();
        for (int x = 0; x < getDimension(); x++) {
            for (int y = 0; y < getDimension(); y++) {
                final boolean hasSquare = hasSquare(x, y);
                if (hasSquare) {
                    final Position position = new Position(x, y);
                    positions.add(position);
                }
            }
        }
        return positions;
    }

    public List<Square> getSquares() {
        final List<Square> squares = new ArrayList<>();
        final List<Position> positions = getSquarePositions();
        for (final Position position : positions) {
            final Square square = new Square(position);
            square.setColorHex(getColorHex());
            squares.add(square);
        }
        return squares;
    }

    public String getColorHex() {
        return colorHex;
    }

    public int getDimension() {
        return (int) Math.sqrt(data.length);
    }

    private int getRotatedIndex(int x, int y) {
        switch (Math.abs(ROTATION_COUNT + rotation) % ROTATION_COUNT) {
            case ROTATION_0:
                return y * getDimension() + x;
            case ROTATION_90:
                return (getDimension() * getDimension() - getDimension()) + y - x * getDimension();
            case ROTATION_180:
                return (getDimension() * getDimension() - 1) - y * getDimension() - x;
            case ROTATION_270:
                return (getDimension() - 1) - y + getDimension() * x;
            default:
                return 0;
        }
    }
}
