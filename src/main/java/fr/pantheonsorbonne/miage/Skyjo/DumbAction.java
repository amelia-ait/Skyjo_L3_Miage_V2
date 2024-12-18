package fr.pantheonsorbonne.miage.Skyjo;

public class DumbAction extends Action {

    @Override
    public  boolean chooseDiscard() {
       return Discard.peekCard().getCardName().getCardValue()<=3;
    }

    @Override
    public  boolean chooseReplace(Card card) {
        return card.getCardName().getCardValue()>=8;
    }
    
}
