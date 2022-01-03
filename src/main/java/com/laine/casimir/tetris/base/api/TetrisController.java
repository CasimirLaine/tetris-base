package com.laine.casimir.tetris.base.api;

import com.laine.casimir.tetris.base.api.model.BaseTetromino;
import com.laine.casimir.tetris.base.api.model.ClearData;
import com.laine.casimir.tetris.base.api.model.TetrisCell;
import com.laine.casimir.tetris.base.control.TetrisManager;
import com.laine.casimir.tetris.base.model.FallingTetromino;
import com.laine.casimir.tetris.base.model.Playfield;
import com.laine.casimir.tetris.base.model.TetrisGame;
import com.laine.casimir.tetris.base.model.Tetromino;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to control the tetris game.
 */
public final class TetrisController {

    private final TetrisManager tetrisManager;

    private final TetrisGame tetrisGame;

    private long lastDrop;

    private boolean started;

    /**
     * Creates and initializes a new tetris game.
     */
    public TetrisController() {
        this.tetrisGame = new TetrisGame();
        this.tetrisManager = new TetrisManager(tetrisGame);
    }

    /**
     * Starts the tetris game.
     */
    public void start() {
        if (started) {
            return;
        }
        started = true;
        tetrisGame.setPaused(false);
    }

    /**
     * Progresses the tetris game one step further. In practise, drops the falling tetromino one step down according to the gravity.
     */
    public void update() {
        if (tetrisGame.isRunning() && started && System.currentTimeMillis() - lastDrop >= tetrisGame.getDropInterval()) {
            if (tetrisGame.getPlayfield().getFallingTetromino() == null) {
                tetrisManager.nextTetromino();
            } else {
                tetrisManager.gravity();
            }
            lastDrop = System.currentTimeMillis();
        }
    }

    /**
     * Moves the falling tetromino left, if possible.
     */
    public void shiftLeft() {
        if (!tetrisGame.isRunning() || !started) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftLeft();
    }

    /**
     * Moves the falling tetromino right, if possible.
     */
    public void shiftRight() {
        if (!tetrisGame.isRunning() || !started) {
            return;
        }
        final Playfield playfield = tetrisGame.getPlayfield();
        playfield.shiftRight();
    }

    /**
     * Rotates the falling tetromino clockwise.
     */
    public void rotateClockwise() {
        if (!tetrisGame.isRunning() || !started) {
            return;
        }
        tetrisManager.rotate(true);
    }

    /**
     * Rotates the falling tetromino counterclockwise.
     */
    public void rotateCounterclockwise() {
        if (!tetrisGame.isRunning() || !started) {
            return;
        }
        tetrisManager.rotate(false);
    }

    /**
     * Drops the falling tetromino to the ground.
     */
    public void hardDrop() {
        if (!tetrisGame.isRunning() || !started) {
            return;
        }
        tetrisManager.hardDrop();
    }

    /**
     * Moves the falling tetromino one step down.
     */
    public void softDrop() {
        if (!tetrisGame.isRunning() || !started) {
            return;
        }
        tetrisManager.softDrop();
    }

    /**
     * Moves the falling tetromino to the hold box. This will pick new tetromino to be the falling one.
     */
    public void hold() {
        if (!tetrisGame.isRunning() || !started) {
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

    /**
     * Pause the game.
     */
    public void pause() {
        tetrisGame.setPaused(true);
    }

    /**
     * Resume the game from pause.
     */
    public void resume() {
        tetrisGame.setPaused(false);
    }

    /**
     * Checks whether the game is paused.
     *
     * @return Whether the game is paused or not.
     */
    public boolean isPaused() {
        return tetrisGame.isPaused();
    }

    /**
     * Checks whether the game is over.
     *
     * @return Whether the game is over or not.
     */
    public boolean isGameOver() {
        return tetrisGame.isGameOver();
    }

    /**
     * Gets all the tetris cells in the playfield.
     *
     * @return A list of {@link TetrisCell} objects.
     */
    public List<TetrisCell> getAlLCells() {
        final List<TetrisCell> squareList = new ArrayList<>(tetrisGame.getPlayfield().getLandedSquares());
        final FallingTetromino fallingTetromino = tetrisGame.getPlayfield().getFallingTetromino();
        if (fallingTetromino != null) {
            squareList.addAll(fallingTetromino.getTetrisCellPositions());
        }
        return squareList;
    }

    /**
     * Returns the position of ghost cells. Ghost cells are where the falling tetromino would land were it hard-dropped.
     *
     * @return A list of {@link TetrisCell} objects.
     */
    public List<TetrisCell> getGhostCells() {
        return tetrisGame.getPlayfield().getGhostCells();
    }

    /**
     * Collect and empty the cleared tetris cells.
     *
     * @return {@link ClearData} object.
     */
    public ClearData collectClearData() {
        final ClearData clearData = tetrisGame.getClearData();
        tetrisGame.setClearData(null);
        return clearData;
    }

    /**
     * Get the tetromino that lies in the hold box or null if the hold box is empty.
     *
     * @return {@link BaseTetromino} object that lies in the hold box.
     */
    public BaseTetromino getHeldTetromino() {
        return tetrisGame.getHoldBox().getTetromino();
    }

    /**
     * Gets the list of tetrominoes that are in the queue to be dropped.
     *
     * @param count How many tetrominoes to fetch from the queue.
     * @return Array of {@link BaseTetromino} objects.
     */
    public BaseTetromino[] getPreviewTetrominos(int count) {
        if (count < 0) {
            count = 0;
        }
        final Tetromino[] previewTetrominos = new Tetromino[count];
        for (int index = 0; index < count; index++) {
            previewTetrominos[index] = tetrisGame.getTetrominoQueue().getPreview(index);
        }
        return previewTetrominos;
    }

    /**
     * Get the current score of the player.
     *
     * @return Player score.
     */
    public int getScore() {
        return tetrisGame.getTetrisScore().getScore();
    }

    /**
     * Get the current level of the player.
     *
     * @return Player level.
     */
    public int getLevel() {
        return tetrisGame.getTetrisScore().getLevel();
    }

    /**
     * Get the amount of rows the player has cleared.
     *
     * @return Cleared row count.
     */
    public int getClearedRows() {
        return tetrisGame.getTetrisScore().getLinesCleared();
    }
}
