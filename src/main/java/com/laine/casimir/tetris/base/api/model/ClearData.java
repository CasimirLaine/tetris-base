package com.laine.casimir.tetris.base.api.model;

import java.util.List;

public final class ClearData {

    private final List<TetrisCell> clearedTetrisCells;
    private final int linesCleared;

    public ClearData(List<TetrisCell> clearedTetrisCells, int linesCleared) {
        this.clearedTetrisCells = clearedTetrisCells;
        this.linesCleared = linesCleared;
    }

    public List<TetrisCell> getClearedTetrisCells() {
        return clearedTetrisCells;
    }

    public int getLinesCleared() {
        return linesCleared;
    }
}
