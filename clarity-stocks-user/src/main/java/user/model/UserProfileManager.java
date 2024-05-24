package user.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
    handles the saving and loading of user information from a local source (e.g. a .dat or .csv file).
    This class may depend on methods defined in the UserProfile
  */
public class UserProfileManager {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveUserInformation(UserProfile userProfile, String filePath){
        try(FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(userProfile, writer);
        } catch ( IOException e){
            e.printStackTrace();
        }
    }
    public static UserProfile loadUserInformation(String filePath){
        try(FileReader reader = new FileReader(filePath)){
            return gson.fromJson(reader, UserProfile.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
