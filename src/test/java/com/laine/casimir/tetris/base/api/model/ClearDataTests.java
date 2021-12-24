package com.laine.casimir.tetris.base.api.model;

import com.laine.casimir.tetris.base.model.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ClearDataTests {

    @Test
    void testCreateNull() {
        new ClearData(null);
    }

    @Test
    void testCreateEmpty() {
        new ClearData(new ArrayList<>());
    }

    @Test
    void testNotSame() {
        final List<TetrisCell> tetrisCellList = new ArrayList<>();
        final ClearData clearData = new ClearData(tetrisCellList);
        Assertions.assertNotSame(clearData.getClearedTetrisCells(), tetrisCellList);
    }

    @Test
    void testSameSizeItems() {
        final List<TetrisCell> tetrisCellList = new ArrayList<>();
        final Square x = new Square(0, 0);
        final Square y = new Square(1, 1);
        tetrisCellList.add(x);
        tetrisCellList.add(y);
        final ClearData clearData = new ClearData(tetrisCellList);
        Assertions.assertSame(clearData.getClearedTetrisCells().get(0), x);
        Assertions.assertSame(clearData.getClearedTetrisCells().get(1), y);
    }
}
