package com.unogame.rules;

import com.unogame.player.PlayersRepository;

public interface TurnStrategy {
    void nextTurn();

    void turnDirection();

    void setDirection(boolean bool);

    PlayersRepository getPlayersRepository();
}
