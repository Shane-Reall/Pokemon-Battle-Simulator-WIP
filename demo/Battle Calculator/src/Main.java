import java.util.*;

public class Main extends BattleFunctions {
    public static void main(String[] args) {
        //Damage Variable
        double level = 100.00;
        MoveClass move = new MoveClass(60, pkmnType.Water, moveCtgry.Special, false, false);
        double attack = 0.00;
        double defense = 0.00;
        double totalDamage = 0;
        double totalDamageMin = 0;
        double totalDamageMax = 0;
        SpeciesClass attacker = new SpeciesClass(241,166,164,124,132,122, pkmnType.Water, pkmnType.Typeless,"Torrent", 20.9);
        SpeciesClass defender = new SpeciesClass(231,156,116,176,136,126, pkmnType.Fire, pkmnType.Typeless,"Blaze", 5.50);

        //Variable Checkers
        boolean multBattle = false;
        boolean pbSecond = false;
        weatherType currentWeather = weatherType.none;
        boolean glaiveUsed = false;
        int critStage = 0; //Ranges from 0 to 4 (If > 4 it will be the same as if it was four)
        boolean burned = false;

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
        //System.out.print("Your Level: ");
        //level = input.nextInt();

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

        weather = weatherCheck(currentWeather, move.getType());

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

        other = otherMulti();

        System.out.println("stab: " + stab);
        System.out.println("type: " + type);
        System.out.println("attack: " + attack);
        System.out.println("defense: " + defense);
        System.out.println("totalDamage: " + totalDamage);

        //Total Multiplier Calculation
        totalDamageMin = Math.floor(totalDamage * targets);
        totalDamageMin = Math.floor(totalDamageMin * pb);
        totalDamageMin = Math.floor(totalDamageMin * weather);
        totalDamageMin = Math.floor(totalDamageMin * glaiveRush);
        totalDamageMin = Math.floor(totalDamageMin * critical);
        totalDamageMin = Math.floor(totalDamageMin * rndmMin);
        totalDamageMin = Math.floor(totalDamageMin * stab);
        totalDamageMin = Math.floor(totalDamageMin * type);
        totalDamageMin = Math.floor(totalDamageMin * burn);
        totalDamageMin = Math.floor(totalDamageMin * other);
        totalDamageMin = Math.floor(totalDamageMin * zMove);
        totalDamageMin = Math.floor(totalDamageMin * teraShield);

        totalDamageMax = Math.floor(totalDamage * targets);
        totalDamageMax = Math.floor(totalDamageMax * pb);
        totalDamageMax = Math.floor(totalDamageMax * weather);
        totalDamageMax = Math.floor(totalDamageMax * glaiveRush);
        totalDamageMax = Math.floor(totalDamageMax * critical);
        totalDamageMax = Math.floor(totalDamageMax * rndmMax);
        totalDamageMax = Math.floor(totalDamageMax * stab);
        totalDamageMax = Math.floor(totalDamageMax * type);
        totalDamageMax = Math.floor(totalDamageMax * burn);
        totalDamageMax = Math.floor(totalDamageMax * other);
        totalDamageMax = Math.floor(totalDamageMax * zMove);
        totalDamageMax = Math.floor(totalDamageMax * teraShield);

        System.out.println("totalDamageMin: " + (totalDamageMin));
        System.out.println("totalDamageMax: " + (totalDamageMax));

        //Total Damage Calculation

        System.out.print(totalDamageMin + "-" + totalDamageMax);
    }
}