package fr.pantheonsorbonne.miage.Skyjo;

import java.util.HashMap;

public class Round {
    private Player player1 ;
    private Player player2 ;
    private HashMap<Player, Integer> score ;
    public Round(Player player1, Player player2){ //ArrayList pour pouvoir mettre plusieur joueurs
        this.player1 = player1;
        this.player2 = player2;
        score.put(player1, 0);
        score.put(player2,0);
    }

    public int sumCard(Player p){ //a bouger dans player
        int sum = 0;
        Card[][] pHand = p.getPlayerCards();
        for(Card[] subCards : pHand){
            for(Card card :subCards){
                if(!(card instanceof HiddenCard)){
                    sum+=card.getCardName().getCardValue();
                }
            }
        }
        return sum;
    }

    public boolean player1Start(){
        return sumCard(this.player1)>=sumCard(this.player2);
    }

    public void endRound(){
        score.put(player1,sumCard(player1));
        score.put(player2,sumCard(player2));
    }

    /*public static void main(String[] args){
        Player player1 = new DumbPlayer("Jean");
        player1.setPlayerCards(Deck.newRandomHand());


        Player player2 = new DumbPlayer("Patrice");
        player2.setPlayerCards(Deck.newRandomHand());

        Discard.addCard(Deck.drawCard());

        player1.returnCard(0, 0);
    }*/




}
