package cardgame.model.battleHandler;

import cardgame.model.Battle;
import cardgame.patterns.command.Command;
import cardgame.patterns.command.Invoker;

public class BattleHandler implements Invoker{
    Battle currentBattle;
    Command command;

    public BattleHandler(Battle currentBattle) {
        this.currentBattle = currentBattle;
    }

    public BattleHandler() {
        this.currentBattle = null;
    }

    public Battle getCurrentBattle() {
        return currentBattle;
    }

    public void setCurrentBattle(Battle currentBattle) {
        this.currentBattle = currentBattle;
    }

    private boolean check(String condition){
        return true;
    }

    @Override
    public void setCommand(String command) {
        command = command.toLowerCase();

        String[] tokens = command.split(" ");

        switch(tokens[0]) {
            case "if":
                if (check(tokens[1])){
                    String newCommand = "";
                    for (int i = 2; i < tokens.length; i++) {
                        newCommand += tokens[i] + " ";
                    }
                    setCommand(newCommand);
                } else this.command = new NullCommand();
                break;
            case "draw":
                this.command = new DrawCommand(currentBattle, command);
                break;

            default:
                this.command = new NullCommand();
        }   
    }

    @Override
    public void execute() {
        
    }
}
