package Presentation.Command;

import Presentation.Controller.Context;

public interface ICommand {
    public Context execute(Object data);
}
