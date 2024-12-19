package fr.pantheonsorbonne.miage.Skyjo.skyjoNetWork;

import java.util.ArrayList;
import java.util.Set;
//package fr.pantheonsorbonne.miage.engine.net;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.engine.WarGameEngine;
import fr.pantheonsorbonne.miage.engine.net.WarGameNetworkEngine;
import fr.pantheonsorbonne.miage.exception.NoMoreCardException;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.game.RandomDeck;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;
import fr.pantheonsorbonne.miage.Skyjo.Main;
import fr.pantheonsorbonne.miage.Skyjo.Player;
import fr.pantheonsorbonne.miage.Skyjo.Round;


public class networkSkyjo extends Main {
    private static final int PLAYER_COUNT = 8;
    private final HostFacade hostFacade;
    private final Game skyjo;

    public networkSkyjo(Deck deck, HostFacade hostFacade, fr.pantheonsorbonne.miage.model.Game skyjo, Player... players) {
        //super(players);
        this.hostFacade = hostFacade;
        this.skyjo = skyjo;
        //ArrayList<Player> playersList = setPlayers(players);
    }



    public static void main(String[] args) {
        //create the host facade
        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();

        //set the name of the player
        hostFacade.createNewPlayer("Host");

        //create a new game of war
        fr.pantheonsorbonne.miage.model.Game skyjo = hostFacade.createNewGame("WAR");

        //wait for enough players to join
        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);

        Main host = new networkSkyjo(new RandomDeck(), hostFacade, skyjo);
       // host.play();
        System.exit(0);


    }
}
