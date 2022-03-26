package Presentation.Command;

import Presentation.Command.Box.CommandCreateBox;
import Presentation.Command.Game.CommandSearchAllByName;
import Presentation.Command.Game.CommandSearchOne;
import Presentation.Controller.Event;

public class CommandFactory extends CommandAbstractFactory{
    @Override
    public ICommand createCommand(int event) {
        ICommand command;
        switch (event) {
            case Event.SEARCH_ALL_BY_NAME:
                command = new CommandSearchAllByName();
                break;
            case Event.SEARCH_ONE:
                command = new CommandSearchOne();
                break;
            case Event.CREATE_BOX:
                command = new CommandCreateBox();
                break;
            default:
                command = null;
                break;
        }

        return command;
    }
}
