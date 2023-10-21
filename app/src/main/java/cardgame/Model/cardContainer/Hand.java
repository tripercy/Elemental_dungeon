package cardgame.model.cardContainer;

import java.util.ArrayList;
import java.util.List;

import cardgame.GameConfig;
import cardgame.model.Card;

public class Hand implements CardContainer {
    List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public Hand() {
        cards = new ArrayList<>();
    }

    @Override
    public int getCapacity() {
        return GameConfig.HAND_CAPACITY;
    }

    @Override
    public void setCapacity(int capacity) {
        
    }

    @Override
    public int getSize() {
        return cards.size();
    }

    @Override
    public void setSize(int size) {
        if (size < cards.size()) {
            cards = cards.subList(0, size);
        }
    }

    @Override
    public void addCard(Card card) {
        if (cards.size() < getCapacity()) {
            cards.add(card);
        }
    }

    @Override
    public void removeCard(Card card) {
        cards.removeIf(c -> c.equals(card));
    }

    @Override
    public void removeCard(int index) {
        cards.remove(index);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void clear() {
        cards.clear();
    }

    public Card play(int index) {
        return cards.remove(index);
    }

}
