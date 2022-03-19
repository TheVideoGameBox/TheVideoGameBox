package Presentation.Controller;

import Presentation.Command.CommandAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.View.ViewAbstractFactory;

public class ApplicationControllerImp extends ApplicationController {
    @Override
    public void action(int event, Object data) {
        ICommand command = CommandAbstractFactory.getInstance().createCommand(event);

        if(command != null){
            Context context = command.execute(data);
            ViewAbstractFactory.getInstance().createView(context.getEvent()).update(context.getEvent(), context.getData());
        }else{
            ViewAbstractFactory.getInstance().createView(event);
        }
    }
}
