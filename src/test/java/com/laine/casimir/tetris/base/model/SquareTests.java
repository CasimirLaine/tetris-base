package com.laine.casimir.tetris.base.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquareTests {

    private static final int INIT_X = 0;
    private static final int INIT_Y = 0;

    private Square square;

    @BeforeEach
    void init() {
        square = new Square(INIT_X, INIT_Y);
    }

    @Test
    void testGetX() {
        Assertions.assertEquals(square.getX(), INIT_X);
    }

    @Test
    void testGetY() {
        Assertions.assertEquals(square.getY(), INIT_Y);
    }

    @Test
    void testPosition() {
        Assertions.assertNotNull(square.getPosition());
        Assertions.assertSame(square.getPosition(), square.getPosition());
    }

    @Test
    void testColorHex() {
        Assertions.assertNull(square.getColorHex());
        final String testColor = "#000000";
        square.setColorHex(testColor);
        Assertions.assertEquals(square.getColorHex(), testColor);
    }
}
