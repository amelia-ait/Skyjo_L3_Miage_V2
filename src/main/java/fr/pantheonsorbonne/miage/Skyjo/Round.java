package fr.pantheonsorbonne.miage.Skyjo;

import java.util.HashMap;


public class Round {
    private Player player1 ;
    private Player player2 ;
    // private HashMap<String, Integer> score ;
    private boolean player1Start;
    public Round(Player player1, Player player2){ //ArrayList pour pouvoir mettre plusieur joueurs
        this.player1 = player1;
        this.player2 = player2;
        // this.score = new HashMap<>();
        // score.put(this.player1.getPlayerName(), 0);
        // score.put(this.player2.getPlayerName(),0);
    }

    public void setPlayer1Start(){
        if(this.player1.sumCard()>=this.player2.sumCard()){
            this.player1Start = true;
        }
        else{this.player1Start = false;
        }
        
    }
    public boolean getPlayer1Start(){
        return this.player1Start;
    }

    // public void scoreUpdate() {
    //     score.put(player1, score.getOrDefault(player1, 0) + player1.sumCard());
    //     score.put(player2, score.getOrDefault(player2, 0) + player2.sumCard());
    // }
    

    public boolean endRound(){
        if (player1.hasHiddenCard() || player2.hasHiddenCard()) {
            return false;
        }
        return true;
    }

    public Object[] endGame(HashMap<String,Integer> score) {
        boolean maxScore = false;
        if (score.get(player1.getPlayerName()) >= 100 ||score.get(player2.getPlayerName()) >= 100) {
            maxScore = true;

            //return new Object[]{true, 2};
        } 
        if(score.get(player1.getPlayerName()) >= score.get(player2.getPlayerName())){
            return new Object[]{maxScore, 2};
        }
        else {
            return new Object[]{maxScore, 1};
        }
    }

    // public static void main(String[] args){
    //     Player player1 = new DumbPlayer("Jean");
    //     player1.setPlayerCards(Deck.newRandomHand());
    //     for (int i = 0; i < 2; i++) {
    //         int[] randomCardCoordP1 = player1.chooseRandomCard();
    //         player1.setCardVisible(randomCardCoordP1[0], randomCardCoordP1[1]);
    //     }

    //     Player player2 = new DumbPlayer("Patrice");
    //     player2.setPlayerCards(Deck.newRandomHand());
    //     for (int i = 0; i < 2; i++) {
    //     int[] randomCardCoordP2 = player2.chooseRandomCard();
    //     player2.setCardVisible(randomCardCoordP2[0], randomCardCoordP2[1]);
    //     }

    //     Discard.addCard(Deck.drawCard());
    //     if (player1Start())
        
    //     System.out.println("TOUT FONCTIONNE");
    //     //System.out.println(Deck.getDeck());
    //     player1.displayCards();
    //     player2.displayCards();
    //     //player1.returnCard(0, 0);
    // }
}

class Main {
    public static void scoreUpdate(HashMap<String, Integer> score, Player player1, Player player2) {
        score.put(player1.getPlayerName(), score.getOrDefault(player1.getPlayerName(), 0) + player1.sumCard());
        score.put(player2.getPlayerName(), score.getOrDefault(player2.getPlayerName(), 0) + player2.sumCard());
    }
    public static void main(String[] args){
        System.out.println("GAME START !");
        Player player1 = new DumbPlayer("Jean");
        Player player2 = new DumbPlayer("Patrice");
        boolean isGameOver = false;
        HashMap<String, Integer> score = new HashMap<>();
        while(!isGameOver){
            Deck.getRandomDeck();
            player1.setPlayerCards(Deck.newRandomHand());
            for (int i = 0; i < 2; i++) {
                int[] randomCardCoordP1 = player1.chooseRandomCard();
                player1.setCardVisible(randomCardCoordP1[0], randomCardCoordP1[1]);
            }

            player2.setPlayerCards(Deck.newRandomHand());
            for (int i = 0; i < 2; i++) {
            int[] randomCardCoordP2 = player2.chooseRandomCard();
            player2.setCardVisible(randomCardCoordP2[0], randomCardCoordP2[1]);
            }

            Round round = new Round(player1, player2);
            Discard.addCard(Deck.drawCard());
            round.setPlayer1Start();
            if (round.getPlayer1Start()){
                System.out.println("Joueur 1 commence, en légende");
                
            }
            else{
                System.out.println("Joueur 2 commence, en légende");
            }
            

            while(!round.endRound()){
                player1.toPlay();
                player2.toPlay();
                player1.displayCards();
                player2.displayCards();
            }
            scoreUpdate(score, player1, player2);
            System.out.println(score);
            Object[] result = round.endGame(score);
            isGameOver = (boolean) result[0];
            int winner = (int) result[1];

            if (isGameOver) {
                System.out.println("Partie terminée !");
                System.out.println("Le joueur gagnant est : " + (winner == 1 ? "Player 1" : "Player 2"));
            } else {
                System.out.println("La partie continue.");
            }

        }
        // System.out.println("TOUT FONCTIONNE");
        //System.out.println(Deck.getDeck());
        // player1.displayCards();
        // player2.displayCards();
        //player1.returnCard(0, 0);
    }
}