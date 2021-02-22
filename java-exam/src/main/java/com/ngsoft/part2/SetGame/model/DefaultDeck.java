package com.ngsoft.part2.SetGame.model;

import com.ngsoft.part2.SetGame.pojos.Card;
import com.ngsoft.part2.SetGame.pojos.CardFeatures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DefaultDeck implements Deck {

    private final List<Card> cards = new ArrayList<>();

    @Override
    public void reset() {
        cards.clear();
        generateCards();
    }

    private void generateCards() {
        for (CardFeatures.Shape shape : CardFeatures.Shape.values()) {
            for (CardFeatures.Color color : CardFeatures.Color.values()) {
                for (CardFeatures.ItemCount itemCount : CardFeatures.ItemCount.values()) {
                    for (CardFeatures.Texture texture : CardFeatures.Texture.values()) {
                        cards.add(Card.createNew(shape, color, itemCount, texture));
                    }
                }
            }
        }
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards, new Random());
    }

    @Override
    public List<Card> deal(int qty) {
        if (!isQtyValid(qty)) {
            throw new IllegalArgumentException("Qty: " + qty + " is invalid for the cards amount: " + cards.size());
        }
        List<Card> dealtCards = new ArrayList<>(cards.subList(0, qty));
        cards.subList(0, qty).clear();
        return dealtCards;
    }

    private boolean isQtyValid(int qty) { // may be <3?
        return qty > 0 && qty <= cards.size();
    }

    @Override
    public int size() {
        return cards.size();
    }
}
