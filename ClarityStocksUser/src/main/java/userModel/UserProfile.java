package userModel;

public class UserProfile {
    private String firstName;
    private String lastName;
    private int age;

    public UserProfile(){
        this.age=0;
        this.firstName= "firstname";
        this.lastName= "lastname";
    }
    public UserProfile(String firstName, String lastName, int age){
        this.age=age;
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

}