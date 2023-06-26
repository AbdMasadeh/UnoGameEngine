package com.unogame.rules.impl;


import com.unogame.card.Card;
import com.unogame.card.CardColor;
import com.unogame.exception.InvalidInputException;
import com.unogame.rules.CardsEffects;
import com.unogame.card.CardsRepository;
import com.unogame.rules.TurnStrategy;

import java.util.Scanner;

public class ClassicCardsEffect implements CardsEffects {
    TurnStrategy turnStrategy;
    CardsRepository cardsRepository;

    public ClassicCardsEffect(TurnStrategy turnStrategy, CardsRepository cardsRepository) {
        this.turnStrategy = turnStrategy;
        this.cardsRepository = cardsRepository;
    }

    @Override
    public void effects(Card card) {
        if (card != null) {
            switch (card.getEffect()) {
                case DRAW_2 -> drawTwoEffect();
                case REVERSE -> reverseEffect();
                case SKIP -> skipEffect();
                case DRAW_4 -> drawFourEffect();
                case CHANGE_COLOR -> changeColorEffect();
            }
        }
        turnStrategy.nextTurn();
    }

    @Override
    public void skipEffect() {
        turnStrategy.nextTurn();
    }

    @Override
    public void reverseEffect() {
        turnStrategy.turnDirection();
    }

    @Override
    public void drawTwoEffect() {
        turnStrategy.nextTurn();
        for (int i = 0; i < 2; i++) {
            turnStrategy.getPlayersRepository().getPlayer().addOneCard(cardsRepository.popCard());
        }
    }

    @Override
    public void drawFourEffect() {
        changeColorEffect();
        turnStrategy.nextTurn();
        for (int i = 0; i < 4; i++) {
            turnStrategy.getPlayersRepository().getPlayer().addOneCard(cardsRepository.popCard());
        }
    }

    @Override
    public void changeColorEffect() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose a color of the four: [");
        for (int i = 0; i < CardColor.values().length - 1; i++) {
            System.out.print(CardColor.values()[i].toString() + ", ");
        }
        System.out.print("]\nEnter index starting from 0 :");
        int colorIndex = scanner.nextInt();
        if(colorIndex < 0 || colorIndex > 3) throw new InvalidInputException("Invalid Input");
        cardsRepository.getCardInMiddle().setColor(CardColor.values()[colorIndex]);
    }

    @Override
    public void startNewGame() {
        turnStrategy.setDirection(true);
    }
}
