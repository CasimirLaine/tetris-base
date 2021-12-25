package com.laine.casimir.tetris.base.model;

public final class Position {

    private int x;
    private int y;

    Position() {
        this.x = 0;
        this.y = 0;
    }

    public boolean collides(int x, int y) {
        return this.x == x && this.y == y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        return getClass().isInstance(obj) && ((Position) obj).x == x && ((Position) obj).y == y;
    }
}
