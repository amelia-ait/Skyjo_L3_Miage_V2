package fr.pantheonsorbonne.miage.Skyjo;

public class DumbAction extends Action {

    @Override
    public  boolean chooseDiscard() {
       return Action.peekAtCardDiscard().getCardName().getCardValue()<=3;
    }

    @Override
    public  boolean chooseReplace(Card card) {
        return card.getCardName().getCardValue()>=8;
    }
    
}
