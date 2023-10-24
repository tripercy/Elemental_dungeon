package cardgame.model.battleHandler;

import cardgame.model.Battle;
import cardgame.model.Element;
import cardgame.patterns.command.Command;
import cardgame.patterns.command.Invoker;
import cardgame.utils.BattleSelector;

public class BattleHandler implements Invoker {
    Battle currentBattle;
    BattleSelector selector;
    Command command;
    Element cardElement;

    public BattleHandler(Battle currentBattle) {
        this.currentBattle = currentBattle;
    }

    public BattleHandler() {
        this.currentBattle = null;
    }

    public Battle getCurrentBattle() {
        return currentBattle;
    }

    public void setCurrentBattle(Battle currentBattle, BattleSelector selector) {
        this.currentBattle = currentBattle;
        this.selector = selector;
    }

    private boolean check(String condition) {
        // Split the condition into tokens, the tokens are separated by the operator
        String[] tokens = condition.split("==|!=|<|>|<=|>=");

        // Get the operator
        String operator = condition.replaceAll("[a-zA-Z0-9]", "");

        int left = 0;

        switch (tokens[0]) {
            case "hp":
                left = currentBattle.getPlayerCharacter().getHealth();
                break;
            case "hand":
                left = currentBattle.getPlayerHand().getSize();
                break;
            case "deck":
                left = currentBattle.getPlayerDeck().getSize();
                break;
            case "enemy":
                left = currentBattle.getEnemies().size();
                break;
            default:
                break;
        }

        int right = Integer.parseInt(tokens[1]);

        switch (operator) {
            case "==":
                return left == right;
            case "!=":
                return left != right;
            case "<":
                return left < right;
            case ">":
                return left > right;
            case "<=":
                return left <= right;
            case ">=":
                return left >= right;
            default:
                return false;
        }
    }

    public void setSelector(BattleSelector selector) {
        this.selector = selector;
    }

    public void setCardElement(Element cardElement) {
        this.cardElement = cardElement;
    }

    @Override
    public void setCommand(String command) {
        command = command.toLowerCase();

        String[] tokens = command.split(" ");

        switch (tokens[0]) {
            case "if":
                if (check(tokens[1])) {
                    String newCommand = "";
                    for (int i = 2; i < tokens.length; i++) {
                        newCommand += tokens[i] + " ";
                    }
                    setCommand(newCommand);
                } else
                    this.command = new NullCommand();
                break;
            case "draw":
                this.command = new DrawCommand(currentBattle, command);
                break;
            case "discard":
                this.command = new DiscardCommand(currentBattle, command, selector);
                break;
            case "attack":
                this.command = new AttackCommand(currentBattle, command, selector, cardElement);
                break;
            case "heal":
                this.command = new HealCommand(currentBattle, command, cardElement);
                break;
            default:
                this.command = new NullCommand();
                break;
        }
    }

    @Override
    public void execute() {
        command.execute();
    }
}
