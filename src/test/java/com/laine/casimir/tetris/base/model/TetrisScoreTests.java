package com.laine.casimir.tetris.base.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TetrisScoreTests {

    private TetrisScore tetrisScore;

    @BeforeEach
    void init() {
        tetrisScore = new TetrisScore();
    }

    @Test
    void testScore() {
        Assertions.assertEquals(0, tetrisScore.getScore());
        tetrisScore.addScore(-1);
        Assertions.assertEquals(0, tetrisScore.getScore());
        final int score = 1;
        final int n = 100;
        for (int index = 0; index < n; index++) {
            tetrisScore.addScore(score);
            Assertions.assertEquals(tetrisScore.getScore(), score * (index + 1));
        }
        Assertions.assertEquals(tetrisScore.getScore(), score * n);
    }

    @Test
    void testLevel() {
        Assertions.assertEquals(TetrisScore.START_LEVEL, tetrisScore.getLevel());
        final int n = TetrisScore.LINES_PER_LEVEL_MULTIPLIER;
        for (int index = 0; index < n; index++) {
            tetrisScore.processLines(1, false);
        }
        Assertions.assertEquals(
                TetrisScore.START_LEVEL + 1,
                tetrisScore.getLevel()
        );
    }

    @Test
    void testLinesCleared() {
        Assertions.assertEquals(0, tetrisScore.getLinesCleared());
        final int n = 100;
        for (int index = 0; index < n; index++) {
            tetrisScore.processLines(1, false);
            Assertions.assertEquals(
                    index + 1,
                    tetrisScore.getLinesCleared()
            );
        }
    }

    @Test
    void testTSpin() {
        Assertions.assertEquals(0, tetrisScore.getScore());
        tetrisScore.processLines(1, false);
        final int noSpin = tetrisScore.getScore();
        tetrisScore.processLines(1, true);
        final int spin = tetrisScore.getScore() - noSpin;
        Assertions.assertTrue(spin > noSpin);
    }

    @Test
    void testProcessIllegalLines() {
        tetrisScore.processLines(-1, false);
        Assertions.assertEquals(0, tetrisScore.getScore());
        tetrisScore.processLines(TetrisScore.MAX_LINES + 1, false);
        Assertions.assertEquals(0, tetrisScore.getScore());
    }

    @Test
    void testZeroLines() {
        tetrisScore.processLines(0, false);
        Assertions.assertEquals(0, tetrisScore.getScore());
    }

    @Test
    void testLegalLines() {
        int previousScore = tetrisScore.getScore();
        for (int index = 0; index < TetrisScore.MAX_LINES; index++) {
            tetrisScore.processLines(index + 1, false);
            Assertions.assertTrue(previousScore < tetrisScore.getScore());
            previousScore = tetrisScore.getScore();
        }
    }

    @Test
    void testLegalLinesTSpin() {
        int previousScore = tetrisScore.getScore();
        for (int index = 0; index < TetrisScore.MAX_LINES; index++) {
            tetrisScore.processLines(index + 1, true);
            Assertions.assertTrue(previousScore < tetrisScore.getScore());
            previousScore = tetrisScore.getScore();
        }
    }
}
