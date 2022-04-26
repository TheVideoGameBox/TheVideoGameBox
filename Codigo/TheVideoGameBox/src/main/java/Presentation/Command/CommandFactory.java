package Presentation.Command;

import Presentation.Command.Box.*;
import Presentation.Command.Game.CommandRandomGames;
import Presentation.Command.Game.CommandSearchAllByName;
import Presentation.Command.Game.CommandSearchAllByPlatform;
import Presentation.Command.Game.CommandSearchOne;
import Presentation.Command.User.*;
import Presentation.Controller.Event;

public class CommandFactory extends CommandAbstractFactory {
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
            case Event.MODIFY_BOX:
                command = new CommandModifyBox();
                break;
            case Event.SHOW_BOX:
                command = new CommandShowBox();
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
            case Event.SEARCH_ALL_BY_PLATFORM:
                command = new CommandSearchAllByPlatform();
                break;
            case Event.DELETE_GAME_FROM_BOX:
                command = new CommandDeleteGame();
                break;
            case Event.DELETE_BOX:
                command = new CommandDeleteBox();
                break;
            case Event.UPDATE_GAME_LIST:
                command = new CommandUpdateGameList();
                break;
            case Event.ASSOCIATE_BOX:
                command = new CommandAssociateBox();
                break;
            case Event.USER_BOXES:
                command = new CommandUserBoxes();
                break;
            case Event.UPDATE_USER_BOX_LIST:
                command = new CommandUpdateUserBoxList();
                break;
            case Event.DELETE_BOX_FROM_USER:
                command = new CommandDeleteBoxFromUser();
                break;
            default:
                break;
        }
        return command;
    }

}
