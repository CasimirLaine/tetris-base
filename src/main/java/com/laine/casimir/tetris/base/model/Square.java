package com.laine.casimir.tetris.base.model;

import com.laine.casimir.tetris.base.api.model.TetrisCell;

public final class Square implements TetrisCell {

    private final Position position = new Position();

    private String colorHex;

    public Square(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public int getX() {
        return position.getX();
    }

    @Override
    public int getY() {
        return position.getY();
    }

    @Override
    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }
}
