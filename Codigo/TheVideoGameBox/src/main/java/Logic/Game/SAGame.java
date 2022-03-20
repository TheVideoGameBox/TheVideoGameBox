package Logic.Game;

import java.util.List;

public interface SAGame {
    public List<TGame> searchAllByName(String name);

    public TGame SearchOne(String name);

}
