import java.util.*;

public class Main extends BattleFunctions {
    public static void main(String[] args) {
        //Damage Variable
        double level = 100.00;
        MoveClass move = new MoveClass(0, pkmnType.Typeless, moveCtgry.Status, false, false);
        double attack = 0.00;
        double defense = 0.00;
        double totalDamage;
        double totalDamageMin;
        double totalDamageMax;
        SpeciesClass attacker = new SpeciesClass(0,0,0,0,0,0, pkmnType.Typeless, pkmnType.Typeless,"Null", 0);
        SpeciesClass defender = new SpeciesClass(0,0,0,0,0,0, pkmnType.Typeless, pkmnType.Typeless,"Null", 0);

        //Variable Checkers
        boolean multBattle = false;
        boolean pbSecond = false;
        currentField field = new currentField();
        boolean glaiveUsed = false;
        int critStage = 0; //Ranges from 0 to 4 (If > 4 it will be the same as if it was four)
        boolean burned = false;

        //Multiplier Variable
        double targets = 1;
        double pb = 1;
        double weather;
        double glaiveRush = 1;
        double critical = 1;
        double rndmMin = 0.85;
        double rndmMax = 1;
        double stab = 1;
        double type;
        double burn = 1;
        double other = 1;
        double zMove = 1;
        double teraShield = 1;

        HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart = new HashMap<>();

        loadMap(effectivenessChart);

        if (move.getCategory() == moveCtgry.Physical) {
            attack = attacker.getAtk();
            defense = defender.getDef();
        } else if (move.getCategory() == moveCtgry.Special) {
            attack = attacker.getSpatk();
            defense = defender.getSpdef();
        }


        //Calculate Damage
        totalDamage = (Math.floor(Math.floor(Math.floor(2 * level / 5 + 2) * move.getBase() * attack / defense) / 50) + 2);

        //Individual Multiplier Checks
        if (multBattle && move.isSpread()) {
            targets = 0.75;
        }

        if (pbSecond) { //Currently Thinking about moving this into other
            pb = 0.25;
        }

        weather = weatherCheck(field.getWeather(), move.getType());

        if (glaiveUsed) { //Currently Thinking about moving this into other
            glaiveRush = 2;
        }

        //critical = critCalc(random, critStage);

        for (int i = 0; i < 3; i++) {
            if (move.getType() == attacker.getTypes()[i]) {
                stab = 1.5;
                if (Objects.equals(attacker.getAbility(), "Adaptability")) {
                    stab = 2.0;
                }
            } else if (Objects.equals(attacker.getAbility(), "Adaptability")) {
                stab = 1.5;
            }
        }

        type = typeCalc(effectivenessChart, move.getType(), defender.getTypes());

        if (burned && move.getCategory() == moveCtgry.Physical) {
            burn = 0.5;
        }

        other = otherMulti(move, attacker, defender);

        //Total Multiplier Calculation

        totalDamageMin = totalCalc(totalDamage, targets, pb, weather, glaiveRush, critical, rndmMin, stab, type, burn, other, zMove, teraShield);
        totalDamageMax = totalCalc(totalDamage, targets, pb, weather, glaiveRush, critical, rndmMax, stab, type, burn, other, zMove, teraShield);

        //Total Damage Calculation

        System.out.print(totalDamageMin + "-" + totalDamageMax);
    }
}