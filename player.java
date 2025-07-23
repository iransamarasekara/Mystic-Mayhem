import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
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



