package fr.pantheonsorbonne.miage.Skyjo;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private final Value cardName;

    public Card(Value cardName) {
        this.cardName = cardName;
    }

    public Value getCardName() {
        return this.cardName;
    }

    public static List<Card> getAllPossibleCards() {
        List<Card> possibleCards = new ArrayList<>(150);
        for (Value value : Value.values()) {
            if (value.getCardValue() == -2) {
                for (int i = 0; i < 5; i++) {
                    possibleCards.add(new Card(value));
                }
            }
            if (value.getCardValue() == 0) {
                for (int i = 0; i < 15; i++) {
                    possibleCards.add(new Card(value));
                }
            } 
            if (value.getCardValue()!=-2 && value.getCardValue()!=0) {
                for (int i = 0; i < 10; i++) {
                    possibleCards.add(new Card(value));
                }
            }
        }
        return possibleCards;
    }


    public static String toString(Card card){
       return ""+card.getCardName().getCardValue();
    }
}
