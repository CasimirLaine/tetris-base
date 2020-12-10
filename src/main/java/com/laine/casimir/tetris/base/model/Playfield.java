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

    private boolean collides(int x, int y) {
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

    public boolean canMove(int x, int y) {
        if (fallingTetromino == null) {
            return false;
        }
        final List<TetrisCell> fallingTetrominoCells = fallingTetromino.getTetrisCells();
        for (int index = 0; index < fallingTetrominoCells.size(); index++) {
            final TetrisCell fallingCell = fallingTetrominoCells.get(index);
            if (collides(fallingCell.getX() + fallingTetromino.getPosition().getX() + x,
                    fallingCell.getY() + fallingTetromino.getPosition().getY() + y)) {
                return false;
            }
        }
        return true;
    }

    public boolean isFullRow(int y) {
        for (int x = 0; x < width; x++) {
            if (!collides(x, y)) {
                return false;
            }
        }
        return true;
    }

    public List<TetrisCell> clearRow(int y) {
        final List<TetrisCell> tetrisCellList = new ArrayList<>();
        if (y < visibleHeight - height || y >= visibleHeight) {
            return tetrisCellList;
        }
        for (int index = 0; index < landedSquares.size(); index++) {
            final Square square = landedSquares.get(index);
            if (square.getPosition().getY() == y) {
                landedSquares.remove(index);
                tetrisCellList.add(square);
                index--;
            } else if (square.getPosition().getY() < y) {
                square.getPosition().setY(square.getPosition().getY() + 1);
            }
        }
        return tetrisCellList;
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
        final boolean pushedOutOfBounds = fallingTetromino.getPosition().getY() < 0;
        final boolean collides = !canMove(moveX, moveY);
        pieceLockedOutOfBounds = pushedOutOfBounds && collides;
        if (!collides) {
            fallingTetromino.move(moveX, moveY);
        } else {
            fallingTetromino.move(0, Math.min(moveY, 0));
        }
        return !collides;
    }

    private int getMaxMoveY() {
        if (fallingTetromino == null) {
            return 0;
        }
        for (int moveY = 1; moveY < height; moveY++) {
            if (!canMove(0, moveY)) {
                return moveY - 1;
            }
        }
        return 0;
    }

    public List<TetrisCell> getGhostCells() {
        final List<TetrisCell> ghostCellList = new ArrayList<>();
        if (fallingTetromino == null) {
            return ghostCellList;
        }
        final int moveY = getMaxMoveY();
        final List<TetrisCell> fallingTetrominoCells = fallingTetromino.getTetrisCells();
        for (int index = 0; index < fallingTetrominoCells.size(); index++) {
            final TetrisCell fallingCell = fallingTetrominoCells.get(index);
            final Square ghostSquare = new Square(fallingCell.getX() + fallingTetromino.getPosition().getX(),
                    fallingCell.getY() + fallingTetromino.getPosition().getY() + moveY);
            ghostSquare.setColorHex(fallingCell.getColorHex());
            ghostCellList.add(ghostSquare);
        }
        return ghostCellList;
    }

    public FallingTetromino getFallingTetromino() {
        return fallingTetromino;
    }

    public void setFallingTetromino(Tetromino fallingTetromino) {
        if (fallingTetromino != null) {
            this.fallingTetromino = new FallingTetromino(fallingTetromino);
            this.fallingTetromino.getPosition().setX((int) (0.5F * TetrisConstants.WIDTH - Math.ceil(fallingTetromino.getDimension() * 0.5F)));
            this.fallingTetromino.getPosition().setY(-fallingTetromino.getDimension() + 1);
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
