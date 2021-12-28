package com.laine.casimir.tetris.base.api.model;

import java.util.List;

/**
 * Represents one tetromino in the playfield.
 */
public interface BaseTetromino {

    /**
     * Get the tetris cells that for the tetrimino.
     *
     * @return Returns a list of {@link TetrisCell} objects.
     */
    List<TetrisCell> getTetrisCells();

    /**
     * The side length of this tetromino's area.
     *
     * @return Dimensions (x and y) of the tetromino.
     */
    int getDimension();
}
