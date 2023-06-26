package com.unogame.rules.impl;

import com.unogame.card.*;
import com.unogame.player.Player;
import com.unogame.player.PlayersRepository;
import com.unogame.rules.InitializeGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ClassicInitializeGame implements InitializeGame {
    @Override
    public PlayersRepository createPlayersList() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        if (numPlayers < 2) throw new RuntimeException("Uno is played with at least 2 players");
        if (numPlayers > 10) throw new RuntimeException("Uno is played with at most 10 players");
        scanner.nextLine();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.printf("Enter the name of player %d: ", i + 1);
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
        }
        return new PlayersRepository(players);
    }

    @Override
    public CardsRepository creatCardsStack() {
        Stack<Card> cardsPile = new Stack<>();

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
        return new CardsRepository(cardsPile);
    }
}
