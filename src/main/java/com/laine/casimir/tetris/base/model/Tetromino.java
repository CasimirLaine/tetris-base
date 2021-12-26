package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.model.BaseTetromino;
import com.laine.casimir.tetris.base.api.model.TetrisCell;

import java.util.ArrayList;
import java.util.List;

public final class Tetromino implements BaseTetromino {

    private static final int ROTATION_COUNT = 4;
    private static final int WALL_KICK_COUNT = 2 * ROTATION_COUNT;
    private static final int NUMBERS_PER_KICK = WALL_KICK_COUNT * 2;

    private static final int ROTATION_0 = 0;
    private static final int ROTATION_90 = 1;
    private static final int ROTATION_180 = 2;
    private static final int ROTATION_270 = 3;

    private final String name;
    private final boolean[] data;
    private final String colorHex;
    private final int[] kickData;

    private int rotation;

    private Tetromino(String name, String colorHex, int dimension, int[] kickData) {
        this.name = name;
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
        return new ArrayList<>(getSquares());
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
        final int dimension = getDimension();
        switch (Math.abs(ROTATION_COUNT + rotation) % ROTATION_COUNT) {
            case ROTATION_0:
                return y * dimension + x;
            case ROTATION_90:
                return (dimension * dimension - dimension) + y - x * dimension;
            case ROTATION_180:
                return (dimension * dimension - 1) - y * dimension - x;
            case ROTATION_270:
                return (dimension - 1) - y + dimension * x;
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
        return kickIndex * NUMBERS_PER_KICK
                + (NUMBERS_PER_KICK +
                rotation * 4 + (clockwise ? 0 : -2)) % NUMBERS_PER_KICK;
    }

    public String getName() {
        return name;
    }

    public static Tetromino createI() {
        final Tetromino tetromino = new Tetromino("I", "#00FFFF", 4, getIKickData());
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        tetromino.setSquare(3, 1, true);
        return tetromino;
    }

    public static Tetromino createJ() {
        final Tetromino tetromino = new Tetromino("J", "#0000FF", 3, getDefaultKickData());
        tetromino.setSquare(0, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    public static Tetromino createL() {
        final Tetromino tetromino = new Tetromino("L", "#FFA500", 3, getDefaultKickData());
        tetromino.setSquare(2, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    public static Tetromino createO() {
        final Tetromino tetromino = new Tetromino("O", "#FFFF00", 2, getDefaultKickData());
        tetromino.setSquare(0, 0, true);
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        return tetromino;
    }

    public static Tetromino createS() {
        final Tetromino tetromino = new Tetromino("S", "#00FF00", 3, getDefaultKickData());
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(2, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        return tetromino;
    }

    public static Tetromino createT() {
        final Tetromino tetromino = new Tetromino("T", "#800080", 3, getDefaultKickData());
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    public static Tetromino createZ() {
        final Tetromino tetromino = new Tetromino("Z", "#FF0000", 3, getDefaultKickData());
        tetromino.setSquare(0, 0, true);
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    private static int[] getDefaultKickData() {
        return new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                -1, 0, 1, 0, 1, 0, -1, 0, 1, 0, -1, 0, -1, 0, 1, 0,
                -1, 1, 1, -1, 1, -1, -1, 1, 1, 1, -1, -1, -1, -1, 1, 1,
                0, -2, 0, 2, 0, 2, 0, -2, 0, -2, 0, 2, 0, 2, 0, -2,
                -1, -2, 1, 2, 1, 2, -1, -2, 1, -2, -1, 2, -1, 2, 1, -2
        };
    }

    private static int[] getIKickData() {
        return new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                -2, 0, 2, 0, -1, 0, 1, 0, 2, 0, -2, 0, 1, 0, -1, 0,
                1, 0, -1, 0, 2, 0, -2, 0, -1, 0, 1, 0, -2, 0, 2, 0,
                -2, -1, 2, 1, -1, 2, 1, -2, 2, 1, -2, -1, 1, -2, -1, 2,
                1, 2, -1, -2, 2, -1, -2, 1, -1, -2, 1, 2, -2, 1, 2, -1
        };
    }
}
