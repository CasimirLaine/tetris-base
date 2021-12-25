package com.laine.casimir.tetris.base.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionTests {

    private Position position;

    @BeforeEach
    void init() {
        position = new Position();
    }

    @Test
    void testCreate() {
        Assertions.assertEquals(position.getX(), 0);
        Assertions.assertEquals(position.getY(), 0);
    }

    @Test
    void testSetX() {
        final int x = 1;
        position.setX(x);
        Assertions.assertEquals(position.getX(), x);
    }

    @Test
    void testSetY() {
        final int y = 2;
        position.setY(y);
        Assertions.assertEquals(position.getY(), y);
    }

    @Test
    void testCollides() {
        final int x = 1;
        final int y = 2;
        Assertions.assertFalse(position.collides(x, y));
        position.setX(x);
        position.setY(y);
        Assertions.assertTrue(position.collides(x, y));
    }

    @Test
    void testEquals() {
        final Position otherPosition = new Position();
        final int x = 1;
        final int y = 2;
        otherPosition.setX(x);
        otherPosition.setY(y);
        Assertions.assertFalse(position.equals(otherPosition));
        position.setX(x);
        position.setY(y);
        Assertions.assertTrue(position.equals(otherPosition));
    }
}
