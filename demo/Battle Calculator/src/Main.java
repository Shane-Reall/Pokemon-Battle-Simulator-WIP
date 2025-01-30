import java.util.*;

public class Main extends BattleFunctions {
    public static void main(String[] args) {
        //Damage Variable
        int level = 100;
        MoveClass move = new MoveClass(moveList.Sucker_Punch,70, pkmnType.Dark, moveCtgry.Physical, false, false, false);
        double attack = 0.00;
        double defense = 0.00;
        double totalDamage;
        double totalDamageMin;
        double totalDamageMax;
        SpeciesClass attacker = new SpeciesClass(271, 271,359,156,167,157,273, pkmnType.Dark, pkmnType.Typeless, abilityList.Magic_Bounce, itemList.Life_Orb, 0, true, status.none);
        SpeciesClass defender = new SpeciesClass(241, 241,166,164,124,132,122, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.Oran_Berry, 0, true, status.none);

        //Variable Checkers
        boolean multBattle = false;
        boolean pbSecond = false;
        currentField field = new currentField();
        boolean glaiveUsed = false;
        int critStage = 0; //Ranges from 0 to 4 (If > 4 it will be the same as if it was four)

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

        //createEffectivenessChart();

        //saveEffectivenessChart();

        effectivenessChart = loadMap();

        Checks checks = new Checks(false, false, false, false, false, false, false, false, 0);

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
        if (move.getType() == attacker.getType1() || move.getType() == attacker.getType2()) {
            stab = 1.5;
        }

        if ((field.getBattle() == battleType.multi || field.getBattle() == battleType.triple) && move.isSpread()) {
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

        type = typeCalc(effectivenessChart, move.getType(), defender.getTypes());

        if (attacker.getStated() == status.Burn && move.getCategory() == moveCtgry.Physical) {
            burn = 0.5;
        }

        other = otherMulti(move, attacker, defender, checks, effectivenessChart);

        //Total Multiplier Calculation

        totalDamageMin = totalCalc(totalDamage, targets, pb, weather, glaiveRush, critical, rndmMin, stab, type, burn, other, zMove, teraShield);
        totalDamageMax = totalCalc(totalDamage, targets, pb, weather, glaiveRush, critical, rndmMax, stab, type, burn, other, zMove, teraShield);

        //Total Damage Calculation

        System.out.print((int) totalDamageMin + "-" + (int) totalDamageMax);
    }
}