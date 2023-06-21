import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *  In order to create a new game variation, ideally, a developer will only need to create
 *  a new class that extends your abstract Game class, and then do necessary implementation
 *  of abstract methods in order to create a Uno Game.
 *
 *  Because we want to minimize programming efforts for creating new games, your code
 * should include predefined set of game rules that developers can choose from when
 * creating their own game variations.
 *
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
    public abstract void drawTheFirstCard();
    public void drawOneCard() {
    }
    public abstract void givePlayersCards();
    /**
     *
     * 1. More than one turn
     * 2.
     *
     */
}