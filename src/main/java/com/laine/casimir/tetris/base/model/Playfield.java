package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.TetrisConstants;

import java.util.ArrayList;
import java.util.List;

public class Playfield {

    private final List<Square> landedSquares = new ArrayList<>();

    private FallingTetromino fallingTetromino;

    private int score;
    private int clearedRows;

    private boolean pieceLockedOutOfBounds;

    Playfield() {
    }

    private boolean checkRow(int y) {
        for (int x = 0; x < TetrisConstants.WIDTH; x++) {
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

    public void softDrop() {
        score += gravity() ? 1 : 0;
    }

    public boolean gravity() {
        if (fallingTetromino == null) {
            return false;
        }
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
        return moved;
    }

    public void hardDrop() {
        int hardDropScore = 0;
        while (fallingTetromino != null) {
            hardDropScore += gravity() ? 2 : 0;
        }
        score += hardDropScore;
    }

    public void shiftLeft() {
        move(-1, 0);
    }

    public void shiftRight() {
        move(1, 0);
    }

    private boolean move(int moveX, int moveY) {
        if (fallingTetromino == null) {
            return false;
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
        if (y < TetrisConstants.VISIBLE_HEIGHT - TetrisConstants.HEIGHT || y >= TetrisConstants.VISIBLE_HEIGHT || x < 0 || x >= TetrisConstants.WIDTH) {
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
            move((int) (0.5F * TetrisConstants.WIDTH - Math.ceil(fallingTetromino.getDimension() * 0.5F)), -fallingTetromino.getDimension() + 1);
        } else {
            this.fallingTetromino = null;
        }
        while (this.fallingTetromino != null && !move(0, 0)) {
            this.fallingTetromino.move(0, -1);
        }
    }

    public List<Square> getLandedSquares() {
        return new ArrayList<>(landedSquares);
    }

    public List<Square> getFallingSquares() {
        final List<Square> squares = new ArrayList<>();
        final FallingTetromino fallingTetromino = getFallingTetromino();
        if (fallingTetromino != null) {
            final List<Square> squareList = fallingTetromino.getSquares();
            for (int index = 0; index < squareList.size(); index++) {
                final Square square = squareList.get(index);
                final Position position = square.getPosition();
                position.setX(position.getX() + fallingTetromino.getPosition().getX());
                position.setY(position.getY() + fallingTetromino.getPosition().getY());
            }
            squares.addAll(squareList);
        }
        return squares;
    }

    public boolean isPieceLockedOutOfBounds() {
        return pieceLockedOutOfBounds;
    }

    public int getScore() {
        return score;
    }

    public int getClearedRows() {
        return clearedRows;
    }
}
