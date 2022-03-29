package Presentation.View;

import java.util.List;

import Logic.Box.TBox;
import Logic.Game.TGame;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.Box.ViewAddGameToBox;
import Presentation.View.Box.ViewCreateBox;
import Presentation.View.Box.ViewListGamesBox;
import Presentation.View.Box.ViewSearchBoxesByName;
import Presentation.View.Game.ViewSearchGamesByName;
import Presentation.View.Game.ViewShowOne;
import Presentation.View.Main.ViewPrincipal;
import org.bson.types.ObjectId;

public class ViewFactory extends ViewAbstractFactory {

    @Override
    public IView createView(Context context) {
        switch (context.getEvent()) {
            case Event.VIEW:
                currentView = new ViewPrincipal();
				break;
			case Event.RES_SEARCH_ALL_BY_NAME_OK:
				currentView = new ViewSearchGamesByName((List<TGame>) context.getData());
				break;
			case Event.RES_SEARCH_ONE_OK:
				currentView = new ViewShowOne((TGame) context.getData());
				break;
			case Event.VIEW_CREATE_BOX:
				currentView = new ViewCreateBox();
				break;
			case Event.RES_CREATE_BOX_OK:
			case Event.RES_CREATE_BOX_KO:
				break;
			case Event.RES_SEARCH_ALL_BOXES_BY_NAME_OK:
				currentView = new ViewSearchBoxesByName((List<TBox>) context.getData());
				break;
			case Event.LIST_GAMES_OF_BOX:
				currentView = new ViewListGamesBox((TBox) context.getData());
				break;
			case Event.ADD_GAME_TO_BOX:
				currentView = new ViewAddGameToBox((ObjectId) context.getData());
				break;

        }

        return currentView;
    }

    @Override
    public IView getCurrentIView() {
        return currentView;
    }
}
