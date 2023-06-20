import java.util.ArrayList;
import java.util.List;

public class Test {
    public List<Card> cardsPile;

    public Test() {
        this.cardsPile = new ArrayList<>();
    }


    public void fillCardsPile() {


        for (int color = 0; color < 4; color++) {

            cardsPile.add(new Card(0, CardColor.values()[color], CardType.NUMBER, CardEffect.NONE));

            for (int i = 1; i <= 9; i++) {
                for (int j = 0; j < 2; j++) {
                    cardsPile.add(new Card(i, CardColor.values()[color], CardType.NUMBER, CardEffect.NONE));
                }
            }
            for (int j = 1; j < 4; j++) {
                for (int i = 0; i < 2; i++) {
                    cardsPile.add(new Card(20, CardColor.values()[color], CardType.ACTION, CardEffect.values()[j]));
                }
            }
        }

        for(int i=0; i < 8; i++) {
            if(i>3) {
                cardsPile.add(new Card(50, CardColor.BLACK, CardType.WILD, CardEffect.values()[4]));
            } else {
                cardsPile.add(new Card(50, CardColor.BLACK, CardType.WILD, CardEffect.values()[5]));
            }
        }
    }
}