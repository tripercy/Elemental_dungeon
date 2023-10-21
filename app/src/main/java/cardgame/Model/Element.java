package cardgame.Model;

public enum Element {
    FIRE ("fire"), 
    WATER ("water"), 
    EARTH ("earth"), 
    WOOD ("wood"), 
    METAL ("metal"), 
    NEUTRAL ("neutral");

    private final String name;

    Element(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}