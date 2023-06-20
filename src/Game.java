import java.util.ArrayList;
import java.util.List;

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

    private final List<String> players;
    protected List<String> cards;

    public Game(List<String> players) {
        this.players = players;
        this.cards = new ArrayList<>();
        initializeCards();
    }

    protected abstract void initializeCards();

    public abstract void play();

    protected void dealCards(int numCards) {
        for (String player : players) {
            System.out.printf("Dealing %d cards to %s\n", numCards, player);
            for (int i = 0; i < numCards; i++) {
                int randomIndex = (int) (Math.random() * cards.size());
                String card = cards.remove(randomIndex);
                System.out.printf("   %s: %s\n", player, card);
            }
        }
    }
}