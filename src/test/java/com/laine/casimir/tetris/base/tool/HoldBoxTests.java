package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.Tetromino;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HoldBoxTests {

    private HoldBox holdBox;

    @BeforeEach
    void init() {
        holdBox = new HoldBox();
    }

    @Test
    void testSet() {
        final Tetromino tetromino = Tetromino.createI();
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
