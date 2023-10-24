package cardgame.patterns.command;

public interface Invoker {
    public void setCommand(String command);

    public void execute();
}
