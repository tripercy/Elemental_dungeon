package cardgame.model;

public class Enemy implements HasElement{
    private String name;
    private int hp;
    private int attack;

    private Element element;

    public Enemy(String name, int hp, int attack, Element element) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.element = element;
    }

    public Enemy() {
        this.name = "";
        this.hp = 0;
        this.attack = 0;
        this.element = Element.NEUTRAL;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public Element getElement() {
        return element;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
