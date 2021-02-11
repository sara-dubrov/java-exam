package com.ngsoft.part2.SetGame;

public class Card {
    private CardFeatures.Shape shape;
    private CardFeatures.Color color;
    private CardFeatures.ItemCount itemCount;
    private CardFeatures.Texture texture;

    public static Card createNew(CardFeatures.Shape shape, CardFeatures.Color color, CardFeatures.ItemCount itemCount, CardFeatures.Texture texture){
        return new Card(shape,color,itemCount,texture);
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
}
