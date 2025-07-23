// Represents an armor item
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

