package cardgame.model.cardContainer;

import cardgame.model.Card;

public interface CardContainer {
    public int getCapacity();

    public void setCapacity(int capacity);

    public int getSize();

    public void setSize(int size);

    public void addCard(Card card);

    public void removeCard(Card card);

    public void removeCard(int index);
}
