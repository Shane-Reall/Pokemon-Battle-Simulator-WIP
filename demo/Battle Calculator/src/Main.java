import java.util.Scanner;
import java.util.Random;

enum weatherType { //All weather types that affect damage
    none,
    sun,
    rain,
    DL,
    PS
}

enum pkmnType { //All pokemon types
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
    Fairy
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
        int critChance = 0; //Ranges from 0 to 4 (If > 4 it will be the same as if it was four)
        boolean burned = false;
        boolean[] otherChecks = new boolean[]{false};
        pkmnType moveType = pkmnType.Normal;
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
        double type = 1;
        double burn = 1;
        double other = 1;
        double zMove = 1;
        double teraShield = 1;
        double totalMultMin = 0;
        double totalMultMax = 0;

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

        if (pbSecond) {
            pb = 0.25;
        }

        switch (currentWeather) {
            case rain:
                if (moveType == pkmnType.Water) {
                    type = 1.5;
                } else if (moveType == pkmnType.Fire) {
                    type = 0.5;
                }
                break;
            case sun:
                if (moveType == pkmnType.Water) {
                    type = 0.5;
                } else if (moveType == pkmnType.Fire) {
                    type = 1.5;
                }
                break;
            case DL:
                if (moveType == pkmnType.Water) {
                    type = 0;
                } else if (moveType == pkmnType.Fire) {
                    type = 1.5;
                }
                break;
            case PS:
                if (moveType == pkmnType.Water) {
                    type = 1.5;
                } else if (moveType == pkmnType.Fire) {
                    type = 0;
                }
                break;
            case none:
                break;
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