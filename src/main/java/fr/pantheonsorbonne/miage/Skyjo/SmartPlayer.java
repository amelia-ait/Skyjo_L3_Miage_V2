package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Random;

public class SmartPlayer extends Player {
    String name;
    Random random = new Random();

    SmartPlayer(String name) {
        super(name);
    }

    public int[] buildColumn(Card testCard) {
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

            lineCoord = 0;
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
        }
        return columnCoord;
    }

    @Override
    public void toPlay() {
        Card[][] playerHand = this.getPlayerCards();
        int x = 0;
        int y = 0;
        if (this.hasHiddenCard() && this.getFirstHiddenCard() != null) {
            x = this.getFirstHiddenCard()[0];
            y = this.getFirstHiddenCard()[1];
        } else {
            int lengthX = playerHand[0].length - 1;
            int lengthY = playerHand[1].length - 1;
            if (lengthX < 0 || lengthY < 0) {
                lengthX = 0;
                lengthY = 0;
            }
            x = random.nextInt(lengthX);
            y = random.nextInt(lengthY);

        }
        Card discardPeek = getAction().peekAtCardDiscard();
        int[] buildColumn = buildColumn(discardPeek);
        if (buildColumn[0] != -1) {
            Card drawedCard = getAction().drawACardDiscard();
            this.replaceCard(buildColumn[0], buildColumn[1], drawedCard);
            this.column(buildColumn[1]);

        } else if (getAction().chooseDiscard()) {
            Card drawedCard = getAction().drawACardDiscard();
            this.replaceCard(x, y, drawedCard);
            this.column(y);
        }

        else {
            Card drawedCard = getAction().drawACardDeck();
            System.out.println("La carte pioché est " + drawedCard.toString());
            buildColumn = buildColumn(drawedCard);
            if (buildColumn[0] != -1) {
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
