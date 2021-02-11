package com.ngsoft.part2.SetGameTests;

import com.ngsoft.part2.SetGame.Deck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class DeckTests {

    @Autowired
    private Deck deck;


    @Test
    public void A_DECK_HAS_81_CARDS(){

    }

    @Test
    public void ALL_CARDS_ARE_DIFFERENT_IN_DECK(){

    }

    @Test
    public void DEAL_SELECT_RETURNS_REQUIRED_AMOUT_OF_CARDS_FROM_DECK(){

    }

    @Test void DEAL_REMOVES_DEALT_CARD_FROM_DECK(){

    }

    @Test
    public void SHUFFLING_WORKS(){
        //....mmmm how do I test this? the requirement seems unmeasureable. probably should check on google....
    }



}
