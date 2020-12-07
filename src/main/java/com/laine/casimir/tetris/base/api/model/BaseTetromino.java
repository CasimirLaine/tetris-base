package com.laine.casimir.tetris.base.api.model;

import java.util.List;

public interface BaseTetromino {

    List<TetrisCell> getTetrisCells();

    int getDimension();
}
