package Logic;

import Logic.Box.SABox;
import Logic.Box.SABoxImp;
import Logic.Game.SAGame;
import Logic.Game.SAGameImp;
import Logic.User.SAUser;
import Logic.User.SAUserImp;

public class SAFactory extends SAAbstractFactory {

    @Override
    public SABox createSABox() {
        return new SABoxImp();
    }

    @Override
    public SAGame createSAGame() {
        return new SAGameImp();
    }

    @Override
    public SAUser createSAUser() {
        return new SAUserImp();
    }

}
