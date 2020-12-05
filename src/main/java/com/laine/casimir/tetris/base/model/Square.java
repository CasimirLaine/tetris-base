package com.laine.casimir.tetris.base.model;

public final class Square {

    private final Position position = new Position();

    private String colorHex;

    public Square(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Position getPosition() {
        return position;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }
}
