package com.laine.casimir.tetris.base.event;

import com.laine.casimir.tetris.base.model.TetrisGame;

import java.util.Timer;

public class TetrisTimer {

    private final Timer timer = new Timer();

    private final TetrisGame tetrisGame;

    public TetrisTimer(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
    }

    public void start() {
    }

    public void stop() {
    }
}
