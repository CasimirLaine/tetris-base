package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.model.*;

import java.util.ArrayList;
import java.util.List;

public final class TetrisController {

    private final TetrisManager tetrisManager;

    private final TetrisGame tetrisGame;

    private long lastDrop;

    public TetrisController() {
        this.tetrisGame = new TetrisGame();
        this.tetrisManager = new TetrisManager(tetrisGame);
    }

    public void start() {
        tetrisGame.getHoldBox().setTetromino(null);
        tetrisGame.setPaused(false);
    }

    public void update() {
        if (!tetrisGame.isGameOver() && System.currentTimeMillis() - lastDrop >= tetrisGame.getDropInterval()) {
            if (!tetrisGame.isRunning()) {
                return;
            }
            if (tetrisGame.getPlayfield().getFallingTetromino() == null) {
                tetrisManager.nextTetromino();
            } else {
                tetrisGame.getPlayfield().fall();
            }
            lastDrop = System.currentTimeMillis();
        }
    }

    public void shiftLeft() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftLeft();
    }

    public void shiftRight() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftRight();
    }

    public void rotateClockwise() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            fallingTetromino.getTetromino().rotateClockwise();
        }
    }

    public void rotateCounterclockwise() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            fallingTetromino.getTetromino().rotateCounterclockwise();
        }
    }

    public void hardDrop() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.drop();
    }

    public void softDrop() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.fall();
    }

    public void hold() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino == null || fallingTetromino.isHoldable()) {
            return;
        }
        final Tetromino heldTetromino = tetrisGame.getHoldBox().setTetromino(fallingTetromino.getTetromino());
        tetrisGame.getPlayfield().setFallingTetromino(heldTetromino);
        if (heldTetromino == null) {
            tetrisManager.nextTetromino();
        }
        final FallingTetromino newFallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (newFallingTetromino != null) {
            newFallingTetromino.setHoldable(true);
        }
    }

    public void pause() {
        tetrisGame.setPaused(true);
    }

    public void resume() {
        tetrisGame.setPaused(false);
    }

    public boolean isPaused() {
        return tetrisGame.isPaused();
    }

    public List<Square> getAllSquares() {
        final List<Square> squareList = new ArrayList<>();
        squareList.addAll(tetrisGame.getPlayfield().getLandedSquares());
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            squareList.addAll(fallingTetromino.getSquares());
        }
        return squareList;
    }

    public Tetromino getHeldTetromino() {
        return tetrisGame.getHoldBox().getTetromino();
    }

    public Tetromino[] getPreviewTetrominos(int count) {
        final Tetromino[] previewTetrominos = new Tetromino[count];
        for (int index = 0; index < count; index++) {
            previewTetrominos[index] = tetrisGame.getTetrominoQueue().getPreview(index);
        }
        return previewTetrominos;
    }

    public int getScore() {
        return 0;
    }

    public int getLevel() {
        return 1;
    }

    public int getClearedRows() {
        return tetrisGame.getPlayfield().getClearedRows();
    }
}
