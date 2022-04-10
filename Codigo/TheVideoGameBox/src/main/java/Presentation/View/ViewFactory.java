package Presentation.View;

import Presentation.Controller.Event;
import Presentation.View.Box.ViewCreateBox;
import Presentation.View.Game.ViewSearchGamesByName;
import Presentation.View.Game.ViewShowOne;
import Presentation.View.Main.ViewMain;
import Presentation.View.User.ViewRegister;

public class ViewFactory extends ViewAbstractFactory {

    @Override
    public IView createView(int event) {
		IView view = null;

        switch (event) {
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
//			case Event.RES_SEARCH_ALL_BOXES_BY_NAME_OK:
//				view = new ViewSearchBoxesByName();
//				break;
//			case Event.LIST_GAMES_OF_BOX:
//				view = new ViewListGamesBox();
//				break;
//			case Event.ADD_GAME_TO_BOX:
//				view = new ViewAddGameToBox();
//				break;
			default:
				break;
		}

		return view;
    }
}
