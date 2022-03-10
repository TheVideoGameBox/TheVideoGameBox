package Data.Pet;

import Data.Connection;
import Logic.Person.Person;
import Logic.Pet.Pet;
import Logic.Pet.TPet;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PetData {

    public void add(TPet tPet) {
        try {
            MongoDatabase db = Connection.getDataBase();
            Pet pet = new Pet(tPet);

            if(!reactivate(tPet, db.getCollection("pets", Pet.class)))
                db.getCollection("pets", Pet.class).insertOne(pet);


        } catch (MongoException e) {

        }
    }

    public ObjectId delete(ObjectId _id) {
        try {
            MongoDatabase db = Connection.getDataBase();
            MongoCollection<Pet> collection = db.getCollection("pets", Pet.class);

            List<Pet> pets = new ArrayList<Pet>();
            collection.find(eq("_id", _id)).into(pets);

            if(pets.size() == 1){
                Pet pet = new Pet(pets.get(0).toTransfer());
                pet.setActivo(false);
                collection.replaceOne(eq("_id", _id), pet);
            }
        } catch (MongoException e) {
            return null;
        }
        return _id;
    }

    public List<TPet> readAll() {
        try {
            MongoDatabase db = Connection.getDataBase();
            List<TPet> ps = new ArrayList<>();
            FindIterable<Pet> iter = db.getCollection("pets", Pet.class).find();
            for(Pet p : iter) {
                ps.add(p.toTransfer());
            }
            return ps;
        } catch (MongoException e) {
            return null;
        }
    }

    public ObjectId update(TPet tPet) {
        try {
            MongoDatabase db = Connection.getDataBase();
            MongoCollection<Pet> collection = db.getCollection("pets", Pet.class);

            List<Pet> pets = new ArrayList<Pet>();
            collection.find(eq("_id", tPet.getId())).into(pets);

            if(pets.size() == 1){
                Pet pet = new Pet(pets.get(0).toTransfer());
                if(tPet.getNif() != null) pet.setNif(tPet.getNif());
                if(tPet.getNombre() != null) pet.setNombre(tPet.getNombre());
                if(tPet.getTipo() != null) pet.setTipo(tPet.getTipo());
                if(tPet.getInfoExtra() != null) pet.setInfoExtra(tPet.getInfoExtra());
                collection.replaceOne(eq("_id", pets.get(0).getId()), pet);
            }
        } catch (MongoException e) {
            return null;
        }

        return null;
    }

    private boolean reactivate(TPet tPet, MongoCollection<Pet> collection) {
        List<Pet> pets = new ArrayList<Pet>();
        collection.find(eq("nif", tPet.getNif())).into(pets);
        if(pets.size() == 1){
            Pet pet = new Pet(pets.get(0).toTransfer());
            pet.setActivo(true);
            collection.replaceOne(eq("_id", pets.get(0).getId()), pet);
            return true;
        }
        return false;
    }

}
