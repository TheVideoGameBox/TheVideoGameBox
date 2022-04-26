package Presentation.Controller;

import Presentation.Command.CommandAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.View.ViewAbstractFactory;

public class ApplicationControllerImp extends ApplicationController {
    @Override
    public void action(Context context) {
        if (currentView != null)
            viewStack.push(currentView);

        ICommand command = CommandAbstractFactory.getInstance().createCommand(context.getEvent());

        if (command != null) {
            Context res = command.execute(context.getData());

            currentView = ViewAbstractFactory.getInstance().createView(res);

            if (currentView == null) {
                currentView = viewStack.pop();
                currentView.update(res);
            } else
                currentView.update(res);
        } else
            currentView = ViewAbstractFactory.getInstance().createView(context);
    }
}
