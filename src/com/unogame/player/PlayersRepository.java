package com.unogame.player;

import java.util.List;

public class PlayersRepository {
    private int playerIndex;
    private final List<Player> players;

    public PlayersRepository(List<Player> players) {
        this.players = players;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer() {
        return players.get(playerIndex);
    }

    public void setPlayerIndex(int playerIndex) {
        if (playerIndex > players.size() || playerIndex < 0) {
            throw new RuntimeException("Incorrect Index");
        } else {
            this.playerIndex = playerIndex;
        }
    }

    public int getPlayersListSize() {
        return players.size();
    }
}
