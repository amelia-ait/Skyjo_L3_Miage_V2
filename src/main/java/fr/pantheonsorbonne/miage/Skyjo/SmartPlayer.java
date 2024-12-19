package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Random;

public class SmartPlayer extends Player {
    String name;
    Random random = new Random();

    SmartPlayer(String name) {
        super(name);
    }

    public int[] finishColumn(Card testCard) {
        int[] columnCoord = { -1, -1 };
        int lineCoord = -1;
        if (testCard.getCardName().getCardValue() >= 0) {
            for (int j = 0; j < this.getPlayerCards()[0].length; j++) {
                int sameCards = 0;
                lineCoord = -1;
                for (int i = 0; i < this.getPlayerCards().length; i++) {
                    Card currentCard = this.getPlayerCards()[i][j];

                    if (!(currentCard instanceof HiddenCard)
                            && currentCard.getCardName().getCardValue() == testCard.getCardName().getCardValue()) {
                        sameCards++;
                    } else if (lineCoord == -1) {
                        lineCoord = i;
                    }
                }
                if (sameCards == 2 && lineCoord != -1) {
                    columnCoord[0] = lineCoord;
                    columnCoord[1] = j;
                    return columnCoord;
                }
            }
        }
        return columnCoord;
    }

    public int[] buildColumn(Card testCard) {
        int[] columnCoord = { -1, -1 };
        int lineCoord = 0;
        for (int j = 0; j < this.getPlayerCards()[0].length; j++) {
            for (int line = 0; line < this.getPlayerCards().length; line++) {
                Card compareCard = this.getPlayerCards()[line][j];
                if (compareCard.getCardName().getCardValue() == testCard.getCardName().getCardValue()
                        && !(compareCard instanceof HiddenCard)) {
                    if (lineCoord != line) {
                        columnCoord[0] = lineCoord;
                        columnCoord[1] = j;
                        break;
                    } else {

                        lineCoord++;
                        columnCoord[0] = lineCoord;
                        columnCoord[1] = j;
                    }

                    return columnCoord;
                }
            }
        }

        return columnCoord;
    }


    @Override
    public void toPlay() {
        
        int x = 0;
        int y = 0;
        if (this.hasHiddenCard() && this.getFirstHiddenCard() != null) {
            x = this.getFirstHiddenCard()[0];
            y = this.getFirstHiddenCard()[1];
        } else {

            x = random.nextInt(1);
            y = random.nextInt(1);

        }
        Card discardPeek = getAction().peekAtCardDiscard();
        int[] finishColumn = finishColumn(discardPeek);
        int[] buildColumn = buildColumn(discardPeek);
        if (finishColumn[0] != -1) {
            Card drawedCard = getAction().drawACardDiscard();
            this.replaceCard(finishColumn[0], finishColumn[1], drawedCard);
            this.column(finishColumn[1]);

        } 
        else if (buildColumn[0] != -1) {
            Card drawedCard = getAction().drawACardDiscard();
            this.replaceCard(buildColumn[0], buildColumn[1], drawedCard);
            this.column(buildColumn[1]);

        } 
        else if (getAction().chooseDiscard()) {
            Card drawedCard = getAction().drawACardDiscard();
            this.replaceCard(x, y, drawedCard);
            this.column(y);
        }

        else {
            Card drawedCard = getAction().drawACardDeck();
            System.out.println("La carte pioché est " + drawedCard.toString());
            finishColumn = finishColumn(drawedCard);
            buildColumn = buildColumn(drawedCard);
            if (finishColumn[0] != -1) {
                this.replaceCard(finishColumn[0], finishColumn[1], drawedCard);
                this.column(finishColumn[1]);
            }
            else if (buildColumn[0] != -1) {
                this.replaceCard(buildColumn[0], buildColumn[1], drawedCard);
                this.column(buildColumn[1]);
            }

            else if (getAction().chooseReplace(drawedCard)) {
                this.replaceCard(x, y, drawedCard);
                this.column(y);
            } else {
                getAction().discardACard(drawedCard);

                if (this.getFirstHiddenCard() != null) {
                    x = this.getFirstHiddenCard()[0];
                    y = this.getFirstHiddenCard()[1];
                    this.setCardVisible(x, y);
                }
                this.column(y);
            }
        }
    }
}
