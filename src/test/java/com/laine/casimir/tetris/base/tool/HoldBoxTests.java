package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.Tetromino;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HoldBoxTests {

    private HoldBox holdBox;

    private Tetromino createTetromino() {
        return new Tetromino("test", "#000000", 4, new int[]{});
    }

    @BeforeEach
    void init() {
        holdBox = new HoldBox();
    }

    @Test
    void testSet() {
        final Tetromino tetromino = createTetromino();
        Assertions.assertNull(holdBox.getTetromino());
        Assertions.assertSame(holdBox.setTetromino(tetromino), null);
        Assertions.assertSame(holdBox.setTetromino(tetromino), tetromino);
        Assertions.assertSame(holdBox.getTetromino(), tetromino);
    }

    @Test
    void testGet() {
        Assertions.assertNull(holdBox.getTetromino());
    }
}
