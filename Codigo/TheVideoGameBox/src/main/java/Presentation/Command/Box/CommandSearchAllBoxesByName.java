package Presentation.Command.Box;

import java.util.List;

import Logic.SAAbstractFactory;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

public class CommandSearchAllBoxesByName implements ICommand {

	@Override
	public Context execute(Object data) {
		SABox saBox = SAAbstractFactory.getInstance().createSABox();
		String aux= (String) data;
		List<TBox> result = saBox.searchAllBoxesByName(aux);
		Context con;

        if(result != null && !result.isEmpty())
			con = new Context(Event.RES_SEARCH_ALL_BOXES_BY_NAME_OK, result);
        else
			con = new Context(Event.RES_SEARCH_ALL_BOXES_BY_NAME_KO, null);

		return con;
	}

}
