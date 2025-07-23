import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class demo {
    // Maps to store player and usename
    private static Map<String, Player> profiles = new HashMap<>();
    // Maps to store player and profiles with ID
    private static Map<Integer, Player> profilesWithID = new HashMap<>();
    // Current player being operated on. then we can switch to other player
    public static Player currentPlayer;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //create a default player to play with other players
        Player player = new Player("GeraltofRivia", "whitewolf");
        profiles.put("whitewolf", player);
        profilesWithID.put(player.userProfile.getUserID(), player);
        player.setIsDefault();
        //create an army for the default player
        Charactors ranger = new Ranger();
        Charactors squire = new Squire();
        Charactors warlock = new Warlock();
        Charactors medic = new Medic();
        Charactors dragon = new Dragon();
        List<Charactors> armyList = new ArrayList<>();
        //add default player's army to his army list
        armyList = player.getArmyList();
        armyList.add(ranger);
        armyList.add(squire);
        armyList.add(warlock);
        armyList.add(medic);
        armyList.add(dragon);
        player.setArmyList(armyList);
        //add charactors to default player
        HashMap<String, Charactors> armyMap = new HashMap<>();
        armyMap = player.getArmyMap();
        armyMap.put("Ranger", ranger);
        armyMap.put("Squire", squire);
        armyMap.put("Warlock", warlock);
        armyMap.put("Medic", medic);
        armyMap.put("Dragon", dragon);
        player.setArmyMap(armyMap);
        //set default player's gold coins equals to 215
        player.setGoldCoins(-15);
        player.setXP(32);

        //set a homeground for default player
        player.setHomeGround("Marshland");

        //set artefact and armour
        player.buyArmour("Ranger","Chainmail");
        player.buyArtefact("Medic", "Amulet");
        //ask what should player want to do and call the method as player want
        boolean continuesA = true;//to controll the while loop
        while (continuesA) {
            System.out.println("1. Create User Profile");
            System.out.println("2. Change Name");
            System.out.println("3. Next");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choiceA = scanner.nextInt();
            scanner.nextLine(); // Consume the newline characte
            switch (choiceA) {
                case 1:
                    createUserProfile(scanner);
                    break;
                case 2:
                    changeName(scanner);
                    break;
                case 3:
                    boolean continuesB = true;//to controll the while loop
                    while (continuesB) {
                        System.out.println("1. Add characters");
                        System.out.println("2. Add armours");
                        System.out.println("3. Add artefacts");
                        System.out.println("4. Sell characters");
                        System.out.println("5. Change current player using username");
                        System.out.println("6. Next");
                        System.out.println("7. Back");
                        System.out.println("Enter your choice: ");
                        int choiceB = scanner.nextInt();
                        scanner.nextLine();
                        switch (choiceB){
                            case 1:
                                if(currentPlayer != null){
                                    currentPlayer.setCharactors();
                                    System.out.println("Your existing gold coins : "+currentPlayer.getGoldCoins());
                                }else{
                                    System.out.println("First you have to Create User Profile !");
                                    continuesB = false;
                                }
                                break;
                            case 2:
                                if(currentPlayer.getArmyList().size() != 0){
                                    getArmourTool(scanner);
                                }else{
                                    System.out.println("First you have to add characters !");
                                }                               
                                break;
                            case 3:
                                if(currentPlayer.getArmyList().size() != 0){
                                    getArtefactTool(scanner);
                                }else{
                                    System.out.println("First you have to add characters !");
                                }                               
                                break;
                            case 4:
                                sellArmyCharactors(scanner);
                                break;
                            case 5:
                                System.out.println("Enter user name :");
                                String ans = scanner.nextLine();
                                scanner.nextLine();
                                if(profiles.containsKey(ans)){
                                    currentPlayer = profiles.get(ans);
                                    System.out.println("Current player is : "+currentPlayer.getUserName());
                                }else{
                                    System.out.println("Invalid username !");
                                }
                                break;
                            case 6:
                                boolean continuesC = true;//to controll the while loop
                                while (continuesC) {
                                    System.out.println("1. Challange to player");
                                    System.out.println("2. Back");
                                    System.out.println("3. Exit");
                                    System.out.println("Enter your choice: ");
                                    int choiceC = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (choiceC){
                                        case 1:
                                            currentPlayer.declareWar(profilesWithID);
                                            break;
                                        case 2:
                                            continuesC = false;
                                            break;
                                        case 3:
                                            continuesA = false;
                                            continuesB = false;
                                            continuesC = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                    }
                                }
                                break;
                            case 7:
                                continuesB = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    continuesA = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // Create a new player profile
    private static void createUserProfile(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            if (profiles.containsKey(username)) {
                System.out.println("Username already taken. Please enter a new username.");
            } else {
                break;
            }
        }

        Player player = new Player(name, username);
        player.setHomeGround();
        profiles.put(username, player);
        profilesWithID.put(player.userProfile.getUserID(), player);
        currentPlayer = profilesWithID.get(profilesWithID.size()-1);
        System.out.println("User profile created successfully.");
        System.out.println(player.userProfile);
        System.out.println("Now you have to add army. Please press 3 to go next!");
    }
    //change the name of an existing user profile
    private static void changeName(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        Player player = profiles.get(username);
        if (player.userProfile == null) {
            System.out.println("User profile not found.");
        } else {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            player.userProfile.setName(newName);
            System.out.println("Name changed successfully.");
            System.out.println(player.userProfile);
        }
    }
    //add armour to a player's character
    public static void getArmourTool(Scanner scanner){
        System.out.println("Now you can add Armour to a player.");
        // Display available characters
        int chrNum=1;
        for(Charactors charA : currentPlayer.getArmyList()){
            System.out.println(chrNum+"."+charA.getName());
            chrNum++;
        }
        System.out.println("Enter the charactor number that you want add armour.");
        int charaInteger = scanner.nextInt();
        scanner.nextLine();
        // Display available armours with prices
        System.out.println("1.Chainmail (70 gc)");
        System.out.println("2.Regalia (105 gc)");
        System.out.println("3.Fleece (150 gc)");
        System.out.println("Enter the armour number that you want add armour.");
        int armourInt = scanner.nextInt();
        scanner.nextLine();
        String armString =null;
        switch (armourInt) {
            case 1:
                armString="Chainmail";
                break;
            case 2:
                armString="Regalia";
                break;
            case 3:
                armString="Fleece";
                break;        
        
            default:
                System.out.println("Invalid Choice! ");
                break;
        }
        // Add armour to the selected character
        if(armString !=null) currentPlayer.buyArmour(currentPlayer.getArmyList().get(charaInteger-1).getName(), armString);
    }

    public static void getArtefactTool(Scanner scanner){
        //add artefact to a player's character
        System.out.println("Now you can add Artefact to a player.");
        //// Display available characters
        int chrNum=1;
        for(Charactors charA : currentPlayer.getArmyList()){
            System.out.println(chrNum+"."+charA.getName());
            chrNum++;
        }
        System.out.println("Enter the charactor number that you want add Artefacts.");
        int charaInteger = scanner.nextInt();
        scanner.nextLine();
        // Display available artefacts
        System.out.println("1.Excalibur (150 gc)");
        System.out.println("2.Amulet (200 gc)");
        System.out.println("3.Crystal (210 gc)");
        System.out.println("Enter the armour number that you want add Artefacts.");
        int artefactsInt = scanner.nextInt();
        scanner.nextLine();
        String artString =null;
        switch (artefactsInt) {
            case 1:
                artString="Excalibur";
                break;
            case 2:
                artString="Amulet";
                break;
            case 3:
                artString="Crystal";
                break;        
        
            default:
                System.out.println("Invalid Choice! ");
                break;
        }
        // Add artefact to the selected character
        if(artString !=null) currentPlayer.buyArtefact(currentPlayer.getArmyList().get(charaInteger-1).getName(), artString);
        
    }
    public static void sellArmyCharactors(Scanner scanner){
        // Method to sell a character from a player's army
        // Display available characters
        int chrNum=1;
        for(Charactors charA : currentPlayer.getArmyList()){
            System.out.println(chrNum+"."+charA.getName());
            chrNum++;
        }
        System.out.println("Enter the charactor number that you want to remove.");
        int charaInteger = scanner.nextInt();
        scanner.nextLine();
        try{
            // Attempt to sell the selected character
            currentPlayer.sellCharactors(currentPlayer.getArmyList().get(charaInteger-1).getName());
        }
        catch(Exception e){
            System.out.println("Somthing went wrong...");
        }
        System.out.println("Your gold coin amount : "+ currentPlayer.getGoldCoins());
    }

}

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


class Charactors {
    // Attributes of character
    private int attackPriority;
    private int receivePriority;
    Armour myArmour= null;
    Artefact myArtefact= null;
    private int price;
    private double attack;
    private double defence;
    private double health;
    private double speed;
    private String myType;
    private String myHomeLand;
    private String name;
    // Temporary storage for swapping artefacts and armours
    Armour tempArmour;
    Artefact tempArtefact;
    Scanner scanner = new Scanner(System.in);

    public Charactors(int price,double attack,double defence,double health,double speed, String myType, String myHomeLand, String name){
        this.price = price;
        this.defence = defence;
        this.health = health;
        this.speed = speed;
        this.attack = attack;
        this.myType = myType;
        this.myHomeLand = myHomeLand;
        this.name = name;
    }
    // Getters and setters for attributes
    public String getName(){
        return name;
    }

    public String getMyType(){
        return myType;
    }

    public String getHomeLand(){
        return myHomeLand;
    }
    public void setAttackPriority(int num){
        this.attackPriority = num;
    }
    public int getAttackPriority(){
        return attackPriority;
    }
    public void setReceivePriority(int num){
        this.receivePriority = num;
    }
    public int getReceivePriority(){
        return receivePriority;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int newPrice){
        price += newPrice;
    }
    public double getattack(){
        return attack;
    }
    public void setAttack(double newAttack){
        attack += newAttack;
    }
    public double gethealth(){
        //round health up to one decimal point
        double roundedNumber = Math.round(health * 10) / 10.0;
        if(roundedNumber < 0) roundedNumber =0;
        return roundedNumber;
    }
    public void setHealth(double newHealth){
        health += newHealth;
    }
    public double getspeed(){
        return speed;
    }
    public void setSpeed(double newSpeed){
        speed += newSpeed;
    }
    public double getDefence(){
        return defence;
    }
    public void setDefence(double newDefence){
        defence += newDefence;
    }
    // Set artefact for the character
    public void setArtefact(String art, Player playerName, boolean isDefault){
        // Check if the character already has an artefact
        if(this.myArtefact == null){
            // Create the artefact based on the user's choice
            if(art.equals("Excalibur")){
                this.myArtefact = new Excalibur();
            }else if(art.equals("Amulet")){
                this.myArtefact = new Amulet();
            }else if(art.equals("Crystal")){
                this.myArtefact = new Crystal();
            }
            
            
        }else{
            // If the character already has an artefact, ask the user if they want to replace it
            System.out.println("You can't take two artefacts ! If you want to buy new one and remove existing one? (Yes/No)");
            String ans = scanner.nextLine();
            scanner.nextLine();
            if(ans.equals("Yes")){
                tempArtefact = this.getArtefact();
                removeArtefact();
                setArtefact(art, playerName, isDefault);
            }
        }
        // Calculate the rounded value for the artefact's price
        int roundedValue = (int) Math.round(myArtefact.getPrice()*0.2);
        // Check if the player has enough gold coins to buy the artefact
        if(roundedValue > playerName.getGoldCoins()){
            System.out.println("You can't get this artefact due to low coins...");
            // If not enough coins, revert changes
            if(tempArtefact == null){
                this.myArtefact = null;
            }else{
                this.myArtefact = tempArtefact;
            }
            
        }
        else if(myArtefact != null){
            // If enough coins, deduct the price from the player's gold coins
            playerName.setGoldCoins(-(myArtefact.getPrice()));
            // Update the character's attributes based on the artefact
            this.setPrice(roundedValue);
            this.setHealth(myArtefact.getDifHealth());
            this.setDefence(myArtefact.getDifDefence());
            this.setSpeed(myArtefact.getDifSpeed());
            // Add the artefact to the player's artefact map
            playerName.setArtefactMap(art, this.getName());
            if(!isDefault) {
                System.out.println(art+" added sucessfully.");
                System.out.println("Your remaining gold coins amount : "+playerName.getGoldCoins());
            }
        }
    }
    // Set armour for the character
    public void setArmour(String arm, Player playerName, boolean isDefault){
        // Check if the character already has an armour
        if(this.myArmour == null){
            // Create the armour based on the user's choice
            if(arm.equals("Chainmail")){
                this.myArmour = new Chainmail();
            }else if(arm.equals("Regalia")){
                this.myArmour = new Regalia();
            }else if(arm.equals("Fleece")){
                this.myArmour = new Fleece();
            }
            
        }else{
            // If the character already has an armour, ask the user if they want to replace it
            System.out.println("You can't take two armours ! If you want to buy new one and remove existing one? (Yes/No)");
            String ans = scanner.nextLine();
            scanner.nextLine();
            if(ans.equals("Yes")){
                tempArmour = this.getArmour();
                removeArmours();
                setArmour(arm, playerName, isDefault);
            }
        }
        // Calculate the rounded value for the armour's price
        int roundedValue = (int) Math.round(myArmour.getPrice()*0.2);
        // Check if the player has enough gold coins to buy the armour
        if(roundedValue > playerName.getGoldCoins()){
            System.out.println("You can't get this armour due to low coins...");
            // If not enough coins, revert changes
            if(tempArmour == null){
                this.myArmour = null;
            }else{
                this.myArmour = tempArmour;
            }
        }
        else if(myArmour != null){
            // If enough coins, deduct the price from the player's gold coins
            playerName.setGoldCoins(-(myArmour.getPrice()));
            // Update the character's attributes based on the armour
            this.setPrice(roundedValue);
            this.setHealth(myArmour.getDifHealth());
            this.setDefence(myArmour.getDifDefence());
            this.setSpeed(myArmour.getDifSpeed());
            // Add the armour to the player's armour map
            playerName.setArmourMap(arm, this.getName());
            if(!isDefault) {
                System.out.println(arm+" added sucessfully.");
                System.out.println("Your remaining gold coins amount : "+playerName.getGoldCoins());
            }
        }
    }
    //remove armour
    public void removeArmours(){
        this.myArmour = null;
        System.out.println("Armour deleted successfully. ");
    }
    //remove artefact
    public void removeArtefact(){
        this.myArtefact = null;
        System.out.println("Artefact deleted successfully. ");
    }

    public Armour getArmour(){
        return myArmour;
    }
    public Artefact getArtefact(){
        return myArtefact;
    }

}
//Create classes for characters extending Charactors class
class Shooter extends Charactors {
    public Shooter() {
        super(80, 11, 4, 6, 9, "Archers", "Hillcrest","Shooter");
    }
}

class Ranger extends Charactors {
    public Ranger() {
        super(115,14,5,8,10,"Archers","Hillcrest","Ranger");
    }
}

class Sunfire extends Charactors {
    public Sunfire() {
        super(160,15,5,7,14,"Archers","Desert","Sunfire");
    }
}

class Zing extends Charactors {
    public Zing() {
        super(200,16,9,11,14,"Archers","Desert","Zing");
    }
}

class Saggitarius extends Charactors {
    public Saggitarius() {
        super(230,18,7,12,17,"Archers","Arcane","Saggitarius");
    }
}

class Squire extends Charactors {
    public Squire() {
        super(85,8,9,7,8,"Knights","Marshland","Squire");
    }
}

class Cavalier extends Charactors {
    public Cavalier() {
        super(110,10,12,7,10,"Knights","Hillcrest","Cavalier");
    }
}

class Templar extends Charactors {
    public Templar() {
        super(155,14,16,12,12,"Knights","Desert","Templar");
    }
}

class Zoro extends Charactors {
    public Zoro() {
        super(180,17,16,13,14,"Knights","Hillcrest","Zoro");
    }
}

class Swiftblade extends Charactors {
    public Swiftblade() {
        super(250,18,20,17,13,"Knights","Marshland","Swiftblade");
    }
}

class Warlock extends Charactors {
    public Warlock() {
        super(100,12,7,10,12,"Mages","Marshland","Warlock");
    }
}

class Illusionist extends Charactors {
    public Illusionist() {
        super(120,13,8,12,14,"Mages","Arcane","Illusionist");
    }
}

class Enchanter extends Charactors {
    public Enchanter() {
        super(160,16,10,13,16,"Mages","Hillcrest","Enchanter");
    }
}

class Conjurer extends Charactors {
    public Conjurer() {
        super(195,18,15,14,12,"Mages","Hillcrest","Conjurer");
    }
}

class Eldritch extends Charactors {
    public Eldritch() {
        super(270,19,17,18,14,"Mages","Arcane","Eldritch");
    }
}

class Soother extends Charactors {
    public Soother() {
        super(95,10,8,9,6,"Healers","Desert","Soother");
    }
}

class Medic extends Charactors {
    public Medic() {
        super(125,12,9,10,7,"Healers","Hillcrest","Medic");
    }
}



class Alchemist extends Charactors {
    public Alchemist() {
        super(150,13,13,13,13,"Healers","Marshland","Alchemist");
    }
}

class Saint extends Charactors {
    public Saint() {
        super(200,16,14,17,9,"Healers","Arcane","Saint");
    }
}

class Lightbringer extends Charactors {
    public Lightbringer() {
        super(260,17,15,19,12,"Healers","Desert","Lightbringer");
    }
}

class Dragon extends Charactors {
    public Dragon() {
        super(120,12,14,15,8,"MythicalCreatures","Desert","Dragon");
    }
}

class Basilisk extends Charactors {
    public Basilisk() {
        super(165,15,11,10,12,"MythicalCreatures","Marshland","Basilisk");
    }
}

class Hydra extends Charactors {
    public Hydra() {
        super(205,12,16,15,11,"MythicalCreatures","Marshland","Hydra");
    }
}

class Phoenix extends Charactors {
    public Phoenix() {
        super(275,17,13,17,19,"MythicalCreatures","Desert","Phoenix");
    }
}

class Pegasus extends Charactors {
    public Pegasus() {
        super(340,14,18,20,20,"MythicalCreatures","Arcane","Pegasus");
    }
}

class Armour{
    private int price;
    private double difDefence;
    private double difHealth;
    private double difSpeed;
    public Armour(int price,double difDefence,double difHealth,double difSpeed){
        this.price = price;
        this.difDefence = difDefence;
        this.difHealth = difHealth;
        this.difSpeed = difSpeed;
    }
    //get the price of the armor
    public int getPrice(){
        return price;
    }
    // get the difference in defense provided by the armor
    public double getDifDefence(){
        return difDefence;
    }
    //get the difference in health provided by the armor
    public double getDifHealth(){
        return difHealth;
    }
    //get the difference in speed provided by the armor
    public double getDifSpeed(){
        return difSpeed;
    }
}
//// Represents specific armor types
class Chainmail extends Armour{
    public Chainmail(){
        super(70,1,0,-1);
    }
}

class Regalia extends Armour{
    public Regalia(){
        super(105,1,0,0);
    }
}

class Fleece extends Armour{
    public Fleece(){
        super(150,2,1,-1);
    }
}
// Represents an artefact item
class Artefact{
    private int price;
    private double difDefence;
    private double difHealth;
    private double difSpeed;
    private double difAttack;
    public Artefact(int price,double difDefence,double difHealth,double difSpeed,double difAttack){
        this.price = price;
        this.difDefence = difDefence;
        this.difHealth = difDefence;
        this.difSpeed = difSpeed;
        this.difAttack = difAttack;
    }
    //to get the price of the artefact
    public int getPrice(){
        return price;
    }
    //get the difference in defense provided by the artefact
    public double getDifDefence(){
        return difDefence;
    }
    //get the difference in health provided by the artefact
    public double getDifHealth(){
        return difHealth;
    }
    //get the difference in speed provided by the artefact
    public double getDifSpeed(){
        return difSpeed;
    }
    //get the difference in attack provided by the artefact
    public double getDifAttack(){
        return difAttack;
    }
}
// Represents specific artefact types extending Artefact class
class Excalibur extends Artefact{
    public Excalibur(){
        super(150,0,0,0,2);
    }
}

class Amulet extends Artefact{
    public Amulet(){
        super(200,-1,1,1,1);
    }
}

class Crystal extends Artefact{
    public Crystal(){
        super(210,1,-1,-1,2);
    }
}

class Player{
    UserProfile userProfile;// User profile of the player
    public Player(String name, String username){
        this.userProfile = new UserProfile(name, username);
    }
    Scanner scanner = new Scanner(System.in);
    private int goldCoins=500;// default amount of gold coins owned by the player
    private int xp=0;
    private String homeGround;
    private boolean isDefault =false;//to find that player is default player or not
    // Maps to store armor and artifact for each character
    private Map<String, String> armourMap = new HashMap<>();
    private Map<String, String> artefactMap = new HashMap<>();
    private List<Charactors> armyList = new ArrayList<>();
    private HashMap<String, Charactors> armyMap = new HashMap<>();
    boolean enableArchers = true, enableKnights = true, enableMages = true, enableHealers = true, enableMythicalCreatures = true;
    //store the armours that own by characters
    public void setArmourMap(String arm, String chra){
        armourMap.put(arm,chra);
    }
    public void setIsDefault(){
        this.isDefault = true;
    }
    public Map<String, String> getArmourMap(){
        return armourMap;
    }
    //store the artefacts that own by characters
    public void setArtefactMap(String art, String chra){
        artefactMap.put(art,chra);
    }
    public Map<String, String> getArtefactMap(){
        return artefactMap;
    }
    //store tha characters own by player
    public void setArmyList(List<Charactors> armyList){
        this.armyList = armyList;
    }
    public List<Charactors> getArmyList(){
        return armyList;
    }
    //store tha characters own by player with names
    public void setArmyMap(HashMap<String, Charactors> armyMap){
        this.armyMap = armyMap;
    }
    public HashMap<String, Charactors> getArmyMap(){
        return armyMap;
    }
    public String getUserName(){
        return this.userProfile.getUsername();
    }
    public String getName(){
        return this.userProfile.getName();
    }
    public void setGoldCoins(int amount){
        goldCoins += amount;
    }
    public int getGoldCoins(){
        int roundedValue = (int) Math.round(goldCoins);
        if(roundedValue<0) roundedValue =0;
        return roundedValue;
    }
    public void setXP(int amount){
        xp += amount;
    }
    public int getXP(){
        return xp;
    }
    //method for default player to set his homeGround
    public void setHomeGround(String homeGround){
        this.homeGround = homeGround;
    }
    //method for user to set his homeGround
    public void setHomeGround(){
        
        System.out.println("1.Hillcrest");
        System.out.println("2.Marshland");
        System.out.println("3.Desert");
        System.out.println("4.Arcane");
        System.out.println("Enter the number of your homeground.");
        int homeGroundNumber = scanner.nextInt();
        if(homeGroundNumber==1){
            this.homeGround = "Hillcrest";
        }
        else if(homeGroundNumber==2){
            this.homeGround = "Marshland";
        }
        else if(homeGroundNumber==3){
            this.homeGround = "Desert";
        }
        else{
            this.homeGround = "Arcane";
        }
    }
    public String getHomeGround(){
        return this.homeGround;
    }
    public int getUserId(){
        return this.userProfile.getUserID();
    }
    //add charactors to player
    public void setCharactors(){
        String addMoreCharactors = "Y";
        do{
            if(goldCoins<=0) {//when gold coins amount 0 stop the loop because player can't buy any characters
                break;
            }
            System.out.println("Enter the number of the character type you want to add to your army.");
            if(enableArchers) System.out.println("1.Archers");
            if(enableKnights) System.out.println("2.Knights");
            if(enableMages) System.out.println("3.Mages");
            if(enableHealers) System.out.println("4.Healers");
            if(enableMythicalCreatures) System.out.println("5.Mythical Creatures");
            if(!(enableArchers || enableKnights || enableHealers || enableMages || enableMythicalCreatures)) {//When all types of characters added user can't add more
                System.out.println("You can't add more characters!");
                break;
            }    
            int charactorTypeNumber = scanner.nextInt(); 
            while(charactorTypeNumber <1 || charactorTypeNumber>5){
                System.out.println("You have entered invalid number ! Enter a valid number.");
                charactorTypeNumber = scanner.nextInt();
            }
            System.out.println("now select palyer from that type you want to add. Enter the number of that character.");
            if(charactorTypeNumber ==1){
                if(enableArchers){//enableArchers prevent get two characters from Archer type
                    System.out.println("1.Shooter (80 gc)");
                    System.out.println("2.Ranger (115 gc)");
                    System.out.println("3.Sunfire (160 gc)");
                    System.out.println("4.Zing (200 gc)");
                    System.out.println("5.Saggitarius (230 gc)");
                }else{
                    System.out.println("You can't add two same type characters !");
                }
            }
            else if(charactorTypeNumber ==2){//enableKnights prevent get two characters from Knight type
                if(enableKnights){
                    System.out.println("1.Squire (85 gc)");
                    System.out.println("2.Cavalier (110 gc)");
                    System.out.println("3.Templar  (155 gc)");
                    System.out.println("4.Zoro (180 gc)");
                    System.out.println("5.Swiftblade (250 gc)");
                }else{
                    System.out.println("You can't add two same type characters !");
                }
            }
            else if(charactorTypeNumber ==3){//enableMages prevent get two characters from Mages type
                if(enableMages){
                    System.out.println("1.Warlock (100 gc)");
                    System.out.println("2.Illusionist (120 gc)");
                    System.out.println("3.Enchanter (160 gc)");
                    System.out.println("4.Conjurer (195 gc)");
                    System.out.println("5.Eldritch (270 gc)");
                }else{
                    System.out.println("You can't add two same type characters !");
                }
            }
            else if(charactorTypeNumber ==4){//enableHealers prevent get two characters from Healers type
                if(enableHealers){
                    System.out.println("1.Soother (95 gc)");
                    System.out.println("2.Medic (125 gc)");
                    System.out.println("3.Alchemist (150 gc)");
                    System.out.println("4.Saint (200 gc)");
                    System.out.println("5.Lightbringer (260 gc)");
                }else{
                    System.out.println("You can't add two same type characters !");
                }
            }
            else{
                if(enableMythicalCreatures){//enableMythicalCreatures prevent get two characters from Mythical Creatures type
                    System.out.println("1.Dragon (120 gc)");
                    System.out.println("2.Basilisk (165 gc)");
                    System.out.println("3.Hydra (205 gc)");
                    System.out.println("4.Phoenix (275 gc)");
                    System.out.println("5.Pegasus (340 gc)");
                }else{
                    System.out.println("You can't add two same type characters !");
                }
            }
            int charactorNumber = scanner.nextInt();
            scanner.nextLine(); 
            while(charactorTypeNumber <1 || charactorTypeNumber>5){
                System.out.println("You have entered invalid number ! Enter a valid number.");
                charactorNumber = scanner.nextInt();
                scanner.nextLine();
            }
            //call buildCharactors to create character
            buildCharactors(charactorTypeNumber, charactorNumber);
            System.out.println("Your remaining gold coins amount : "+ this.getGoldCoins());
            System.out.println("You want to add more charactors? (Y/N)");
            addMoreCharactors = scanner.nextLine();
        }while(addMoreCharactors.equals("Y") && goldCoins>0);//ask same questions while user finish adding players
    }
    //sell characters of player
    public void sellCharactors(String sellCharactorName){
        if(armyMap.containsKey(sellCharactorName)){//if player have that type of character sell it for 90% of its current value
            int roundedValue = (int) Math.round(armyMap.get(sellCharactorName).getPrice()*0.9);
            this.setGoldCoins(roundedValue);
            if(armyMap.get(sellCharactorName).getMyType().equals("Archers")) enableArchers =true;
            else if(armyMap.get(sellCharactorName).getMyType().equals("Knights")) enableKnights =true;
            else if(armyMap.get(sellCharactorName).getMyType().equals("Mages")) enableMages =true;
            else if(armyMap.get(sellCharactorName).getMyType().equals("Healers")) enableHealers =true;
            else if(armyMap.get(sellCharactorName).getMyType().equals("MythicalCreatures")) enableMythicalCreatures =true;
            System.out.println(sellCharactorName+" sell successfully.");
            armyMap.remove(sellCharactorName);
        }else{
            System.out.println("You haven't "+sellCharactorName+ "character !");
        }
    }
    public List<Charactors> getCharactors(){
        return this.armyList;
    }
    public Map<String,Charactors> getCharactorsMap(){
        return this.armyMap;
    }

    //user's declare war method to declare war
    public void declareWar(Map<Integer, Player> profileMap){
        Random rand = new Random();
        int randInt = rand.nextInt(this.userProfile.getUserIDCounter()-1);//get random integer to select random player from the map "profileMap"
        while(randInt == this.userProfile.getUserID()){//if the random player's userID equals given random number get another random number
            declareWar(profileMap);
        }
        System.out.println("Your opponent's XP level : "+ profileMap.get(randInt).getXP());
        System.out.print("Your opponent's charactors : ");
        for (String key : profileMap.get(randInt).getCharactorsMap().keySet()) {
            System.out.print(key+"  ");
        }
        System.out.println("");
        System.out.println("Challenge them to battle? (Y/N)");//ask user if he want to declare battle with the choosen player
        String ans = scanner.nextLine();
        if(ans.equals("Y")){
            System.out.println("Your XP level : "+ this.getXP());//user's details
            System.out.print("Your charactors : ");
            for (String key : this.getCharactorsMap().keySet()){
                System.out.print(key+"  ");
            }
            System.out.println("");
            
            War war= new War(this,profileMap.get(randInt),profileMap.get(randInt).getHomeGround());//create war object to do battle
            //war object return the winning player 
            if(war.winner == null){
                System.out.println("Battle Draw!");
            }
            else if(war.winner.equals(this)){
                this.xp++;
                System.out.println(this.userProfile.getUsername()+" Win!");
                int roundedValue = (int) Math.round(profileMap.get(randInt).getGoldCoins()*0.1);
                this.setGoldCoins(roundedValue);//increase the winners gold coins
                profileMap.get(randInt).setGoldCoins(-roundedValue);//decrease other player's gold coins
                
            }else if(war.winner.equals(profileMap.get(randInt))){
                profileMap.get(randInt).xp++;
                System.out.println(profileMap.get(randInt).userProfile.getUsername()+" Win!");
                int roundedValue = (int) Math.round(this.getGoldCoins()*0.1);
                profileMap.get(randInt).setGoldCoins(roundedValue);//increase the winners gold coins
                this.setGoldCoins(-roundedValue);//decrease other player's gold coins
            }  
            //print result after the war
            System.out.println(this.userProfile.getUsername()+ " : "+ this.xp +"   Gold Coins :" +this.getGoldCoins());
            System.out.println(profileMap.get(randInt).userProfile.getUsername()+ " : "+ profileMap.get(randInt).xp +"   Gold Coins :" +profileMap.get(randInt).getGoldCoins());
            restore(); //user's characters are restored to their state before the battle. 
            restoreArmour();//restore user's armours before battle
            restoreArtefact();//restore user's artefacts before battle
            profileMap.get(randInt).restore();//opponent's characters are restored to their state before the battle.
            profileMap.get(randInt).restoreArmour();//restore opponent's armours before battle
            profileMap.get(randInt).restoreArtefact();//restore opponent's artefacts before battle
        }else{
            declareWar(profileMap);//if user refuce to battle recall declareWar
        }
    }

    //buy armours for charactors
    public void buyArmour(String charactorName, String armourName){
        if(armyMap.containsKey(charactorName)){
            armyMap.get(charactorName).setArmour(armourName, this, this.isDefault);//call the set armour method in character class
        }else{//if user enter a character that he haven't in his army refuce the request
            System.out.println("You haven't "+charactorName+" Charactor !");
        }
    }
    public void buyArtefact(String charactorName, String artefactName){
        if(armyMap.containsKey(charactorName)){//call the set artefact method in character class
            armyMap.get(charactorName).setArtefact(artefactName, this, this.isDefault);
        }else{//if user enter a character that he haven't in his army refuce the request
            System.out.println("You haven't "+charactorName+" Charactor !");
        }
    }

    public void restoreArtefact(){//after the war restore the artefact
        for(String str: getArtefactMap().keySet()){//get artefacts which user have before the war from ArtefactMap
            Artefact ar;
            if(str.equals("Excalibur")){
                ar = new Excalibur();
            }else if(str.equals("Amulet")){
                ar = new Amulet();
            }else{
                ar = new Crystal();
            }
            if(armyMap.get(getArtefactMap().get(str)) != null){
                //reset the funtionalities of artefacts 
                int roundedValue = (int) Math.round(ar.getPrice()*0.2);
                armyMap.get(getArtefactMap().get(str)).setPrice(roundedValue);
                armyMap.get(getArtefactMap().get(str)).setHealth(ar.getDifHealth());
                armyMap.get(getArtefactMap().get(str)).setDefence(ar.getDifDefence());
                armyMap.get(getArtefactMap().get(str)).setSpeed(ar.getDifSpeed());
            }
        }
    }

    public void restoreArmour(){//after the war restore the armour
        for(String str: getArmourMap().keySet()){//get armours which user have before the war from ArmourMap
            Armour ar;
            if(str.equals("Chainmail")){
                ar = new Chainmail();
            }else if(str.equals("Regalia")){
                ar = new Regalia();
            }else{
                ar = new Fleece();
            }
            if(armyMap.get(getArmourMap().get(str)) != null){
                //reset the funtionalities of armour
                int roundedValue = (int) Math.round(ar.getPrice()*0.2);
                armyMap.get(getArmourMap().get(str)).setPrice(roundedValue);
                armyMap.get(getArmourMap().get(str)).setHealth(ar.getDifHealth());
                armyMap.get(getArmourMap().get(str)).setDefence(ar.getDifDefence());
                armyMap.get(getArmourMap().get(str)).setSpeed(ar.getDifSpeed());
            }
        }
    }

    public void restore(){//re store characters that player have before the war
        armyList.clear();
        for(String charA : armyMap.keySet()){
            if (charA.equals("Shooter")) {
                armyMap.put(charA, new Shooter());
                armyList.add(new Shooter());
            }
            else if (charA.equals("Ranger")) {
                armyMap.put(charA, new Ranger());
                armyList.add(new Ranger());
            }
            else if (charA.equals("Sunfire")) {
                armyMap.put(charA, new Sunfire());
                armyList.add(new Sunfire());
                
            }
            else if (charA.equals("Zing")) {
                armyMap.put(charA, new Zing());
                armyList.add(new Zing());
            }
            else if (charA.equals("Saggitarius")) {
                armyMap.put(charA, new Saggitarius());
                armyList.add(new Saggitarius());
            }
            else if (charA.equals("Squire")) {
                armyMap.put(charA, new Squire());
                armyList.add(new Squire());
            }
            else if (charA.equals("Cavalier")) {
                armyMap.put(charA, new Cavalier());
                armyList.add(new Cavalier());
            }
            else if (charA.equals("Templar")) {
                armyMap.put(charA, new Templar());
                armyList.add(new Templar());
            }
            else if (charA.equals("Zoro")) {
                armyMap.put(charA, new Zoro());
                armyList.add(new Zoro());
            }
            else if (charA.equals("Swiftblade")) {
                armyMap.put(charA, new Swiftblade());
                armyList.add(new Swiftblade());
            }
            else if (charA.equals("Warlock")) {
                armyMap.put(charA, new Warlock());
                armyList.add(new Warlock());
            }
            else if (charA.equals("Illusionist")) {
                armyMap.put(charA, new Illusionist());
                armyList.add(new Illusionist());
            }
            else if (charA.equals("Enchanter")) {
                armyMap.put(charA, new Enchanter());
                armyList.add(new Enchanter());
            }
            else if (charA.equals("Conjurer")) {
                armyMap.put(charA, new Conjurer());
                armyList.add(new Conjurer());
            }
            else if (charA.equals("Eldritch")) {
                armyMap.put(charA, new Eldritch());
                armyList.add(new Eldritch());
            }
            else if (charA.equals("Soother")) {
                armyMap.put(charA, new Soother());
                armyList.add(new Soother());
            }
            else if (charA.equals("Medic")) {
                armyMap.put(charA, new Medic());
                armyList.add(new Medic());
            }
            else if (charA.equals("Alchemist")) {
                armyMap.put(charA, new Alchemist());
                armyList.add(new Alchemist());
            }
            else if (charA.equals("Saint")) {
                armyMap.put(charA, new Saint());
                armyList.add(new Saint());
            }
            else if (charA.equals("Lightbringer")) {
                armyMap.put(charA, new Lightbringer());
                armyList.add(new Lightbringer());
            }
            else if (charA.equals("Dragon")) {
                armyMap.put(charA, new Dragon());
                armyList.add(new Dragon());
            }
            else if (charA.equals("Basilisk")) {
                armyMap.put(charA, new Basilisk());
                armyList.add(new Basilisk());
            }
            else if (charA.equals("Hydra")) {
                armyMap.put(charA, new Hydra());
                armyList.add(new Hydra());
            }
            else if (charA.equals("Phoenix")) {
                armyMap.put(charA, new Phoenix());
                armyList.add(new Phoenix());
            }
            else if (charA.equals("Pegasus")) {
                armyMap.put(charA, new Pegasus());
                armyList.add(new Pegasus());
            }
            //set armyList and armyMap with new values
            this.setArmyList(armyList);
            this.setArmyMap(armyMap);
        
        }
        
    }
    //create characters according to the user inputs
    public void buildCharactors(int charactorTypeNumber, int charactorNumber){
        if(charactorTypeNumber ==1 && enableArchers){
            if(charactorNumber ==1){
                Charactors shooter = new Shooter();
                if(shooter.getPrice()<=goldCoins){
                    armyList.add(shooter);
                    armyMap.put("Shooter", shooter);
                    this.setGoldCoins(-(shooter.getPrice()));
                    enableArchers = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==2){
                Charactors ranger = new Ranger();
                if(ranger.getPrice()<=goldCoins){
                    armyList.add(ranger);
                    armyMap.put("Ranger", ranger);
                    this.setGoldCoins(-(ranger.getPrice()));
                    enableArchers = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==3){
                Charactors sunfire = new Sunfire();
                if(sunfire.getPrice()<=goldCoins){
                    armyList.add(sunfire);
                    armyMap.put("Sunfire", sunfire);
                    this.setGoldCoins(-(sunfire.getPrice()));
                    enableArchers = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==4){
                Charactors zing = new Zing();
                if(zing.getPrice()<=goldCoins){
                    armyList.add(zing);
                    armyMap.put("Zing", zing);
                    this.setGoldCoins(-(zing.getPrice()));
                    enableArchers = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else{
                Charactors saggitarius = new Saggitarius();
                if(saggitarius.getPrice()<=goldCoins){
                    armyList.add(saggitarius);
                    armyMap.put("Saggitarius", saggitarius);
                    this.setGoldCoins(-(saggitarius.getPrice()));
                    enableArchers = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }
            
        }else if(charactorTypeNumber ==2 && enableKnights){
            if(charactorNumber ==1){
                Charactors squire = new Squire();
                if(squire.getPrice()<=goldCoins){
                    armyList.add(squire);
                    armyMap.put("Squire", squire);
                    this.setGoldCoins(-(squire.getPrice()));
                    enableKnights =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==2){
                Charactors cavalier = new Cavalier();
                if(cavalier.getPrice()<=goldCoins){
                    armyList.add(cavalier);
                    armyMap.put("Cavalier", cavalier);
                    this.setGoldCoins(-(cavalier.getPrice()));
                    enableKnights =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==3){
                Charactors templar = new Templar();
                if(templar.getPrice()<=goldCoins){
                    armyList.add(templar);
                    armyMap.put("Templar", templar);
                    this.setGoldCoins(-(templar.getPrice()));
                    enableKnights =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==4){
                Charactors zoro = new Zoro();
                if(zoro.getPrice()<=goldCoins){
                    armyList.add(zoro);
                    armyMap.put("Zore", zoro);
                    this.setGoldCoins(-(zoro.getPrice()));
                    enableKnights =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else{
                Charactors swiftblade = new Swiftblade();
                if(swiftblade.getPrice()<=goldCoins){
                    armyList.add(swiftblade);
                    armyMap.put("Swiftblade", swiftblade);
                    this.setGoldCoins(-(swiftblade.getPrice()));
                    enableKnights =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }
            
        }else if(charactorTypeNumber ==3 && enableMages){
            if(charactorNumber ==1){
                Charactors warlock = new Warlock();
                if(warlock.getPrice()<=goldCoins){
                    armyList.add(warlock);
                    armyMap.put("Warlock", warlock);
                    this.setGoldCoins(-(warlock.getPrice()));
                    enableMages = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==2){
                Charactors illusionist = new Illusionist();
                if(illusionist.getPrice()<=goldCoins){
                    armyList.add(illusionist);
                    armyMap.put("Illusionist", illusionist);
                    this.setGoldCoins(-(illusionist.getPrice()));
                    enableMages = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==3){
                Charactors enchanter = new Enchanter();
                if(enchanter.getPrice()<=goldCoins){
                    armyList.add(enchanter);
                    armyMap.put("Enchanter", enchanter);
                    this.setGoldCoins(-(enchanter.getPrice()));
                    enableMages = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==4){
                Charactors conjurer = new Conjurer();
                if(conjurer.getPrice()<=goldCoins){
                    armyList.add(conjurer);
                    armyMap.put("Conjurer", conjurer);
                    this.setGoldCoins(-(conjurer.getPrice()));
                    enableMages = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else{
                Charactors eldritch = new Eldritch();
                if(eldritch.getPrice()<=goldCoins){
                    armyList.add(eldritch);
                    armyMap.put("Eldritch", eldritch);
                    this.setGoldCoins(-(eldritch.getPrice()));
                    enableMages = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }
            
        }else if(charactorTypeNumber ==4 && enableHealers){
            if(charactorNumber ==1){
                Charactors soother = new Soother();
                if(soother.getPrice()<=goldCoins){
                    armyList.add(soother);
                    armyMap.put("Soother", soother);
                    this.setGoldCoins(-(soother.getPrice()));
                    enableHealers =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==2){
                Charactors medic = new Medic();
                if(medic.getPrice()<=goldCoins){
                    armyList.add(medic);
                    armyMap.put("Medic", medic);
                    this.setGoldCoins(-(medic.getPrice()));
                    enableHealers =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==3){
                Charactors alchemist = new Alchemist();
                if(alchemist.getPrice()<=goldCoins){
                    armyList.add(alchemist);
                    armyMap.put("Alchemist", alchemist);
                    this.setGoldCoins(-(alchemist.getPrice()));
                    enableHealers =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==4){
                Charactors saint = new Saint();
                if(saint.getPrice()<=goldCoins){
                    armyList.add(saint);
                    armyMap.put("Saint", saint);
                    this.setGoldCoins(-(saint.getPrice()));
                    enableHealers =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else{
                Charactors lightbringer = new Lightbringer();
                if(lightbringer.getPrice()<=goldCoins){
                    armyList.add(lightbringer);
                    armyMap.put("Lightbringer", lightbringer);
                    this.setGoldCoins(-(lightbringer.getPrice()));
                    enableHealers =false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }
            
        }else if(enableMythicalCreatures){
            if(charactorNumber ==1){
                Charactors dragon = new Dragon();
                if(dragon.getPrice()<=goldCoins){
                    armyList.add(dragon);
                    armyMap.put("Dragon", dragon);
                    this.setGoldCoins(-(dragon.getPrice()));
                    enableMythicalCreatures = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==2){
                Charactors basilisk = new Basilisk();
                if(basilisk.getPrice()<=goldCoins){
                    armyList.add(basilisk);
                    armyMap.put("Basilisk", basilisk);
                    this.setGoldCoins(-(basilisk.getPrice()));
                    enableMythicalCreatures = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==3){
                Charactors hydra = new Hydra();
                if(hydra.getPrice()<=goldCoins){
                    armyList.add(hydra);
                    armyMap.put("Hydra", hydra);
                    this.setGoldCoins(-(hydra.getPrice()));
                    enableMythicalCreatures = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else if(charactorNumber ==4){
                Charactors phoenix = new Phoenix();
                if(phoenix.getPrice()<=goldCoins){
                    armyList.add(phoenix);
                    armyMap.put("Phoenix", phoenix);
                    this.setGoldCoins(-(phoenix.getPrice()));
                    enableMythicalCreatures = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }else{
                Charactors pegasus = new Pegasus();
                if(pegasus.getPrice()<=goldCoins){
                    armyList.add(pegasus);
                    armyMap.put("Pegasus", pegasus);
                    this.setGoldCoins(-(pegasus.getPrice()));
                    enableMythicalCreatures = false;
                }else{
                    System.out.println("There is no sufficient gold coins to buy that character !");
                }
            }
        }
    }
   
}

class War {
    // Define lists to store armies and battle results
    List<Charactors> armyList1, armyList2, attackArmy1, attackArmy2, receivingArmy1, receivingArmy2, healerAttackArmy1, healerAttackArmy2;
    // Store the opponent player's homeground as battle ground and the players involved
    String battleHomeGround;
    Player player1, player2, winner = null;
    public War(Player player1, Player palyer2, String homeGround){
        this.player1 = player1;
        this.player2 = palyer2;
        this.armyList1 = player1.getCharactors();
        this.armyList2 = palyer2.getCharactors();
        this.battleHomeGround = homeGround;
        battle();// Start the battle
    }
    // Set attack priorities when two charactors have same speeds based on character types
    public void setAttackPriorityWar(Charactors charA){
        if(charA.getMyType().equals("Archers")) charA.setAttackPriority(5);
        else if(charA.getMyType().equals("Knights")) charA.setAttackPriority(4);
        else if(charA.getMyType().equals("MythicalCreatures")) charA.setAttackPriority(3);
        else if(charA.getMyType().equals("Mages")) charA.setAttackPriority(2);
        else charA.setAttackPriority(1);
    }
    // Set receiving attack priorities when two charactors have same defends based on character types
    public void setReceivePriorityWar(Charactors charA){
        if(charA.getMyType().equals("Archers")) charA.setReceivePriority(3);
        else if(charA.getMyType().equals("Knights")) charA.setReceivePriority(2);
        else if(charA.getMyType().equals("MythicalCreatures")) charA.setReceivePriority(4);
        else if(charA.getMyType().equals("Mages")) charA.setReceivePriority(1);
        else charA.setReceivePriority(5);
    }
    public List<Charactors> setAttackOrder(List<Charactors> armyList){
        // Sort armyList based on speed and when two players have same speeds basede on attack priorities using bubble sort
        for (int i = 0; i < armyList.size()-1; i++){
            for(int j = 0; j < armyList.size()-i-1; j++){
                if(armyList.get(j).getspeed() == armyList.get(j + 1).getspeed()){
                    setAttackPriorityWar(armyList.get(j));
                    setAttackPriorityWar(armyList.get(j + 1));
                    //swap the elements
                    if(armyList.get(j).getAttackPriority()<armyList.get(j + 1).getAttackPriority()){
                        Charactors tempVar = armyList.get(j + 1);
                        armyList.remove(armyList.get(j+1));
                        armyList.add(j+1,armyList.get(j));
                        armyList.remove(armyList.get(j));
                        armyList.add(j,tempVar);
                    }
                }
                //swap the elements
                else if(armyList.get(j).getspeed() < armyList.get(j + 1).getspeed()){
                    Charactors tempVar = armyList.get(j + 1);
                        armyList.remove(armyList.get(j+1));
                        armyList.add(j+1,armyList.get(j));
                        armyList.remove(armyList.get(j));
                        armyList.add(j,tempVar);
                }
            }
        }
        return armyList;
    }

    public List<Charactors> setReceivingAttackOrder(List<Charactors> armyList){
        // Sort receiving armyList based on defence values and when two players have same speeds basede on riceiving attack priorities using bubble sort
        for (int i = 0; i < armyList.size()-1; i++){
            for(int j = 0; j < armyList.size()-i-1; j++){
                if(armyList.get(j).getDefence() == armyList.get(j + 1).getDefence()){
                    setReceivePriorityWar(armyList.get(j));
                    setReceivePriorityWar(armyList.get(j + 1));
                    //swap the elements
                    if(armyList.get(j).getReceivePriority()<armyList.get(j + 1).getReceivePriority()){
                        Charactors tempVar = armyList.get(j + 1);
                        armyList.remove(armyList.get(j+1));
                        armyList.add(j+1,armyList.get(j));
                        armyList.remove(armyList.get(j));
                        armyList.add(j,tempVar);
                    }
                }
                //swap the elements
                if(armyList.get(j).getDefence() > armyList.get(j + 1).getDefence()){
                    Charactors tempVar = armyList.get(j + 1);
                    armyList.remove(armyList.get(j+1));
                    armyList.add(j+1,armyList.get(j));
                    armyList.remove(armyList.get(j));
                    armyList.add(j,tempVar);
                }
            }
        }
        return armyList;
    }
    // Method to set healer attack order for an army
    public List<Charactors> setHealerAttackOrder(List<Charactors> armyList){
        // Sort armyList based on health of the characters using bubble sort
        for (int i = 0; i < armyList.size()-1; i++){
            for(int j = 0; j < armyList.size()-i-1; j++){
                if(armyList.get(j).gethealth() > armyList.get(j + 1).gethealth()){
                    //swap the elements
                    Charactors tempVar = armyList.get(j + 1);
                    armyList.remove(armyList.get(j+1));
                    armyList.add(j+1,armyList.get(j));
                    armyList.remove(armyList.get(j));
                    armyList.add(j,tempVar);
                }
            }
        }
        return armyList;
    }
    // Method to adjust character behavior based on battle ground
    public void adjustCharactorBehaviour(Charactors charA){
        // Adjust character behavior based on battle ground and home land
        if(battleHomeGround.equals("Hillcrest")){
            if(charA.getHomeLand().equals("Hillcrest")){
                charA.setAttack(1);
                charA.setDefence(1);
            }else if(charA.getHomeLand().equals("Marshland") || charA.getHomeLand().equals("Desert")){
                charA.setSpeed(-1);
            }
        }else if(battleHomeGround.equals("Marshland")){
            if(charA.getHomeLand().equals("Marshland")){
                charA.setDefence(2); 
            }else if(charA.getHomeLand().equals("Desert") ){
                charA.setAttack(-1);
            }else if(charA.getHomeLand().equals("Arcane") ){
                charA.setSpeed(-1);
            }
        }else if(battleHomeGround .equals("Desert")){
            if(charA.getHomeLand().equals("Desert") ){
                charA.setAttack(1); 
            }else if(charA.getHomeLand().equals("Marshland")){
                charA.setHealth(-1);
            }
        }else{
            if(charA.getHomeLand().equals("Arcane") ){
                charA.setAttack(2);
            }else if(charA.getHomeLand().equals("Marshland")  || charA.getHomeLand().equals("Hillcrest") ){
                charA.setSpeed(-1);
                charA.setDefence(-1);
            }
        }
    }
    // Method to simulate an attack between two characters
    //take sorted attack army and riceiving army as parameters 
    public Charactors attack(List<Charactors> army1, List<Charactors> army2, double attackAmount, double defenceAmount){
        //print the attaks player and opponent player and their health after the war
        System.out.println(army1.get(0).getName() + " attacks " + army2.get(0).getName());
        //set the health of defending player
        army2.get(0).setHealth(attackAmount+defenceAmount);
        //when battle occurs in arcane increase mystics health 10%
        if(army1.get(0).getHomeLand().equals(battleHomeGround) && battleHomeGround.equals("Arcane")) army1.get(0).setHealth(army1.get(0).gethealth()*0.1);
        System.out.println(army2.get(0).getName()+ "'s health: "+ army2.get(0).gethealth());
        System.out.println(army1.get(0).getName()+ "'s health: "+ army1.get(0).gethealth());
        //when a player died print it
        if(army2.get(0).gethealth()<=0) {
            System.out.println(army2.get(0).getName() + " died!");
            return (army2.get(0));//return when a player died
        }
        return null;
    }
    public void battle(){
        // Adjust character behaviors according to the battle ground of both teams
        for(Charactors charA:armyList1){
            adjustCharactorBehaviour(charA);
        }
        for(Charactors charA:armyList2){
            adjustCharactorBehaviour(charA);
        }
        
        int turnNo =1;
        for(int i=0; i<10; i++){//give 10 turns to each team
            attackArmy1= setAttackOrder(armyList1);
            if(!attackArmy1.get(0).getMyType().equals("Healers")){//healers shoid attack their own team. So search wheather attacker is not a healer
                System.out.println("Turn "+ turnNo +":" + attackArmy1.get(0).getName());
                turnNo ++;
                receivingArmy2 = setReceivingAttackOrder(armyList2);
                //set health of attack riceiver and if that charactor died store that charactor in diedCharactor 
                Charactors diedCharactor = attack(attackArmy1, receivingArmy2, -0.5*attackArmy1.get(0).getattack(), 0.1*receivingArmy2.get(0).getDefence());
                if(diedCharactor != null) armyList2.remove(diedCharactor);// if a character died remove that one from opponent army list
                
                if(attackArmy1.get(0).getHomeLand().equals(battleHomeGround) && battleHomeGround.equals("Hillcrest")){//when attack occurs hillcrest give bonus turn to higherlanders
                    System.out.println("Bonus Turn "+ turnNo +":" + attackArmy1.get(0).getName());
                    turnNo ++;
                    receivingArmy2 = setReceivingAttackOrder(armyList2);
                    diedCharactor = attack(attackArmy1, receivingArmy2, -0.5*attackArmy1.get(0).getattack()*0.2, 0.1*receivingArmy2.get(0).getDefence());
                    if(diedCharactor != null) armyList2.remove(diedCharactor);
                }
            }else{
                //when healer's attack
                System.out.println("Turn "+ turnNo +":" + attackArmy1.get(0).getName());
                turnNo ++;
                healerAttackArmy1 = setHealerAttackOrder(armyList1);//take the armylist which sorted according to health values
                attack(attackArmy1, healerAttackArmy1, 0.1*attackArmy1.get(0).getattack(), 0);//increase the health of the character which has lowest health 
                
                if(attackArmy1.get(0).getHomeLand().equals(battleHomeGround) && battleHomeGround.equals("Hillcrest")){//when attack occurs hillcrest give bonus turn to higherlanders
                    System.out.println("Bonus Turn "+ turnNo +":" + attackArmy1.get(0).getName());
                    turnNo ++;
                    healerAttackArmy1 = setHealerAttackOrder(armyList1);
                    attack(attackArmy1, healerAttackArmy1, 0.1*attackArmy1.get(0).getattack()*0.2, 0);
                }
            }//do same thing for oppenent player
            if(armyList2.size() !=0){
                attackArmy2= setAttackOrder(armyList2);
                
                if(!attackArmy2.get(0).getMyType().equals("Healers")){
                    System.out.println("Turn "+ turnNo +":" + attackArmy2.get(0).getName());
                    turnNo ++;
                    receivingArmy1 = setReceivingAttackOrder(armyList1);
                    Charactors diedCharactor = attack(attackArmy2, receivingArmy1, -0.5*attackArmy2.get(0).getattack(), 0.1*receivingArmy1.get(0).getDefence());

                    if(diedCharactor != null) armyList1.remove(diedCharactor);
                    
                    if(attackArmy2.get(0).getHomeLand().equals(battleHomeGround) && battleHomeGround.equals("Hillcrest")){
                        System.out.println("Bonus Turn "+ turnNo +":" + attackArmy1.get(0).getName());
                        turnNo ++;
                        receivingArmy1 = setReceivingAttackOrder(armyList1);
                        diedCharactor = attack(attackArmy2, receivingArmy1, -0.5*attackArmy2.get(0).getattack()*0.2, 0.1*receivingArmy1.get(0).getDefence());
                        
                        if(diedCharactor != null) armyList1.remove(diedCharactor);
                    }
                }else{
                    System.out.println("Turn "+ turnNo +":" + attackArmy2.get(0).getName());
                    turnNo ++;
                    healerAttackArmy2 = setHealerAttackOrder(armyList2);
                    attack(attackArmy2, healerAttackArmy2, 0.1*attackArmy2.get(0).getattack(), 0);//meken maren nee

                    if(attackArmy2.get(0).getHomeLand().equals(battleHomeGround) && battleHomeGround.equals("Hillcrest")){
                        System.out.println("Bonus Turn "+ turnNo +":" + attackArmy2.get(0).getName());
                        turnNo ++;
                        healerAttackArmy2 = setHealerAttackOrder(armyList2);
                        attack(attackArmy1, healerAttackArmy1, 0.1*attackArmy1.get(0).getattack()*0.2, 0);
                    }
                }
            }
            if(armyList1.isEmpty()){//when all the players of a team died set a winner player
                winner=  player2;
                break;
            }else if(armyList2.isEmpty()){
                winner = player1;
                break;
            }
        } 
    }

}
