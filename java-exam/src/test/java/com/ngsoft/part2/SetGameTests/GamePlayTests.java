package com.ngsoft.part2.SetGameTests;

import com.ngsoft.part2.SetGame.model.GamePlay;
import com.ngsoft.part2.SetGame.model.GameRules;
import com.ngsoft.part2.SetGame.pojos.Card;
import com.ngsoft.part2.SetGame.pojos.GameStatus;
import com.ngsoft.part2.SetGame.pojos.PlayerStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyCollection;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class GamePlayTests {

    /**
     * Gameplay should be the main class to interact with the UX.
     * After starting new deck will be reset and shuffled.
     * 12 cards will be displayed on revealed cards list
     * Player may select a set of 3 cards by stating their index (0-11) within the revealed cards list
     * so there should probably be a method called play(int card1,int card2, int card3)
     * this method will return some sort of response object, stating whether success or not, what is the score,
     * the next 3 cards being dealt (that will be added to the revealed cards list), and whether the game is over)
     * How do I make the gameplay testable?
     */

    @Autowired
    private GamePlay gamePlay;

    private GameRules gameRules;

    @Before
    public void setup() {
        mockGameRules();
        gamePlay.start();
    }

    private void mockGameRules() {
        gameRules = Mockito.mock(GameRules.class);
        ReflectionTestUtils.setField(gamePlay, "gameRules", gameRules);
    }

    @Test
    public void WHEN_STARTING_12_CARDS_ARE_DEALT_FROM_DECK() {
        assertEquals(12, gamePlay.getRevealedCards().size());
    }

    @Test
    public void PLAY_WHEN_SET_SELECTED_THEN_GAME_STATUS_UPDATED() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(true);

        GameStatus gameStatus = gamePlay.play(1, 2, 3);
        PlayerStatus playerStatus = gameStatus.getPlayerStatus();

        assertFalse(gameStatus.isGameOver());
        assertTrue(playerStatus.isSuccess());
        assertEquals(1, playerStatus.getScore());
    }

    @Test
    public void PLAY_WHEN_SET_SELECTED_THEN_CARDS_ARE_REMOVED_FROM_REVEALED_LIST() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(true);
        HashSet<Card> selectedCards = new HashSet<>(gamePlay.getRevealedCards().subList(1, 3));

        gamePlay.play(1, 2, 3);

        List<Card> revealedCards = gamePlay.getRevealedCards();
        selectedCards.forEach(card -> assertFalse(revealedCards.contains(card)));
    }

    @Test
    public void PLAY_WHEN_SET_SELECTED_THEN_DEAL_ADDITIONAL_CARDS() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(true);

        gamePlay.play(1, 2, 3);

        assertEquals(12, gamePlay.getRevealedCards().size());
    }

    @Test
    public void PLAY_SETS_SELECTED_WHEN_DECK_CARDS_FINISHED_THEN_CARDS_ARE_NOT_DEALT() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(true);

        for (int i = 0; i < 24; i++) {
            gamePlay.play(1, 2, 3);
        }

        assertEquals(9, gamePlay.getRevealedCards().size());
    }

    @Test
    public void PLAY_WHEN_REVEALED_CARDS_FINISHED_THEN_GAME_OVER() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(true);

        for (int i = 0; i < 27; i++) {
            gamePlay.play(0, 1, 2);
        }

        assertEquals(0, gamePlay.getRevealedCards().size());
        assertTrue(gamePlay.getGameStatus().isGameOver());
    }

    @Test
    public void PLAY_WHEN_NOT_SET_SELECTED_THEN_GAME_STATUS_UPDATED() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(false);

        GameStatus gameStatus = gamePlay.play(1, 2, 3);
        PlayerStatus playerStatus = gameStatus.getPlayerStatus();

        assertFalse(gameStatus.isGameOver());
        assertFalse(playerStatus.isSuccess());
        assertEquals(-1, playerStatus.getScore());
    }

    @Test
    public void PLAY_WHEN_NOT_SET_SELECTED_THEN_CARDS_ARE_NOT_REMOVED_FROM_REVEALED_LIST() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(false);
        HashSet<Card> selectedCards = new HashSet<>(gamePlay.getRevealedCards().subList(1, 3));

        gamePlay.play(1, 2, 3);

        assertTrue(gamePlay.getRevealedCards().containsAll(selectedCards));
    }

    @Test
    public void PLAY_WHEN_NOT_SET_SELECTED_THEN_DEAL_ADDITIONAL_CARDS() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(false);

        gamePlay.play(1, 2, 3);

        assertEquals(15, gamePlay.getRevealedCards().size());
    }

    @Test
    public void PLAY_WHEN_SET_SELECTED_AND_THERE_ARE_EXTRA_REVEALED_CARDS_THEN_NOT_DEAL_ADDITIONAL_CARDS() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(false);
        gamePlay.play(1, 2, 3);
        assertEquals(15, gamePlay.getRevealedCards().size());

        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(true);
        gamePlay.play(4, 5, 6);
        assertEquals(12, gamePlay.getRevealedCards().size());
    }

    @Test
    public void PLAY_INVALID_SETS_SELECTED_WHEN_DECK_CARDS_FINISHED_THEN_CARDS_ARE_NOT_DEALT() {
        Mockito.when(gameRules.isSet(anyCollection())).thenReturn(false);

        for (int i = 0; i < 100; i++) {
            gamePlay.play(1, 2, 3);
        }

        assertEquals(81, gamePlay.getRevealedCards().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void PLAY_WHEN_CARD_INDEX_IS_NEGATIVE_THEN_THROWS_EXCEPTION() {
        gamePlay.play(-1, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void PLAY_WHEN_CARD_INDEX_IS_NOT_EXISTS_THEN_THROWS_EXCEPTION() {
        gamePlay.play(1, 2, 12);
    }

}

