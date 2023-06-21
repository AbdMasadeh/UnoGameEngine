import entity.Card;
import entity.Player;

import java.util.List;
import java.util.Stack;

/**
 * In order to create a new game variation, ideally, a developer will only need to create
 * a new class that extends your abstract Game class, and then do necessary implementation
 * of abstract methods in order to create a Uno Game.
 * Because we want to minimize programming efforts for creating new games, your code
 * should include predefined set of game rules that developers can choose from when
 * creating their own game variations.
 * Inside the Game class, there must be a method called play, which simulates the game.
 * Surely, you can add other methods as well.
 */
abstract public class Game {
    protected List<Player> players;
    protected Stack<Card> cardsPile;

    public Game(List<Player> players) {
        this.players = players;
        this.cardsPile = new Stack<>();
    }

    public abstract void play();

    public abstract void fillCardsPile();

    public abstract void shuffleCards();

    public abstract void givePlayersCards();

    public abstract void drawTheFirstCard();

    public abstract void playerTurn();

    public abstract void printListOfCards(List<Card> cards, String title);

    public abstract boolean matchingCards(Card card);
    public abstract void nextTurn();

    public abstract void turnClockWise();

    public abstract void turnCounterClockWise();
    public abstract void effects(Card card);

    public abstract Card drawCardFromPile();

    public abstract void skipEffect();

    public abstract void reverseEffect();

    public abstract void drawTwoEffect();

    public abstract void drawFourEffect();

    public abstract void changeColorEffect();
}