package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.tetromino.Tetromino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TetrominoBag {

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
        if (index >= tetrominos.size()) {
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

    private Tetromino createI() {
        final Tetromino tetromino = new Tetromino("#00FFFF", 4);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        tetromino.setSquare(3, 1, true);
        return tetromino;
    }

    private Tetromino createJ() {
        final Tetromino tetromino = new Tetromino("#0000FF", 3);
        tetromino.setSquare(0, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    private Tetromino createL() {
        final Tetromino tetromino = new Tetromino("#FFA500", 3);
        tetromino.setSquare(2, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    private Tetromino createO() {
        final Tetromino tetromino = new Tetromino("#FFFF00", 2);
        tetromino.setSquare(0, 0, true);
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        return tetromino;
    }

    private Tetromino createS() {
        final Tetromino tetromino = new Tetromino("#00FF00", 3);
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(2, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        return tetromino;
    }

    private Tetromino createT() {
        final Tetromino tetromino = new Tetromino("#800080", 3);
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(0, 1, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }

    private Tetromino createZ() {
        final Tetromino tetromino = new Tetromino("#FF0000", 3);
        tetromino.setSquare(0, 0, true);
        tetromino.setSquare(1, 0, true);
        tetromino.setSquare(1, 1, true);
        tetromino.setSquare(2, 1, true);
        return tetromino;
    }
}
