package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Random;

public abstract class Player {
    private Card[][] cards;
    private String name;
    private Action action;
    private final static int nbLigne = 3;
    private final static int nbcolumn = 4;

    public Player(String name) {
        this.name = name;
        this.cards = new Card[nbLigne][nbcolumn];
        this.action = new Action();
    }

    public String getPlayerName() {
        return this.name;
    }

    public Card[][] getPlayerCards() {
        return this.cards;
    }

    public abstract void toPlay();

    public void setPlayerCards(HiddenCard[][] hiddenCards) {
        this.cards = new Card[3][4];
        for (int i = 0; i < hiddenCards.length; i++) {
            for (int j = 0; j < hiddenCards[i].length; j++) {
                this.cards[i][j] = hiddenCards[i][j];
            }
        }
    }

    public void setCardVisible(int x, int y) {
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

    public int[] getFirstHiddenCard() {
        if (this.hasHiddenCard()) {
            Card[][] pHand = this.getPlayerCards();
            for (int i = 0; i < pHand[0].length; i++) {
                for (int j = 0; j < pHand[1].length; j++) {
                    if (pHand[i][j] instanceof HiddenCard) {
                        return new int[] { i, j };
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
        return new int[] { randomColumnNumber, randomLineNumber };
    }

    public void replaceCard(int x, int y, Card card) {
        Card replacedCard = new Card(this.cards[x][y].getCardName());
        if (replacedCard instanceof HiddenCard) {
            HiddenCard hiddenCard = (HiddenCard) this.cards[x][y];
            replacedCard = new Card(hiddenCard.getCardName());
        }
        getAction().discardACard(replacedCard);
        this.cards[x][y] = card;
    }

    public Action getAction() {
        return this.action;
    }

    public void displayCards() {
        for (int i = 0; i < this.cards.length; i++) {
            for (int j = 0; j < this.cards[i].length; j++) {
                System.out.print(this.cards[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int sumCard() {
        int sum = 0;
        Card[][] pHand = this.getPlayerCards();
        for (Card[] subCards : pHand) {
            for (Card card : subCards) {
                if (!(card instanceof HiddenCard)) {
                    sum += card.getCardName().getCardValue();
                }
            }
        }
        return sum;
    }

    public boolean hasHiddenCard() {
        Card[][] pHand = this.getPlayerCards();
        for (Card[] subCards : pHand) {
            for (Card card : subCards) {
                if ((card instanceof HiddenCard)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean column(int y) {
        Card card1 = this.cards[0][y];
        Card card2 = this.cards[1][y];
        Card card3 = this.cards[2][y];
        if (!(card1 instanceof HiddenCard || card2 instanceof HiddenCard || card3 instanceof HiddenCard)) {
            if (card1.getCardName().getCardValue() == card2.getCardName().getCardValue()
                    && card1.getCardName().getCardValue() == card3.getCardName().getCardValue()) {
                Card[][] cards = new Card[3][this.cards[0].length - 1];
                for (int x = 0; x < this.cards.length; x++) {
                    int k = 0;
                    for (int j = 0; j < this.cards[0].length; j++) {
                        if (j != y) {
                            cards[x][k++] = this.cards[x][j];
                        } else {
                            getAction().discardACard(this.cards[x][y]);
                        }
                    }
                }
                this.cards = cards;
                return true;
            }
        }
        return false;
    }

}