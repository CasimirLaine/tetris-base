package com.laine.casimir.tetris.base.api;

import com.laine.casimir.tetris.base.api.model.BaseTetromino;
import com.laine.casimir.tetris.base.api.model.TetrisCell;
import com.laine.casimir.tetris.base.control.TetrisManager;
import com.laine.casimir.tetris.base.model.FallingTetromino;
import com.laine.casimir.tetris.base.model.Playfield;
import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.Tetromino;

import java.util.ArrayList;
import java.util.List;

public final class TetrisController {

    private final TetrisManager tetrisManager;

    private final TetrisGame tetrisGame;

    private long lastDrop;

    private boolean started;

    public TetrisController() {
        this.tetrisGame = new TetrisGame();
        this.tetrisManager = new TetrisManager(tetrisGame);
    }

    public void start() {
        if (started) {
            return;
        }
        started = true;
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
                tetrisGame.getPlayfield().gravity();
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
            fallingTetromino.rotateClockwise();
        }
    }

    public void rotateCounterclockwise() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            fallingTetromino.rotateCounterclockwise();
        }
    }

    public void hardDrop() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.hardDrop();
    }

    public void softDrop() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.softDrop();
    }

    public void hold() {
        if (!tetrisGame.isRunning()) {
            return;
        }
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino == null || fallingTetromino.isUnholdable()) {
            return;
        }
        final Tetromino heldTetromino = tetrisGame.getHoldBox().setTetromino(fallingTetromino.getTetromino());
        if (heldTetromino != null) {
            heldTetromino.resetRotation();
        }
        tetrisGame.getPlayfield().setFallingTetromino(heldTetromino);
        if (heldTetromino == null) {
            tetrisManager.nextTetromino();
        }
        final FallingTetromino newFallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (newFallingTetromino != null) {
            newFallingTetromino.setUnholdable(true);
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

    public boolean isGameOver() {
        return tetrisGame.isGameOver();
    }

    public List<TetrisCell> getAllSquares() {
        final List<TetrisCell> squareList = new ArrayList<>();
        squareList.addAll(tetrisGame.getPlayfield().getLandedSquares());
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            squareList.addAll(fallingTetromino.getTetrisCellsWithPosition());
        }
        return squareList;
    }

    public BaseTetromino getHeldTetromino() {
        return tetrisGame.getHoldBox().getTetromino();
    }

    public BaseTetromino[] getPreviewTetrominos(int count) {
        final Tetromino[] previewTetrominos = new Tetromino[count];
        for (int index = 0; index < count; index++) {
            previewTetrominos[index] = tetrisGame.getTetrominoQueue().getPreview(index);
        }
        return previewTetrominos;
    }

    public int getScore() {
        return tetrisGame.getPlayfield().getScore();
    }

    public int getLevel() {
        return 1;
    }

    public int getClearedRows() {
        return tetrisGame.getPlayfield().getClearedRows();
    }
}
