import java.io.Serializable;

class BattleContext implements Serializable {
    SpeciesClass pokemon;
    SpeciesClass opponent;
    Checks checks;
    MoveClass move;
    String currentMove;

    public BattleContext(SpeciesClass pokemon, SpeciesClass opponent, Checks checks, MoveClass move, String currentMove) {
        this.pokemon = pokemon;
        this.opponent = opponent;
        this.checks = checks;
        this.move = move;
        this.currentMove = currentMove;
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

    public MoveClass getMove() {
        return move;
    }

    public String getCurrentMove() {
        return currentMove;
    }
}
