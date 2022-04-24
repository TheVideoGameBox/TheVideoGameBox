package Integration.Box;

import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.SABox;
import Logic.Box.TBox;
import Logic.SAAbstractFactory;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ModifyBox {

    private static ObjectId idBox;
    private static SABox saBox;
    private static TBox original = new TBox("TEST_MODIFY_BOX", "TEST_MODIFY_BOX_DESCRIPTION", Privacy.PRIVATE, new ArrayList<Genres>(Arrays.asList(Genres.INDIE, Genres.RACING, Genres.SHOOTER)));


    @BeforeClass
    public static void init() {
        saBox = SAAbstractFactory.getInstance().createSABox();
        idBox = saBox.createBox(original);
        original.setId(idBox);
    }

    @Test
    public void modifyName() {
        original.setName("NAME_MODIFIED");
        ObjectId correct = saBox.modifyBox(original);
        original.setName("TEST_MODIFY_BOX");
        assertEquals(correct, original.getId());
    }

    @Test
    public void modifyDescription() {
        original.setDescription("DESCRIPTION_MODIFIED");
        ObjectId correct = saBox.modifyBox(original);
        original.setDescription("TEST_MODIFY_BOX_DESCRIPTION");
        assertEquals(correct, original.getId());
    }

    @Test
    public void modifyPrivacy() {
        original.setPrivacy(Privacy.PUBLIC);
        ObjectId correct = saBox.modifyBox(original);
        original.setPrivacy(Privacy.PRIVATE);
        assertEquals(correct, original.getId());
    }

    @Test
    public void modifyGenres() {
        List<Genres> genres = new ArrayList<>(Arrays.asList(Genres.SPORTS, Genres.SURVIVAL));
        original.setGenres(genres);
        ObjectId correct = saBox.modifyBox(original);
        original.setGenres(new ArrayList<Genres>(Arrays.asList(Genres.INDIE, Genres.RACING, Genres.SHOOTER)));
        assertEquals(correct, original.getId());
    }

    @AfterClass
    public static void after() {
        if (idBox != null) saBox.deleteFromDatabase(idBox);
    }
}

