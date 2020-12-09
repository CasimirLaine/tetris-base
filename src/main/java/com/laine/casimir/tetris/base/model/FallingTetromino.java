package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.model.TetrisCell;

import java.util.List;

public final class FallingTetromino {

    private final Tetromino tetromino;

    private final Position position = new Position();

    private boolean unholdable;

    FallingTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
    }

    public void move(int moveX, int moveY) {
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

    public List<TetrisCell> getTetrisCells() {
        return tetromino.getTetrisCells();
    }

    public List<Square> getTetrisCellsWithPosition() {
        final List<Square> squareList = tetromino.getSquares();
        for (int index = 0; index < squareList.size(); index++) {
            final Square square = squareList.get(index);
            final Position squarePosition = square.getPosition();
            squarePosition.setX(squarePosition.getX() + getPosition().getX());
            squarePosition.setY(squarePosition.getY() + getPosition().getY());
        }
        return squareList;
    }

    public Position getPosition() {
        return position;
    }
}
