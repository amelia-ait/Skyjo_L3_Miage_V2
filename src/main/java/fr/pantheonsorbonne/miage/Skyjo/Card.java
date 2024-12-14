package fr.pantheonsorbonne.miage.Skyjo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card {
    protected final Value cardName;
    private static HashMap<Value, Integer> nbCard = new HashMap<>();;

    public Card(Value cardName) {
        this.cardName = cardName;

    }

    public Value getCardName() { // surcharger dans hidden card FAIT
        return this.cardName;
    }

    public static List<Card> getAllPossibleCards() { // utiliser une hashMap FAIT
        nbCard.put(Value.MINUSTWO, 5);
        nbCard.put(Value.ZERO, 15);
        List<Card> possibleCards = new ArrayList<>(150);
        for (Map.Entry<Value, Integer> entry : nbCard.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                possibleCards.add(new Card(entry.getKey()));
            }
        }

        for (Value values : Value.values()) {
            if (values.getCardValue() != -2 && values.getCardValue() != 0 ) {
                for (int i = 0; i < 10; i++) {
                    possibleCards.add(new Card(values));
                }
            }
        }
        return possibleCards;
    }

    @Override
    public String toString() {
        return "" + this.getCardName().getCardValue();
    }

}
