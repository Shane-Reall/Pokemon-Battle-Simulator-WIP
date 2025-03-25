import java.util.*;
import java.util.stream.IntStream;

public class Main extends BattleFunctions {
    public static void main(String[] args) {

        new AbilityFunctions();
        saveEffectivenessChart();

        HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart;
        HashMap<String, SpeciesClass> pokemonList;
        HashMap<String, MoveClass> moveList;
        Scanner input = new Scanner(System.in);

        String ignorable[] = {
                "Aroma_Veil", "Battle_Armor", "Big_Pecks", "Bulletproof",
                "Clear_Body", "Contrary", "Damp", "Dazzling",
                "Disguise", "Dry_Skin", "Filter", "Flash_Fire",
                "Flower_Gift", "Flower_Veil", "Fluffy", "Friend_Guard",
                "Fur_Coat", "Heatproof", "Heavy_Metal", "Hyper_Cutter",
                "Immunity", "Inner_Focus", "Insomnia", "Keen_Eye",
                "Leaf_Guard", "Levitate", "Light_Metal", "Lightningrod",
                "Limber", "Magic_Bounce", "Magma_Armor", "Marvel_Scale",
                "Motor_Drive", "Multiscale", "Oblivious", "Overcoat",
                "Own_Tempo", "Queenly_Majesty", "Sand_Veil", "Sap_Sipper",
                "Shell_Armor", "Shield_Dust", "Simple", "Snow_Cloak",
                "Solid_Rock", "Soundproof", "Sticky_Hold", "Storm_Drain",
                "Sturdy", "Suction_Cups", "Sweet_Veil", "Tangled_Feet",
                "Telepathy", "Thick_Fat", "Unaware", "Vital_Spirit",
                "Volt_Absorb", "Water_Absorb", "Water_Bubble", "Water_Veil",
                "White_Smoke", "Wonder_Guard", "Wonder_Skin"
        };

        String exception[] = {
                "Neutralizing Gas", " Multitype", " Zen Mode", " Stance Change",
                " Power Construct", " Schooling", " RKS System", " Shields Down",
                " Battle Bond", " Comatose", " Disguise", " Gulp Missile",
                " Ice Face", " As One", "Tera Shift"
        };

        effectivenessChart = loadMapEC();
        pokemonList = loadMapPL();
        moveList = loadMapML();

        System.out.print("Move: ");

        //Damage Variable
        int level = 100;
        int hitCount = 1;
        String currentMove = input.nextLine();
        MoveClass move = moveList.get(currentMove);
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
        String defendNature = "Docile";

        String pokemonA = "Feraligatr";
        String pokemonD = "Goodra";
        SpeciesClass attacker = pokemonList.get(pokemonA);
        SpeciesClass defender = pokemonList.get(pokemonD);

        attacker.setAbility(abilityList.Water_Bubble);
        defender.setAbility(abilityList.Water_Bubble);

        itemList itemA = itemList.None;
        itemList itemD = itemList.None;

        int hp = calcHP((int) attacker.getHp(), attackerIV[0], attackerEV[0], level);
        String[] natures = natureGet(attackNature);

        attacker = new SpeciesClass (hp, hp, statCalc((int) attacker.getAtk(), attackerIV[1], attackerEV[1], level, natures, "Attack"), statCalc((int) attacker.getDef(), attackerIV[2], attackerEV[2], level, natures, "Defense"), statCalc((int) attacker.getSpatk(), attackerIV[3], attackerEV[3], level, natures, "Sp.Attack"),statCalc((int) attacker.getSpdef(), attackerIV[4], attackerEV[4], level, natures, "Sp.Defense"), statCalc((int) attacker.getSpd(), attackerIV[5], attackerEV[5], level, natures, "Speed"), attacker.getType1(), attacker.getType2(), attacker.getAbility(), itemA, attacker.getWeight(), grounded(attacker.getType1(), attacker.getType2(), attacker.getAbility()), status.none);

        hp = calcHP((int) defender.getHp(), defenderIV[0], defenderEV[0], level);
        natures = natureGet(defendNature);

        defender = new SpeciesClass (hp, hp, statCalc((int) defender.getAtk(), defenderIV[1], defenderEV[1], level, natures, "Attack"), statCalc((int) defender.getDef(), defenderIV[2], defenderEV[2], level, natures, "Defense"), statCalc((int) defender.getSpatk(), defenderIV[3], defenderEV[3], level, natures, "Sp.Attack"),statCalc((int) defender.getSpdef(), defenderIV[4], defenderEV[4], level, natures, "Sp.Defense"), statCalc((int) defender.getSpd(), defenderIV[5], defenderEV[5], level, natures, "Speed"), defender.getType1(), defender.getType2(), defender.getAbility(), itemD, defender.getWeight(), grounded(defender.getType1(), defender.getType2(), defender.getAbility()), status.none);

        double attack = 0.00;
        double defense = 0.00;

        //defender.setStated(status.Burn);

        //Variable Checkers
        //int critStage = 0; //Ranges from 0 to 4 (If > 4 it will be the same as if it was four)

        //Multiplier Variable
        double targets = 1;
        double pb = 1;
        double weather = 1;
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

        Checks checks = new Checks( false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 0, weatherType.none, terrainType.none);

        if ((currentMove.equals("Brick_Break") || currentMove.equals("Psychic_Fangs") || currentMove.equals("Raging_Bull")) && (checks.isReflect() || checks.isLightScreen())) {
            checks.setReflect(false);
            checks.setLightScreen(false);
        } else if (currentMove.equals("Punishment")) {
            move.setBase(60);
            for (int i = 0; i < 5; i++) {
                if (statBoostsD[i] > 0) {
                    move.setBase(move.getBase() + (20 * statBoostsD[i]));
                    if (move.getBase() >= 200) {
                        move.setBase(200);
                        break;
                    }
                }
            }
        } else if (currentMove.equals("Stored_Power")) {
            move.setBase(20);
            for (int i = 0; i < 5; i++) {
                if (statBoostsA[i] > 0) {
                    move.setBase(move.getBase() + (20 * statBoostsA[i]));
                    if (move.getBase() >= 860) {
                        move.setBase(200);
                        break;
                    }
                }
            }
        } else if (currentMove.equals("Spectral_Thief")) {
            statBoostsA = statBoostsD;
            statBoostsD = new int[]{0, 0, 0, 0, 0};
        } else if (currentMove.equals("Sunsteel_Strike")) {
            defender.setAbility(null);
        }

        if (attacker.getAbility() == abilityList.Klutz) {
            attacker.setItem(itemList.None);
        } else if (attacker.getAbility() == abilityList.Merciless) {
            if (defender.getStated() == status.Poison || defender.getStated() == status.Toxiced) {
                critical = 2;
            }
        } else if (attacker.getAbility() == abilityList.Mold_Breaker || attacker.getAbility() == abilityList.Mycelium_Might || attacker.getAbility() == abilityList.Teravolt || attacker.getAbility() == abilityList.Turboblaze) {
            final abilityList tempAbility = defender.getAbility();
            if (Arrays.stream(ignorable).anyMatch(i -> i.equals(tempAbility.toString()))) {
                defender.setAbility(abilityList.none);
            }
        } else if (attacker.getAbility() == abilityList.Neutralizing_Gas) {
            final abilityList tempAbility = defender.getAbility();
            if (!(Arrays.stream(exception).anyMatch(i -> i.equals(tempAbility.toString())))) {
                defender.setAbility(abilityList.none);
            }
        } else if (attacker.getAbility() == abilityList.Unaware) {
            statBoostsD = new int[]{0, 0, 0, 0, 0};
        }

        if (defender.getAbility() == abilityList.Klutz) {
            defender.setItem(itemList.None);
        } else if (defender.getAbility() == abilityList.Mold_Breaker || defender.getAbility() == abilityList.Mycelium_Might) {
            final abilityList tempAbility = attacker.getAbility();
            if (Arrays.stream(ignorable).anyMatch(i -> i.equals(tempAbility.toString()))) {
                attacker.setAbility(abilityList.none);
            }
        } else if (defender.getAbility() == abilityList.Neutralizing_Gas) {
            final abilityList tempAbility = attacker.getAbility();
            if (!(Arrays.stream(exception).anyMatch(i -> i.equals(tempAbility.toString())))) {
                attacker.setAbility(abilityList.none);
            }
        } else if (defender.getAbility() == abilityList.Unaware) {
            statBoostsA = new int[]{0, 0, 0, 0, 0};
        }

        //Stat Changes and Modifications
        attacker = modifications(statBoostsA, attacker);
        if (!currentMove.equals("Chip_Away") && !currentMove.equals("Darkest_Lariat") && !attacker.getAbility().equals("Unaware")) {
            defender = modifications(statBoostsD, defender);
        }
        move = damageChanges(move, currentMove, attacker, pokemonA, defender, checks);
        if (move.getBase() <= 0) {
            printDamageRange(0, 0, 0, 0, defender);
            System.exit(0);
        }
        attacker = pokemonChanges(move, currentMove, attacker, pokemonA);
        defender = pokemonChanges(move, currentMove, defender, pokemonD);

        if (currentMove.equals("Body_Press")) {
            attack = attacker.getDef();
            defense = defender.getDef();
        } else if (currentMove.equals("Foul_Play")) {
            attack = defender.getAtk();
            defense = defender.getDef();
        } else if (currentMove.equals("Psyshock") || currentMove.equals("Psystrike") || currentMove.equals("Secret_Sword")) {
            attack = attacker.getSpatk();
            defense = defender.getDef();
        } else if (currentMove.equals("Shell_Side_Arm")) {
            if (((Math.floor(Math.floor(Math.floor(2 * level / 5 + 2) * move.getBase() * attacker.getAtk() / defender.getDef()) / 50) + 2)) >= ((Math.floor(Math.floor(Math.floor(2 * level / 5 + 2) * move.getBase() * attacker.getSpatk() / defender.getSpdef()) / 50) + 2))) {
                attack = attacker.getAtk();
                defense = defender.getDef();
                move.setContact(true);
            } else {
                attack = attacker.getSpatk();
                defense = defender.getSpdef();
                System.out.println("Special");
            }
        } else if (move.getCategory() == moveCtgry.Physical) {
            attack = attacker.getAtk();
            defense = defender.getDef();
        } else if (move.getCategory() == moveCtgry.Special) {
            attack = attacker.getSpatk();
            defense = defender.getSpdef();
        }

        if (currentMove.equals("Photon_Geyser")) {
            attack = Math.max(attacker.getAtk(), attacker.getSpatk());
        }

        //Calculate Damage
        totalDamage = (Math.floor(Math.floor(Math.floor(2 * level / 5 + 2) * Math.floor(move.getBase()) * Math.floor(attack) / Math.floor(defense)) / 50) + 2);

        //Individual Multiplier Checks
        if (move.getType() == attacker.getType1() || move.getType() == attacker.getType2()) {
            if (attacker.getAbility() == abilityList.Adaptability) {
                stab = 2;
            } else {
                stab = 1.5;
            }
        } else if ((move.getType() == pkmnType.Rock && attacker.getAbility() == abilityList.Rocky_Payload) || (move.getType() == pkmnType.Steel && (attacker.getAbility() == abilityList.Steelworker || attacker.getAbility() == abilityList.Steely_Spirit))) {
            stab = 1.5;
        }

        if (checks.isDoubleBattle() && move.isSpread()) {
            targets = 0.75;
        }

        if (!defender.getItem().equals(itemList.Utility_Umbrella)) {
            weather = weatherCheck(checks.getWeather(), move.getType());
        }

        //critical = critCalc(random, critStage);

        type = typeCalc(effectivenessChart, move.getType(), defender.getTypes());
        if (defender.getItem().equals(itemList.Ring_Target) && type == 0) {
            if (typeCalc(effectivenessChart, move.getType(), new pkmnType[]{defender.getType1(), pkmnType.Typeless}) == 0) {
                type = typeCalc(effectivenessChart, move.getType(), new pkmnType[]{defender.getType2(), pkmnType.Typeless});
            } else if (typeCalc(effectivenessChart, move.getType(), new pkmnType[]{defender.getType2(), pkmnType.Typeless}) == 0) {
                type = typeCalc(effectivenessChart, move.getType(), new pkmnType[]{defender.getType1(), pkmnType.Typeless});
            }
        }
        if (currentMove.equals("Flying_Press")) {
            type = type * typeCalc(effectivenessChart, pkmnType.Flying, defender.getTypes());
        }
        if ((attacker.getAbility() == abilityList.Minds_Eye || attacker.getAbility() == abilityList.Scrappy) && type == 0) {
            if (defender.getTypes()[1] == pkmnType.Ghost) {
                type = typeCalc(effectivenessChart, move.getType(), new pkmnType[]{defender.getType1(), pkmnType.Typeless});
            } else {
                type = typeCalc(effectivenessChart, move.getType(), new pkmnType[]{defender.getType2(), pkmnType.Typeless});
            }
        } else if ((attacker.getAbility() == abilityList.Tera_Shell) && (attacker.getCurrentHp() == attacker.getHp())) {
            type = 0.5;
        } else if ((attacker.getAbility() == abilityList.Tinted_Lens) && (type == 0.5)) {
            type = 1;
        }
        if ((defender.getAbility() == abilityList.Wonder_Guard) && (type != 2)) {
            type = 0;
        }

        if (attacker.getStated() == status.Burn && move.getCategory() == moveCtgry.Physical && attacker.getAbility() != abilityList.Guts) {
            burn = 0.5;
        }

        other = otherMulti(move, attacker, defender, checks, effectivenessChart, currentMove);

        //Total Multiplier Calculation

        totalDamageMin = totalCalc(totalDamage, targets, pb, weather, glaiveRush, critical, rndmMin, stab, type, burn, other, zMove, teraShield);
        totalDamageMax = totalCalc(totalDamage, targets, pb, weather, glaiveRush, critical, rndmMax, stab, type, burn, other, zMove, teraShield);

        if (currentMove.equals("Bonemerang") || currentMove.equals("Double_Hit") || currentMove.equals("Double_Iron_Bash") || currentMove.equals("Double_Kick") || currentMove.equals("Dragon_Darts") || currentMove.equals("Dual_Chop") || currentMove.equals("Dual_Wingbeat") || currentMove.equals("Gear_Grind") || currentMove.equals("Twineedle") || currentMove.equals("Tachyon_Cutter") || currentMove.equals("Twin_Beam")) {
            //for (int i = 0; i < hitCount; i++) {
            totalDamageMin += totalCalc(totalDamage, targets, pb, weather, glaiveRush, critical, rndmMin, stab, type, burn, other, zMove, teraShield);
            totalDamageMax += totalCalc(totalDamage, targets, pb, weather, glaiveRush, critical, rndmMax, stab, type, burn, other, zMove, teraShield);
            //}
        } else if (attacker.getAbility().equals(abilityList.Parental_Bond)) { //Currently Thinking about moving this into other
            totalDamageMin = totalDamageMin + totalCalc(totalDamage, targets, 0.25, weather, glaiveRush, critical, rndmMin, stab, type, burn, other, zMove, teraShield);
            totalDamageMax = totalDamageMax + totalCalc(totalDamage, targets, 0.25, weather, glaiveRush, critical, rndmMax, stab, type, burn, other, zMove, teraShield);
        } else if (currentMove.equals("Night_Shade") || currentMove.equals("Seismic_Toss")) {
            totalDamageMin = level;
            totalDamageMax = level;
        } else if (currentMove.equals("Final_Gambit")) {
            totalDamageMin = attacker.getCurrentHp();
            totalDamageMax = attacker.getCurrentHp();
        }


        //Total Damage Calculation

        double percentageMin = 100.0 - (((defender.getHp() - totalDamageMin) / (double) defender.getHp()) * 100.0);
        double percentageMax = 100.0 - (((defender.getHp() - totalDamageMax) / (double) defender.getHp()) * 100.0);
        percentageMin = Math.floor(percentageMin * 10) / 10.0;
        percentageMax = Math.floor(percentageMax * 10) / 10.0;

        printDamageRange(totalDamageMin, totalDamageMax, percentageMin, percentageMax, defender);
    }

    public static void printDamageRange(double totalDamageMin, double totalDamageMax, double percentageMin, double percentageMax, SpeciesClass defender) {
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