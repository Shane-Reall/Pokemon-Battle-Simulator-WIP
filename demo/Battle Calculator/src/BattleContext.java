import java.io.Serializable;

class BattleContext implements Serializable {
    SpeciesClass pokemon;
    SpeciesClass opponent;
    Checks checks; // Assuming this has relevant battle state data

    public BattleContext(SpeciesClass pokemon, SpeciesClass opponent, Checks checks) {
        this.pokemon = pokemon;
        this.opponent = opponent;
        this.checks = checks;
    }

    public SpeciesClass getPokemon() {
        return pokemon;
    }

    public SpeciesClass getOpponent() {
        return opponent;
    }

    public Checks getChecks() {
        return checks;
    }
}
