package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.Tetromino;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TetrominoBagTests {

    private TetrominoBag tetrominoBag;

    @BeforeEach
    void init() {
        tetrominoBag = new TetrominoBag();
    }

    @Test
    void testFill() {
        tetrominoBag.fill();
        final int size = tetrominoBag.size();
        Assertions.assertEquals(size, TetrominoQueue.BAG_SIZE);
        tetrominoBag.fill();
        Assertions.assertEquals(size, tetrominoBag.size());
    }

    @Test
    void testPick() {
        final Tetromino tetromino = tetrominoBag.pick();
        Assertions.assertNull(tetromino);
        tetrominoBag.fill();
        for (int index = 0; index < TetrominoQueue.BAG_SIZE; index++) {
            Assertions.assertNotNull(tetrominoBag.pick());
        }
        Assertions.assertNull(tetrominoBag.pick());
    }

    @Test
    void testPreview() {
        Assertions.assertNull(tetrominoBag.getPreview(-1));
        Assertions.assertNull(tetrominoBag.getPreview(0));
        Assertions.assertNull(tetrominoBag.getPreview(1));
        tetrominoBag.fill();
        final Tetromino tetromino = tetrominoBag.getPreview(0);
        Assertions.assertEquals(tetromino, tetrominoBag.getPreview(0));
        Assertions.assertNotNull(tetrominoBag.getPreview(TetrominoQueue.BAG_SIZE - 1));
        Assertions.assertNull(tetrominoBag.getPreview(TetrominoQueue.BAG_SIZE));
    }

    @Test
    void testSize() {
        Assertions.assertEquals(tetrominoBag.size(), 0);
        tetrominoBag.fill();
        Assertions.assertEquals(tetrominoBag.size(), TetrominoQueue.BAG_SIZE);
        tetrominoBag.fill();
        Assertions.assertEquals(tetrominoBag.size(), TetrominoQueue.BAG_SIZE);
    }

    @Test
    void testEmpty() {
        Assertions.assertTrue(tetrominoBag.isEmpty());
        tetrominoBag.fill();
        Assertions.assertFalse(tetrominoBag.isEmpty());
    }
}
