public class Card {
    private int value;
    private CardColor color;
    private CardType type;
    private CardEffect effect;

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

    public void setValue(int value) {
        this.value = value;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public void setEffect(CardEffect effect) {
        this.effect = effect;
    }
}