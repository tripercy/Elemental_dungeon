package cardgame.model.battleHandler;

import cardgame.model.Battle;
import cardgame.model.Element;
import cardgame.patterns.command.Command;
import cardgame.utils.BattleSelector;

public class AttackCommand implements Command{
    private Battle currentBattle;
    private BattleSelector selector;
    
    private int quant;
    private int damage;
    private Element element;
    
    public AttackCommand(Battle currentBattle, String command, BattleSelector selector, Element element){
        this.currentBattle = currentBattle;
        this.selector = selector;
        this.element = element;
        
        String[] parts = command.split(" ");
        quant = Integer.parseInt(parts[1]);
        damage = Integer.parseInt(parts[3]);
    }

    public void execute() {
        for (int i = 0; i < quant; i++) {
            int enemy = selector.selectEnemy();
            currentBattle.attackEnemy(enemy, damage, element);
        }
    }
}
