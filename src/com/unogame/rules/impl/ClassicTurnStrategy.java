package com.unogame.rules.impl;

import com.unogame.player.PlayersRepository;
import com.unogame.rules.TurnStrategy;

public class ClassicTurnStrategy implements TurnStrategy {
    PlayersRepository playersRepository;
    private boolean isClockwise = true;

    public ClassicTurnStrategy(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @Override
    public PlayersRepository getPlayersRepository() {
        return playersRepository;
    }

    @Override
    public void setDirection(boolean clockwise) {
        isClockwise = true;
    }

    @Override
    public void nextTurn() {
        if (isClockwise) {
            turnClockwise();
        } else {
            turnCounterClockwise();
        }
    }

    @Override
    public void turnDirection() {
        isClockwise = !isClockwise;
    }

    public void turnClockwise() {
        if (playersRepository.getPlayerIndex() + 1 >= playersRepository.getPlayersListSize()) {
            playersRepository.setPlayerIndex(0);
        } else {
            playersRepository.setPlayerIndex(playersRepository.getPlayerIndex() + 1);
        }
    }

    public void turnCounterClockwise() {
        if (playersRepository.getPlayerIndex() - 1 < 0) {
            playersRepository.setPlayerIndex(playersRepository.getPlayersListSize() - 1);
        } else {
            playersRepository.setPlayerIndex(playersRepository.getPlayerIndex() - 1);
        }
    }
}
