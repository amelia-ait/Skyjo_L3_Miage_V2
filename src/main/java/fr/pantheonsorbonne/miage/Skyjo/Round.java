package fr.pantheonsorbonne.miage.Skyjo;

import java.util.ArrayList;
import java.util.HashMap;

public class Round {
    ArrayList<Player> nbPlayers;

    public Round(Player... players) {
        this.nbPlayers = nbPlayers;
        for (Player player : players) {
            nbPlayers.add(player);
        }
    }

    public Player whoStarts() {
        int max = -10;
        Player startingPlayer = this.nbPlayers.get(0);
        for (Player p : this.nbPlayers) {
            int sum = p.sumCard();
            if (sum > max) {
                max = sum;
                startingPlayer = p;
            }
        }
        return startingPlayer ;
    }

    /*
     * public void endRound(){
     * score.put(player1,sumCard(player1));
     * score.put(player2,sumCard(player2));
     * }
     */
    public static void main(String[] args) {
        Player player1 = new DumbPlayer("Jean");
        player1.setPlayerCards(Deck.newRandomHand());

        Player player2 = new DumbPlayer("Patrice");
        player2.setPlayerCards(Deck.newRandomHand());

        Round round = new Round(player1, player2);
        round.whoStarts();

        Discard.addCard(Deck.drawCard());

    }

}
