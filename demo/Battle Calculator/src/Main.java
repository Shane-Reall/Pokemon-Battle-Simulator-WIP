import java.util.*;

public class Main extends BattleFunctions {
    public static void main(String[] args) {

        //saveEffectivenessChart();

        HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart;
        HashMap<String, SpeciesClass> pokemonList;

        effectivenessChart = loadMapEC();
        pokemonList = loadMapPL();

        //Damage Variable
        int level = 100;
        MoveClass move = new MoveClass(moveList.Shadow_Ball,120, pkmnType.Dark, moveCtgry.Physical, false, false, false);
        double totalDamage;
        double totalDamageMin;
        double totalDamageMax;

        int[] statBoostsA = {0,0,0,0,0}; //Max and Min of +/- 6 [Attack, Defense, Sp.Attack, Sp.Defense, Speed]

        int[] attackerIV = {31,31,31,31,31,31}; //Max 31 [HP, Attack, Defense, Sp.Attack, Sp.Defense, Speed]
        int[] attackerEV = {0,0,0,0,0,0}; //Max 252 (Limit: 510) [HP, Attack, Defense, Sp.Attack, Sp.Defense, Speed]
        String attackNature = "Bashful";

        int[] statBoostsD = {0,0,0,0,0}; //Max and Min of +/- 6 [Attack, Defense, Sp.Attack, Sp.Defense, Speed]

        int[] defenderIV = {31,31,31,31,31,31};;//Max 31 [HP, Attack, Defense, Sp.Attack, Sp.Defense, Speed]
        int[] defenderEV = {0,0,0,0,0,0}; //Max 252 (Limit: 510) [HP, Attack, Defense, Sp.Attack, Sp.Defense, Speed]
        String defendNature = "Bashful";

        SpeciesClass attacker = pokemonList.get("Mewtwo");
        SpeciesClass defender = pokemonList.get("Gengar");

        int hp = calcHP((int) attacker.getHp(), attackerIV[0], attackerEV[0], level);
        String[] natures = natureGet(attackNature);

        attacker = new SpeciesClass (hp, hp, statCalc((int) attacker.getAtk(), attackerIV[1], attackerEV[1], level, natures, "Attack"), statCalc((int) attacker.getDef(), attackerIV[2], attackerEV[2], level, natures, "Defense"), statCalc((int) attacker.getSpatk(), attackerIV[3], attackerEV[3], level, natures, "Sp.Attack"),statCalc((int) attacker.getSpdef(), attackerIV[4], attackerEV[4], level, natures, "Sp.Defense"), statCalc((int) attacker.getSpd(), attackerIV[5], attackerEV[5], level, natures, "Speed"), attacker.getType1(), attacker.getType2(), attacker.getAbility(), itemList.None, attacker.getWeight(), grounded(attacker.getType1(), attacker.getType2(), attacker.getAbility()), status.none);

        hp = calcHP((int) defender.getHp(), defenderIV[0], defenderEV[0], level);
        natures = natureGet(defendNature);

        defender = new SpeciesClass (hp, hp, statCalc((int) defender.getAtk(), defenderIV[1], defenderEV[1], level, natures, "Attack"), statCalc((int) defender.getDef(), defenderIV[2], defenderEV[2], level, natures, "Defense"), statCalc((int) defender.getSpatk(), defenderIV[3], defenderEV[3], level, natures, "Sp.Attack"),statCalc((int) defender.getSpdef(), defenderIV[4], defenderEV[4], level, natures, "Sp.Defense"), statCalc((int) defender.getSpd(), defenderIV[5], defenderEV[5], level, natures, "Speed"), defender.getType1(), defender.getType2(), defender.getAbility(), itemList.None, defender.getWeight(), grounded(defender.getType1(), defender.getType2(), defender.getAbility()), status.none);

        double attack = 0.00;
        double defense = 0.00;

        attacker.setItem(itemList.Life_Orb);

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

        Checks checks = new Checks(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 0, weatherType.none, terrainType.none);

        //Stat Changes and Modifications
        attacker = modifications(statBoostsA, attacker);
        defender = modifications(statBoostsD, defender);

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

        System.out.println(totalDamageMin + " - " + totalDamageMax);

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
        int modular = (int) Math.ceil((defender.getCurrentHp() / totalDamageMin));
        if (defender.getCurrentHp() < totalDamageMin) {
            System.out.print("guaranteed OHKO");
        } else if (modular < 10) {
            System.out.print("guaranteed " + modular + "HKO");
        } else {
            System.out.print("possibly the worst move ever");
        }
    }
}