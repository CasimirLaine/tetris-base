package com.laine.casimir.tetris.base.tool;

import com.laine.casimir.tetris.base.model.Tetromino;

public final class TetrominoQueue {

    public static final int BAG_SIZE = 7;

    private TetrominoBag currentTetrominoBag;
    private TetrominoBag nextTetrominoBag;

    public TetrominoQueue() {
        nextBag();
        nextBag();
    }

    public Tetromino pick() {
        if (currentTetrominoBag.isEmpty()) {
            nextBag();
        }
        return currentTetrominoBag.pick();
    }

    public Tetromino getPreview(int index) {
        if (index < 0) {
            return null;
        }
        if (currentTetrominoBag.isEmpty()) {
            nextBag();
        }
        if (index < currentTetrominoBag.size()) {
            return currentTetrominoBag.getPreview(index);
        }
        if (index - currentTetrominoBag.size() < nextTetrominoBag.size()) {
            return nextTetrominoBag.getPreview(index - currentTetrominoBag.size());
        }
        return null;
    }

    private void nextBag() {
        currentTetrominoBag = nextTetrominoBag;
        nextTetrominoBag = new TetrominoBag();
        nextTetrominoBag.fill();
    }
}
