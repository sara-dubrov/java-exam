package com.ngsoft.part2.SetGameTests;

import com.ngsoft.part2.SetGame.model.Deck;
import com.ngsoft.part2.SetGame.pojos.Card;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class DeckTests {

    private static final int TOTAL_DECK_CARDS = 81;

    @Autowired
    private Deck deck;

    @Before
    public void setup() {
        deck.reset();
    }

    @Test
    public void A_DECK_HAS_81_CARDS() {
        assertEquals(TOTAL_DECK_CARDS, deck.size());
    }

    @Test
    public void ALL_CARDS_ARE_DIFFERENT_IN_DECK() {
        List<Card> cardList = deck.deal(TOTAL_DECK_CARDS);
        HashSet<Card> cardSet = new HashSet<>(cardList);
        assertEquals(TOTAL_DECK_CARDS, cardSet.size());
    }

    @Test
    public void DEAL_RETURNS_REQUIRED_AMOUNT_OF_CARDS_FROM_DECK() {
        assertEquals(3, deck.deal(3).size());
    }

    @Test
    public void DEAL_ALL_CARDS_THEN_RETURNS_ALL_CARDS_AND_DECK_EMPTY() {
        assertEquals(81, deck.deal(81).size());
        assertEquals(0, deck.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void DEAL_WHEN_QTY_0_THROW_EXCEPTION() {
        deck.deal(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void DEAL_WHEN_QTY_NEGATIVE_THROW_EXCEPTION() {
        deck.deal(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void DEAL_WHEN_QTY_GREATER_THAN_CARDS_THROW_EXCEPTION() {
        deck.deal(82);
    }

    @Test
    public void DEAL_REMOVES_DEALT_CARD_FROM_DECK() {
        deck.deal(3);
        assertEquals(TOTAL_DECK_CARDS - 3, deck.size());
    }
}
