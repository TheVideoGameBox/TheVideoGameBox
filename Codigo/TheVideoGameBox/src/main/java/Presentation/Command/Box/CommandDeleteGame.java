package Presentation.Command.Box;

import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;

import Logic.SAAbstractFactory;
import Logic.Box.SABox;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

public class CommandDeleteGame implements ICommand {

	@Override
	public Context execute(Object data) {
		SABox saBox = SAAbstractFactory.getInstance().createSABox();
		Pair<ObjectId, ObjectId> args = (Pair<ObjectId, ObjectId>) data;
		ObjectId result = saBox.deleteGame(args.getLeft(), args.getRight());
		Context con;

		if (result != null)
			con = new Context(Event.RES_DELETE_GAME_FROM_BOX_OK, result);
		else
			con = new Context(Event.RES_DELETE_GAME_FROM_BOX_KO, null);

		return con;
	}

}
