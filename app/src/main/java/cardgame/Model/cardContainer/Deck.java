package cardgame.model.cardContainer;

import java.util.LinkedList;
import java.util.List;

import cardgame.GameConfig;
import cardgame.model.Card;

public class Deck implements CardContainer {

    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public Deck() {
        this.cards = new LinkedList<>();
    }

    @Override
    public int getCapacity() {
        return GameConfig.DECK_CAPACITY;
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
        cards.add(card);
    }

    @Override
    public void removeCard(Card card) {
        for (Card c : cards) {
            if (c.getName().equals(card.getName())) {
                cards.remove(c);
                break;
            }
        }
    }

    @Override
    public void removeCard(int index) {
        cards.remove(index);
    }

    public Card draw() {
        return cards.remove(0);
    }

    public void shuffle() {
        for (int m = 0; m < 3; m++)
            for (int i = 0; i < cards.size(); i++) {
                int randomIndex = (int) (Math.random() * cards.size());
                Card temp = cards.get(i);
                cards.set(i, cards.get(randomIndex));
                cards.set(randomIndex, temp);
            }
    }

    public List<Card> getCards() {
        return cards;
    }
}
