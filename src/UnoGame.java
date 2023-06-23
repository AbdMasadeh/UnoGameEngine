import entity.Card;
import entity.Player;
import entity.CardColor;
import entity.CardEffect;
import entity.CardType;
import entityDTO.CardDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static mapper.CardMapper.mapToCardDTO;

class UnoGame extends Game {
    Scanner scanner = new Scanner(System.in);
    Card cardInTheMiddle;
    CardDTO cardInTheMiddleDTO;
    int currentPlayerTurn;
    boolean isClockWise;
    boolean isEnd = false;

    public UnoGame(List<Player> players) {
        super(players);
    }

    @Override
    public void skipEffect() {
        nextTurn();
    }

    @Override
    public void reverseEffect() {
        isClockWise = !isClockWise;
    }

    @Override
    public void drawTwoEffect() {
        nextTurn();
        for (int i = 0; i < 2; i++) {
            players.get(currentPlayerTurn).addOneCard(drawCardFromPile());
        }
    }

    @Override
    public void drawFourEffect() {
        changeColorEffect();
        nextTurn();
        for (int i = 0; i < 4; i++) {
            players.get(currentPlayerTurn).addOneCard(drawCardFromPile());
        }
    }

    @Override
    public void changeColorEffect() {
        System.out.print("Choose a color of the four: [");
        for (int i = 0; i < CardColor.values().length - 1; i++) {
            System.out.print(CardColor.values()[i].toString() + ", ");
        }
        System.out.print("]\nEnter index starting from 0 :");
        int colorIndex = scanner.nextInt();
        cardInTheMiddle.setColor(CardColor.values()[colorIndex]);
    }

    @Override
    public void effects(Card card) {
        if (card.getType() != CardType.NUMBER) {
            switch (card.getEffect()) {
                case DRAW_2 -> drawTwoEffect();
                case REVERSE -> reverseEffect();
                case SKIP -> skipEffect();
                case DRAW_4 -> drawFourEffect();
                case CHANGE_COLOR -> changeColorEffect();
            }
        }
    }

    @Override
    public void fillCardsPile() {
        for (int color = 0; color < 4; color++) {

            cardsPile.push(new Card(0, CardColor.values()[color], CardType.NUMBER, CardEffect.NONE));

            for (int i = 1; i <= 9; i++) {
                for (int j = 0; j < 2; j++) {
                    cardsPile.push(new Card(i, CardColor.values()[color], CardType.NUMBER, CardEffect.NONE));
                }
            }
            for (int j = 1; j < 4; j++) {
                for (int i = 0; i < 2; i++) {
                    cardsPile.push(new Card(20, CardColor.values()[color], CardType.ACTION, CardEffect.values()[j]));
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            if (i > 3) {
                cardsPile.push(new Card(50, CardColor.BLACK, CardType.WILD, CardEffect.values()[4]));
            } else {
                cardsPile.push(new Card(50, CardColor.BLACK, CardType.WILD, CardEffect.values()[5]));
            }
        }
    }

    @Override
    public void shuffleCards() {
        Collections.shuffle(cardsPile);
    }

    @Override
    public void givePlayersCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addOneCard(cardsPile.pop());
            }
        }
    }

    @Override
    public void drawTheFirstCard() {
        cardInTheMiddle = cardsPile.pop();
    }

    @Override
    public void setCardInTheMiddle(Card card) {
        cardInTheMiddle = card;
    }

    @Override
    public boolean matchingCards(Card card) {
        return card.getType() == CardType.WILD || card.getColor() == cardInTheMiddle.getColor() ||
                (card.getValue() == cardInTheMiddle.getValue() && card.getType() == CardType.NUMBER) ||
                (card.getEffect() == cardInTheMiddle.getEffect() && card.getType() == CardType.ACTION);
    }

    @Override
    public void playerTurn() {
        printCardInTheMiddleDTO();

        Player player = players.get(currentPlayerTurn);

        System.out.println("\nPlayer turn: Player " + player.getName());
        System.out.println("Number of player cards= " + player.getCards().size());
        printListOfCards(player.getCards(), "All player Cards:");
        System.out.println();

        List<Card> allowedCards = new ArrayList<>();

        for (Card card : player.getCards()) {
            if (matchingCards(card)) {
                allowedCards.add(card);
            }
        }
        if (allowedCards.size() > 0) {
            printListOfCards(allowedCards, "Cards allowed to play:");
            System.out.print("\nChoose a card by entering its index starting from 0 : ");

            int cardIndex = scanner.nextInt();
            //
            //MultiThreading
            //
            while (cardIndex < 0 || cardIndex > allowedCards.size() - 1) {
                System.out.println("Invalid Input\n Re-Enter your card index");
                cardIndex = scanner.nextInt();
            }
            playCard(allowedCards.get(cardIndex));
            player.removeOneCard(allowedCards.get(cardIndex));

            scanner.nextLine();

            if (player.getCards().isEmpty()) {
                scoreCounter(player);
                printEachPlayersCardsDTO();
                printPlayersScores();
                if (!isEnd) startNewGame();
            }

            System.out.println();
        } else {
            System.out.println("No Cards can be played. drawing a card...");

            Card extraCard = drawCardFromPile();
            player.addOneCard(extraCard);

            System.out.print("Card drawn: ");
            cardPrintAndMapToDTO(extraCard);
            System.out.println();

            if (matchingCards(extraCard)) {
                System.out.println("Card can be played, Do you want to play it?");
                System.out.print("Enter 0 -> false, 1 -> true : ");
                boolean isPlay = scanner.nextInt() == 1;
                System.out.println();
                if (isPlay) {
                    playCard(extraCard);
                    player.removeOneCard(extraCard);
                }
            } else {
                System.out.println("Card not allowed to be played, skipping turn...");
            }
        }
    }

    @Override
    public void playCard(Card card) {
        setCardInTheMiddle(card);
        effects(card);
    }

    @Override
    public void printPlayersScores() {
        System.out.println("\nPlayers Scores:");
        for (Player player: players) {
            System.out.printf("\nPlayer %s score = ",player.getName());
            System.out.println(player.getScore());
        }
        System.out.println();
    }

    @Override
    public void startNewGame() {
        System.out.println("\n\nStarting a new Uno game...\n");

        currentPlayerTurn = -1;
        isClockWise = true;
        removePlayersCards();
        fillCardsPile();
        shuffleCards();
        givePlayersCards();
        drawTheFirstCard();
    }

    @Override
    public void removePlayersCards() {
        for (Player player : players) {
            player.removeAllCards();
        }
    }

    @Override
    public void turnClockWise() {
        currentPlayerTurn++;
        if (currentPlayerTurn >= players.size()) {
            currentPlayerTurn = 0;
        }
    }

    @Override
    public void turnCounterClockWise() {
        currentPlayerTurn--;
        if (currentPlayerTurn < 0) {
            currentPlayerTurn = players.size() - 1;
        }
    }

    @Override
    public void scoreCounter(Player winPlayer) {
        System.out.printf("\n* * * Congratulation Player %s won! * * *", winPlayer.getName());
        for (Player player: players) {
            winPlayer.setScore(winPlayer.getScore() + player.leftCards());
        }
        if (winPlayer.getScore() >= 50) {
            isEnd = true;
        }
    }

    @Override
    public void EndGame() {
        System.out.printf("\nGame Ended\nPlayer %s won.\n", players.get(currentPlayerTurn).getName());
    }

    @Override
    public void nextTurn() {
        if (isClockWise) {
            turnClockWise();
        } else {
            turnCounterClockWise();
        }
    }

    @Override
    public Card drawCardFromPile() {
        return cardsPile.pop();
    }

    @Override
    public void play() {
        startNewGame();

        while (!isEnd){
            nextTurn();
            playerTurn();
        }
        EndGame();
        scanner.close();
    }


    //Here Are Test Methods
    private void printCardsPile() {
        System.out.println("size=" + cardsPile.size());
        for (Card card : cardsPile) {
            card.printCard();
        }
    }

    private void printCardsPileDTO() {
        System.out.println("size=" + cardsPile.size());
        for (Card card : cardsPile) {
            cardPrintAndMapToDTO(card);
        }
    }

    private void printEachPlayersCards() {
        for (Player player : players) {
            System.out.println();
            System.out.println(player.getName() + " Cards :");
            List<Card> cards = player.getCards();
            for (Card card : cards) {
                card.printCard();
            }
        }
    }

    private void printEachPlayersCardsDTO() {
        for (Player player : players) {
            System.out.println();
            System.out.println("Player " + player.getName() + " Cards :");
            List<Card> cards = player.getCards();
            for (Card card : cards) {
                cardPrintAndMapToDTO(card);
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    private void printCardInTheMiddle() {
        System.out.print("Card in the middle: ");
        cardInTheMiddle.printCard();
        System.out.println();
    }

    private void printCardInTheMiddleDTO() {
        System.out.print("Card in the middle: [");
        cardInTheMiddleDTO = mapToCardDTO(cardInTheMiddle);
        if (cardInTheMiddle.getType() == CardType.WILD) {
            cardInTheMiddleDTO.setSecondValue(cardInTheMiddle.getColor().toString());
        }
        cardInTheMiddleDTO.printCardDTO();
        System.out.println("]");
    }

    public void printListOfCards(List<Card> cards, String title) {
        System.out.print(title + " [ ");
        for (Card card : cards) {
            cardPrintAndMapToDTO(card);
            System.out.print(", ");
        }
        System.out.print("\b\b ]");
    }

    public void cardPrintAndMapToDTO(Card card) {
        CardDTO cardDTO = mapToCardDTO(card);
        System.out.print("[");
        cardDTO.printCardDTO();
        System.out.print("]");
    }
}