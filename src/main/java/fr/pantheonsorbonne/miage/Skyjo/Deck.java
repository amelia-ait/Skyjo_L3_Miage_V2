package fr.pantheonsorbonne.miage.Skyjo;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
    private Queue<Card> deck = new LinkedList<>();

    public Deck() {
        this.deck = this.getRandomDeck();
    }
    public  Queue<Card> getDeck(){
        return this.deck;
    }
    public Queue<Card> getRandomDeck() {
        Queue<Card> randomDeck = new LinkedList<>();
        List<Card> possibleCards = Card.getAllPossibleCards();
        Collections.shuffle(possibleCards);
        for (Card card : possibleCards) {
            randomDeck.offer(card);
        }
        return randomDeck;
    }

    public Card[] drawCards(int length) {
        Card[] cards = new Card[length];
        for(int i =0; i<length; i++){
            Card drawCard = this.deck.poll();
            cards[i]=drawCard;
        }
        return cards;
    }

    public Card drawCard() {
        Card cardDrawn = this.deck.poll();
        return cardDrawn;
        
    }

   public static  String toString(Deck deck){
    String result = "";
    for(Card cards : deck.deck){
        result += Card.toString(cards);
        result += " ";
    }
    return result;
   }

}
