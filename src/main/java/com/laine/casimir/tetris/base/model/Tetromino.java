package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.model.BaseTetromino;
import com.laine.casimir.tetris.base.api.model.TetrisCell;

import java.util.ArrayList;
import java.util.List;

public final class Tetromino implements BaseTetromino {

    private static final int ROTATION_COUNT = 4;
    private static final int WALL_KICK_COUNT = 2 * ROTATION_COUNT;

    private static final int ROTATION_0 = 0;
    private static final int ROTATION_90 = 1;
    private static final int ROTATION_180 = 2;
    private static final int ROTATION_270 = 3;

    private final boolean[] data;
    private final String colorHex;
    private final int[] kickData;

    private int rotation;

    public Tetromino(String colorHex, int dimension, int[] kickData) {
        this.colorHex = colorHex;
        this.data = new boolean[dimension * dimension];
        this.kickData = kickData;
    }

    private boolean hasSquare(int x, int y) {
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

    public void resetRotation() {
        this.rotation = ROTATION_0;
    }

    @Override
    public List<TetrisCell> getTetrisCells() {
        final List<TetrisCell> tetrisCells = new ArrayList<>(getSquares());
        return tetrisCells;
    }

    List<Square> getSquares() {
        final List<Square> squares = new ArrayList<>();
        for (int x = 0; x < getDimension(); x++) {
            for (int y = 0; y < getDimension(); y++) {
                final boolean hasSquare = hasSquare(x, y);
                if (hasSquare) {
                    final Square square = new Square(x, y);
                    square.setColorHex(colorHex);
                    squares.add(square);
                }
            }
        }
        return squares;
    }

    @Override
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

    public int getKickIndexCount() {
        return kickData.length / (WALL_KICK_COUNT * 2);
    }

    public int getKickX(int kickIndex, boolean clockwise) {
        return kickData[getKickDataIndex(kickIndex, clockwise)];
    }

    public int getKickY(int kickIndex, boolean clockwise) {
        return -kickData[getKickDataIndex(kickIndex, clockwise) + 1];
    }

    private int getKickDataIndex(int kickIndex, boolean clockwise) {
        return (kickIndex * WALL_KICK_COUNT * 2
                + (WALL_KICK_COUNT * 2 +
                (rotation * 4 + (clockwise ? 0 : -2))) % (WALL_KICK_COUNT * 2));
    }
}
