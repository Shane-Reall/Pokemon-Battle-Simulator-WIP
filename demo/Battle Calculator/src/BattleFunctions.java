import java.io.*;
import java.util.HashMap;
import java.util.Random;

import static java.lang.System.exit;

public class BattleFunctions extends StatCalculation {

    private static final long serialVersionUID = 1L;

    static MoveClass damageChanges(MoveClass move, String currentMove, SpeciesClass pokemon, String currentMon) {

        if (currentMove.equals("Techno_Blast")) {
            if (pokemon.getItem().equals(itemList.Douse_Drive)) {
                move.setType(pkmnType.Water);
            } else if (pokemon.getItem().equals(itemList.Burn_Drive)) {
                move.setType(pkmnType.Fire);
            } else if (pokemon.getItem().equals(itemList.Shock_Drive)) {
                move.setType(pkmnType.Electric);
            } else if (pokemon.getItem().equals(itemList.Chill_Drive)) {
                move.setType(pkmnType.Ice);
            }
        }
        if (move.getType() == pkmnType.Dragon && (pokemon.getItem().equals(itemList.Dragon_Fang) || pokemon.getItem().equals(itemList.Draco_Plate))) {
            move.setBase((int) (move.getBase() * 1.2));
        }
        if (move.getType() == pkmnType.Steel && (pokemon.getItem().equals(itemList.Metal_Coat) || pokemon.getItem().equals(itemList.Iron_Plate))) {
            move.setBase((int) (move.getBase() * 1.2));
        }


        return move;
    }

    static SpeciesClass pokemonChanges(MoveClass move, String currentMove, SpeciesClass pokemon, String currentMon) {

        if (pokemon.getItem().equals(itemList.Power_Anklet)||pokemon.getItem().equals(itemList.Power_Band)||pokemon.getItem().equals(itemList.Power_Belt)||pokemon.getItem().equals(itemList.Power_Bracer)||pokemon.getItem().equals(itemList.Power_Lens)||pokemon.getItem().equals(itemList.Power_Weight)) {
            pokemon.setSpd(pokemon.getSpd()/2);
        }
        if (currentMon.equals("Clamperl") && pokemon.getItem().equals(itemList.Deep_Sea_Scale)) {
            pokemon.setSpdef(pokemon.getSpdef()*2);
        }
        if (currentMon.equals("Clamperl") && pokemon.getItem().equals(itemList.Deep_Sea_Tooth)) {
            pokemon.setSpatk(pokemon.getSpatk()*2);
        }

        return pokemon;
    }

    static double otherMulti(MoveClass move, SpeciesClass attacker, SpeciesClass defender, Checks checks, HashMap effectivenessChart, String currentMove) {
        double multipliers = 1;

        if ((currentMove.equals("Body_Slam") || currentMove.equals("Stomp") || currentMove.equals("Dragon_Rush") || currentMove.equals("Steamroller") || currentMove.equals("Heat_Crash") || currentMove.equals("Heavy_Slam") || currentMove.equals("Flying_Press") || currentMove.equals("Malicious_Moonsault") || currentMove.equals("Supercell_Slam")) && checks.isMinimized()) {
            multipliers *= 2;
        }
        if ((currentMove.equals("Earthquake") || currentMove.equals("Magnitude")) && checks.isUnderground()) {
            multipliers *= 2;
        }
        if ((currentMove.equals("Surf") || currentMove.equals("Whirlpool")) && checks.isUnderwater()) {
            multipliers *= 2;
        }
        if (checks.isAuroraVeil() && !defender.getAbility().equals(abilityList.Infiltrator)) {
            multipliers *= 0.5;
        } else if (checks.isReflect() && move.getCategory().equals(moveCtgry.Physical) && !defender.getAbility().equals(abilityList.Infiltrator)) {
            multipliers *= 0.5;
        } else if (checks.isLightScreen() && move.getCategory().equals(moveCtgry.Special) && !defender.getAbility().equals(abilityList.Infiltrator)) {
            multipliers *= 0.5;
        }
        if (typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1 && (currentMove.equals("Collision_Course") || currentMove.equals("Electro_Drift"))) {
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
        if (typeCalc(effectivenessChart, move.getType(), defender.getTypes()) > 1 && attacker.getItem().equals(itemList.Expert_Belt)) {
            multipliers *= 1.2;
        }
        if (attacker.getItem().equals(itemList.Life_Orb)) {
            multipliers *= 130.0 / 100.0;
        }
        if (attacker.getItem().equals(itemList.Metronome)) {
            double holder = (1 + (0.2 * checks.getContinueCounter()));
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

    /*public static void saveEffectivenessChart() {
        try {
            // Create directories if they don't exist
            new File("Serialisation").mkdirs();

            // Create and save the chart
            HashMap<String, SpeciesClass> chart = createPokemonList();

            FileOutputStream fileOut = new FileOutputStream("Serialisation/pokemonList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(chart);
            out.close();
            fileOut.close();
            System.out.println("Effectiveness chart has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    static HashMap loadMapEC() {

        try {
            FileInputStream fileInput = new FileInputStream("Serialisation/effectivenessChart.ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            HashMap<pkmnType, HashMap<pkmnType, Double>> effectivenessChart = (HashMap<pkmnType, HashMap<pkmnType, Double>>) objectInput.readObject(); // Cast to the correct type

            objectInput.close();
            fileInput.close();

            //System.out.println("Deserialized HashMap: Complete");

            return effectivenessChart;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static HashMap loadMapPL() {

        try {
            FileInputStream fileInput = new FileInputStream("Serialisation/pokemonList.ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            HashMap<String, SpeciesClass> pokemonList = (HashMap<String, SpeciesClass>) objectInput.readObject(); // Cast to the correct type

            objectInput.close();
            fileInput.close();

            //System.out.println("Deserialized HashMap: Complete");

            return pokemonList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static HashMap loadMapML() {

        try {
            FileInputStream fileInput = new FileInputStream("Serialisation/moveList.ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            HashMap<String, MoveClass> moveList = (HashMap<String, MoveClass>) objectInput.readObject(); // Cast to the correct type

            objectInput.close();
            fileInput.close();

            //System.out.println("Deserialized HashMap: Complete");

            return moveList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    static SpeciesClass modifications(int[] changes, SpeciesClass pokemon) {
        int stat = 0;
        int affectedStat = 0;

        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                    affectedStat = (int) pokemon.getAtk();
                    break;
                case 1:
                    affectedStat = (int) pokemon.getDef();
                    break;
                case 2:
                    affectedStat = (int) pokemon.getSpatk();
                    break;
                case 3:
                    affectedStat = (int) pokemon.getSpdef();
                    break;
                case 4:
                    affectedStat = (int) pokemon.getSpd();
                    break;
            }
            switch (changes[i]) {
                case -6:
                    stat = (int) (affectedStat * (2.0/8));
                    break;
                case -5:
                    stat = (int) (affectedStat * (2.0/7));
                    break;
                case -4:
                    stat = (int) (affectedStat * (2.0/6));
                    break;
                case -3:
                    stat = (int) (affectedStat * (2.0/5));
                    break;
                case -2:
                    stat = (int) (affectedStat * (2.0/4));
                    break;
                case -1:
                    stat = (int) (affectedStat * (2.0/3));
                    break;
                case 0:
                    stat = affectedStat;
                    break;
                case 1:
                    stat = (int) (affectedStat * (3.0/2));
                    break;
                case 2:
                    stat = (int) (affectedStat * (4.0/2));
                    break;
                case 3:
                    stat = (int) (affectedStat * (5.0/2));
                    break;
                case 4:
                    stat = (int) (affectedStat * (6.0/2));
                    break;
                case 5:
                    stat = (int) (affectedStat * (7.0/2));
                    break;
                case 6:
                    stat = (int) (affectedStat * (8.0/2));
                    break;
            }
            switch (i) {
                case 0:
                    pokemon.setAtk(stat);
                    break;
                case 1:
                    pokemon.setDef(stat);
                    break;
                case 2:
                    pokemon.setSpatk(stat);
                    break;
                case 3:
                    pokemon.setSpdef(stat);
                    break;
                case 4:
                    pokemon.setSpd(stat);
                    break;
            }
        }
        return pokemon;
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
        calculation = (calculation * other);
        //calculation = Math.floor(calculation * zMove);
        //calculation = Math.floor(calculation * teraShield);

        return Math.round(calculation);
    }
}
