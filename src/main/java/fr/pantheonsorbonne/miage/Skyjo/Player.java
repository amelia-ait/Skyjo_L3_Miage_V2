package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Random;

public abstract class  Player {
    private Card[][] cards;
    private String name;
    private Action action;
    private final static int nbLigne =3;
    private final static int nbColonne =4;
    public Player(String name) {
        this.name = name;
        this.cards = new Card[nbLigne][nbColonne];
        this.action = new Action(); //On devrait pouvoir initialiser une action 
        //mais la classe est abstracte pour une raison inconnue      
        } 
    public String getPlayerName() {
        return this.name;
    }

    public Card[][] getPlayerCards() {
        return this.cards;
    }

    public abstract void toPlay(); // liée à dumbPlayer

    public void setPlayerCards(HiddenCard[][] hiddenCards) {
        for (int i = 0; i < hiddenCards.length; i++) {
            for (int j = 0; j < hiddenCards[i].length; j++) {
                this.cards[i][j] = hiddenCards[i][j];
            }
        }
    }

    public void setCardVisible(int x, int y) { //set carte visible
        if (this.cards[x][y] instanceof HiddenCard) {
            HiddenCard hiddenCard = (HiddenCard) this.cards[x][y];
            Card revealedCard = new Card(hiddenCard.getCardName());
            this.cards[x][y] = revealedCard;
        }
    }
    public void setAllCardVisible() {
        Card[][] playerCards = this.getPlayerCards(); 
        for (int i = 0; i < playerCards.length; i++) {
            for (int j = 0; j < playerCards[i].length; j++) {
                if (playerCards[i][j] instanceof HiddenCard) {
                    HiddenCard hiddenCard = (HiddenCard) playerCards[i][j];
                    playerCards[i][j] = new Card(hiddenCard.getCardName());
                }
            }
        }
    }

    public int[] getFirstHiddenCard(){
        if(this.hasHiddenCard()){
            Card[][] pHand = this.getPlayerCards();
                for (int i = 0; i < pHand[0].length; i++) { 
                    for (int j = 0; j < pHand[1].length; j++) {
                        if (pHand[i][j] instanceof HiddenCard) {
                            return new int[]{i, j}; 
                        }
                    }
                }
        }
        return null;
    }

    

    public int[] chooseRandomCard() {
        Random random = new Random();
        int randomLineNumber = random.nextInt(4);
        int randomColumnNumber = random.nextInt(3);
        return new int[]{randomColumnNumber, randomLineNumber};
    }


    public void replaceCard(int x, int y, Card card){ //card elle est piochée
        Card replacedCard = new Card(this.cards[x][y].getCardName());
        if(replacedCard instanceof HiddenCard){
            HiddenCard hiddenCard = (HiddenCard) this.cards[x][y];
            replacedCard = new Card(hiddenCard.getCardName());
        }
        getAction().discardACard(replacedCard);// ATTENTION CHANGEMENT DE ACTION. à getAction().
        this.cards[x][y] = card;
    }

    public Action getAction() {
        return this.action; // Nouvelle fonction ajoutée
    }

    public void displayCards() {
        for (int i = 0; i < this.cards.length; i++) {
            for (int j = 0; j < this.cards[i].length; j++) { 
                System.out.print(this.cards[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int sumCard(){ //bougé dans player
        int sum = 0;
        Card[][] pHand = this.getPlayerCards();
        for(Card[] subCards : pHand){
            for(Card card :subCards){
                if(!(card instanceof HiddenCard)){
                    sum+=card.getCardName().getCardValue();
                }
            }
        }
        return sum;
    }

    public boolean hasHiddenCard(){
        Card[][] pHand = this.getPlayerCards();
        for(Card[] subCards : pHand){
            for(Card card :subCards){
                if((card instanceof HiddenCard)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean colonne(int y){
        if(this.cards[0][y]==this.cards[1][y] &&this.cards[0][y]==this.cards[2][y]){
            Card[][] cards = new Card[3][this.cards.length];
            for(int x = 0; x<this.cards.length;x++ ){
                for(int j =0, k =0; j<this.cards[0].length; j++){
                    if(j!=y){
                        cards[x][k++]=this.cards[x][j];
                    }
                    if(j==y){
                        getAction().discardACard(this.cards[x][y]);
                    }
                }
            }
            this.cards=cards;
            return true;
        }
        return false;
    }

@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Card[] ligne : this.getPlayerCards()) {
            for (Card card : ligne) {
                if (card instanceof HiddenCard) {
                    builder.append(card.toString());
                    builder.append(" ");
                } else {
                    builder.append(card.toString()).append(" ");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}