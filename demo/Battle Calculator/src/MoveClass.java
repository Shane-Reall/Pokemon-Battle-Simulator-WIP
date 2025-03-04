import java.io.Serializable;

public class MoveClass implements Serializable {
    private double base;
    private pkmnType type;
    private moveCtgry category;
    private boolean contact;
    private boolean spread;
    private boolean sound;


    public MoveClass(int base, pkmnType type, moveCtgry category, boolean contact, boolean spread, boolean sound) {
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

    public void setBase(double base) {
        this.base = base;
    }

    public void setType(pkmnType type) {
        this.type = type;
    }

    public void setCategory(moveCtgry category) {
        this.category = category;
    }

    public boolean isContact() {
        return contact;
    }

    public boolean isSpread() {
        return spread;
    }

    public boolean isSound() {
        return sound;
    }
}
