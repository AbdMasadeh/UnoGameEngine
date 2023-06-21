package mapper;

import entity.Card;
import entity.CardType;
import entityDTO.CardDTO;

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