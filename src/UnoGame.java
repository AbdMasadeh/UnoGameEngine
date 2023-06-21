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
    Card cardInTheMiddle;
    CardDTO cardInTheMiddleDTO;
    int currentPlayerTurn = 0;
    boolean isClockWise = true;

    Scanner scanner = new Scanner(System.in);

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
        for(int i=0; i < 2; i++) {
            players.get(currentPlayerTurn).addOneCard(drawCardFromPile());
        }
    }

    @Override
    public void drawFourEffect() {
        nextTurn();
        for(int i=0; i < 4; i++) {
            players.get(currentPlayerTurn).addOneCard(drawCardFromPile());
        }
    }

    @Override
    public void changeColorEffect() {

    }

    @Override
    public void effects(Card card) {
        if (card.getType() != CardType.NUMBER ) {
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
        for (int i = 0; i < 7; i++) {
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
    public void printListOfCards(List<Card> cards, String title) {
        System.out.print(title + " [ ");
        for (Card card : cards) {
            CardDTO cardDTO = mapToCardDTO(card);
            System.out.print("[");
            cardDTO.printCardDTO();
            System.out.print("], ");
        }
        System.out.println(" ]");
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
        System.out.println("Number of players cards= "+ player.getCards().size());

        printListOfCards(player.getCards(), "All player Cards:");

        List<Card> allowedCards = new ArrayList<>();

        for (Card card : player.getCards()) {
            if (matchingCards(card)) {
                allowedCards.add(card);
            }
        }
        if (allowedCards.size() > 0) {
            printListOfCards(allowedCards, "allowed cards:");
            System.out.print("Choose a card by entering its index: ");
            int cardIndex = scanner.nextInt();
            while (cardIndex < 0 || cardIndex > allowedCards.size() - 1) {
                System.out.println("Invalid Input\n Re-Enter your card index");
                cardIndex = scanner.nextInt();
            }
            effects(allowedCards.get(cardIndex));
            cardInTheMiddle = allowedCards.get(cardIndex);
            player.removeOneCard(allowedCards.get(cardIndex));

            scanner.nextLine();
            System.out.println();
        } else {
            System.out.println("No Card can be played. drawing a card...");
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
        System.out.println("\nStarting Uno game...");

        fillCardsPile();
        shuffleCards();
//        printCardsPileDTO();

        givePlayersCards();
        printEachPlayersCardsDTO();

        drawTheFirstCard();

//        System.out.println("number of cards: " + cardsPile.size());

        for (int i = 0; i < 20; i++) {
            playerTurn();
            nextTurn();
        }
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
            CardDTO cardDTO = mapToCardDTO(card);
            System.out.print("[");
            cardDTO.printCardDTO();
            System.out.println("], ");
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
                CardDTO cardDTO = mapToCardDTO(card);
                System.out.print("[");
                cardDTO.printCardDTO();
                System.out.print("], ");
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
        cardInTheMiddleDTO.printCardDTO();
        System.out.println("]");
    }
}