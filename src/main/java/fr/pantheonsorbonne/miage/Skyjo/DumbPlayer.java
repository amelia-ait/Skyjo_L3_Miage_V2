package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Random;

public class DumbPlayer extends Player {
    String name ;
    Random random = new Random();
    DumbPlayer(String name){
        super(name);
    }
    public void toPlay(){}
   /*  public void toPlay(){
        Card[][] playerHand = this.getPlayerCards();
        int x = random.nextInt(playerHand.length-1);   
        int y = random.nextInt(playerHand[0].length-1);
        if(chooseDiscard()){
            Card drawedCard = Player.drawACardDiscard();
            this.replaceCard(x, y, drawedCard);
            this.colonne(y);
        }
        else{
            Card drawedCard = Player.drawACardDeck();
            if(chooseReplace(drawedCard)){
            this.replaceCard(x, y, drawedCard);
            this.colonne(y);
            }
            else{
                Player.discardACard(drawedCard);
                //this.returnCard(x, y);
                this.colonne(y);
            }
        }*/
    }

    

    
    
    



