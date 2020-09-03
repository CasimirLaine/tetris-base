package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.tool.HoldBox;
import com.laine.casimir.tetris.base.tool.TetrominoBag;

public class TetrisGame {

    private final HoldBox holdBox = new HoldBox();
    private TetrominoBag tetrominoBag;
    private Playfield playfield;
}
