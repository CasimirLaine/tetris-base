package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.model.TetrisCell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FallingTetrominoTests {

    private FallingTetromino fallingTetromino;

    @BeforeEach
    void init() {
        fallingTetromino = new FallingTetromino(Tetromino.createI());
    }

    @Test
    void testUnholdable() {
        fallingTetromino.setUnholdable(true);
        Assertions.assertTrue(fallingTetromino.isUnholdable());
        fallingTetromino.setUnholdable(false);
        Assertions.assertFalse(fallingTetromino.isUnholdable());
    }

    @Test
    void testGetTetromino() {
        Assertions.assertNotNull(fallingTetromino.getTetromino());
    }

    @Test
    void testGetTetrisCells() {
        final List<TetrisCell> cells = fallingTetromino.getTetrisCells();
        Assertions.assertNotNull(cells);
        Assertions.assertEquals(fallingTetromino.getTetromino().getTetrisCells().size(), cells.size());
        Assertions.assertNotSame(cells, fallingTetromino.getTetrisCells());
    }

    @Test
    void testPosition() {
        Assertions.assertNotNull(fallingTetromino.getPosition());
    }

    @Test
    void testMove() {
        final Position position = fallingTetromino.getPosition();
        final int x = position.getX();
        final int y = position.getY();
        final int moveX = 1;
        final int moveY = 2;
        fallingTetromino.move(moveX, moveY);
        Assertions.assertEquals(x + moveX, position.getX());
        Assertions.assertEquals(y + moveY, position.getY());
    }

    @Test
    void testCellPositions() {
        final List<Square> squares = fallingTetromino.getTetrisCellPositions();
        Assertions.assertNotNull(squares);
        Assertions.assertNotSame(squares, fallingTetromino.getTetrisCellPositions());
        final Set<String> positions = new HashSet<>();
        for (final Square cell : squares) {
            final int x = cell.getX();
            final int y = cell.getY();
            final String position = x + ":" + y;
            Assertions.assertFalse(positions.contains(position));
            positions.add(position);
        }
    }
}
