// import javax.swing.JPanel;
// Represents a user profile
class UserProfile {
    private static int userIDCounter = 0;
    private int userID;
    protected String name;
    private String username;
    // Constructor to create a new user profile
    public UserProfile(String name, String username) {
        // Assigning a unique user ID 
        this.userID = userIDCounter;
        userIDCounter++;// Increment the user ID counter for the next user
        this.name = name;
        this.username = username;
    }//get the total number of user profiles created
    public int getUserIDCounter(){
        return userIDCounter;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //no setter for set username.
    public String getUsername() {
        return username;
    }
    //represent the user profile as a string
    @Override
    public String toString() {
        return "User Profile [userID=" + userID + ", name=" + name + ", username=" + username + "]";
    }

}


