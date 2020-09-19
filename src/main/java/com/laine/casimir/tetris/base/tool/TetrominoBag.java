package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.tetromino.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TetrominoBag {

    private final List<AbstractTetromino> tetrominos = new ArrayList<>();

    public void fill() {
        tetrominos.clear();
        tetrominos.add(new TetrominoI());
        tetrominos.add(new TetrominoJ());
        tetrominos.add(new TetrominoL());
        tetrominos.add(new TetrominoO());
        tetrominos.add(new TetrominoS());
        tetrominos.add(new TetrominoT());
        tetrominos.add(new TetrominoZ());
        Collections.shuffle(tetrominos);
    }

    public AbstractTetromino pick() {
        if (tetrominos.isEmpty()) {
            return null;
        }
        return tetrominos.remove(0);
    }

    public AbstractTetromino getPreview(int index) {
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
}
