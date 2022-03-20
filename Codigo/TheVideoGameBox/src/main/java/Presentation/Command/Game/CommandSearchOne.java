package Presentation.Command.Game;

import Logic.Game.SAGame;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

public class CommandSearchOne implements ICommand {
    @Override
    public Context execute(Object data) {
        SAGame saGame = SAAbstractFactory.getInstance().createSAGame();
        TGame result = saGame.SearchOne((String) data);
        Context con;

        if(result != null) con = new Context(Event.RES_SEARCH_ONE_OK, result);
        else new Context(Event.RES_SEARCH_ONE_KO, null);

        return null;
    }
}