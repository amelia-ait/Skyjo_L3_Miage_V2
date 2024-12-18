package fr.pantheonsorbonne.miage.Skyjo.skyjoLocal;
import java.util.Arrays;

import fr.pantheonsorbonne.miage.Skyjo.Main;
import fr.pantheonsorbonne.miage.Skyjo.Round;
import fr.pantheonsorbonne.miage.engine.local.LocalWarGame;
import fr.pantheonsorbonne.miage.game.RandomDeck;

public class LocalSkyjo extends Main {
    public LocalSkyjo(){
        
    }
    public static void main(String... args) {
        LocalSkyjo localWarGame = new LocalSkyjo(new RandomDeck(), 5, Arrays.asList("Joueur1", "Joueur2", "Joueur3"));
        localWarGame.play();
        System.exit(0);

    }

}
