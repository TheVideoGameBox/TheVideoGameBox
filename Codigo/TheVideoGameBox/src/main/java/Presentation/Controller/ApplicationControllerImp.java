package Presentation.Controller;

import Presentation.Command.CommandAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.View.ViewAbstractFactory;

public class ApplicationControllerImp extends ApplicationController {
    @Override
    public void action(Context context) {
        ICommand command = CommandAbstractFactory.getInstance().createCommand(context.getEvent());

        if(command != null){
        	Context res = command.execute(context.getData());
        	if(ViewAbstractFactory.getInstance().createView(res) == null) {
        		 ViewAbstractFactory.getInstance().createView(context).update(res);
        	}
        }else{
        	ViewAbstractFactory.getInstance().createView(context);
        }
    }
}
