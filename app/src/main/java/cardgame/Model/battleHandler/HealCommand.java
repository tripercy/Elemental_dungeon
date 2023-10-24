package cardgame.model.battleHandler;

import cardgame.model.Battle;
import cardgame.model.Element;
import cardgame.patterns.command.Command;

public class HealCommand implements Command{
    private int quant;
    private Battle currentBattle;
    private Element element;

    public HealCommand(Battle currentBattle, String command, Element element){
        this.currentBattle = currentBattle;
        this.element = element;

        String[] parts = command.split(" ");
        quant = Integer.parseInt(parts[1]);
    }

    @Override
    public void execute() {
        currentBattle.heal(quant, element);
    }
}
