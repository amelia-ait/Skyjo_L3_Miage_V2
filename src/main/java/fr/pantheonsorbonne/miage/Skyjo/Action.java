package fr.pantheonsorbonne.miage.Skyjo;

public class Action {

    public boolean chooseDiscard() {
        Card peekDiscard = Discard.peekCard();
        return peekDiscard.getCardName().getCardValue() <= 3;

    }

    public Card drawACardDeck() {
        Card cardDrawed = Deck.drawCard();
        return cardDrawed;
    }

    public Card peekAtCardDiscard() {
        Card cardPeek = Discard.peekCard();
        return cardPeek;
    }

    public Card drawACardDiscard() {
        Card cardDrawed = Discard.takeCard();
        return cardDrawed;
    }

    public void discardACard(Card card) {
        Discard.addCard(card);
    }

    public boolean chooseReplace(Card card) {
        return (card.getCardName().getCardValue() < 8);
    }

}