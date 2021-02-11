package com.ngsoft.part2.SetGameTests;

import com.ngsoft.part2.SetGame.GamePlay;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class GameplayTests {

    /**
     *   Gameplay should be the main class to interact with the UX.
     *   After starting new deck will be reset and shuffled.
     *   12 cards will be displayed on revealed cards list
     *   Player may select a set of 3 cards by stating their index (0-11) within the revealed cards list
     *   so there should probably be a method called play(int card1,int card2, int card3)
     *   this method will return some sort of response object, stating whether success or not, what is the score,
     *   the next 3 cards being dealt (that will be added to the revealed cards list), and whether the game is over)
     *   How do I make the gameplay testable?
     */





    @Autowired
    private GamePlay gamePlay;



    //These are the tests I can think of, but feel free to add some more.....


    @Before
    public void setup(){
        gamePlay.start();
    }


    @Test
    public void WHEN_STARTING_12_CARDS_ARE_DEALT_FROM_DECK(){

    }

    @Test
    public void CAN_SELECT_CARDS_AND_VERIFY(){

    }

    @Test
    public void IF_SELECTED_PROPER_SET_SCORE_IS_ENHANCED_BY_1(){

    }

    @Test
    public void IF_PROPER_SET_THEN_CARDS_ARE_REMOVED_FROM_REVEALED_LIST(){

    }

    @Test
    public void IF_PROPER_SET_THEN_NEW_CARDS_ARE_DEALT_UNLESS_DECK_IS_EMPTY(){

    }

    @Test
    public void FAILED_SET_ATTEMPT_REDUCES_SCORE_BY_1(){

    }




}
