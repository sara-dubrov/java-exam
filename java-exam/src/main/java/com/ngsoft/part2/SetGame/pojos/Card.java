package com.ngsoft.part2.SetGame.pojos;

import java.util.Objects;

public class Card {

    private final CardFeatures.Shape shape;
    private final CardFeatures.Color color;
    private final CardFeatures.ItemCount itemCount;
    private final CardFeatures.Texture texture;

    public static Card createNew(CardFeatures.Shape shape, CardFeatures.Color color, CardFeatures.ItemCount itemCount, CardFeatures.Texture texture) {
        return new Card(shape, color, itemCount, texture);
    }

    public Card(CardFeatures.Shape shape, CardFeatures.Color color, CardFeatures.ItemCount itemCount, CardFeatures.Texture texture) {
        this.shape = shape;
        this.color = color;
        this.itemCount = itemCount;
        this.texture = texture;
    }

    public CardFeatures.Shape getShape() {
        return shape;
    }

    public CardFeatures.Color getColor() {
        return color;
    }

    public CardFeatures.ItemCount getItemCount() {
        return itemCount;
    }

    public CardFeatures.Texture getTexture() {
        return texture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return shape == card.shape &&
                color == card.color &&
                itemCount == card.itemCount &&
                texture == card.texture;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, color, itemCount, texture);
    }
}
