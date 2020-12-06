package com.laine.casimir.tetris.base.model;

import java.util.List;

public final class FallingTetromino {

    private final Tetromino tetromino;

    private final Position position = new Position();

    private boolean holdable;

    FallingTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
    }

    void move(int moveX, int moveY) {
        position.setX(position.getX() + moveX);
        position.setY(position.getY() + moveY);
    }

    public boolean isHoldable() {
        return holdable;
    }

    public void setHoldable(boolean holdable) {
        this.holdable = holdable;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }

    public List<Square> getSquares() {
        final List<Square> squareList = tetromino.getSquares();
        for (int index = 0; index < squareList.size(); index++) {
            final Square square = squareList.get(index);
            square.getPosition().add(position.getX(), position.getY());
        }
        return squareList;
    }

    public Position getPosition() {
        return position;
    }
}
