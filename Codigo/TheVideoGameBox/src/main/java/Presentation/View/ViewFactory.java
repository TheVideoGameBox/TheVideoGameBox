package Presentation.View;

import java.util.List;

import Logic.Game.TGame;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.Game.ViewSearchByName;
import Presentation.View.Main.ViewPrincipal;

public class ViewFactory extends ViewAbstractFactory{
    @SuppressWarnings("unchecked")
	@Override
    public IView createView(Context context) {
        return switch(context.getEvent()) {
        	case Event.VIEW -> new ViewPrincipal();
        	case Event.SEARCH_ALL_BY_NAME -> new ViewSearchByName((List<TGame>) context.getData());
        	default -> null;  
        };
    }
}
