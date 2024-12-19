package fr.pantheonsorbonne.miage.Skyjo;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Main {

  
    public static void main(String[] args) {
        System.out.println("GAME START !");
        Player player1 = new SmartPlayer("Jean");
        Player player2 = new SmartPlayer("Patricia");
        Player player3 = new DumbPlayer("Lucas");
        boolean isGameOver = false;
        HashMap<String, Integer> score = new HashMap<>();
        while (!isGameOver) {
            Deck.getRandomDeck();
            Round round = new Round(player1,player2, player3);
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
                    System.out.println(player.getPlayerName() + " commence");
                }
            }
            Object[] infoEndRound = round.endRound();
            boolean endRound = (boolean)infoEndRound[0];
            Player endingPlayer = (Player)infoEndRound[1];
            while (!endRound) {
                 infoEndRound = round.endRound();
                for (Player player : round.getnbPlayer()) {
                    System.out.println("Le tour du joueur " + player.getPlayerName());
                    player.displayCards();
                    Card test = Discard.peekCard();
                    System.out.println("La défausse " + test.toString());
                    player.toPlay();     
                    player.displayCards();
                }
                 endRound = (boolean)infoEndRound[0];
                 endingPlayer = (Player)infoEndRound[1];
            }
            
            
            System.out.println("Le joueur "+endingPlayer.getPlayerName()+" fini le tour");
            if(endingPlayer.sumCard()!=round.minScore()){
                int endingPlayerScoreDouble = endingPlayer.sumCard()*2;
                System.out.println("Le joueur "+endingPlayer.getPlayerName()+" n'as pas le plus petit score donc son score de "+endingPlayer.sumCard()+" est doublé a "+endingPlayerScoreDouble);
                int newScore = score.getOrDefault(endingPlayer.getPlayerName(), 0) + endingPlayerScoreDouble;
                score.put(endingPlayer.getPlayerName(), newScore);
            }
            for (Player player : round.getnbPlayer()) {
                if (!player.equals(endingPlayer)) {
                    player.toPlay();
                    System.out.println("Le tour du joueur " + player.getPlayerName());
                    Card test = Discard.peekCard();
                    System.out.println("La défausse " + test.toString());
                    player.displayCards();
                    score.put(player.getPlayerName(), score.getOrDefault(player.getPlayerName(), 0) + player.sumCard());
                }
            }
            for (Player players : round.getnbPlayer()) {
                players.setAllCardVisible();
                System.out.println("Le jeu du joueur " + players.getPlayerName());
                players.displayCards();
            }
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
    }
}