public class MoveClass {
    private moveList name;
    private double base;
    private pkmnType type;
    private moveCtgry category;
    private boolean contact;
    private boolean spread;
    private boolean sound;


    public MoveClass(moveList name, int base, pkmnType type, moveCtgry category, boolean contact, boolean spread, boolean sound) {
        this.name = name;
        this.base = base;
        this.type = type;
        this.category = category;
        this.contact = contact;
        this.spread = spread;
        this.sound = sound;
    }


    public double getBase() {
        return base;
    }

    public pkmnType getType() {
        return type;
    }

    public moveCtgry getCategory() {
        return category;
    }

    public boolean isContact() {
        return contact;
    }

    public boolean isSpread() {
        return spread;
    }

    public moveList getName() {
        return name;
    }

    public boolean isSound() {
        return sound;
    }
}
