package Presentation.Command.Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import Presentation.Controller.Context;
import Presentation.Command.ICommand;
import Presentation.Controller.Event;

import java.util.List;

public class CommandSearchAllByName implements ICommand {

    @Override
    public Context execute(Object data) {
        SAGame saGame = SAAbstractFactory.getInstance().createSAGame();
        List<TGame> result = saGame.SearchAllByName((String) data);
        Context con;

        if(!result.isEmpty()) con = new Context(Event.RES_SEARCH_ALL_BY_NAME_OK, result);
        else con = new Context(Event.RES_SEARCH_ALL_BY_NAME_KO, result);

        return con;
    }
}
