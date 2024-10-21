import java.util.*;

import static java.lang.System.exit;

enum weatherType { //All weather types that affect damage
    none,
    sun,
    rain,
    DL,
    PS
}

enum pkmnType { //All Pokemon Types
    Normal,
    Fighting,
    Flying,
    Poison,
    Ground,
    Rock,
    Bug,
    Ghost,
    Steel,
    Fire,
    Water,
    Grass,
    Electric,
    Psychic,
    Ice,
    Dragon,
    Dark,
    Fairy,
    Typeless
}

public class Main {
    public static void main(String[] args) {
        //Damage Variable
        double level = 0;
        double power = 0;
        double attack = 0;
        double defense = 0;
        double totalDamage = 0;
        int totalDamageMin = 0;
        int totalDamageMax = 0;

        //Variable Checkers
        boolean multBattle = false;
        boolean spreadMove = false;
        boolean pbSecond = false;
        weatherType currentWeather = weatherType.none;
        boolean glaiveUsed = false;
        int critStage = 0; //Ranges from 0 to 4 (If > 4 it will be the same as if it was four)
        boolean physicalCheck = false;
        boolean burned = false;
        String[] abilities = new String[4];
        pkmnType moveType = pkmnType.Typeless;
        pkmnType[] attackerType = {pkmnType.Typeless, pkmnType.Typeless, pkmnType.Typeless};
        pkmnType[] defenderType = {pkmnType.Typeless, pkmnType.Typeless, pkmnType.Typeless};
        boolean shieldUp = false;
        boolean tShieldUp = false;

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
        double totalMultMin = 0;
        double totalMultMax = 0;

        HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart = new HashMap<>();

        loadMap(effectivenessChart);

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        //Damage Variable Assignment
        System.out.print("Your Level: ");
        level = input.nextInt();

        System.out.print("Your Attacking Stat: ");
        attack = input.nextInt();

        System.out.print("Opponent Defending Stat: ");
        defense = input.nextInt();

        System.out.print("Attacking Move's Power: ");
        power = input.nextInt();

        //Calculate Damage
        totalDamage = (((((2*level)/5)+2) * power * (attack/defense))/50)+2;

        //Individual Multiplier Checks
        if (multBattle && spreadMove) {
            targets = 0.75;
        }

        if (pbSecond) { //Currently Thinking about moving this into other
            pb = 0.25;
        }

        type = weatherCheck(currentWeather, moveType);

        if (glaiveUsed) { //Currently Thinking about moving this into other
            glaiveRush = 2;
        }

        critical = critCalc(random, critStage);

        for (int i = 0; i < 3; i++) {
            if (moveType == attackerType[i]) {
                stab = 1.5;
                if (Objects.equals(abilities[0], "Adaptability")) {
                    stab = 2.0;
                }
                break;
            } else if (Objects.equals(abilities[0], "Adaptability")) {
                stab = 1.5;
            }
        }

         type = typeCalc(effectivenessChart, moveType, defenderType);

        if (burned && physicalCheck) {
            burn = 0.5;
        }



        //Total Multiplier Calculation
        totalMultMin = targets * pb * weather * glaiveRush * critical* rndmMin * stab * type * burn * other * zMove * teraShield;
        totalMultMax = targets * pb * weather * glaiveRush * critical* rndmMax * stab * type * burn * other * zMove * teraShield;

        //Total Damage Calculation
        totalDamageMin = (int) Math.floor(totalDamage * totalMultMin);
        totalDamageMax = (int) Math.floor(totalDamage * totalMultMax);

        System.out.print(totalDamageMin + "-" + totalDamageMax);
    }

    private static double typeCalc(HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart, pkmnType moveType, pkmnType[] defenderType) {
        double multiplier = 1.0;
        HashMap<pkmnType, Double> effectivness = effectivenessChart.get(moveType);

        for (int i = 0; i < 3; i++) {
            if (effectivness.containsKey(defenderType[i])) {
                multiplier *= effectivness.get(defenderType[i]);
            }
        }

        System.out.println(multiplier);

        return multiplier;
    }

    private static void loadMap(HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart) {
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

}