package Presentation.View;

import Logic.Box.TBox;
import Logic.Game.TGame;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.Box.ViewCreateBox;
import Presentation.View.Box.ViewListGamesBox;
import Presentation.View.Box.ViewSearchBoxesByName;
import Presentation.View.Game.ViewSearchGamesByName;
import Presentation.View.Game.ViewShowOne;
import Presentation.View.Main.ViewMain;
import Presentation.View.User.ViewRegister;

import java.util.List;

public class ViewFactory extends ViewAbstractFactory {

    @Override
    public IView createView(Context context) {
		IView view = null;

        switch (context.getEvent()) {
            case Event.VIEW:
                view = new ViewMain();
				break;
			case Event.RES_SEARCH_ALL_BY_NAME_OK:
				view = new ViewSearchGamesByName();
				break;
			case Event.RES_SEARCH_ONE_OK:
				view = new ViewShowOne();
				break;
			case Event.VIEW_CREATE_BOX:
				view = new ViewCreateBox();
				break;
			case Event.VIEW_CREATE_USER:
				view = new ViewRegister();
				break;
			case Event.RES_SEARCH_ALL_BOXES_BY_NAME_OK:
				view = new ViewSearchBoxesByName();
				break;
			case Event.LIST_GAMES_OF_BOX:
				view = new ViewListGamesBox((TBox) context.getData());
				break;
//			case Event.ADD_GAME_TO_BOX:
//				view = new ViewAddGameToBox();
//				break;
			default:
				break;
		}
		return view;
    }
}
