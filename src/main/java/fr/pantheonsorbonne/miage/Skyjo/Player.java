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

    

    

    public boolean colonne(int y){
        if(this.cards[0][y]==this.cards[1][y] &&this.cards[0][y]==this.cards[2][y]){
            Card[][] cards = new Card[3][this.cards.length];
            for(int x = 0; x<this.cards.length;x++ ){
                for(int j =0, k =0; j<this.cards[0].length; j++){
                    if(j!=y){
                        cards[x][k++]=this.cards[x][j];
                    }
                    if(j==y){
                        Action.discardACard(this.cards[x][y]);
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
