package com.unogame;

import com.unogame.card.CardsRepository;
import com.unogame.player.PlayersRepository;

abstract public class Game {
    protected PlayersRepository playersRepository;
    protected CardsRepository cardsRepository;

    public Game() {
    }

    public abstract void initiateGame();

    public abstract void selectVariations();

    public abstract void startNewGame();

    public abstract void playGame();

    public abstract void play();
}