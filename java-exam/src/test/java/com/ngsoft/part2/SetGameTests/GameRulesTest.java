package com.ngsoft.part2.SetGameTests;

import com.ngsoft.part2.SetGame.model.GameRules;
import com.ngsoft.part2.SetGame.pojos.Card;
import com.ngsoft.part2.SetGame.pojos.CardFeatures.Color;
import com.ngsoft.part2.SetGame.pojos.CardFeatures.ItemCount;
import com.ngsoft.part2.SetGame.pojos.CardFeatures.Shape;
import com.ngsoft.part2.SetGame.pojos.CardFeatures.Texture;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class GameRulesTest extends TestCase {

    @Autowired
    private GameRules gameRules;

    @Test
    public void IS_SET_RETURNS_FALSE_WHEN_GIVEN_NULL() {
        assertFalse(gameRules.isSet(null));
    }

    @Test
    public void IS_SET_RETURNS_FALSE_WHEN_NOT_GIVEN_3_CARDS() {
        assertFalse(gameRules.isSet(null));
        assertFalse(gameRules.isSet(Collections.emptySet()));
        assertFalse(gameRules.isSet(Collections.singleton(new Card(Shape.OVAL, Color.BLUE, ItemCount.ONE, Texture.FULL))));
    }

    @Test
    public void IS_SET_FALSE_WHEN_THE_CARDS_ARE_NOT_SET() {
        Set<Card> set = new HashSet<>();
        set.add(new Card(Shape.OVAL, Color.BLUE, ItemCount.TWO, Texture.FULL));
        set.add(new Card(Shape.DIAMOND, Color.GREEN, ItemCount.TWO, Texture.HOLLOW));
        set.add(new Card(Shape.OVAL, Color.PURPLE, ItemCount.TWO, Texture.STRIPED));

        gameRules.isSet(set);
    }

    @Test
    public void IS_SET_RETURNS_TRUE_WHEN_THE_CARDS_ARE_SET() {
        Set<Card> set = new HashSet<>();
        set.add(new Card(Shape.OVAL, Color.BLUE, ItemCount.TWO, Texture.FULL));
        set.add(new Card(Shape.OVAL, Color.GREEN, ItemCount.TWO, Texture.HOLLOW));
        set.add(new Card(Shape.OVAL, Color.PURPLE, ItemCount.TWO, Texture.STRIPED));

        gameRules.isSet(set);
    }

}