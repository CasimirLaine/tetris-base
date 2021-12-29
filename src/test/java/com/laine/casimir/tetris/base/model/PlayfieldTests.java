package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.TetrisConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayfieldTests {

    private Playfield playfield;

    @BeforeEach
    void init() {
        playfield = new Playfield(
                TetrisConstants.WIDTH,
                TetrisConstants.HEIGHT,
                TetrisConstants.VISIBLE_HEIGHT
        );
    }

    @Test
    void testVisibleHeight() {
        Assertions.assertEquals(TetrisConstants.VISIBLE_HEIGHT, playfield.getVisibleHeight());
    }

    @Test
    void testFallingTetromino() {
        Assertions.assertNull(playfield.getFallingTetromino());
        final Tetromino fallingTetromino = Tetromino.createI();
        playfield.setFallingTetromino(fallingTetromino);
        Assertions.assertSame(fallingTetromino, playfield.getFallingTetromino().getTetromino());
        playfield.setFallingTetromino(null);
        Assertions.assertNull(playfield.getFallingTetromino());
    }

    @Test
    void testLandedSquares() {
        Assertions.assertNotNull(playfield.getLandedSquares());
    }

    @Test
    void testGhostCells() {
        Assertions.assertNotNull(playfield.getGhostCells());
        Assertions.assertTrue(playfield.getGhostCells().isEmpty());
        playfield.setFallingTetromino(Tetromino.createO());
        Assertions.assertFalse(playfield.getGhostCells().isEmpty());
    }

    @Test
    void testMove() {
        Assertions.assertFalse(playfield.move(0, 1));
        Assertions.assertFalse(playfield.move(1, 0));
        playfield.setFallingTetromino(Tetromino.createO());
        Assertions.assertTrue(playfield.move(0, 1));
        Assertions.assertTrue(playfield.move(1, 0));
        Assertions.assertFalse(playfield.isPieceLockedOutOfBounds());
        Assertions.assertFalse(playfield.move(-100, 0));
        Assertions.assertFalse(playfield.move(0, 100));
        Assertions.assertFalse(playfield.isPieceLockedOutOfBounds());
        Assertions.assertTrue(playfield.move(0, -1));
        Assertions.assertFalse(playfield.move(100, 0));
        Assertions.assertTrue(playfield.isPieceLockedOutOfBounds());
    }

    @Test
    void testShiftLeft() {
        playfield.setFallingTetromino(Tetromino.createO());
        final int x = playfield.getFallingTetromino().getPosition().getX();
        playfield.shiftLeft();
        Assertions.assertTrue(x > playfield.getFallingTetromino().getPosition().getX());
    }

    @Test
    void testShiftRight() {
        playfield.setFallingTetromino(Tetromino.createO());
        final int x = playfield.getFallingTetromino().getPosition().getX();
        playfield.shiftRight();
        Assertions.assertTrue(x < playfield.getFallingTetromino().getPosition().getX());
    }

    @Test
    void testCanMove() {
        Assertions.assertFalse(playfield.canMove(0, 1));
        Assertions.assertFalse(playfield.canMove(1, 0));
        playfield.setFallingTetromino(Tetromino.createO());
        Assertions.assertTrue(playfield.canMove(0, 1));
        Assertions.assertTrue(playfield.canMove(1, 0));
        Assertions.assertFalse(playfield.canMove(-100, 0));
        Assertions.assertFalse(playfield.canMove(0, 100));
    }

    @Test
    void testFullRow() {
        for (int index = 0; index < TetrisConstants.VISIBLE_HEIGHT; index++) {
            Assertions.assertFalse(playfield.isFullRow(index));
        }
        Assertions.assertTrue(playfield.isFullRow(TetrisConstants.VISIBLE_HEIGHT + 1));
    }

    @Test
    void testClearRow() {
        for (int index = 0; index < TetrisConstants.VISIBLE_HEIGHT; index++) {
            Assertions.assertTrue(playfield.clearRow(index).isEmpty());
        }
        Assertions.assertTrue(playfield.clearRow(TetrisConstants.VISIBLE_HEIGHT + 1).isEmpty());
    }
}
