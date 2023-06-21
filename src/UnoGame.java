import java.util.*;

class UnoGame extends Game {
    List<Player> playersCards = new ArrayList<>();
    Card cardInTheMiddle;

    public UnoGame(List<Player> players) {
        super(players);
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

        //printCardsPile

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
        //printEachPlayerCards();
    }

    @Override
    public void drawTheFirstCard() {
        cardInTheMiddle = cardsPile.pop();
    }

    @Override
    public void play() {
        System.out.println("\nStarting Uno game...");

        fillCardsPile();
        shuffleCards();
        printCardsPile();

        givePlayersCards();
        printEachPlayersCards();

        drawTheFirstCard();
        printCardInTheMiddle();

        System.out.println("number of cards: " + cardsPile.size());
    }


    //Here Are Test Methods
    private void printCardsPile() {
        System.out.println("size=" + cardsPile.size());
        for (Card card : cardsPile) {
            card.printCard();
        }
    }

    private void printEachPlayersCards() {
        for (Player player : players) {
            System.out.println();
            System.out.println(player.getName() + " Cards :");
            List<Card> cards = player.getCards();
            for(Card card : cards) {
                card.printCard();
            }
        }
    }

    private void printCardInTheMiddle() {
        System.out.println();
        cardInTheMiddle.printCard();
    }
}