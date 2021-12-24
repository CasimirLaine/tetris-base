package com.laine.casimir.tetris.base.api.model;

import java.util.ArrayList;
import java.util.List;

public final class ClearData {

    private final List<TetrisCell> clearedTetrisCells = new ArrayList<>();

    public ClearData(List<TetrisCell> clearedTetrisCells) {
        if (clearedTetrisCells != null) {
            this.clearedTetrisCells.addAll(clearedTetrisCells);
        }
    }

    public List<TetrisCell> getClearedTetrisCells() {
        return clearedTetrisCells;
    }
}
