package fr.pantheonsorbonne.miage.Skyjo;

import java.util.ArrayList;
import java.util.HashMap;

public class Round {
    /*
     * private Player player1 ;
     * private Player player2 ;
     */
    private ArrayList<Player> nbPlayer;
    // private HashMap<String, Integer> score ;
    private boolean player1Start;

    public Round(Player... players) {
        nbPlayer = new ArrayList<>();
        nbPlayer = setPlayers(players);
        this.nbPlayer = nbPlayer;

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

    public boolean endRound() {
        for (Player player : this.getnbPlayer()) {
            if (!player.hasHiddenCard()) {
                return true;
            }
        }
        return false;
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
        return new Object[]{maxScore, potentialWinner};
    }
    

}

class Main {
    public static void scoreUpdate(HashMap<String, Integer> score, ArrayList<Player> players) {
        for (Player player : players) {
            score.put(player.getPlayerName(), score.getOrDefault(player.getPlayerName(), 0) + player.sumCard());
        }

    }

    public static void main(String[] args) {
        System.out.println("GAME START !");
        Player player1 = new DumbPlayer("Jean");
        Player player2 = new DumbPlayer("Patrice");
        Player player3 = new DumbPlayer("Lucas");
        boolean isGameOver = false;
        HashMap<String, Integer> score = new HashMap<>();
        while (!isGameOver) {
            Deck.getRandomDeck();
            Round round = new Round(player1, player2, player3);
            for (Player player : round.getnbPlayer()) {
                player.setPlayerCards(Deck.newRandomHand());
                for (int i = 0; i < 2; i++) {
                    int[] randomCardCoord = player.chooseRandomCard();
                    player.setCardVisible(randomCardCoord[0], randomCardCoord[1]);
                }
            }

            Discard.addCard(Deck.drawCard());
            Player startingPlayer = round.getStartingPlayer();
            for (Player player : round.getnbPlayer()) {
                if (player.equals(startingPlayer)) {
                    System.out.println(player.getPlayerName() + " commence, en légende");
                }
            }
            /*
             * if (round.getPlayer1Start()){
             * System.out.println("Joueur 1 commence, en légende");
             * 
             * }
             * else{
             * System.out.println("Joueur 2 commence, en légende");
             * }
             */

            while (!round.endRound()) {
                for (Player player : round.getnbPlayer()) {
                    player.toPlay();
                    System.out.println("Le tour du joueur "+player.getPlayerName());
                    player.displayCards();
                }
                /*
                 * player1.toPlay();
                 * player2.toPlay();
                 * player1.displayCards();
                 * player2.displayCards();
                 */
            }
            for(Player players : round.getnbPlayer()){
                players.setAllCardVisible();
            }

            scoreUpdate(score, round.getnbPlayer());
            System.out.println(score);
            Object[] result = round.endGame(score);
            isGameOver = (boolean) result[0];
            Player winner = (Player) result[1];

            if (isGameOver) {
                System.out.println("Partie terminée !");
                System.out.println("Le joueur gagnant est : " + winner.getPlayerName());
            } else {
                System.out.println("La partie continue.");
            }

        }
        // System.out.println("TOUT FONCTIONNE");
        // System.out.println(Deck.getDeck());
        // player1.displayCards();
        // player2.displayCards();
        // player1.returnCard(0, 0);
    }
}