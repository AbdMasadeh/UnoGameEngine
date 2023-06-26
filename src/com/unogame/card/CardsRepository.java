package com.unogame.card;

import java.util.Stack;

public class CardsRepository {
    private static CardsRepository instance;
    private Stack<Card> cardsPile;
    private Card cardInMiddle;

    public CardsRepository(Stack<Card> cardsPile) {
        this.cardsPile = cardsPile;
    }

    public Stack<Card> getCardsPile() {
        return cardsPile;
    }

    public void setCardsPile(Stack<Card> cardsPile) {
        this.cardsPile = cardsPile;
    }

    public Card popCard() {
        return cardsPile.pop();
    }

    public Card getCardInMiddle() {
        return cardInMiddle;
    }

    public void setCardInMiddle(Card cardInMiddle) {
        this.cardInMiddle = cardInMiddle;
    }
}
