package Presentation.Command.Game;

import java.util.List;

import Logic.SAAbstractFactory;
import Logic.Game.SAGame;
import Logic.Game.TGame;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

public class CommandSearchAllByPlatform implements ICommand {

	@Override
    public Context execute(Object data) {
        SAGame saGame = SAAbstractFactory.getInstance().createSAGame();
        List<TGame> result = saGame.searchAllByPlatform((String) data);
        
        Context con;

        if(result != null && !result.isEmpty()) con = new Context(Event.RES_SEARCH_ALL_BY_PLATFORM_OK, result);
        else con = new Context(Event.RES_SEARCH_ALL_BY_PLATFORM_KO, result);

        return con;
    }

}
