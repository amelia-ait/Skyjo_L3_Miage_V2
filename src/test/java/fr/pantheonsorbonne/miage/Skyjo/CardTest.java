package fr.pantheonsorbonne.miage.Skyjo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CardTest {
    
    @Test
    void testPrint(){
        Card card = new Card(Value.ONE);
        assertEquals("1", card.toString());
    }
}