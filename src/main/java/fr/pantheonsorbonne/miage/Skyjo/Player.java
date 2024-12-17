package fr.pantheonsorbonne.miage.Skyjo;

public abstract class  Player {
    private Card[][] cards;
    private String name;
    private final static int nbLigne =3;
    private final static int nbColonne =4;


    public Player(String name) {
        this.name = name;
        this.cards = new Card[nbLigne][nbColonne]; }

    public String getPlayerName() {
        return this.name;
    }

    public Card[][] getPlayerCards() {
        return this.cards;
    }

    public abstract void toPlay();

    public void setPlayerCards(HiddenCard[][] hiddenCards) {
        for (int i = 0; i < hiddenCards.length; i++) {
            for (int j = 0; j < hiddenCards[i].length; j++) {
                this.cards[i][j] = hiddenCards[i][j];
            }
        }
    }

    public void setCardVisible(int x, int y) { //set cart visible
        if (this.cards[x][y] instanceof HiddenCard) {
            HiddenCard hiddenCard = (HiddenCard) this.cards[x][y];
            Card revealedCard = new Card(hiddenCard.getCardName());
            this.cards[x][y] = revealedCard;
        }
    }


    public void replaceCard(int x, int y, Card card){
        Card replacedCard = new Card(this.cards[x][y].getCardName());
        if(replacedCard instanceof HiddenCard){
            HiddenCard hiddenCard = (HiddenCard) this.cards[x][y];
            replacedCard = new Card(hiddenCard.getCardName());
        }
        Action.discardACard(replacedCard);
        this.cards[x][y] = card;
    }


    public int sumCard(){ //a bouger dans player FAIT
        int sum = 0;
        for(Card[] subCards : this.cards){
            for(Card card :subCards){
                if(!(card instanceof HiddenCard)){
                    sum+=card.getCardName().getCardValue();
                }
            }
        }
        return sum;
    }

    public Card[][] colonne(int y){
        int nbLine = this.cards.length;
        int nbColonne = this.cards[0].length;
        Card[][] replaceTab = new Card[nbLine][nbColonne];
        for(int i =0; i <nbLine ; i++){
            int k = 0;
            for(int j =0; j<nbColonne;j++){
                if(j!=y){
                    replaceTab[i][k++] = this.cards[i][j];
                }
                else{
                    Action.discardACard(this.cards[i][j]);
                }
            }
        }
        return replaceTab;
    }

    public boolean testColonne(int y){
        return (this.cards[0][y] == this.cards[1][y])&&(this.cards[1][y]==this.cards[2][y]);
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
