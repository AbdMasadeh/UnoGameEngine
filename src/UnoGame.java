import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class UnoGame extends Game {
    private List<String> gameRules;

    public UnoGame(List<String> players, List<String> gameRules) {
        super(players);
        this.gameRules = gameRules;
    }

    @Override
    protected void initializeCards() {
        cards.addAll(Arrays.asList("Red 1", "Red 2", "Red 3"));
        Collections.shuffle(cards);
    }

    @Override
    public void play() {
        System.out.println("Starting Uno game with rules: " + gameRules);
        dealCards(7);
        // Implement the game logic here
    }
}