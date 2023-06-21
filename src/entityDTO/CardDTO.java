package entityDTO;

public class CardDTO {
    private final String firstValue;
    private final String secondValue;

    public CardDTO(String firstValue, String secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public void printCardDTO() {
        System.out.print(this.firstValue + ", " + this.secondValue);
    }
}