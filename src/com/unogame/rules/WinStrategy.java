package com.unogame.rules;

public interface WinStrategy {

    boolean oneRoundWin();

    void scoreCounter();

    void printPlayersScores();

    boolean gameWin();

    void endGame();
}
