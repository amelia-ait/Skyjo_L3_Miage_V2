package fr.pantheonsorbonne.miage.Skyjo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeckTest {
    
   
    @Test
   void testString(){
    Deck decktest = new Deck();
    String test =Deck.toString(decktest);
    System.out.println(test);
    int compteur= 0;
    for(Card card : decktest.getDeck()){
            compteur++;
        
    }
    assertEquals(150, compteur);
   }

}
