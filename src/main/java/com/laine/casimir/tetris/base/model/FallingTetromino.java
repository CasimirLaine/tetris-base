package com.laine.casimir.tetris.base.model;

import java.util.List;

public final class FallingTetromino {

    private final Tetromino tetromino;

    private final Position position = new Position();

    private boolean unholdable;

    FallingTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
    }

    void move(int moveX, int moveY) {
        position.setX(position.getX() + moveX);
        position.setY(position.getY() + moveY);
    }

    public boolean isUnholdable() {
        return unholdable;
    }

    public void setUnholdable(boolean unholdable) {
        this.unholdable = unholdable;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }

    public List<Square> getSquares() {
        return tetromino.getSquares();
    }

    public Position getPosition() {
        return position;
    }
}
