import java.util.*;

public class Main extends BattleFunctions {
    public static void main(String[] args) {
        //Damage Variable
        double level = 0;
        double power = 0;
        double attack = 0;
        double defense = 0;
        double totalDamage = 0;
        int totalDamageMin = 0;
        int totalDamageMax = 0;
        SpeciesClass attacker = new SpeciesClass(0,0,0,0,0,0, pkmnType.Typeless, pkmnType.Typeless,"Good as Gold", 0.00);
        SpeciesClass defender = new SpeciesClass(0,0,0,0,0,0, pkmnType.Typeless, pkmnType.Typeless,"Good as Gold", 0.00);

        //Variable Checkers
        boolean multBattle = false;
        boolean spreadMove = false;
        boolean pbSecond = false;
        weatherType currentWeather = weatherType.none;
        boolean glaiveUsed = false;
        int critStage = 0; //Ranges from 0 to 4 (If > 4 it will be the same as if it was four)
        boolean physicalCheck = false;
        boolean burned = false;
        pkmnType moveType = pkmnType.Typeless;
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
            if (moveType == attacker.getTypes()[i]) {
                stab = 1.5;
                if (Objects.equals(attacker.getAbility(), "Adaptability")) {
                    stab = 2.0;
                }
            } else if (Objects.equals(attacker.getAbility(), "Adaptability")) {
                stab = 1.5;
            }
        }

         type = typeCalc(effectivenessChart, moveType, defender.getTypes());

        if (burned && physicalCheck) {
            burn = 0.5;
        }

        other = otherMulti();

        //Total Multiplier Calculation
        totalMultMin = targets * pb * weather * glaiveRush * critical* rndmMin * stab * type * burn * other * zMove * teraShield;
        totalMultMax = targets * pb * weather * glaiveRush * critical* rndmMax * stab * type * burn * other * zMove * teraShield;

        //Total Damage Calculation
        totalDamageMin = (int) Math.floor(totalDamage * totalMultMin);
        totalDamageMax = (int) Math.floor(totalDamage * totalMultMax);

        System.out.print(totalDamageMin + "-" + totalDamageMax);
    }
}