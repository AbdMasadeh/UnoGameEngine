package com.unogame.card;

public class CardDTO {
    private final String firstValue;
    private String secondValue;

    public CardDTO(String firstValue, String secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public void printCardDTO() {
        System.out.print("[" + this.firstValue + ", " + this.secondValue + "]");
    }
}