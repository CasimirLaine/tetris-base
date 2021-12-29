package com.laine.casimir.tetris.base.control;

import com.laine.casimir.tetris.base.model.TetrisGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TetrisManagerTests {

    private TetrisGame tetrisGame;
    private TetrisManager tetrisManager;

    @BeforeEach
    void init() {
        tetrisGame = new TetrisGame();
        tetrisManager = new TetrisManager(tetrisGame);
    }

    @Test
    void setNextTetromino() {
        Assertions.assertNull(tetrisGame.getPlayfield().getFallingTetromino());
        tetrisManager.nextTetromino();
        Assertions.assertNotNull(tetrisGame.getPlayfield().getFallingTetromino());
    }

    @Test
    void testRotate() {
        tetrisManager.rotate(true);
        tetrisManager.rotate(false);
        tetrisManager.nextTetromino();
        tetrisManager.rotate(true);
        tetrisManager.rotate(false);
    }

    @Test
    void testGravity() {
        Assertions.assertFalse(tetrisManager.gravity());
        tetrisManager.nextTetromino();
        Assertions.assertTrue(tetrisManager.gravity());
    }

    @Test
    void testHardDrop() {
        final int score = tetrisGame.getTetrisScore().getScore();
        tetrisManager.nextTetromino();
        Assertions.assertTrue(score == tetrisGame.getTetrisScore().getScore());
        tetrisManager.hardDrop();
        Assertions.assertTrue(score < tetrisGame.getTetrisScore().getScore());
    }

    @Test
    void testSoftDrop() {
        final int score = tetrisGame.getTetrisScore().getScore();
        tetrisManager.nextTetromino();
        Assertions.assertTrue(score == tetrisGame.getTetrisScore().getScore());
        tetrisManager.softDrop();
        Assertions.assertTrue(score < tetrisGame.getTetrisScore().getScore());
    }
}
