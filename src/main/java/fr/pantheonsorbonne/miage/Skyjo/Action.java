package fr.pantheonsorbonne.miage.Skyjo;

public abstract class Action {

    public Action() {
    }

    public  abstract  boolean chooseDiscard();
    public  abstract  boolean chooseReplace(Card card);

    public static Card drawACardDeck() {
        Card cardDrawed = Deck.drawCard();
        return cardDrawed;
    }

    public static Card peekAtCardDiscard() {
        Card cardPeek = Discard.peekCard();
        return cardPeek;
    }

    public static Card drawACardDiscard() {
        Card cardDrawed = Discard.takeCard();
        return cardDrawed;
    }

    public static void discardACard(Card card){
        Discard.addCard(card);
    }

}
