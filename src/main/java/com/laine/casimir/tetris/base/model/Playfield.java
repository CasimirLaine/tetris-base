package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.TetrisConstants;
import com.laine.casimir.tetris.base.api.model.TetrisCell;

import java.util.ArrayList;
import java.util.List;

public final class Playfield {

    private final List<Square> landedSquares = new ArrayList<>();

    private final int width;
    private final int height;
    private final int visibleHeight;

    private FallingTetromino fallingTetromino;

    private boolean pieceLockedOutOfBounds;

    Playfield(int width, int height, int visibleHeight) {
        this.width = width;
        this.height = height;
        this.visibleHeight = visibleHeight;
    }

    public boolean collides(int x, int y) {
        if (y < visibleHeight - height || y >= visibleHeight || x < 0 || x >= width) {
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

    public boolean isFullRow(int y) {
        for (int x = 0; x < width; x++) {
            if (!collides(x, y)) {
                return false;
            }
        }
        return true;
    }

    public void clearRow(int y) {
        if (y < visibleHeight - height || y >= visibleHeight) {
            return;
        }
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
    }

    public void shiftLeft() {
        move(-1, 0);
    }

    public void shiftRight() {
        move(1, 0);
    }

    public boolean move(int moveX, int moveY) {
        if (fallingTetromino == null) {
            return false;
        }
        pieceLockedOutOfBounds = false;
        final List<TetrisCell> tetrisCellList = fallingTetromino.getTetrisCells();
        boolean collides = false;
        boolean pushedOutOfBounds = false;
        for (int index = 0; index < tetrisCellList.size(); index++) {
            final TetrisCell tetrisCell = tetrisCellList.get(index);
            if (pushedOutOfBounds || tetrisCell.getY() + fallingTetromino.getPosition().getY() < 0) {
                pushedOutOfBounds = true;
            }
            if (collides || collides(tetrisCell.getX() + fallingTetromino.getPosition().getX() + moveX,
                    tetrisCell.getY() + fallingTetromino.getPosition().getY() + moveY)) {
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

    public int getVisibleHeight() {
        return visibleHeight;
    }

    public List<Square> getLandedSquares() {
        return landedSquares;
    }

    public boolean isPieceLockedOutOfBounds() {
        return pieceLockedOutOfBounds;
    }
}
