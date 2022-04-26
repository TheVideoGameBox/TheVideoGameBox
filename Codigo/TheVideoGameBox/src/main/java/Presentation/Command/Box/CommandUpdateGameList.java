package Presentation.Command.Box;

import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

import java.util.List;

public class CommandUpdateGameList implements ICommand {

    @Override
    public Context execute(Object data) {
        SABox saBox = SAAbstractFactory.getInstance().createSABox();
        List<TGame> result = saBox.listGames((TBox) data);
        Context con;

        if (result != null) con = new Context(Event.RES_UPDATE_GAME_LIST_OK, result);
        else con = new Context(Event.RES_UPDATE_GAME_LIST_KO, null);

        return con;
    }
}
