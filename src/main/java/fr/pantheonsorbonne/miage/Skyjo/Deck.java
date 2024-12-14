package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
    private static Queue<Card> deck = new LinkedList<>();

    static {
    getRandomDeck();
    }
    

    public static Queue<Card> getDeck(){
        return deck;
    }
    
    public static void getRandomDeck() {
        deck.clear();
        List<Card> possibleCards = Card.getAllPossibleCards(); 
        Collections.shuffle(possibleCards); 
        deck.addAll(possibleCards);  
    }

    public static HiddenCard[] drawCards(int length) {
        HiddenCard[] cards = new HiddenCard[length];
        for (int i = 0; i < length; i++) {
            Card drawCard = deck.poll();
            cards[i] = new HiddenCard(drawCard); 
        }
        return cards;
    }
    

    public static Card drawCard() {
        Card cardDrawn = deck.poll();
        return cardDrawn;
        
    }

   public static  String DecktoString(){ //méthode de test
    String result = "";
    for(Card cards : deck){
        result += cards.toString();
        result += " ";
    }
    return result;
   }                          
   public static  HiddenCard[][] newRandomHand() {
    HiddenCard[] line1 = drawCards(4); 
    HiddenCard[] line2 = drawCards(4); 
    HiddenCard[] line3 = drawCards(4); 
    HiddenCard[][] randomHand = {line1,line2,line3};
    return randomHand; 
}


}
