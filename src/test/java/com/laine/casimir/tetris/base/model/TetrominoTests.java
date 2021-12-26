package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.model.TetrisCell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TetrominoTests {

    private static final Tetromino[] TETROMINO_LIST = new Tetromino[]{
            Tetromino.createI(),
            Tetromino.createJ(),
            Tetromino.createL(),
            Tetromino.createO(),
            Tetromino.createS(),
            Tetromino.createT(),
            Tetromino.createZ()
    };

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testCreate(final int tetromimoIndex) {
        final Tetromino tetromino = TETROMINO_LIST[tetromimoIndex];
        Assertions.assertNotNull(tetromino);
    }

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testGetName(final int tetromimoIndex) {
        final Tetromino tetromino = TETROMINO_LIST[tetromimoIndex];
        Assertions.assertNotNull(tetromino.getName());
    }

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testKickX(final int tetromimoIndex) {
        final Tetromino tetromino = TETROMINO_LIST[tetromimoIndex];
        for (int index = 0; index < tetromino.getKickIndexCount(); index++) {
            tetromino.getKickX(index, true);
            tetromino.getKickX(index, false);
        }
    }

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testKickY(final int tetromimoIndex) {
        final Tetromino tetromino = TETROMINO_LIST[tetromimoIndex];
        for (int index = 0; index < tetromino.getKickIndexCount(); index++) {
            tetromino.getKickY(index, true);
            tetromino.getKickY(index, false);
        }
    }

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testDimension(final int tetromimoIndex) {
        final Tetromino tetromino = TETROMINO_LIST[tetromimoIndex];
        Assertions.assertTrue(tetromino.getDimension() > 0);
    }

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testRotateClockwise(final int tetromimoIndex) {
        final Tetromino tetromino = TETROMINO_LIST[tetromimoIndex];
        for (int index = 0; index < tetromino.getKickIndexCount(); index++) {
            tetromino.rotateClockwise();
            final List<TetrisCell> cells = tetromino.getTetrisCells();
            Assertions.assertNotNull(cells);
        }
    }

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testRotateCounterclockwise(final int tetromimoIndex) {
        final Tetromino tetromino = TETROMINO_LIST[tetromimoIndex];
        for (int index = 0; index < tetromino.getKickIndexCount(); index++) {
            tetromino.rotateCounterclockwise();
            final List<TetrisCell> cells = tetromino.getTetrisCells();
            Assertions.assertNotNull(cells);
        }
    }

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testResetRotation(final int tetromimoIndex) {
        final Tetromino tetromino = TETROMINO_LIST[tetromimoIndex];
        tetromino.resetRotation();
    }

    @ParameterizedTest()
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void testTetrisCells(final int tetromimoIndex) {
        final Tetromino tetromino = TETROMINO_LIST[tetromimoIndex];
        final List<TetrisCell> cells = tetromino.getTetrisCells();
        Assertions.assertNotNull(cells);
        Assertions.assertFalse(cells.isEmpty());
        Assertions.assertNotSame(cells, tetromino.getTetrisCells());
        final Set<String> positions = new HashSet<>();
        for (final TetrisCell cell : cells) {
            final int x = cell.getX();
            final int y = cell.getY();
            final String position = x + ":" + y;
            Assertions.assertFalse(positions.contains(position));
            positions.add(position);
        }
        Assertions.assertTrue(tetromino.getDimension() * 2 >= cells.size());
    }
}
