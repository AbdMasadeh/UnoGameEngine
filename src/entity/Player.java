package entity;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private List<Card> cards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addOneCard(Card card) {
        this.cards.add(card);
    }

    public void removeOneCard(Card card) {
        this.cards.remove(card);
    }
}
