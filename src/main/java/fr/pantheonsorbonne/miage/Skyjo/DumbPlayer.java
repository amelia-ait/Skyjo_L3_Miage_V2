package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Random;

public class DumbPlayer extends Player {
    String name;
    Random random = new Random();
    Action dumbAction = new DumbAction();

    DumbPlayer(String name) {
        super(name);
    }

    public void toPlay() {
        Card[][] playerHand = this.getPlayerCards();
        int x = random.nextInt(playerHand.length - 1);
        int y = random.nextInt(playerHand[0].length - 1);
        if (dumbAction.chooseDiscard()) {
            Card drawedCard = Action.drawACardDiscard();
            this.replaceCard(x, y, drawedCard);
            if (this.testColonne(y)) {
                playerHand = this.colonne(y);
            }
        } else {
            Card drawedCard = Action.drawACardDeck();
            if (dumbAction.chooseReplace(drawedCard)) {
                this.replaceCard(x, y, drawedCard);
                if (this.testColonne(y)) {
                    playerHand = this.colonne(y);
                }
            } else {
                Action.discardACard(drawedCard);
                this.setCardVisible(x, y);
                if (this.testColonne(y)) {
                    playerHand = this.colonne(y);
                }
            }
        }
    }
}
