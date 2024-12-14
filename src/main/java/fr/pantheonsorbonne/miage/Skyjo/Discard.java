package fr.pantheonsorbonne.miage.Skyjo;

import java.util.Deque;
import java.util.LinkedList;

public class Discard {
    private static Deque<Card> discard = new LinkedList<>();
       /* public Discard(){
            this.discard = discard;
        }*/
        static {
            discard.clear();
    }
    public static void addCard(Card card){
        discard.addFirst(card);
    }

    public static Card takeCard(){
        return discard.removeFirst();
    }

    public static Card peekCard(){
        return discard.peek();
    }

    public static  String DiscardtoString(){
        String result = "";
        for(Card cards : discard){
            result += cards.toString();
            result += " ";
        }
        return result;
       }
}
