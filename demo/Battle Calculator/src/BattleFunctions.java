import java.util.HashMap;
import java.util.Random;

import static java.lang.System.exit;

public class BattleFunctions {
    static double otherMulti(MoveClass move, SpeciesClass attacker, SpeciesClass defender) {

        return 1;
    }

    static double typeCalc(HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart, pkmnType moveType, pkmnType[] defenderType) {
        double multiplier = 1.0;
        HashMap<pkmnType, Double> effectivness = effectivenessChart.get(moveType);

        for (int i = 0; i < 3; i++) {
            if (effectivness.containsKey(defenderType[i])) {
                multiplier *= effectivness.get(defenderType[i]);
            }
        }

        return multiplier;
    }

    static void loadMap(HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart) {
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
