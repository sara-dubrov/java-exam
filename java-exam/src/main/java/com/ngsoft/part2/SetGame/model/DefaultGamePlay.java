package com.ngsoft.part2.SetGame.model;

import com.ngsoft.part2.SetGame.pojos.Card;
import com.ngsoft.part2.SetGame.pojos.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DefaultGamePlay implements GamePlay {

    private static final int SET_QTY = 3;
    private static final int INITIAL_REVEALED_CARDS_QTY = 12;

    private List<Card> revealedCards;
    private GameStatus gameStatus;

    @Autowired
    private Deck deck;

    @Autowired
    private GameRules gameRules;

    @Override
    public void start() {
        deck.reset();
        deck.shuffle();
        revealedCards = deck.deal(INITIAL_REVEALED_CARDS_QTY);
        gameStatus = new GameStatus();
    }

    @Override
    public List<Card> getRevealedCards() {
        return revealedCards;
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public GameStatus play(int card1, int card2, int card3) {
        validateCardIndex(card1, card2, card3);
        List<Card> selectedCards = getCardsByIndex(card1, card2, card3);
        if (gameRules.isSet(selectedCards)) {
            handleSuccess(selectedCards);
        } else {
            handleFailure();
        }
        return gameStatus;
    }

    private void validateCardIndex(int card1, int card2, int card3) {
        boolean invalidCardSelected = Stream.of(card1, card2, card3).anyMatch(this::isInvalidIndexCard);
        if (invalidCardSelected) {
            throw new IllegalArgumentException("The selected indexes are invalid");
        }
    }

    private boolean isInvalidIndexCard(int card) {
        return card < 0 || card >= revealedCards.size();
    }

    private List<Card> getCardsByIndex(int card1, int card2, int card3) {
        return Arrays.asList(revealedCards.get(card1),
                revealedCards.get(card2),
                revealedCards.get(card3));
    }

    private void handleSuccess(List<Card> selectedCards) {
        revealedCards.removeAll(selectedCards);
        gameStatus.getPlayerStatus().setSuccess();
        if (haveCardsInDeck() && isMissingRevealedCards()) {
            revealedCards.addAll(deck.deal(SET_QTY));
        } else if (isRevealedCardsFinished()) {
            gameStatus.gameOver();
        }
    }

    private void handleFailure() {
        gameStatus.getPlayerStatus().setFailure();
        if (haveCardsInDeck()) {
            revealedCards.addAll(deck.deal(SET_QTY));
        }
    }

    private boolean isRevealedCardsFinished() {
        return revealedCards.size() < SET_QTY;
    }

    private boolean haveCardsInDeck() {
        return deck.size() >= SET_QTY;
    }

    private boolean isMissingRevealedCards() {
        return revealedCards.size() < INITIAL_REVEALED_CARDS_QTY;
    }

}
