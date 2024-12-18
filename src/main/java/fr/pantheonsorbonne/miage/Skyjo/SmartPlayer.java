package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Random;

public class SmartPlayer extends Player {
    String name;
    Random random = new Random();
    SmartPlayer(String name){
        super(name);
    }



    public void buildColumn(){
        int count = 0;
        
        for(int x = 0; x < this.getPlayerCards().length;x++ ){
            for(int j = 0; j<this.getPlayerCards()[0].length-1; j++){
                if(this.getPlayerCards()[j]==this.getPlayerCards()[j+1]){
                    chooseReplace

                }
                
            }
        }
        
    }

   /*  public boolean colonne(int y){ 
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
    }*/

}
