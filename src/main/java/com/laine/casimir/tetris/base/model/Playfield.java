package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;
import com.laine.casimir.tetris.base.model.tetromino.FallingTetromino;
import com.laine.casimir.tetris.base.model.tetromino.Square;

import java.util.ArrayList;
import java.util.List;

public class Playfield {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 40;
    public static final int VISIBLE_HEIGHT = 20;
    public static final String BACKGROUND_COLOR = "#000000";
    public static final String GRID_COLOR = "#FFFFFF";

    private final List<Square> landedSquares = new ArrayList<>();

    private FallingTetromino fallingTetromino;

    public void fall() {
        move(0, 1);
    }

    public void drop() {

    }

    public void shiftLeft() {
        move(-1, 0);
    }

    public void shiftRight() {
        move(1, 0);
    }

    private void move(int moveX, int moveY) {

    }

    public boolean collides(Square square) {
        if (square.getPosition().getY() < 0 || square.getPosition().getY() >= HEIGHT) {
            return true;
        }
        if (square.getPosition().getX() < 0 || square.getPosition().getX() >= WIDTH) {
            return true;
        }
        for (int index = 0; index < landedSquares.size(); index++) {
            final Square landedSquare = landedSquares.get(index);
            if (landedSquare.getPosition().collides(square.getPosition())) {
                return true;
            }
        }
        return false;
    }

    public FallingTetromino getFallingTetromino() {
        return fallingTetromino;
    }

    public void setFallingTetromino(AbstractTetromino fallingTetromino) {
        if (fallingTetromino != null) {
            this.fallingTetromino = new FallingTetromino(fallingTetromino);
        } else {
            this.fallingTetromino = null;
        }
    }

    public List<Square> getLandedSquares() {
        return landedSquares;
    }
}
