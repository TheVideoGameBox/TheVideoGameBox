package Presentation.Command;

import Presentation.Command.Game.CommandSearchAllByName;
import Presentation.Command.Game.CommandSearchOne;
import Presentation.Controller.Event;

public class CommandFactory extends CommandAbstractFactory{
    @Override
    public ICommand createCommand(int event) {

        return switch (event) {
            case Event.SEARCH_ALL_BY_NAME -> new CommandSearchAllByName();
            case Event.SEARCH_ONE -> new CommandSearchOne();
            default -> null;
        };
    }
}
