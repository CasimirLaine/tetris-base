package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.model.tetromino.Tetromino;

import java.util.ArrayList;
import java.util.List;

public class Playfield {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 40;
    public static final int VISIBLE_HEIGHT = 20;

    private final List<Square> landedSquares = new ArrayList<>();

    private FallingTetromino fallingTetromino;

    private int clearedRows;

    private boolean pieceLockedOutOfBounds;

    Playfield() {
    }

    private boolean checkRow(int y) {
        for (int x = 0; x < WIDTH; x++) {
            if (!collides(x, y)) {
                return false;
            }
        }
        return true;
    }

    private void clearRow(int y) {
        for (int index = 0; index < landedSquares.size(); index++) {
            final Square square = landedSquares.get(index);
            if (square.getPosition().getY() == y) {
                landedSquares.remove(index);
                index--;
            }
        }
        for (int index = 0; index < landedSquares.size(); index++) {
            final Square square = landedSquares.get(index);
            if (square.getPosition().getY() < y) {
                square.getPosition().setY(square.getPosition().getY() + 1);
            }
        }
        clearedRows++;
    }

    public void fall() {
        final boolean moved = move(0, 1);
        if (!moved) {
            final List<Square> squares = fallingTetromino.getSquares();
            for (int index = 0; index < squares.size(); index++) {
                final Square square = squares.get(index);
                square.getPosition().setX(square.getPosition().getX() + fallingTetromino.getPosition().getX());
                square.getPosition().setY(square.getPosition().getY() + fallingTetromino.getPosition().getY());
                landedSquares.add(square);
            }
            for (int index = 0; index < squares.size(); index++) {
                final int y = squares.get(index).getPosition().getY();
                final boolean shouldClear = checkRow(y);
                if (shouldClear) {
                    clearRow(y);
                }
            }
            fallingTetromino = null;
        }
    }

    public void drop() {

    }

    public void shiftLeft() {
        move(-1, 0);
    }

    public void shiftRight() {
        move(1, 0);
    }

    private boolean move(int moveX, int moveY) {
        if (fallingTetromino == null) {
            return true;
        }
        pieceLockedOutOfBounds = false;
        final List<Square> squares = fallingTetromino.getSquares();
        boolean collides = false;
        boolean pushedOutOfBounds = false;
        for (int index = 0; index < squares.size(); index++) {
            final Square square = squares.get(index);
            if (pushedOutOfBounds || square.getPosition().getY() + fallingTetromino.getPosition().getY() < 0) {
                pushedOutOfBounds = true;
            }
            if (collides || collides(square.getPosition().getX() + fallingTetromino.getPosition().getX() + moveX,
                    square.getPosition().getY() + fallingTetromino.getPosition().getY() + moveY)) {
                collides = true;
            }
        }
        if (pushedOutOfBounds && collides) {
            pieceLockedOutOfBounds = true;
        }
        if (!collides) {
            fallingTetromino.move(moveX, moveY);
        } else {
            fallingTetromino.move(0, Math.min(moveY, 0));
        }
        return !collides;
    }

    private boolean collides(int x, int y) {
        if (y < VISIBLE_HEIGHT - HEIGHT || y >= VISIBLE_HEIGHT || x < 0 || x >= WIDTH) {
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

    public void setFallingTetromino(Tetromino fallingTetromino) {
        if (fallingTetromino != null) {
            this.fallingTetromino = new FallingTetromino(fallingTetromino);
        } else {
            this.fallingTetromino = null;
        }
        while (!move(0, 0)) {
            this.fallingTetromino.move(0, -1);
        }
    }

    public List<Square> getLandedSquares() {
        return landedSquares;
    }

    public boolean isPieceLockedOutOfBounds() {
        return pieceLockedOutOfBounds;
    }

    public int getClearedRows() {
        return clearedRows;
    }
}
