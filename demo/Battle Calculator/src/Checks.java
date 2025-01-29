public class Checks {
    private boolean minimize;
    private boolean underground;
    private boolean underwater;
    private boolean reflect;
    private boolean lightScreen;
    private boolean auroraVeil;
    private boolean friendGuard;
    private boolean crit;
    private double continueCounter;

    public Checks (boolean minimize, boolean underground, boolean underwater, boolean reflect, boolean lightScreen, boolean auroraVeil, boolean friendGuard, boolean crit, double continueCounter) {
        this.minimize = minimize;
        this.underground = underground;
        this.underwater = underwater;
        this.reflect = reflect;
        this.lightScreen = lightScreen;
        this.auroraVeil = auroraVeil;
        this.friendGuard = friendGuard;
        this.crit = crit;
        this.continueCounter = continueCounter;
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

    public boolean isAuroraVeil() {
        return auroraVeil;
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
}
