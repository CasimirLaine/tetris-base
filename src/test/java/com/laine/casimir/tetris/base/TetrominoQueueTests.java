package com.laine.casimir.tetris.base;

import com.laine.casimir.tetris.base.model.Tetromino;
import com.laine.casimir.tetris.base.tool.TetrominoQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TetrominoQueueTests {
    @Test
    void testCreate() {
        final TetrominoQueue tetrominoQueue = new TetrominoQueue();
        Assertions.assertNotNull(tetrominoQueue.pick());
    }

    @Test
    void testPick() {
        final TetrominoQueue tetrominoQueue = new TetrominoQueue();
        for (int index = 0; index < TetrominoQueue.BAG_SIZE; index++) {
            Assertions.assertNotNull(tetrominoQueue.pick());
        }
        final Tetromino x = tetrominoQueue.pick();
        final Tetromino y = tetrominoQueue.pick();
        Assertions.assertNotSame(x, y);
    }

    @Test
    void testPreview() {
        final TetrominoQueue tetrominoQueue = new TetrominoQueue();
        final Tetromino x = tetrominoQueue.getPreview(0);
        for (int index = 0; index < TetrominoQueue.BAG_SIZE * 2; index++) {
            Assertions.assertNotNull(tetrominoQueue.getPreview(index));
        }
        Assertions.assertNull(tetrominoQueue.getPreview(TetrominoQueue.BAG_SIZE * 2 + 1));
        final Tetromino y = tetrominoQueue.pick();
        Assertions.assertSame(x, y);
    }
}
