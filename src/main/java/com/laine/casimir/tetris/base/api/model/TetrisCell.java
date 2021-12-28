package com.laine.casimir.tetris.base.api.model;

/**
 * Represents one tetris cell.
 */
public interface TetrisCell {

    /**
     * X position of the cell.
     *
     * @return X position.
     */
    int getX();

    /**
     * Y position of the cell.
     *
     * @return Y position.
     */
    int getY();

    /**
     * Color of the cell in hexadecimal.
     *
     * @return A hexadecimal string.
     */
    String getColorHex();
}
