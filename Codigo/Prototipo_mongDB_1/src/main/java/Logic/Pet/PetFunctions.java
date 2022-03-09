package Logic.Pet;

import Data.Pet.PetData;
import org.bson.types.ObjectId;

import java.util.List;

public class PetFunctions {

    public ObjectId add(TPet tPet) {
        if (tPet.getNif().length() != 9) return null;
        else if (!tPet.getNif().substring(0, 9).matches("^[0-9]+$")) return null;

        PetData petData = new PetData();
        petData.add(tPet);
        return tPet.getId();
    }

    public ObjectId delete(ObjectId _id) {
        PetData petData = new PetData();
        petData.delete(_id);
        return _id;
    }

    public List<TPet> readAll() {
        PetData petData = new PetData();
        return petData.readAll();
    }

    public int update(TPet tPet) {
        PetData petData = new PetData();
        petData.update(tPet);
        return 0;
    }
}
