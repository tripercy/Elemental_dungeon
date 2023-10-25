package cardgame.utils;

public class RandomSelector implements BattleSelector {

    @Override
    public int selectEnemy() {
        return (int) (Math.random() * 3);
    }

    @Override
    public int selectCard() {
        return (int) (Math.random() * 3);
    }
    
}
