package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.model.tetromino.AbstractTetromino;

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

    Playfield() {
    }

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
        if (fallingTetromino == null) {
            return;
        }
        final List<Square> squares = fallingTetromino.getSquares();
        boolean collides = false;
        for (int index = 0; index < squares.size(); index++) {
            final Square square = squares.get(index);
            if (collides(square.getPosition().getX() + fallingTetromino.getPosition().getX() + moveX,
                    square.getPosition().getY() + fallingTetromino.getPosition().getY() + moveY)) {
                collides = true;
                break;
            }
        }
        if (!collides) {
            fallingTetromino.move(moveX, moveY);
        } else {
            for (int index = 0; index < squares.size(); index++) {
                final Square square = squares.get(index);
                square.getPosition().setX(square.getPosition().getX() + fallingTetromino.getPosition().getX());
                square.getPosition().setY(square.getPosition().getY() + fallingTetromino.getPosition().getY());
                landedSquares.add(square);
            }
            fallingTetromino = null;
        }
    }

    private boolean collides(int x, int y) {
        if (y < 0 || y >= VISIBLE_HEIGHT || x < 0 || x >= WIDTH) {
            return true;
        }
        for (int index = 0; index < landedSquares.size(); index++) {
            final Square landedSquare = landedSquares.get(index);
            if (landedSquare.getPosition().collides(x, y)) {
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
