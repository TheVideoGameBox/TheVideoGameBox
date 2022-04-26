package Presentation.Command;

public abstract class CommandAbstractFactory {

    private static CommandAbstractFactory instance;

    public static CommandAbstractFactory getInstance() {
        if (instance == null)
            instance = new CommandFactory();
        return instance;
    }

    public abstract ICommand createCommand(int event);
}
