package cardgame.model.battleHandler;

import cardgame.model.Battle;
import cardgame.patterns.command.Command;
import cardgame.utils.BattleSelector;

public class DiscardCommand implements Command {
    private Battle currentBattle;
    private BattleSelector selector;

    private int quant;

    public DiscardCommand(Battle currentBattle, String command, BattleSelector selector){
        this.currentBattle = currentBattle;
        this.selector = selector;

        String[] parts = command.split(" ");
        quant = Integer.parseInt(parts[1]);
    }

    @Override
    public void execute() {
        for (int i = 0; i < quant; i++) {
            int card = selector.selectCard();
            currentBattle.discardCard(card);
        }
    }
    
}
