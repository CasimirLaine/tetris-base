package com.laine.casimir.tetris.base.api;

import com.laine.casimir.tetris.base.api.model.BaseTetromino;
import com.laine.casimir.tetris.base.api.model.TetrisCell;
import com.laine.casimir.tetris.base.model.TetrisScore;
import com.laine.casimir.tetris.base.tool.TetrominoQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

class TetrisControllerTests {

    private TetrisController tetrisController;

    @BeforeEach
    void init() {
        tetrisController = new TetrisController();
    }

    void update() {
        try {
            TimeUnit.MILLISECONDS.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tetrisController.update();
    }

    @Test
    void testScore() {
        Assertions.assertTrue(tetrisController.getScore() == 0);
    }

    @Test
    void testLevel() {
        Assertions.assertTrue(tetrisController.getLevel() == TetrisScore.START_LEVEL);
    }

    @Test
    void testClearedRows() {
        Assertions.assertTrue(tetrisController.getClearedRows() == 0);
    }

    @Test
    void testPreviewTetrominos() {
        Assertions.assertTrue(tetrisController.getPreviewTetrominos(-1).length == 0);
        Assertions.assertTrue(tetrisController.getPreviewTetrominos(0).length == 0);
        for (int index = 0; index < TetrominoQueue.BAG_SIZE; index++) {
            Assertions.assertTrue(tetrisController.getPreviewTetrominos(index).length == index);
        }
    }

    @Test
    void testHeldTetromino() {
        Assertions.assertNull(tetrisController.getHeldTetromino());
        tetrisController.hold();
        Assertions.assertNull(tetrisController.getHeldTetromino());
        tetrisController.start();
        tetrisController.hold();
        update();
        tetrisController.hold();
        final BaseTetromino tetromino = tetrisController.getHeldTetromino();
        Assertions.assertNotNull(tetromino);
        update();
        tetrisController.hold();
        Assertions.assertSame(tetromino, tetrisController.getHeldTetromino());
        tetrisController.hardDrop();
        update();
        tetrisController.hold();
        Assertions.assertNotSame(tetromino, tetrisController.getHeldTetromino());
    }

    @Test
    void testClearData() {
        Assertions.assertNull(tetrisController.collectClearData());
    }

    @Test
    void testGhostCells() {
        Assertions.assertTrue(tetrisController.getGhostCells().isEmpty());
    }

    @Test
    void testAllCells() {
        Assertions.assertTrue(tetrisController.getAllCells().isEmpty());
        tetrisController.start();
        update();
        Assertions.assertFalse(tetrisController.getAllCells().isEmpty());
    }

    @Test
    void testGameOver() {
        Assertions.assertFalse(tetrisController.isGameOver());
        tetrisController.start();
        Assertions.assertFalse(tetrisController.isGameOver());
        tetrisController.pause();
        Assertions.assertFalse(tetrisController.isGameOver());
        tetrisController.resume();
        Assertions.assertFalse(tetrisController.isGameOver());
    }

    @Test
    void testPaused() {
        Assertions.assertFalse(tetrisController.isPaused());
        tetrisController.start();
        Assertions.assertFalse(tetrisController.isPaused());
        tetrisController.pause();
        Assertions.assertTrue(tetrisController.isPaused());
    }

    @Test
    void testSoftDrop() {
        tetrisController.softDrop();
        tetrisController.start();
        update();
        final List<TetrisCell> tetrisCells = tetrisController.getAllCells();
        final int cellCount = tetrisCells.size();
        Assertions.assertTrue(cellCount > 0);
        tetrisController.softDrop();
        update();
        Assertions.assertTrue(cellCount == tetrisController.getAllCells().size());
    }

    @Test
    void testHardDrop() {
        tetrisController.hardDrop();
        tetrisController.start();
        final List<TetrisCell> tetrisCells = tetrisController.getAllCells();
        final int cellCount = tetrisCells.size();
        Assertions.assertTrue(cellCount == 0);
        tetrisController.hardDrop();
        update();
        Assertions.assertTrue(cellCount != tetrisController.getAllCells().size());
    }

    @Test
    void testRotateClockwise() {
        tetrisController.rotateClockwise();
        tetrisController.start();
        tetrisController.rotateClockwise();
    }

    @Test
    void testRotateCounterclockwise() {
        tetrisController.rotateCounterclockwise();
        tetrisController.start();
        tetrisController.rotateCounterclockwise();
    }

    @Test
    void testShiftLeft() {
        tetrisController.shiftLeft();
        tetrisController.start();
        tetrisController.shiftLeft();
    }

    @Test
    void testShiftRight() {
        tetrisController.shiftRight();
        tetrisController.start();
        tetrisController.shiftRight();
    }

    @Test
    void testStart() {
        tetrisController.start();
        tetrisController.start();
    }
}
