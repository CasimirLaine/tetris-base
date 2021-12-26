package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.Tetromino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class TetrominoBag {

    private final List<Tetromino> tetrominos = new ArrayList<>();

    public void fill() {
        tetrominos.clear();
        tetrominos.add(Tetromino.createI());
        tetrominos.add(Tetromino.createJ());
        tetrominos.add(Tetromino.createL());
        tetrominos.add(Tetromino.createO());
        tetrominos.add(Tetromino.createS());
        tetrominos.add(Tetromino.createT());
        tetrominos.add(Tetromino.createZ());
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
}
