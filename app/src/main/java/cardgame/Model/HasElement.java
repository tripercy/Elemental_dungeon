package cardgame.Model;

public interface HasElement {
    public Element getElement();

    public default boolean isCountered(Element otherElement){
        if (getElement() == Element.NEUTRAL || otherElement == Element.NEUTRAL){
            return false;
        }

        if (getElement() == Element.FIRE && otherElement == Element.WATER){
            return true;
        }

        if (getElement() == Element.WATER && otherElement == Element.EARTH){
            return true;
        }

        if (getElement() == Element.WOOD && otherElement == Element.METAL){
            return true;
        }

        if (getElement() == Element.METAL && otherElement == Element.FIRE){
            return true;
        }

        if (getElement() == Element.EARTH && otherElement == Element.WOOD){
            return true;
        }

        return false;
    }
}
