package fr.pantheonsorbonne.miage.Skyjo;

public enum Value {
    MINUSTWO(-2),
    MINUSONE(-1),
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    TWELVE(12);

   

    private int value;
 
    private Value(int value) {
        this.value = value;
    }

    public int getCardValue() {
        return value;
    }
}