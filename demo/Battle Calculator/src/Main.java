import java.util.*;

public class Main extends BattleFunctions {
    public static void main(String[] args) {
        //Damage Variable
        int level = 100;
        MoveClass move = new MoveClass(moveList.Sucker_Punch,70, pkmnType.Electric, moveCtgry.Physical, false, false, false);
        double attack = 0.00;
        double defense = 0.00;
        double totalDamage;
        double totalDamageMin;
        double totalDamageMax;
        SpeciesClass attacker = new SpeciesClass(271, 271,359,156,167,157,273, pkmnType.Dark, pkmnType.Typeless, abilityList.Magic_Bounce, itemList.Oran_Berry, 0, true, status.none);
        SpeciesClass defender = new SpeciesClass(241, 241,166,164,124,132,122, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.Oran_Berry, 0, true, status.none);

        //Variable Checkers
        boolean multBattle = false;
        boolean pbSecond = false;
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

        Checks checks = new Checks(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 0, weatherType.none, terrainType.none);

        if (move.getName().equals(moveList.Body_Press)) {
            attack = attacker.getDef();
            defense = defender.getDef();
        } else if (move.getName().equals(moveList.Foul_Play)) {
            attack = defender.getAtk();
            defense = defender.getDef();
        } else if (move.getName().equals(moveList.Psyshock) || move.getName().equals(moveList.Psystrike) || move.getName().equals(moveList.Secret_Sword)) {
            attack = attacker.getSpatk();
            defense = defender.getDef();
        } else if (move.getCategory() == moveCtgry.Physical) {
            attack = attacker.getAtk();
            defense = defender.getDef();
        } else if (move.getCategory() == moveCtgry.Special) {
            attack = attacker.getSpatk();
            defense = defender.getSpdef();
        }

        //Stat Changes and Modifications
        

        //Calculate Damage
        totalDamage = (Math.floor(Math.floor(Math.floor(2 * level / 5 + 2) * move.getBase() * attack / defense) / 50) + 2);

        //Individual Multiplier Checks
        if (move.getType() == attacker.getType1() || move.getType() == attacker.getType2()) {
            stab = 1.5;
        }

        if (checks.isDoubleBattle() && move.isSpread()) {
            targets = 0.75;
        }

        if (pbSecond) { //Currently Thinking about moving this into other
            pb = 0.25;
        }

        weather = weatherCheck(checks.getWeather(), move.getType());

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

        double percentageMin = 100.0 - (((defender.getHp() - totalDamageMin) / (double) defender.getHp()) * 100.0);
        double percentageMax = 100.0 - (((defender.getHp() - totalDamageMax) / (double) defender.getHp()) * 100.0);
        percentageMin = Math.floor(percentageMin * 10) / 10.0;
        percentageMax = Math.floor(percentageMax * 10) / 10.0;

        System.out.print((int) totalDamageMin + "-" + (int) totalDamageMax + " (");
        System.out.printf("%.1f%%", percentageMin);
        System.out.print(" - ");
        System.out.printf("%.1f%%", percentageMax);
        System.out.print(") -- ");
        if (defender.getCurrentHp() < totalDamageMin) {
            System.out.print("guaranteed OHKO");
        } else {
            int modular = (int) Math.ceil((defender.getCurrentHp() / totalDamageMin));
            System.out.print("guaranteed " + modular + "HKO");
        }
    }
}