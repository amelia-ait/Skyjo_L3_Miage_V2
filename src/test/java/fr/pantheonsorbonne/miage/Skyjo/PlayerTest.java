package fr.pantheonsorbonne.miage.Skyjo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {
     @Test
    void testPrint(){
        //Deck decktest = new Deck();
        Player player = new DumbPlayer("johan");
        Card test = new Card(Value.SIX);
       player.setPlayerCards(Deck.newRandomHand());
        System.out.println(player.toString());
        player.setCardVisible(0, 0);
        System.out.println(player.toString());
        player.replaceCard(2, 3, test);
        System.out.println(player.toString());
    }

    @Test
    void testReplace(){
        Player player = new DumbPlayer("johan");
        Card test1 = new Card(Value.SIX);
        Card test2 = new Card(Value.EIGHT);
        player.setPlayerCards(Deck.newRandomHand());
        player.replaceCard(1, 3, test1);
        player.replaceCard(1, 3, test2);
        System.out.println(player.toString());
        System.out.println(Discard.peekCard().toString());
        Card test = Discard.peekCard();
        System.out.println(test.toString());
        assertEquals(test.toString(), test1.toString());
    }

    @Test
    void colonne(){
        Player player = new DumbPlayer("johan");
        player.setPlayerCards(Deck.newRandomHand());
        Card test2 = new Card(Value.EIGHT);
        Card test = new Card(Value.MINUSONE);
        player.replaceCard(0, 3, test2);
        player.replaceCard(1, 3, test2);
        player.replaceCard(2, 3, test2);
        player.replaceCard(2, 1, test);
        player.replaceCard(1, 1, test2);
        System.out.println(player.toString());
        player.column(3);
        System.out.println(player.toString());

    }
}
