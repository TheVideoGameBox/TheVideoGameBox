package Presentation.Command;

import Presentation.Command.Game.CommandSearchAllByName;
import Presentation.Controller.Event;

public class CommandFactory extends CommandAbstractFactory{
    @Override
    public ICommand createCommand(int event) {

        return switch (event) {
            case Event.SEARCH_ALL_BY_NAME -> new CommandSearchAllByName();
            default -> null;
        };
    }
}
