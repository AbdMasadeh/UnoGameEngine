package com.unogame.mapper;

import com.unogame.card.Card;
import com.unogame.card.CardType;
import com.unogame.card.CardDTO;

public class CardMapper {
    public static CardDTO mapToCardDTO(Card card) {
        CardDTO cardDTO;
        if (card.getType() == CardType.WILD) {
            cardDTO = new CardDTO(card.getEffect().toString(), "All colors");
        } else if (card.getType() == CardType.ACTION) {
            cardDTO = new CardDTO(card.getEffect().toString(), card.getColor().toString());
        } else {
            cardDTO = new CardDTO(Integer.toString(card.getValue()), card.getColor().toString());
        }
        return cardDTO;
    }
}