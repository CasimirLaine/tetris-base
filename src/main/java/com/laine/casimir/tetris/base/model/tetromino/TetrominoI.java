package com.laine.casimir.tetris.base.model.tetromino;

import com.laine.casimir.tetris.base.model.Position;

public class TetrominoI extends AbstractTetromino {

    @Override
    public Position getKick(int kick) {
        switch (kick) {
            case 1:
                switch (rotationIndex) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        return new Position(0, 0);
                }
            case 2:
                switch (rotationIndex) {
                    case 0:
                        return new Position(-2, 0);
                    case 1:
                        return new Position(-1, 0);
                    case 2:
                        return new Position(2, 0);
                    case 3:
                        return new Position(1, 0);
                }
            case 3:
                switch (rotationIndex) {
                    case 0:
                        return new Position(1, 0);
                    case 1:
                        return new Position(2, 0);
                    case 2:
                        return new Position(-1, 0);
                    case 3:
                        return new Position(-2, 0);
                }
            case 4:
                switch (rotationIndex) {
                    case 0:
                        return new Position(-2, 1);
                    case 1:
                        return new Position(-1, -2);
                    case 2:
                        return new Position(2, -1);
                    case 3:
                        return new Position(1, 2);
                }
            case 5:
                switch (rotationIndex) {
                    case 0:
                        return new Position(1, -2);
                    case 1:
                        return new Position(2, 1);
                    case 2:
                        return new Position(-1, 2);
                    case 3:
                        return new Position(-2, -1);
                }
        }
        return null;
    }

    @Override
    public String getColorHex() {
        return "#00FFFF";
    }

    @Override
    public double getSpawnLocation() {
        return 0.5;
    }

    @Override
    public Position getRotationPoint() {
        return new Position(1, 0);
    }

    @Override
    protected int[] getSquareCoordinates() {
        return new int[]{
                0, 0,
                1, 0,
                2, 0,
                3, 0
        };
    }
}
