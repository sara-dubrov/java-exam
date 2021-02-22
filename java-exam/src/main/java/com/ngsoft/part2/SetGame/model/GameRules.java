package com.ngsoft.part2.SetGame.model;

import com.ngsoft.part2.SetGame.pojos.Card;

import java.util.Collection;

public interface GameRules {

    /**
     * states for every collection of cards whether they create a SET
     * @param cards collection of three cards
     * @return true if the cards are set
     */
    boolean isSet(Collection<Card> cards);
}
