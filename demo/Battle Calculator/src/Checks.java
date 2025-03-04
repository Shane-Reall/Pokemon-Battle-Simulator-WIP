public class Checks {
    private boolean minimize;
    private boolean underground;
    private boolean underwater;
    private boolean reflect;
    private boolean lightScreen;
    private boolean friendGuard;
    private boolean crit;
    private double continueCounter;
    private boolean doubleBattle;
    private boolean trickRoom;
    private boolean magicRoom;
    private boolean wonderRoom;
    private boolean gravity;
    private boolean protect;
    private boolean leechSeed;
    private boolean foresight;
    private boolean helpingHand;
    private boolean tailwind;
    private boolean flowerGift;
    private boolean steelySpirit;
    private boolean battery;
    private boolean powerSpot;
    private boolean switching;
    private boolean glaiveRush;


    private weatherType weather;
    private terrainType terrain;

    public Checks (boolean minimize, boolean underground, boolean underwater, boolean reflect, boolean lightScreen, boolean friendGuard, boolean crit, boolean doubleBattle, boolean trickRoom, boolean magicRoom, boolean wonderRoom, boolean gravity, boolean protect, boolean leechSeed, boolean foresight, boolean helpingHand, boolean tailwind, boolean flowerGift, boolean steelySpirit, boolean battery, boolean powerSpot, boolean switching, boolean glaiveRush, double continueCounter, weatherType weather, terrainType terrain) {
        this.minimize = minimize;
        this.underground = underground;
        this.underwater = underwater;
        this.reflect = reflect;
        this.lightScreen = lightScreen;
        this.friendGuard = friendGuard;
        this.crit = crit;
        this.continueCounter = continueCounter;
        this.doubleBattle = doubleBattle;
        this.weather = weather;
        this.terrain = terrain;
        this.trickRoom = trickRoom;
        this.magicRoom = magicRoom;
        this.wonderRoom = wonderRoom;
        this.gravity = gravity;
        this.protect = protect;
        this.leechSeed = leechSeed;
        this.foresight = foresight;
        this.helpingHand = helpingHand;
        this.tailwind = tailwind;
        this.flowerGift = flowerGift;
        this.steelySpirit = steelySpirit;
        this.battery = battery;
        this.powerSpot = powerSpot;
        this.switching = switching;
        this.glaiveRush = glaiveRush;
    }

    public boolean isMinimized() {
        return minimize;
    }

    public boolean isUnderground() {
        return underground;
    }

    public boolean isUnderwater() {
        return underwater;
    }

    public boolean isReflect() {
        return reflect;
    }

    public boolean isLightScreen() {
        return lightScreen;
    }

    public boolean isFriendGuard() {
        return friendGuard;
    }

    public boolean isCrit() {
        return crit;
    }

    public double getContinueCounter() {
        return continueCounter;
    }

    public boolean isDoubleBattle() {
        return doubleBattle;
    }

    public weatherType getWeather() {
        return weather;
    }

    public terrainType getTerrain() {
        return terrain;
    }

    public boolean isMagicRoom() {
        return magicRoom;
    }

    public boolean isWonderRoom() {
        return wonderRoom;
    }

    public boolean isTrickRoom() {
        return trickRoom;
    }

    public boolean isGravity() {
        return gravity;
    }

    public boolean isProtect() {
        return protect;
    }

    public boolean isLeechSeed() {
        return leechSeed;
    }

    public boolean isForesight() {
        return foresight;
    }

    public boolean isHelpingHand() {
        return helpingHand;
    }

    public boolean isTailwind() {
        return tailwind;
    }

    public boolean isFlowerGift() {
        return flowerGift;
    }

    public boolean isSteelySpirit() {
        return steelySpirit;
    }

    public boolean isBattery() {
        return battery;
    }

    public boolean isPowerSpot() {
        return powerSpot;
    }

    public boolean isSwitching() {
        return switching;
    }

    public void setReflect(boolean reflect) {
        this.reflect = reflect;
    }

    public void setLightScreen(boolean lightScreen) {
        this.lightScreen = lightScreen;
    }
}
