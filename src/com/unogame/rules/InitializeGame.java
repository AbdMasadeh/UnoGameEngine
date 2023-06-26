package com.unogame.rules;

import com.unogame.card.CardsRepository;
import com.unogame.player.PlayersRepository;

public interface InitializeGame {
    PlayersRepository createPlayersList();

    CardsRepository creatCardsStack();
}
