import entity.Player;

import java.util.*;

/**
 * that contains the main method. In the main method, you should only write two lines of code.
 * The first line is to instantiate a game object. The second line is to invoke the play method.
 */

public class GameDriver {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        if(numPlayers < 2) throw new RuntimeException("Uno is played with at least 2 players");
        if(numPlayers > 10) throw new RuntimeException("Uno is played with at most 10 players");
        scanner.nextLine();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.printf("Enter the name of player %d: ", i + 1);
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
        }

        Game unoGame = new UnoGame(players);
        unoGame.play();

        scanner.close();
    }
}