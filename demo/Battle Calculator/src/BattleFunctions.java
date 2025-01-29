import java.io.*;
import java.util.HashMap;
import java.util.Random;

import static java.lang.System.exit;

public class BattleFunctions {

    private static final long serialVersionUID = 1L;

    static double otherMulti(MoveClass move, SpeciesClass attacker, SpeciesClass defender, Checks checks, HashMap effectivenessChart) {
        double multipliers = 1;

        if ((move.getName().equals(moveList.Body_Slam) || move.getName().equals(moveList.Stomp) || move.getName().equals(moveList.Dragon_Rush) || move.getName().equals(moveList.Steamroller) || move.getName().equals(moveList.Heat_Crash) || move.getName().equals(moveList.Heavy_Slam) || move.getName().equals(moveList.Flying_Press) || move.getName().equals(moveList.Malicious_Moonsault) || move.getName().equals(moveList.Supercell_Slam)) && checks.isMinimized()) {
            multipliers *= 2;
        }
        if ((move.getName().equals(moveList.Earthquake) || move.getName().equals(moveList.Magnitude)) && checks.isUnderground()) {
            multipliers *= 2;
        }
        if ((move.getName().equals(moveList.Surf) || move.getName().equals(moveList.Whirlpool)) && checks.isUnderwater()) {
            multipliers *= 2;
        }
        if (checks.isUnderwater()) {
            multipliers *= 2;
        }
        if (checks.isAuroraVeil() && !defender.getAbility().equals(abilityList.Infiltrator)) {
            multipliers *= 0.5;
        } else if (checks.isReflect() && move.getCategory().equals(moveCtgry.Physical) && !defender.getAbility().equals(abilityList.Infiltrator)) {
            multipliers *= 0.5;
        } else if (checks.isLightScreen() && move.getCategory().equals(moveCtgry.Special) && !defender.getAbility().equals(abilityList.Infiltrator)) {
            multipliers *= 0.5;
        }
        if (typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1 && (move.getName().equals(moveList.Collision_Course) || move.getName().equals(moveList.Electro_Drift))) {
            multipliers *= (5461.0/4096.0);
        }
        if (defender.getCurrentHp() == defender.getHp() && (defender.getAbility().equals(abilityList.Multiscale) || defender.getAbility().equals(abilityList.Shadow_Shield))) {
            multipliers *= 0.5;
        }
        if (move.isContact() && defender.getAbility().equals(abilityList.Fluffy)) {
            multipliers *= 0.5;
        }
        if (move.isSound() && defender.getAbility().equals(abilityList.Punk_Rock)) {
            multipliers *= 0.5;
        }
        if (move.getCategory().equals(moveCtgry.Special) && defender.getAbility().equals(abilityList.Ice_Scales)) {
            multipliers *= 0.5;
        }
        if (checks.isFriendGuard()) {
            multipliers *= 0.5;
        }
        if (typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1 && (defender.getAbility().equals(abilityList.Filter) || defender.getAbility().equals(abilityList.Prism_Armor) || defender.getAbility().equals(abilityList.Solid_Rock))) {
            multipliers *= 0.75;
        }
        if (typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1 && defender.getAbility().equals(abilityList.Neuroforce)) {
            multipliers *= 1.25;
        }
        if (checks.isCrit() && defender.getAbility().equals(abilityList.Sniper)) {
            multipliers *= 1.5;
        }
        if (typeCalc(effectivenessChart, move.getType(), defender.getTypes()) < 1 && defender.getAbility().equals(abilityList.Tinted_Lens)) {
            multipliers *= 2;
        }
        if (move.getType().equals(pkmnType.Fire) && defender.getAbility().equals(abilityList.Fluffy)) {
            multipliers *= 2;
        }
        if (berryCheck(defender, move, effectivenessChart)) {
            if (defender.getAbility().equals(abilityList.Ripen)) {
                multipliers *= 0.25;
            }
            else {
                multipliers *= 0.5;
            }
        }
        if (typeCalc(effectivenessChart, move.getType(), defender.getTypes()) < 1 && attacker.getItem().equals(itemList.Expert_Belt)) {
            multipliers *= (4915.0/4096.0);
        }
        if (attacker.getItem().equals(itemList.Life_Orb)) {
            multipliers *= (5324.0/4096.0);
        }
        if (attacker.getItem().equals(itemList.Metronome)) {
            double holder = (1 + ((819.0/4096.0) * checks.getContinueCounter()));
            if (holder > 2) {
                multipliers *= 2;
            } else {
                multipliers *= holder;
            }
        }

        return multipliers;
    }

    private static boolean berryCheck(SpeciesClass defender, MoveClass move, HashMap effectivenessChart) {
        if (defender.getItem().equals(itemList.Chilan_Berry) && move.getType().equals(pkmnType.Normal)) {
            return true;
        }
        if (defender.getItem().equals(itemList.Occa_Berry) && move.getType().equals(pkmnType.Fire) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Passho_Berry) && move.getType().equals(pkmnType.Water) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Wacan_Berry) && move.getType().equals(pkmnType.Electric) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Rindo_Berry) && move.getType().equals(pkmnType.Grass) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Yache_Berry) && move.getType().equals(pkmnType.Ice) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Chople_Berry) && move.getType().equals(pkmnType.Fighting) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Kebia_Berry) && move.getType().equals(pkmnType.Poison) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Shuca_Berry) && move.getType().equals(pkmnType.Ground) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Coba_Berry) && move.getType().equals(pkmnType.Flying) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Payapa_Berry) && move.getType().equals(pkmnType.Psychic) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Tanga_Berry) && move.getType().equals(pkmnType.Bug) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Charti_Berry) && move.getType().equals(pkmnType.Rock) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Kasib_Berry) && move.getType().equals(pkmnType.Ghost) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Haban_Berry) && move.getType().equals(pkmnType.Dragon) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Colbur_Berry) && move.getType().equals(pkmnType.Dark) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Babiri_Berry) && move.getType().equals(pkmnType.Steel) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        if (defender.getItem().equals(itemList.Roseli_Berry) && move.getType().equals(pkmnType.Fairy) && typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1) {
            return true;
        }
        return false;
    }

    static double typeCalc(HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart, pkmnType moveType, pkmnType[] defenderType) {
        double multiplier = 1.0;
        HashMap<pkmnType, Double> effectivness = effectivenessChart.get(moveType);

        for (int i = 0; i < 3; i++) {
            if (moveType == pkmnType.Typeless || defenderType[i] == pkmnType.Typeless) {
                return multiplier;
            } else if (effectivness.containsKey(defenderType[i])) {
                multiplier *= effectivness.get(defenderType[i]);
            }
        }

        return multiplier;
    }

    public static HashMap<pkmnType, HashMap<pkmnType, Double>> createEffectivenessChart() {

        HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart = new HashMap<>();

        effectivenessChart.put(pkmnType.Normal, new HashMap<>() {
            {
                put(pkmnType.Rock, 0.5);
                put(pkmnType.Ghost, 0.0);
                put(pkmnType.Steel, 0.5);
            }});
        effectivenessChart.put(pkmnType.Fighting, new HashMap<>() {
            {
                put(pkmnType.Normal, 2.0);
                put(pkmnType.Flying, 0.5);
                put(pkmnType.Poison, 0.5);
                put(pkmnType.Rock, 2.0);
                put(pkmnType.Bug, 0.5);
                put(pkmnType.Ghost, 0.0);
                put(pkmnType.Steel, 2.0);
                put(pkmnType.Psychic, 0.5);
                put(pkmnType.Ice, 2.0);
                put(pkmnType.Dark, 2.0);
                put(pkmnType.Fairy, 0.5);
            }});
        effectivenessChart.put(pkmnType.Flying, new HashMap<>() {
            {
                put(pkmnType.Fighting, 2.0);
                put(pkmnType.Rock, 0.5);
                put(pkmnType.Bug, 2.0);
                put(pkmnType.Steel, 0.5);
                put(pkmnType.Grass, 2.0);
                put(pkmnType.Electric, 0.5);
            }});
        effectivenessChart.put(pkmnType.Poison, new HashMap<>() {
            {
                put(pkmnType.Poison, 0.5);
                put(pkmnType.Ground, 0.5);
                put(pkmnType.Rock, 0.5);
                put(pkmnType.Ghost, 0.5);
                put(pkmnType.Steel, 0.0);
                put(pkmnType.Grass, 2.0);
                put(pkmnType.Fairy, 2.0);
            }});
        effectivenessChart.put(pkmnType.Ground, new HashMap<>() {
            {
                put(pkmnType.Flying, 0.0);
                put(pkmnType.Poison, 2.0);
                put(pkmnType.Rock, 2.0);
                put(pkmnType.Bug, 0.5);
                put(pkmnType.Steel, 2.0);
                put(pkmnType.Fire, 2.0);
                put(pkmnType.Grass, 0.5);
                put(pkmnType.Electric, 2.0);
            }});
        effectivenessChart.put(pkmnType.Rock, new HashMap<>() {
            {
                put(pkmnType.Fighting, 0.5);
                put(pkmnType.Flying, 2.0);
                put(pkmnType.Ground, 0.5);
                put(pkmnType.Bug, 2.0);
                put(pkmnType.Steel, 0.5);
                put(pkmnType.Fire, 2.0);
                put(pkmnType.Ice, 2.0);
            }});
        effectivenessChart.put(pkmnType.Bug, new HashMap<>() {
            {
                put(pkmnType.Fighting, 0.5);
                put(pkmnType.Flying, 0.5);
                put(pkmnType.Poison, 0.5);
                put(pkmnType.Ghost, 0.5);
                put(pkmnType.Steel, 0.5);
                put(pkmnType.Fire, 0.5);
                put(pkmnType.Grass, 2.0);
                put(pkmnType.Psychic, 2.0);
                put(pkmnType.Dark, 2.0);
                put(pkmnType.Fairy, 0.5);
            }});
        effectivenessChart.put(pkmnType.Ghost, new HashMap<>() {
            {
                put(pkmnType.Normal, 0.0);
                put(pkmnType.Ghost, 2.0);
                put(pkmnType.Psychic, 2.0);
                put(pkmnType.Dark, 0.5);
            }});
        effectivenessChart.put(pkmnType.Steel, new HashMap<>() {
            {
                put(pkmnType.Rock, 2.0);
                put(pkmnType.Steel, 0.5);
                put(pkmnType.Fire, 0.5);
                put(pkmnType.Water, 0.5);
                put(pkmnType.Electric, 0.5);
                put(pkmnType.Ice, 2.0);
                put(pkmnType.Fairy, 2.0);
            }});
        effectivenessChart.put(pkmnType.Fire, new HashMap<>() {
            {
                put(pkmnType.Rock, 0.5);
                put(pkmnType.Bug, 2.0);
                put(pkmnType.Steel, 2.0);
                put(pkmnType.Fire, 0.5);
                put(pkmnType.Water, 0.5);
                put(pkmnType.Grass, 2.0);
                put(pkmnType.Ice, 2.0);
                put(pkmnType.Dragon, 0.5);
            }});
        effectivenessChart.put(pkmnType.Water, new HashMap<>() {
            {
                put(pkmnType.Ground, 2.0);
                put(pkmnType.Rock, 2.0);
                put(pkmnType.Fire, 2.0);
                put(pkmnType.Water, 0.5);
                put(pkmnType.Grass, 0.5);
                put(pkmnType.Dragon, 0.5);
            }});
        effectivenessChart.put(pkmnType.Grass, new HashMap<>() {
            {
                put(pkmnType.Flying, 0.5);
                put(pkmnType.Poison, 0.5);
                put(pkmnType.Ground, 2.0);
                put(pkmnType.Rock, 2.0);
                put(pkmnType.Bug, 0.5);
                put(pkmnType.Steel, 0.5);
                put(pkmnType.Fire, 0.5);
                put(pkmnType.Water, 2.0);
                put(pkmnType.Electric, 0.5);
                put(pkmnType.Dragon, 0.5);
            }});
        effectivenessChart.put(pkmnType.Electric, new HashMap<>() {
            {
                put(pkmnType.Flying, 2.0);
                put(pkmnType.Ground, 0.0);
                put(pkmnType.Water, 2.0);
                put(pkmnType.Grass, 0.5);
                put(pkmnType.Electric, 0.5);
                put(pkmnType.Dragon, 0.5);
            }});
        effectivenessChart.put(pkmnType.Psychic, new HashMap<>() {
            {
                put(pkmnType.Fighting, 2.0);
                put(pkmnType.Poison, 2.0);
                put(pkmnType.Steel, 0.5);
                put(pkmnType.Psychic, 0.5);
                put(pkmnType.Dark, 0.0);
            }});
        effectivenessChart.put(pkmnType.Ice, new HashMap<>() {
            {
                put(pkmnType.Flying, 2.0);
                put(pkmnType.Ground, 2.0);
                put(pkmnType.Steel, 0.5);
                put(pkmnType.Fire, 0.5);
                put(pkmnType.Water, 0.5);
                put(pkmnType.Grass, 2.0);
                put(pkmnType.Ice, 0.5);
                put(pkmnType.Dragon, 2.0);
            }});
        effectivenessChart.put(pkmnType.Dragon, new HashMap<>() {
            {
                put(pkmnType.Steel, 0.5);
                put(pkmnType.Dragon, 2.0);
                put(pkmnType.Fairy, 0.0);
            }});
        effectivenessChart.put(pkmnType.Dark, new HashMap<>() {
            {
                put(pkmnType.Fighting, 0.5);
                put(pkmnType.Ghost, 2.0);
                put(pkmnType.Psychic, 2.0);
                put(pkmnType.Dark, 0.5);
                put(pkmnType.Fairy, 0.5);
            }});
        effectivenessChart.put(pkmnType.Fairy, new HashMap<>() {
            {
                put(pkmnType.Fighting, 2.0);
                put(pkmnType.Poison, 0.5);
                put(pkmnType.Steel, 0.5);
                put(pkmnType.Fire, 0.5);
                put(pkmnType.Dragon, 2.0);
                put(pkmnType.Dark, 2.0);
            }});

        return effectivenessChart;
    }

    public static void saveEffectivenessChart() {
        try {
            // Create directories if they don't exist
            new File("Serialisation").mkdirs();

            // Create and save the chart
            HashMap<pkmnType, HashMap<pkmnType, Double>> chart = createEffectivenessChart();

            FileOutputStream fileOut = new FileOutputStream("Serialisation/effectivenessChart.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(chart);
            out.close();
            fileOut.close();
            System.out.println("Effectiveness chart has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static HashMap loadMap() {

        try {
            FileInputStream fileInput = new FileInputStream("Serialisation/effectivenessChart.ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart = (HashMap<pkmnType, HashMap<pkmnType, Double>>) objectInput.readObject(); // Cast to the correct type

            objectInput.close();
            fileInput.close();

            System.out.println("Deserialized HashMap: Complete");

            return effectivenessChart;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    static double weatherCheck(weatherType currentWeather, pkmnType moveType) {
        switch (currentWeather) {
            case rain:
                if (moveType == pkmnType.Water) {
                    return 1.5;
                } else if (moveType == pkmnType.Fire) {
                    return 0.5;
                }
                break;
            case sun:
                if (moveType == pkmnType.Water) {
                    return 0.5;
                } else if (moveType == pkmnType.Fire) {
                    return 1.5;
                }
                break;
            case DL:
                if (moveType == pkmnType.Water) {
                    return 0;
                } else if (moveType == pkmnType.Fire) {
                    return 1.5;
                }
                break;
            case PS:
                if (moveType == pkmnType.Water) {
                    return 1.5;
                } else if (moveType == pkmnType.Fire) {
                    return 0;
                }
                break;
            case none:
                return 1;
        }
        return 1;
    }

    static double critCalc(Random random, int critStage) {
        if (critStage <= -1) {
            critStage = 0;
        } else if (critStage >= 5) {
            critStage = 4;
        }
        int roll;
        switch (critStage) {
            case (0):
                roll = random.nextInt(0,24);
                if (roll/24 == 1) {
                    return 1.5;
                } else {
                    return 1;
                }
            case (1):
                roll = random.nextInt(0,8);
                if (roll/8 == 1) {
                    return 1.5;
                } else {
                    return 1;
                }
            case (2):
                roll = random.nextInt(0,2);
                if (roll/8 == 1) {
                    return 1.5;
                } else {
                    return 1;
                }
            case (4):
                return 1.5;
            default:
                System.out.println("Unforeseen Error: critCalc Reached Critical Failure" + '\n' + " Exiting Program...");
                exit(1);
        }
        exit(1);
        return 0;
    }

    static double totalCalc(double totalDamage, double targets, double pb, double weather, double glaiveRush, double critical, double rndm, double stab, double type, double burn, double other, double zMove, double teraShield) {
        double calculation;
        calculation = Math.floor(totalDamage * targets);
        calculation = Math.floor(calculation * pb);
        calculation = Math.floor(calculation * weather);
        calculation = Math.floor(calculation * glaiveRush);
        calculation = Math.floor(calculation * critical);
        calculation = Math.floor(calculation * rndm);
        calculation = Math.floor(calculation * stab);
        calculation = Math.floor(calculation * type);
        calculation = Math.floor(calculation * burn);
        calculation = Math.floor(calculation * other);
        calculation = Math.floor(calculation * zMove);
        calculation = Math.floor(calculation * teraShield);
        return calculation;

    }
}
