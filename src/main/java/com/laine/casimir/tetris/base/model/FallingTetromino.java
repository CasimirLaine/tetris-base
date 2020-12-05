package com.laine.casimir.tetris.base.model;

import java.util.List;

public class FallingTetromino {

    private final Tetromino tetromino;

    private final Position position = new Position();

    private boolean takenFromHoldBox;

    FallingTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
        move((int) (0.5F * Playfield.WIDTH - Math.ceil(tetromino.getDimension() * 0.5F)), -tetromino.getDimension() + 1);
    }

    void move(int moveX, int moveY) {
        position.setX(position.getX() + moveX);
        position.setY(position.getY() + moveY);
    }

    public boolean isTakenFromHoldBox() {
        return takenFromHoldBox;
    }

    public void setTakenFromHoldBox(boolean takenFromHoldBox) {
        this.takenFromHoldBox = takenFromHoldBox;
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
