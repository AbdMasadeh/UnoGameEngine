import java.util.*;

/**
 * that contains the main method. In the main method, you should only write two lines of code.
 * The first line is to instantiate a game object. The second line is to invoke the play method.
 */

public class GameDriver {
    public static void main(String[] args) {
        Test test = new Test();

        test.fillCardsPile();

//        Collections.shuffle(test.cardsPile);
        System.out.println("size=" + test.cardsPile.size());
        for(int i=0; i < test.cardsPile.size(); i++) {
            System.out.println(test.cardsPile.get(i).getValue()+ " "+test.cardsPile.get(i).getColor()+ " "+test.cardsPile.get(i).getType()+ " "+test.cardsPile.get(i).getEffect());
        }
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter the number of players: ");
//        int numPlayers = scanner.nextInt();
//        scanner.nextLine();
//
//        List<String> players = new ArrayList<>();
//        for (int i = 0; i < numPlayers; i++) {
//            System.out.printf("Enter the name of player %d: ", i + 1);
//            String playerName = scanner.nextLine();
//            players.add(playerName);
//        }
//
//        List<String> gameRules = Arrays.asList("Rule 1", "Rule 2", "Rule 3"); // Predefined game rules
//
//        Game unoGame = new UnoGame(players, gameRules);
//        unoGame.play();
//
//        scanner.close();
    }
}
