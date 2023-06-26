package com.unogame.rules.impl;

import com.unogame.card.Card;
import com.unogame.player.Player;
import com.unogame.player.PlayersRepository;
import com.unogame.rules.WinStrategy;

public class ClassicWinStrategy implements WinStrategy {
    PlayersRepository playersRepository;

    public ClassicWinStrategy(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @Override
    public boolean oneRoundWin() {
        return playersRepository.getPlayer().getCards().isEmpty();
    }

    @Override
    public void scoreCounter() {
        System.out.printf("\n* * * Congratulation Player %s won! * * *\n", playersRepository.getPlayer().getName());
        for (Player player : playersRepository.getPlayers()) {
            playersRepository.getPlayer().setScore(playersRepository.getPlayer().getScore() +
                    player.remainingCards());
        }
    }

    @Override
    public void printPlayersScores() {
        printPlayersCards();
        System.out.println("\nPlayers Scores:");
        for (Player player : playersRepository.getPlayers()) {
            System.out.printf("\nPlayer %s score = ", player.getName());
            System.out.println(player.getScore());
        }
        System.out.println();
    }

    @Override
    public boolean gameWin() {
        return playersRepository.getPlayer().getScore() < 500;
    }

    @Override
    public void endGame() {
        System.out.printf("\nGame Ended.\nPlayer %s won.\n", playersRepository.getPlayer().getName());

    }

    public void printPlayersCards() {
        System.out.println("\nPlayers Cards: ");
        for (Player player : playersRepository.getPlayers()) {
            System.out.printf("Player %s : [ ", player.getName());
            for (Card card : player.getCards()) {
                card.printCardDTO();
                System.out.print(", ");
            }
            System.out.println(" ]");
        }

    }
}
