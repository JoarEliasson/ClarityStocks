package user.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Manages the user profile information, including saving and loading the user profile.
 * <p>
 * The class uses the Gson library to serialize and deserialize the user profile information.
 * </p>
 *
 * @see UserProfile
 *
 * @author Ibrahim Tafankaji
 */
public class UserProfileManager {

  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    /**
     * Saves the user profile information to the specified file path.
     *
     * @param userProfile the user profile to be saved
     * @param filePath the file path where the user profile will be saved
     */
    public static void saveUserInformation(UserProfile userProfile, String filePath){
        try(FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(userProfile, writer);
        } catch ( IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Loads the user profile information from the specified file path.
     *
     * @param filePath the file path from which the user profile will be loaded
     * @return the loaded user profile, or null if an error occurred
     */
    public static UserProfile loadUserInformation(String filePath){
        try(FileReader reader = new FileReader(filePath)){
            return gson.fromJson(reader, UserProfile.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
