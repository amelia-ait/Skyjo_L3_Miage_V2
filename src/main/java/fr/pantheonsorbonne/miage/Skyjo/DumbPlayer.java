package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Random;

public class DumbPlayer extends Player {
    String name ;
    Random random = new Random();
    DumbPlayer(String name){
        super(name);
    }
    //public void toPlay(){}
    public void toPlay(){
        Card[][] playerHand = this.getPlayerCards();
        int x = 0;
        int y = 0;
        if(this.hasHiddenCard()){
            x = this.getFirstHiddenCard()[0]; //random.nextInt(playerHand[0].length-1);   
            y = this.getFirstHiddenCard()[1]; //random.nextInt(playerHand[1].length-1);
        }
        else{
            x = random.nextInt(playerHand[0].length-1);   
            y = random.nextInt(playerHand[1].length-1);
        }

        if(getAction().chooseDiscard()){
            Card drawedCard = getAction().drawACardDiscard();
            this.replaceCard(x, y, drawedCard);
            this.colonne(y);
        }
        else{
            Card drawedCard = getAction().drawACardDeck();
            if(getAction().chooseReplace(drawedCard)){
            this.replaceCard(x, y, drawedCard);
            this.colonne(y);
            }
            else{
                getAction().discardACard(drawedCard);
                //this.returnCard(x, y);
                this.colonne(y);
                if(this.getFirstHiddenCard() == null){
                    this.setCardVisible(x, y);
            }
        }
        }
    }
}

    

    
    
    


