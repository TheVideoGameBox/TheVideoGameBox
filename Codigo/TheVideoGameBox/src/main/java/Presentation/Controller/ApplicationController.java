package Presentation.Controller;

public abstract class ApplicationController {
    private static ApplicationController instance;

    public static ApplicationController getInstance() {
        if (instance == null)
            instance = new ApplicationControllerImp();

        return instance;
    }

    public abstract void action(int event, Object data);
}
