package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;

import java.util.ArrayList;
import java.util.List;

public class FallingTetromino {

    private final AbstractTetromino tetromino;
    private final List<Square> squares = new ArrayList<>();

    private final Position position = new Position();

    private boolean takenFromHoldBox;

    FallingTetromino(AbstractTetromino tetromino) {
        this.tetromino = tetromino;
        move((int) (0.5F * Playfield.WIDTH) - tetromino.getRotationPoint().getX() - 1, 0);
        squares.addAll(tetromino.getSquares());
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

    public AbstractTetromino getTetromino() {
        return tetromino;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public Position getPosition() {
        return position;
    }
}
