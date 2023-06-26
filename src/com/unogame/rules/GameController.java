package com.unogame.rules;

import com.unogame.card.Card;

public interface GameController {
    void shuffleCards();

    void removePlayersCards();

    void dealPlayersCards();

    void placeCardInTheMiddle();

    Card playCard(Card card);

    Card playerTurn();

    boolean matchingCards(Card card);

    Card noMatchingCards();
}
