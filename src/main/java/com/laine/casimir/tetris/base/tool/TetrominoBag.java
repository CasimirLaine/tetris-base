package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.Tetromino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class TetrominoBag {

    private final List<Tetromino> tetrominos = new ArrayList<>();

    public void fill() {
        tetrominos.clear();
        tetrominos.add(createI());
        tetrominos.add(createJ());
        tetrominos.add(createL());
        tetrominos.add(createO());
        tetrominos.add(createS());
        tetrominos.add(createT());
        tetrominos.add(createZ());
        Collections.shuffle(tetrominos);
    }

    public Tetromino pick() {
        if (tetrominos.isEmpty()) {
            return null;
        }
        return tetrominos.remove(0);
    }

    public Tetromino getPreview(int index) {
        if (index >= tetrominos.size() || index < 0) {
            return null;
        }
        return tetrominos.get(index);
    }

    public int size() {
        return tetrominos.size();
    }

    public boolean isEmpty() {
        return tetrominos.isEmpty();
    }

    private static Tetromino createI() {
        final Tetromino tetromino = new Tetromino("I", "#00FFFF", 4, getIKickData());
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        tetromino.setSquare(3, 1, true);
        return tetromino;
    }

    private static Tetromino createJ() {
        final Tetromino tetromino = new Tetromino("J", "#0000FF", 3, getDefaultKickData());
        tetromino.setSquare(0, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    private static Tetromino createL() {
        final Tetromino tetromino = new Tetromino("L", "#FFA500", 3, getDefaultKickData());
        tetromino.setSquare(2, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    private static Tetromino createO() {
        final Tetromino tetromino = new Tetromino("O", "#FFFF00", 2, getDefaultKickData());
        tetromino.setSquare(0, 0, true);
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        return tetromino;
    }

    private static Tetromino createS() {
        final Tetromino tetromino = new Tetromino("S", "#00FF00", 3, getDefaultKickData());
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(2, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        return tetromino;
    }

    private static Tetromino createT() {
        final Tetromino tetromino = new Tetromino("T", "#800080", 3, getDefaultKickData());
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    private static Tetromino createZ() {
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
