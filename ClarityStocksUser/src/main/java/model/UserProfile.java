package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserProfile {
    private String firstName;
    private String lastName;
    private int age;
    private Profession profession;

    public UserProfile(){
        this.age=0;
        this.profession= Profession.UNKNOWN;
        this.firstName= "firstname";
        this.lastName= "lastname";
    }
    public UserProfile(String firstName, String lastName, int age, Profession profession){
        this.age=age;
        this.profession= profession;
        this.firstName= firstName;
        this.lastName= lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
    // Saves user information in a JSON file
    public void saveInformation(String filePath){
        Gson gson= new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        } catch ( IOException e){
            e.printStackTrace();
        }
    }
    // Load user information from a JSON file
    public static UserProfile loadInformation(String filePath){
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(filePath)){
            return gson.fromJson(reader, UserProfile.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}