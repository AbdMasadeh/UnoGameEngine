package entity;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards = new ArrayList<>();
    private int score = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addOneCard(Card card) {
        this.cards.add(card);
    }

    public void removeOneCard(Card card) {
        this.cards.remove(card);
    }

    public void removeAllCards() {
        this.cards.clear();
    }

    public int leftCards() {
        int cardsLeft = 0;
        for (Card card : cards) {
            cardsLeft += card.getValue();
        }
        return cardsLeft;
    }
}
