package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.model.FallingTetromino;
import com.laine.casimir.tetris.base.model.Position;
import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.Tetromino;

public final class TetrisManager {

    private final TetrisGame tetrisGame;

    public TetrisManager(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
    }

    public void softDrop() {
        tetrisGame.getTetrisScore().addScore(gravity() ? 1 : 0);
    }

    public void hardDrop() {
        int hardDropScore = 0;
        while (tetrisGame.getPlayfield().getFallingTetromino() != null) {
            hardDropScore += gravity() ? 2 : 0;
        }
        tetrisGame.getTetrisScore().addScore(hardDropScore);
    }

    public boolean gravity() {
        if (tetrisGame.getPlayfield().getFallingTetromino() == null) {
            return false;
        }
        final boolean moved = tetrisGame.getPlayfield().move(0, 1);
        if (!moved) {
            atomizeTetromino();
        }
        return moved;
    }

    public void rotate(boolean clockwise) {
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        final boolean canMove = tetrisGame.getPlayfield().move(0, 0);
        if (fallingTetromino != null && canMove) {
            final int x = fallingTetromino.getPosition().getX();
            final int y = fallingTetromino.getPosition().getY();
            for (int index = 0; index < fallingTetromino.getTetromino().getKickIndexCount(); index++) {
                fallingTetromino.move(fallingTetromino.getTetromino().getKickX(index, clockwise),
                        fallingTetromino.getTetromino().getKickY(index, clockwise));
                if (clockwise) {
                    fallingTetromino.getTetromino().rotateClockwise();
                } else {
                    fallingTetromino.getTetromino().rotateCounterclockwise();
                }
                if (!tetrisGame.getPlayfield().move(0, 0)) {
                    if (!clockwise) {
                        fallingTetromino.getTetromino().rotateClockwise();
                    } else {
                        fallingTetromino.getTetromino().rotateCounterclockwise();
                    }
                    fallingTetromino.getPosition().setX(x);
                    fallingTetromino.getPosition().setY(y);
                } else {
                    break;
                }
            }
        }
    }

    private void atomizeTetromino() {
        final Position fallingTetrominoPosition = tetrisGame.getPlayfield().getFallingTetromino().getPosition();
        tetrisGame.getPlayfield().getLandedSquares().addAll(tetrisGame.getPlayfield().getFallingTetromino().getTetrisCellsWithPosition());
        int linesCleared = 0;
        for (int y = tetrisGame.getPlayfield().getVisibleHeight() - 1; y >= fallingTetrominoPosition.getY(); y--) {
            final boolean shouldClear = tetrisGame.getPlayfield().isFullRow(y);
            if (shouldClear) {
                tetrisGame.getPlayfield().clearRow(y);
                linesCleared++;
                y++;
            }
        }
        tetrisGame.getTetrisScore().addLinesCleared(linesCleared);
        if (linesCleared <= 0) {
            tetrisGame.getTetrisScore().resetCombo();
        }
        tetrisGame.getPlayfield().setFallingTetromino(null);
    }

    public void nextTetromino() {
        if (tetrisGame.getPlayfield().isPieceLockedOutOfBounds()) {
            tetrisGame.end();
            return;
        }
        final Tetromino nextTetromino = tetrisGame.getTetrominoQueue().pick();
        tetrisGame.getPlayfield().setFallingTetromino(nextTetromino);
    }
}
