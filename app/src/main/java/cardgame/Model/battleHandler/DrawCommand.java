package cardgame.model.battleHandler;

import cardgame.model.Battle;
import cardgame.patterns.command.Command;

public class DrawCommand implements Command{
    private Battle currenBattle;
    private int quant;

    public DrawCommand(Battle currentBattle, String command) {
        this.currenBattle = currentBattle;

        String[] parts = command.split(" ");
        quant = Integer.parseInt(parts[1]);
    }

    @Override
    public void execute() {
        currenBattle.drawCard(quant);
    }
    
}
