package com.ngsoft.part2.SetGame.model;

import com.ngsoft.part2.SetGame.pojos.Card;

import java.util.List;

public interface Deck {

    /*
    Clear the deck and generating all the cards
     */
    void reset();

    /*
    Shuffle the deck cards
     */
    void shuffle();

    /*
    Return the amount of cards requested, and remove them from the deck
     */
    List<Card> deal(int qty);

    /*
    Return the deck cards qty
     */
    int size();
}
