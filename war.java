import java.util.List;

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