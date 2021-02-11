package com.ngsoft.part2.SetGame;

import java.util.Collection;

public interface GameRules {

    /**
     * states for every collection of cards whether they create a SET
     * @param cards
     * @return
     */
    boolean isSet(Collection<Card> cards);
}
