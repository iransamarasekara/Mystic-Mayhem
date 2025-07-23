import java.util.Scanner;

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