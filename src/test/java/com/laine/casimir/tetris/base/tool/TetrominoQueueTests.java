package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.Tetromino;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TetrominoQueueTests {
    private TetrominoQueue tetrominoQueue;

    @BeforeEach
    void init() {
        tetrominoQueue = new TetrominoQueue();
    }

    @Test
    void testCreate() {
        Assertions.assertNotNull(tetrominoQueue.pick());
    }

    @Test
    void testPick() {
        for (int index = 0; index < TetrominoQueue.BAG_SIZE; index++) {
            Assertions.assertNotNull(tetrominoQueue.pick());
        }
        final Tetromino x = tetrominoQueue.pick();
        final Tetromino y = tetrominoQueue.pick();
        Assertions.assertNotSame(x, y);
    }

    @Test
    void testPreview() {
        final Tetromino x = tetrominoQueue.getPreview(0);
        for (int index = 0; index < TetrominoQueue.BAG_SIZE * 2; index++) {
            Assertions.assertNotNull(tetrominoQueue.getPreview(index));
        }
        Assertions.assertNull(tetrominoQueue.getPreview(TetrominoQueue.BAG_SIZE * 2 + 1));
        final Tetromino y = tetrominoQueue.pick();
        Assertions.assertSame(x, y);
        Assertions.assertNull(tetrominoQueue.getPreview(-1));
    }

    @Test
    void testPreviewEmpty() {
        for (int index = 0; index < TetrominoQueue.BAG_SIZE * 2; index++) {
            tetrominoQueue.pick();
        }
        final Tetromino x = tetrominoQueue.getPreview(0);
        Assertions.assertNotNull(x);
    }
}
