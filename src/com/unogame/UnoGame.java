package com.unogame;

import com.unogame.card.Card;
import com.unogame.rules.*;
import com.unogame.rules.impl.*;

class UnoGame extends Game {
    InitializeGame initializeGame = new ClassicInitializeGame();
    TurnStrategy turnStrategy;
    CardsEffects cardsEffects;
    GameController gameController;
    WinStrategy winStrategy;

    public UnoGame() {
    }

    @Override
    public void initiateGame() {
        playersRepository = initializeGame.createPlayersList();
        cardsRepository = initializeGame.creatCardsStack();
    }

    @Override
    public void selectVariations() {
        turnStrategy = new ClassicTurnStrategy(playersRepository);
        cardsEffects = new ClassicCardsEffect(turnStrategy, cardsRepository);
        gameController = new ClassicGameController(playersRepository, cardsRepository);
        winStrategy = new ClassicWinStrategy(playersRepository);
    }

    @Override
    public void startNewGame() {
        System.out.println("\nStarting a new Uno game...");
        gameController.shuffleCards();
        gameController.removePlayersCards();
        gameController.dealPlayersCards();
        gameController.placeCardInTheMiddle();
        cardsEffects.startNewGame();
    }

    @Override
    public void playGame() {
        Card card;
        while (true) {
            card = gameController.playerTurn();
            if (winStrategy.oneRoundWin()) break;
            cardsEffects.effects(card);
        }
        winStrategy.scoreCounter();
        winStrategy.printPlayersScores();
    }

    @Override
    public void play() {
        initiateGame();
        selectVariations();

        while (winStrategy.gameWin()) {
            startNewGame();
            playGame();
        }
        winStrategy.endGame();
    }
}