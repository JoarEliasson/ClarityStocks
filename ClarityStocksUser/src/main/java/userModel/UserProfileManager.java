package userModel;

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

    // Saves user information in a JSON file
    public void saveUserInformation(UserProfile userProfile, String filePath){
        Gson gson= new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(userProfile, writer);
            System.out.println("User profile saved successfully.");
        } catch ( IOException e){
            e.printStackTrace();
        }
    }
    // Load user information from a JSON file
    public static UserProfile loadUserInformation(String filePath){
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(filePath)){
            System.out.println("User profile loaded successfully.");
            return gson.fromJson(reader, UserProfile.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
