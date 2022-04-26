package Logic;

import Logic.Box.SABox;
import Logic.Game.SAGame;
import Logic.User.SAUser;

public abstract class SAAbstractFactory {

    private static SAAbstractFactory instance;

    public static SAAbstractFactory getInstance() {
        if (instance == null)
            instance = new SAFactory();
        return instance;
    }

    public static void setInstance(SAAbstractFactory instance) {
        SAAbstractFactory.instance = instance;
    }

    public abstract SABox createSABox();

    public abstract SAGame createSAGame();

    public abstract SAUser createSAUser();

}
