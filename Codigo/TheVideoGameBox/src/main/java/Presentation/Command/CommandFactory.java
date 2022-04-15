package Presentation.Command;

import Presentation.Command.Box.CommandCreateBox;
import Presentation.Command.Box.CommandListGames;
import Presentation.Command.Box.CommandSearchAllBoxesByName;
import Presentation.Command.Game.CommandRandomGames;
import Presentation.Command.Game.CommandSearchAllByName;
import Presentation.Command.Game.CommandSearchOne;
import Presentation.Command.User.CommandCreateUser;
import Presentation.Command.User.CommandLogIn;
import Presentation.Controller.Event;

public class CommandFactory extends CommandAbstractFactory{
    @Override
    public ICommand createCommand(int event) {
        ICommand command = null;
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
            case Event.SEARCH_ALL_BOXES_BY_NAME:
                command = new CommandSearchAllBoxesByName();
                break;
            case Event.CREATE_USER:
            	command = new CommandCreateUser();
            	break;
            case Event.LIST_GAMES_OF_BOX:
                command = new CommandListGames();
                break;
            case Event.RANDOM_GAMES:
                command = new CommandRandomGames();
                break;
            case Event.LOGIN_USER:
                command = new CommandLogIn();
                break;
            default:
                break;
        }
        return command;
    }
}
