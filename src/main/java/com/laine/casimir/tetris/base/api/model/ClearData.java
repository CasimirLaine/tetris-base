package com.laine.casimir.tetris.base.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains information on the {@link TetrisCell} objects that were cleared from the playfield.
 */
public final class ClearData {

    private final List<TetrisCell> clearedTetrisCells = new ArrayList<>();

    /***
     * Create the object.
     * @param clearedTetrisCells List of {@link TetrisCell} objects.
     */
    public ClearData(List<TetrisCell> clearedTetrisCells) {
        if (clearedTetrisCells != null) {
            this.clearedTetrisCells.addAll(clearedTetrisCells);
        }
    }

    /**
     * Get the list of {@link TetrisCell} objects.
     *
     * @return {@link TetrisCell} list.
     */
    public List<TetrisCell> getClearedTetrisCells() {
        return clearedTetrisCells;
    }
}
