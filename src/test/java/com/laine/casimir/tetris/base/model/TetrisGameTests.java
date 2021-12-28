package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.model.ClearData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TetrisGameTests {

    private TetrisGame tetrisGame;

    @BeforeEach
    void init() {
        tetrisGame = new TetrisGame();
    }

    @Test
    void testPlayfield() {
        Assertions.assertNotNull(tetrisGame.getPlayfield());
    }

    @Test
    void testHoldBox() {
        Assertions.assertNotNull(tetrisGame.getHoldBox());
    }

    @Test
    void testTetrominoQueue() {
        Assertions.assertNotNull(tetrisGame.getTetrominoQueue());
    }

    @Test
    void testTetrisScore() {
        Assertions.assertNotNull(tetrisGame.getTetrisScore());
    }

    @Test
    void testPause() {
        tetrisGame.setPaused(true);
        Assertions.assertTrue(tetrisGame.isPaused());
        Assertions.assertFalse(tetrisGame.isRunning());
        tetrisGame.setPaused(false);
        Assertions.assertFalse(tetrisGame.isPaused());
        Assertions.assertTrue(tetrisGame.isRunning());
    }

    @Test
    void testEnd() {
        Assertions.assertTrue(tetrisGame.isRunning());
        Assertions.assertFalse(tetrisGame.isGameOver());
        tetrisGame.end();
        Assertions.assertFalse(tetrisGame.isRunning());
        Assertions.assertTrue(tetrisGame.isGameOver());
    }

    @Test
    void testDropInterval() {
        Assertions.assertTrue(tetrisGame.getDropInterval() > 0);
    }

    @Test
    void testClearData() {
        Assertions.assertNull(tetrisGame.getClearData());
        final ClearData clearData = new ClearData(new ArrayList<>());
        tetrisGame.setClearData(clearData);
        Assertions.assertSame(clearData, tetrisGame.getClearData());
    }
}
