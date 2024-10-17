import java.util.Scanner;

enum weatherType {
    none,
    sun,
    rain,
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

        //Booleans
        boolean multBattle = false;
        boolean pbSecond = false;
        weatherType currentWeather = weatherType.none;
        boolean glaiveUsed = false;
        boolean multBattle = false;
        boolean multBattle = false;
        boolean multBattle = false;
        boolean multBattle = false;
        boolean multBattle = false;
        boolean multBattle = false;
        boolean multBattle = false;
        boolean multBattle = false;

        //Multiplier Variable
        double targets = 1;
        double pb = 1;
        double weather = 1;
        double glaiveRush = 1;
        double critical = 1;
        double rndmMin = 0.85;
        double rndmMax = 1;
        double stab = 1;
        double type = 1;
        double burn = 1;
        double other = 1;
        double zMove = 1;
        double teraShield = 1;
        double totalMultMin = 0;
        double totalMultMax = 0;

        Scanner input = new Scanner(System.in);

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
        if () {

        }

        //Total Multiplier Calculation
        totalMultMin = targets * pb * weather * glaiveRush * critical* rndmMin * stab * type * burn * other * zMove * teraShield;
        totalMultMax = targets * pb * weather * glaiveRush * critical* rndmMax * stab * type * burn * other * zMove * teraShield;

        //Total Damage Calculation
        totalDamageMin = (int) Math.floor(totalDamage * totalMultMin);
        totalDamageMax = (int) Math.floor(totalDamage * totalMultMax);

        System.out.print(totalDamageMin + "-" + totalDamageMax);
    }
}