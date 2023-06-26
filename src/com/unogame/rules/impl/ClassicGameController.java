package com.unogame.rules.impl;

import com.unogame.card.Card;
import com.unogame.card.CardType;
import com.unogame.card.CardsRepository;
import com.unogame.card.CardDTO;
import com.unogame.player.Player;
import com.unogame.player.PlayersRepository;
import com.unogame.rules.GameController;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

import static com.unogame.mapper.CardMapper.mapToCardDTO;

public class ClassicGameController implements GameController {
    Scanner scanner = new Scanner(System.in);
    PlayersRepository playersRepository;
    CardsRepository cardsRepository;

    public ClassicGameController(PlayersRepository playersRepository, CardsRepository cardsRepository) {
        this.playersRepository = playersRepository;
        this.cardsRepository = cardsRepository;
    }

    @Override
    public void shuffleCards() {
        Stack<Card> shuffledCards = cardsRepository.getCardsPile();
        Collections.shuffle(shuffledCards);
        cardsRepository.setCardsPile(shuffledCards);
    }

    @Override
    public void removePlayersCards() {
        for (Player player : playersRepository.getPlayers()) {
            player.removeAllCards();
        }
    }

    @Override
    public void dealPlayersCards() {
        for (int i = 0; i < 1; i++) {
            for (int index = 0; index < playersRepository.getPlayersListSize(); index++) {
                playersRepository.setPlayerIndex(index);
                playersRepository.getPlayer().addOneCard(cardsRepository.popCard());
            }
            playersRepository.setPlayerIndex(0);
        }
    }

    @Override
    public void placeCardInTheMiddle() {
        cardsRepository.setCardInMiddle(cardsRepository.popCard());
        while (cardsRepository.getCardInMiddle().getType() != CardType.NUMBER) {
            cardsRepository.setCardInMiddle(cardsRepository.popCard());
        }
    }

    @Override
    public boolean matchingCards(Card card) {
        return card.getType() == CardType.WILD || card.getColor() == cardsRepository.getCardInMiddle().getColor() ||
                (card.getValue() == cardsRepository.getCardInMiddle().getValue() && card.getType() == CardType.NUMBER) ||
                (card.getEffect() == cardsRepository.getCardInMiddle().getEffect() && card.getType() == CardType.ACTION);
    }

    @Override
    public Card noMatchingCards() {
        System.out.println("No Cards can be played. drawing a card...");
        Card extraCard = cardsRepository.popCard();
        playersRepository.getPlayer().addOneCard(extraCard);

        System.out.print("Card drawn: ");
        cardPrintAndMapToDTO(extraCard);
        System.out.println();

        if (matchingCards(extraCard)) {
            System.out.println("Card can be played, Do you want to play it?");
            System.out.print("Enter 0 -> false, 1 -> true : ");
            boolean isPlay = scanner.nextInt() == 1;
            scanner.nextLine();
            System.out.println();
            if (isPlay) {
                return playCard(extraCard);
            } else {
                return null;
            }
        } else {
            System.out.println("Card not allowed to be played, skipping turn...");
            return null;
        }
    }

    @Override
    public Card playCard(Card card) {
        cardsRepository.setCardInMiddle(card);
        playersRepository.getPlayer().removeOneCard(card);
        System.out.println();
        return card;
    }

    @Override
    public Card playerTurn() {
        viewCardInTheMiddleDTO();

        Player player = playersRepository.getPlayer();
        printPlayerTurnInfo(player);
        List<Card> allowedCards = allowedCardsList(player);

        if (allowedCards.size() > 0) {
            return playCard(chooseCard(allowedCards));
        } else {
            return noMatchingCards();
        }
    }


    private Card chooseCard(List<Card> allowedCards) {
        printListOfCards(allowedCards, "Cards allowed to play:");
        System.out.print("\nChoose a card by entering its index starting from 0 : ");
        int cardIndex = scanner.nextInt();
        scanner.nextLine();
        while (cardIndex < 0 || cardIndex > allowedCards.size() - 1) {
            System.out.println("Invalid Input\n Re-Enter your card index");
            cardIndex = scanner.nextInt();
        }
        scanner.nextLine();
        return allowedCards.get(cardIndex);
    }

    private List<Card> allowedCardsList(Player player) {
        List<Card> allowedCards = new ArrayList<>();
        for (Card card : player.getCards()) {
            if (matchingCards(card)) {
                allowedCards.add(card);
            }
        }
        return allowedCards;
    }

    private void printPlayerTurnInfo(Player player) {
        System.out.println("\nPlayer turn: Player " + player.getName());
        System.out.println("Number of player cards= " + player.getCards().size());
        printListOfCards(player.getCards(), "All player Cards:");
        System.out.println();
    }

    public void viewCardInTheMiddleDTO() {
        System.out.print("\nCard in the middle: [");
        CardDTO cardInTheMiddleDTO = mapToCardDTO(cardsRepository.getCardInMiddle());
        if (cardsRepository.getCardInMiddle().getType() == CardType.WILD) {
            cardInTheMiddleDTO.setSecondValue(cardsRepository.getCardInMiddle().getColor().toString());
        }
        cardInTheMiddleDTO.printCardDTO();
        System.out.println("]");
    }

    public void cardPrintAndMapToDTO(Card card) {
        CardDTO cardDTO = mapToCardDTO(card);
        System.out.print("[");
        cardDTO.printCardDTO();
        System.out.print("]");
    }

    public void printListOfCards(List<Card> cards, String title) {
        System.out.print(title + " [ ");
        for (Card card : cards) {
            cardPrintAndMapToDTO(card);
            System.out.print(", ");
        }
        System.out.print("\b\b ]");
    }
}
