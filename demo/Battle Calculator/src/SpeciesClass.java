public class SpeciesClass {
    private double hp;
    private double currentHp;
    private double atk;
    private double def;
    private double spatk;
    private double spdef;
    private double spd;
    private pkmnType type1 = pkmnType.Typeless;
    private pkmnType type2 = pkmnType.Typeless;
    private pkmnType typeAddition = pkmnType.Typeless;
    private abilityList ability;
    private itemList item;
    private double weight;
    private boolean grounded;
    private status stated;



    public SpeciesClass(int hp, int currentHp, int atk, int def, int spatk, int spdef, int spd, pkmnType type1, pkmnType type2, abilityList ability, itemList item, double weight, boolean grounded, status stated) {
        this.hp = hp;
        this.currentHp = currentHp;
        this.atk = atk;
        this.def = def;
        this.spatk = spatk;
        this.spdef = spdef;
        this.spd = spd;
        this.type1 = type1;
        this.type2 = type2;
        this.ability = ability;
        this.item = item;
        this.weight = weight;
        this.grounded = grounded;
        this.stated = stated;
    }

    public void setTypeAddition(pkmnType typeAddition) {
        this.typeAddition = typeAddition;
    }

    public double getHp() {
        return hp;
    }

    public double getCurrentHp() {
        return currentHp;
    }

    public double getAtk() {
        return atk;
    }

    public double getDef() {
        return def;
    }

    public double getSpatk() {
        return spatk;
    }

    public double getSpdef() {
        return spdef;
    }

    public double getSpd() {
        return spd;
    }

    public pkmnType getType1() {
        return type1;
    }

    public pkmnType getType2() {
        return type2;
    }

    public abilityList getAbility() {
        return ability;
    }

    public double getWeight() {
        return weight;
    }

    public pkmnType getTypeAddition() {
        return typeAddition;
    }

    public pkmnType[] getTypes() {
        return new pkmnType[]{type1,type2,typeAddition};
    }

    public status getStated() {
        return stated;
    }

    public void setStated(status stated) {
        this.stated = stated;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public itemList getItem() {
        return item;
    }
}
