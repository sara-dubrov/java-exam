package com.ngsoft.part2.SetGame.model;

import com.ngsoft.part2.SetGame.pojos.Card;

import java.util.Collection;

public class DefaultGameRules implements GameRules {

    @Override
    public boolean isSet(Collection<Card> cards) {
        if (cards == null || !isThreeCards(cards)) {
            return false;
        }
        return verifyColor(cards)
                && verifyShape(cards)
                && verifyTexture(cards)
                && verifyItemCount(cards);
    }

    private boolean isThreeCards(Collection<Card> cards) {
        return cards.size() == 3;
    }

    private boolean verifyColor(Collection<Card> cards) {
        long count = cards.stream().map(Card::getColor)
                .distinct()
                .count();
        return isSameOrUnique(count, cards.size());
    }

    private boolean verifyShape(Collection<Card> cards) {
        long count = cards.stream().map(Card::getShape)
                .distinct()
                .count();
        return isSameOrUnique(count, cards.size());
    }

    private boolean verifyTexture(Collection<Card> cards) {
        long count = cards.stream().map(Card::getTexture)
                .distinct()
                .count();
        return isSameOrUnique(count, cards.size());
    }

    private boolean verifyItemCount(Collection<Card> cards) {
        long count = cards.stream().map(Card::getItemCount)
                .distinct()
                .count();
        return isSameOrUnique(count, cards.size());
    }

    private boolean isSameOrUnique(long count, int size) {
        return count == 1 || count == size;
    }
}
