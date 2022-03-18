package Presentation.Command;

import Presentation.Command.Game.CommandReadAllByName;
import Presentation.Controller.Event;

public class CommandFactory extends CommandAbstractFactory{
    @Override
    public ICommand createCommand(int event) {

        switch (event) {
            case Event.SearchAllByName -> {
                return new CommandReadAllByName();
            }
        }

        return null;
    }
}
