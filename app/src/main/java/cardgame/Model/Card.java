package cardgame.model;

import java.util.Queue;

public class Card implements HasElement {
    private String name;
    private int cost;
    private Element element;
    private Queue<String> effects;

    public Card(String name, int cost, Element element, Queue<String> effects) {
        this.name = name;
        this.cost = cost;
        this.element = element;
        this.effects = effects;
    }

    public Element getElement() {
        return element;
    }

    public Queue<String> getEffects() {
        return effects;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getEffect() {
        return effects.poll();
    }

    public boolean hasEffect() {
        return !effects.isEmpty();
    }

    public void setEffects(Queue<String> effects) {
        this.effects = effects;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }
}
