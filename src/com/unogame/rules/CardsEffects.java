package com.unogame.rules;

import com.unogame.card.Card;

public interface CardsEffects {

    void effects(Card card);

    void skipEffect();

    void reverseEffect();

    void drawTwoEffect();

    void drawFourEffect();

    void changeColorEffect();

    void startNewGame();
}
