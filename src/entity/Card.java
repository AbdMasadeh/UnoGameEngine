package entity;

public class Card {
    private final int value;
    private final CardColor color;
    private final CardType type;
    private final CardEffect effect;

    public Card(int value, CardColor color, CardType type, CardEffect effect) {
        this.value = value;
        this.color = color;
        this.type = type;
        this.effect = effect;
    }

    public int getValue() {
        return value;
    }

    public CardColor getColor() {
        return color;
    }

    public CardType getType() {
        return type;
    }

    public CardEffect getEffect() {
        return effect;
    }

    public void printCard() {
        System.out.println(this.value + " " + this.color + " " + this.type + " " + this.effect);
    }
}