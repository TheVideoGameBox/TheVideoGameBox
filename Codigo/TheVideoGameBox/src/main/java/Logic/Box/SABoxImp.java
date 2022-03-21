package Logic.Box;

import java.util.Objects;

import Data.DAOAbstractFactory;
import Data.Box.DAOBox;

public class SABoxImp implements SABox {

	@Override
	public int createBox(TBox box) {
	int result=-1;
		
		DAOBox daoBox= DAOAbstractFactory.getInstance().createDAOBox();
		
		if(correctName(box.getName())&&correctDescription(box.getDescription())
			&&correctPrivacy(box.getPrivacy())&&correctCategory(box.getCategory())) {
			result=daoBox.create(box);
		}
		
		return result;
	}

	
	private boolean correctPrivacy(boolean privacy) {
		return !Objects.isNull(privacy);
	}



	private boolean correctCategory(String category) {
		return category.length()>0;
	}



	private boolean correctDescription(String description) {
		return description.length()>0&&description.length()<=250;
	}

	private boolean correctName(String name) {
		return name.length()>0 && name.length()<=50;
	}
	
	
}
