package fr.pantheonsorbonne.miage.Skyjo;

//public abstract class Action {
public class Action {// ATTENTION : Changement, abstract retiré !!!!!!

    public Action() { 
    }

    public boolean chooseDiscard() {
        Card peekDiscard = Discard.peekCard();
        return peekDiscard.getCardName().getCardValue() <= 3;//ca sera dans if, donc si c true elle prend la carte du discard, et apres on lui fera piocher

    }

// ATTENTION : TOUS LES STATICS SONT ENELEVER A PARTIR DE LA

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

    public void discardACard(Card card){
        Discard.addCard(card);
    }
// ATTENTION : JUSQU'ICI

    public boolean chooseReplace(Card card) {
        return (card.getCardName().getCardValue() < 8);
    }

}