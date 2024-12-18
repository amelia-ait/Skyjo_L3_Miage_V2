package fr.pantheonsorbonne.miage.Skyjo;

import java.util.ArrayList;
import java.util.HashMap;

public class Round {
    private ArrayList<Player> nbPlayer;
    private boolean player1Start;

    public Round(Player... players) {
        nbPlayer = new ArrayList<>();
        this.nbPlayer = setPlayers(players);

    }

    public ArrayList<Player> setPlayers(Player... players) {
        ArrayList<Player> nbPlayer = new ArrayList<>();
        for (Player player : players) {
            nbPlayer.add(player);
        }
        return nbPlayer;
    }

    public ArrayList<Player> getnbPlayer() {
        return this.nbPlayer;
    }

    public Player getStartingPlayer() {
        int max = -10;
        Player startingPlayer = this.nbPlayer.get(0);
        for (Player player : this.nbPlayer) {
            int sum = player.sumCard();
            if (player.sumCard() > max) {
                max = sum;
                startingPlayer = player;
            }
        }
        return startingPlayer;

    }

    public boolean getPlayer1Start() {
        return this.player1Start;
    }

    public Object[] endRound() {
        Player endingPlayer = this.getnbPlayer().get(0);
        boolean endRound = false;
        for (Player player : this.getnbPlayer()) {
            if (!player.hasHiddenCard()) {
                endingPlayer = player;
                return new Object[]{!endRound, endingPlayer};
            }
        }
        return new Object[]{endRound, endingPlayer};
    }

    public Object[] endGame(HashMap<String, Integer> score) {
        boolean maxScore = false;
        int min = Integer.MAX_VALUE;
        Player potentialWinner = null;
        for (Player player : this.getnbPlayer()) {
            int playerScore = score.get(player.getPlayerName());

            if (playerScore >= 100) {
                maxScore = true;
            }
            if (playerScore < min) {
                min = playerScore;
                potentialWinner = player;
            }
        }
        return new Object[] { maxScore, potentialWinner };
    }

    public int minScore(Player ...players){
        int min =145;
        for(Player player : players){
            if(min>player.sumCard()){
                min = player.sumCard();
            }
        }
        return min;
    }
   
}

