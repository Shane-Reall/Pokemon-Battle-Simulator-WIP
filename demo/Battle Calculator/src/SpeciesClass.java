public class SpeciesClass {
    private int hp;
    private int atk;
    private int def;
    private int spatk;
    private int spdef;
    private int spd;
    private pkmnType type1 = pkmnType.Typeless;
    private pkmnType type2 = pkmnType.Typeless;
    private pkmnType typeAddition = pkmnType.Typeless;
    private String ability;
    private double weight;


    public SpeciesClass(int hp, int atk, int def, int spatk, int spdef, int spd, pkmnType type1, pkmnType type2, String ability, double weight) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spatk = spatk;
        this.spdef = spdef;
        this.spd = spd;
        this.type1 = type1;
        this.type2 = type2;
        this.ability = ability;
        this.weight = weight;
    }

    public void setTypeAddition(pkmnType typeAddition) {
        this.typeAddition = typeAddition;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getSpatk() {
        return spatk;
    }

    public int getSpdef() {
        return spdef;
    }

    public int getSpd() {
        return spd;
    }

    public pkmnType getType1() {
        return type1;
    }

    public pkmnType getType2() {
        return type2;
    }

    public String getAbility() {
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
}
