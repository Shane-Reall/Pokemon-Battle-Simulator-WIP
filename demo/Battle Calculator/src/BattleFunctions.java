import java.io.*;
import java.util.HashMap;
import java.util.Random;

import static java.lang.System.exit;

public class BattleFunctions extends StatCalculation {

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

        System.out.println("Mult: " + multipliers);

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

    public static HashMap<String, SpeciesClass> createPokemonList() {
        HashMap<String, SpeciesClass> pokeList = new HashMap<>();

        pokeList.put("Bulbasaur", new SpeciesClass(45, 45, 49, 49, 65, 65, 45, pkmnType.Grass, pkmnType.Poison, abilityList.Overgrow, itemList.None, 15.2, false, status.none));
        pokeList.put("Ivysaur", new SpeciesClass(60, 60, 62, 63, 80, 80, 60, pkmnType.Grass, pkmnType.Poison, abilityList.Overgrow, itemList.None, 28.7, false, status.none));
        pokeList.put("Venusaur", new SpeciesClass(80, 80, 82, 83, 100, 100, 80, pkmnType.Grass, pkmnType.Poison, abilityList.Overgrow, itemList.None, 220.5, false, status.none));
        pokeList.put("Venusaur-M", new SpeciesClass(80, 80, 100, 123, 122, 120, 80, pkmnType.Grass, pkmnType.Poison, abilityList.Thick_Fat, itemList.None, 342.8, false, status.none));
        pokeList.put("Charmander", new SpeciesClass(39, 39, 52, 43, 60, 50, 65, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 18.7, false, status.none));
        pokeList.put("Charmeleon", new SpeciesClass(58, 58, 64, 58, 80, 65, 80, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 41.9, false, status.none));
        pokeList.put("Charizard", new SpeciesClass(78, 78, 84, 78, 109, 85, 100, pkmnType.Fire, pkmnType.Flying, abilityList.Blaze, itemList.None, 199.5, false, status.none));
        pokeList.put("Charizard-M", new SpeciesClass(78, 78, 130, 111, 130, 85, 100, pkmnType.Fire, pkmnType.Dragon, abilityList.Tough_Claws, itemList.None, 243.6, false, status.none));
        pokeList.put("Charizard-M", new SpeciesClass(78, 78, 104, 78, 159, 115, 100, pkmnType.Fire, pkmnType.Flying, abilityList.Drought, itemList.None, 221.6, false, status.none));
        pokeList.put("Squirtle", new SpeciesClass(44, 44, 48, 65, 50, 64, 43, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 19.8, false, status.none));
        pokeList.put("Wartortle", new SpeciesClass(59, 59, 63, 80, 65, 80, 58, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 49.6, false, status.none));
        pokeList.put("Blastoise", new SpeciesClass(79, 79, 83, 100, 85, 105, 78, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 188.5, false, status.none));
        pokeList.put("Blastoise-M", new SpeciesClass(79, 79, 103, 120, 135, 115, 78, pkmnType.Water, pkmnType.Typeless, abilityList.Mega_Launcher, itemList.None, 222.9, false, status.none));
        pokeList.put("Caterpie", new SpeciesClass(45, 45, 30, 35, 20, 20, 45, pkmnType.Bug, pkmnType.Typeless, abilityList.Shield_Dust, itemList.None, 6.4, false, status.none));
        pokeList.put("Metapod", new SpeciesClass(50, 50, 20, 55, 25, 25, 30, pkmnType.Bug, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 21.8, false, status.none));
        pokeList.put("Butterfree", new SpeciesClass(60, 60, 45, 50, 90, 80, 70, pkmnType.Bug, pkmnType.Flying, abilityList.Compound_Eyes, itemList.None, 70.5, false, status.none));
        pokeList.put("Weedle", new SpeciesClass(40, 40, 35, 30, 20, 20, 50, pkmnType.Bug, pkmnType.Poison, abilityList.Shield_Dust, itemList.None, 7.1, false, status.none));
        pokeList.put("Kakuna", new SpeciesClass(45, 45, 25, 50, 25, 25, 35, pkmnType.Bug, pkmnType.Poison, abilityList.Shed_Skin, itemList.None, 22.0, false, status.none));
        pokeList.put("Beedrill", new SpeciesClass(65, 65, 90, 40, 45, 80, 75, pkmnType.Bug, pkmnType.Poison, abilityList.Swarm, itemList.None, 65.0, false, status.none));
        pokeList.put("Beedrill-M", new SpeciesClass(65, 65, 150, 40, 15, 80, 145, pkmnType.Bug, pkmnType.Poison, abilityList.Adaptability, itemList.None, 89.3, false, status.none));
        pokeList.put("Pidgey", new SpeciesClass(40, 40, 45, 40, 35, 35, 56, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 4.0, false, status.none));
        pokeList.put("Pidgeotto", new SpeciesClass(63, 63, 60, 55, 50, 50, 71, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 66.1, false, status.none));
        pokeList.put("Pidgeot", new SpeciesClass(83, 83, 80, 75, 70, 70, 101, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 87.1, false, status.none));
        pokeList.put("Pidgeot-M", new SpeciesClass(83, 83, 80, 80, 135, 80, 121, pkmnType.Normal, pkmnType.Flying, abilityList.No_Guard, itemList.None, 111.3, false, status.none));
        pokeList.put("Rattata", new SpeciesClass(30, 30, 56, 35, 25, 35, 72, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 7.7, false, status.none));
        pokeList.put("Rattata-A", new SpeciesClass(30, 30, 56, 35, 25, 35, 72, pkmnType.Dark, pkmnType.Normal, abilityList.Gluttony, itemList.None, 8.4, false, status.none));
        pokeList.put("Raticate", new SpeciesClass(55, 55, 81, 60, 50, 70, 97, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 40.8, false, status.none));
        pokeList.put("Raticate-A", new SpeciesClass(75, 75, 71, 70, 40, 80, 77, pkmnType.Dark, pkmnType.Normal, abilityList.Gluttony, itemList.None, 56.2, false, status.none));
        pokeList.put("Spearow", new SpeciesClass(40, 40, 60, 30, 31, 31, 70, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 4.4, false, status.none));
        pokeList.put("Fearow", new SpeciesClass(65, 65, 90, 65, 61, 61, 100, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 83.8, false, status.none));
        pokeList.put("Ekans", new SpeciesClass(35, 35, 60, 44, 40, 54, 55, pkmnType.Poison, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 15.2, false, status.none));
        pokeList.put("Arbok", new SpeciesClass(60, 60, 95, 69, 65, 79, 80, pkmnType.Poison, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 143.3, false, status.none));
        pokeList.put("Pikachu", new SpeciesClass(35, 35, 55, 40, 50, 50, 90, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 13.2, false, status.none));
        pokeList.put("Pikachu-P", new SpeciesClass(45, 45, 80, 50, 75, 60, 120, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 13.2, false, status.none));
        pokeList.put("Raichu", new SpeciesClass(60, 60, 90, 55, 90, 80, 110, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 66.1, false, status.none));
        pokeList.put("Raichu-A", new SpeciesClass(60, 60, 85, 50, 95, 85, 110, pkmnType.Electric, pkmnType.Psychic, abilityList.Surge_Surfer, itemList.None, 46.3, false, status.none));
        pokeList.put("Sandshrew", new SpeciesClass(50, 50, 75, 85, 20, 30, 40, pkmnType.Ground, pkmnType.Typeless, abilityList.Sand_Veil, itemList.None, 26.5, false, status.none));
        pokeList.put("Sandshrew-A", new SpeciesClass(50, 50, 75, 90, 10, 35, 40, pkmnType.Ice, pkmnType.Steel, abilityList.Snow_Cloak, itemList.None, 88.2, false, status.none));
        pokeList.put("Sandslash", new SpeciesClass(75, 75, 100, 110, 45, 55, 65, pkmnType.Ground, pkmnType.Typeless, abilityList.Sand_Veil, itemList.None, 65.0, false, status.none));
        pokeList.put("Sandslash-A", new SpeciesClass(75, 75, 100, 120, 25, 65, 65, pkmnType.Ice, pkmnType.Steel, abilityList.Snow_Cloak, itemList.None, 121.3, false, status.none));
        pokeList.put("Nidoran_F", new SpeciesClass(55, 55, 47, 52, 40, 40, 41, pkmnType.Poison, pkmnType.Typeless, abilityList.Poison_Point, itemList.None, 15.4, false, status.none));
        pokeList.put("Nidorina", new SpeciesClass(70, 70, 62, 67, 55, 55, 56, pkmnType.Poison, pkmnType.Typeless, abilityList.Poison_Point, itemList.None, 44.1, false, status.none));
        pokeList.put("Nidoqueen", new SpeciesClass(90, 90, 92, 87, 75, 85, 76, pkmnType.Poison, pkmnType.Ground, abilityList.Poison_Point, itemList.None, 132.3, false, status.none));
        pokeList.put("Nidoran_M", new SpeciesClass(46, 46, 57, 40, 40, 40, 50, pkmnType.Poison, pkmnType.Typeless, abilityList.Poison_Point, itemList.None, 19.8, false, status.none));
        pokeList.put("Nidorino", new SpeciesClass(61, 61, 72, 57, 55, 55, 65, pkmnType.Poison, pkmnType.Typeless, abilityList.Poison_Point, itemList.None, 43.0, false, status.none));
        pokeList.put("Nidoking", new SpeciesClass(81, 81, 102, 77, 85, 75, 85, pkmnType.Poison, pkmnType.Ground, abilityList.Poison_Point, itemList.None, 136.7, false, status.none));
        pokeList.put("Clefairy", new SpeciesClass(70, 70, 45, 48, 60, 65, 35, pkmnType.Fairy, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 16.5, false, status.none));
        pokeList.put("Clefable", new SpeciesClass(95, 95, 70, 73, 95, 90, 60, pkmnType.Fairy, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 88.2, false, status.none));
        pokeList.put("Vulpix", new SpeciesClass(38, 38, 41, 40, 50, 65, 65, pkmnType.Fire, pkmnType.Typeless, abilityList.Flash_Fire, itemList.None, 21.8, false, status.none));
        pokeList.put("Vulpix-A", new SpeciesClass(38, 38, 41, 40, 50, 65, 65, pkmnType.Ice, pkmnType.Typeless, abilityList.Snow_Cloak, itemList.None, 21.8, false, status.none));
        pokeList.put("Ninetales", new SpeciesClass(73, 73, 76, 75, 81, 100, 100, pkmnType.Fire, pkmnType.Typeless, abilityList.Flash_Fire, itemList.None, 43.9, false, status.none));
        pokeList.put("Ninetales-A", new SpeciesClass(73, 73, 67, 75, 81, 100, 109, pkmnType.Ice, pkmnType.Fairy, abilityList.Snow_Cloak, itemList.None, 43.9, false, status.none));
        pokeList.put("Jigglypuff", new SpeciesClass(115, 115, 45, 20, 45, 25, 20, pkmnType.Normal, pkmnType.Fairy, abilityList.Cute_Charm, itemList.None, 12.1, false, status.none));
        pokeList.put("Wigglytuff", new SpeciesClass(140, 140, 70, 45, 85, 50, 45, pkmnType.Normal, pkmnType.Fairy, abilityList.Cute_Charm, itemList.None, 26.5, false, status.none));
        pokeList.put("Zubat", new SpeciesClass(40, 40, 45, 35, 30, 40, 55, pkmnType.Poison, pkmnType.Flying, abilityList.Inner_Focus, itemList.None, 16.5, false, status.none));
        pokeList.put("Golbat", new SpeciesClass(75, 75, 80, 70, 65, 75, 90, pkmnType.Poison, pkmnType.Flying, abilityList.Inner_Focus, itemList.None, 121.3, false, status.none));
        pokeList.put("Oddish", new SpeciesClass(45, 45, 50, 55, 75, 65, 30, pkmnType.Grass, pkmnType.Poison, abilityList.Chlorophyll, itemList.None, 11.9, false, status.none));
        pokeList.put("Gloom", new SpeciesClass(60, 60, 65, 70, 85, 75, 40, pkmnType.Grass, pkmnType.Poison, abilityList.Chlorophyll, itemList.None, 19.0, false, status.none));
        pokeList.put("Vileplume", new SpeciesClass(75, 75, 80, 85, 110, 90, 50, pkmnType.Grass, pkmnType.Poison, abilityList.Chlorophyll, itemList.None, 41.0, false, status.none));
        pokeList.put("Paras", new SpeciesClass(35, 35, 70, 55, 45, 55, 25, pkmnType.Bug, pkmnType.Grass, abilityList.Effect_Spore, itemList.None, 11.9, false, status.none));
        pokeList.put("Parasect", new SpeciesClass(60, 60, 95, 80, 60, 80, 30, pkmnType.Bug, pkmnType.Grass, abilityList.Effect_Spore, itemList.None, 65.0, false, status.none));
        pokeList.put("Venonat", new SpeciesClass(60, 60, 55, 50, 40, 55, 45, pkmnType.Bug, pkmnType.Poison, abilityList.Compound_Eyes, itemList.None, 66.1, false, status.none));
        pokeList.put("Venomoth", new SpeciesClass(70, 70, 65, 60, 90, 75, 90, pkmnType.Bug, pkmnType.Poison, abilityList.Shield_Dust, itemList.None, 27.6, false, status.none));
        pokeList.put("Diglett", new SpeciesClass(10, 10, 55, 25, 35, 45, 95, pkmnType.Ground, pkmnType.Typeless, abilityList.Sand_Veil, itemList.None, 1.8, false, status.none));
        pokeList.put("Diglett-A", new SpeciesClass(10, 10, 55, 30, 35, 45, 90, pkmnType.Ground, pkmnType.Steel, abilityList.Sand_Veil, itemList.None, 2.2, false, status.none));
        pokeList.put("Dugtrio", new SpeciesClass(35, 35, 100, 50, 50, 70, 120, pkmnType.Ground, pkmnType.Typeless, abilityList.Sand_Veil, itemList.None, 73.4, false, status.none));
        pokeList.put("Dugtrio-A", new SpeciesClass(35, 35, 100, 60, 50, 70, 110, pkmnType.Ground, pkmnType.Steel, abilityList.Sand_Veil, itemList.None, 146.8, false, status.none));
        pokeList.put("Meowth", new SpeciesClass(40, 40, 45, 35, 40, 40, 90, pkmnType.Normal, pkmnType.Typeless, abilityList.Pickup, itemList.None, 9.3, false, status.none));
        pokeList.put("Meowth-A", new SpeciesClass(40, 40, 35, 35, 50, 40, 90, pkmnType.Dark, pkmnType.Typeless, abilityList.Pickup, itemList.None, 9.3, false, status.none));
        pokeList.put("Meowth-G", new SpeciesClass(50, 50, 65, 55, 40, 40, 40, pkmnType.Steel, pkmnType.Typeless, abilityList.Pickup, itemList.None, 16.5, false, status.none));
        pokeList.put("Persian", new SpeciesClass(65, 65, 70, 60, 65, 65, 115, pkmnType.Normal, pkmnType.Typeless, abilityList.Limber, itemList.None, 70.5, false, status.none));
        pokeList.put("Persian-A", new SpeciesClass(65, 65, 60, 60, 75, 65, 115, pkmnType.Dark, pkmnType.Typeless, abilityList.Fur_Coat, itemList.None, 72.8, false, status.none));
        pokeList.put("Psyduck", new SpeciesClass(50, 50, 52, 48, 65, 50, 55, pkmnType.Water, pkmnType.Typeless, abilityList.Damp, itemList.None, 43.2, false, status.none));
        pokeList.put("Golduck", new SpeciesClass(80, 80, 82, 78, 95, 80, 85, pkmnType.Water, pkmnType.Typeless, abilityList.Damp, itemList.None, 168.9, false, status.none));
        pokeList.put("Mankey", new SpeciesClass(40, 40, 80, 35, 35, 45, 70, pkmnType.Fighting, pkmnType.Typeless, abilityList.Vital_Spirit, itemList.None, 61.7, false, status.none));
        pokeList.put("Primeape", new SpeciesClass(65, 65, 105, 60, 60, 70, 95, pkmnType.Fighting, pkmnType.Typeless, abilityList.Vital_Spirit, itemList.None, 70.5, false, status.none));
        pokeList.put("Growlithe", new SpeciesClass(55, 55, 70, 45, 70, 50, 60, pkmnType.Fire, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 41.9, false, status.none));
        pokeList.put("Growlithe-H", new SpeciesClass(60, 60, 75, 45, 65, 50, 55, pkmnType.Fire, pkmnType.Rock, abilityList.Intimidate, itemList.None, 50.0, false, status.none));
        pokeList.put("Arcanine", new SpeciesClass(90, 90, 110, 80, 100, 80, 95, pkmnType.Fire, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 341.7, false, status.none));
        pokeList.put("Arcanine-H", new SpeciesClass(95, 95, 115, 80, 95, 80, 90, pkmnType.Fire, pkmnType.Rock, abilityList.Intimidate, itemList.None, 370.4, false, status.none));
        pokeList.put("Poliwag", new SpeciesClass(40, 40, 50, 40, 40, 40, 90, pkmnType.Water, pkmnType.Typeless, abilityList.Water_Absorb, itemList.None, 27.3, false, status.none));
        pokeList.put("Poliwhirl", new SpeciesClass(65, 65, 65, 65, 50, 50, 90, pkmnType.Water, pkmnType.Typeless, abilityList.Water_Absorb, itemList.None, 44.1, false, status.none));
        pokeList.put("Poliwrath", new SpeciesClass(90, 90, 95, 95, 70, 90, 70, pkmnType.Water, pkmnType.Fighting, abilityList.Water_Absorb, itemList.None, 119.0, false, status.none));
        pokeList.put("Abra", new SpeciesClass(25, 25, 20, 15, 105, 55, 90, pkmnType.Psychic, pkmnType.Typeless, abilityList.Synchronize, itemList.None, 43.0, false, status.none));
        pokeList.put("Kadabra", new SpeciesClass(40, 40, 35, 30, 120, 70, 105, pkmnType.Psychic, pkmnType.Typeless, abilityList.Synchronize, itemList.None, 124.6, false, status.none));
        pokeList.put("Alakazam", new SpeciesClass(55, 55, 50, 45, 135, 95, 120, pkmnType.Psychic, pkmnType.Typeless, abilityList.Synchronize, itemList.None, 105.8, false, status.none));
        pokeList.put("Alakazam-M", new SpeciesClass(55, 55, 50, 65, 175, 105, 150, pkmnType.Psychic, pkmnType.Typeless, abilityList.Trace, itemList.None, 105.8, false, status.none));
        pokeList.put("Machop", new SpeciesClass(70, 70, 80, 50, 35, 35, 35, pkmnType.Fighting, pkmnType.Typeless, abilityList.Guts, itemList.None, 43.0, false, status.none));
        pokeList.put("Machoke", new SpeciesClass(80, 80, 100, 70, 50, 60, 45, pkmnType.Fighting, pkmnType.Typeless, abilityList.Guts, itemList.None, 155.4, false, status.none));
        pokeList.put("Machamp", new SpeciesClass(90, 90, 130, 80, 65, 85, 55, pkmnType.Fighting, pkmnType.Typeless, abilityList.Guts, itemList.None, 286.6, false, status.none));
        pokeList.put("Bellsprout", new SpeciesClass(50, 50, 75, 35, 70, 30, 40, pkmnType.Grass, pkmnType.Poison, abilityList.Chlorophyll, itemList.None, 8.8, false, status.none));
        pokeList.put("Weepinbell", new SpeciesClass(65, 65, 90, 50, 85, 45, 55, pkmnType.Grass, pkmnType.Poison, abilityList.Chlorophyll, itemList.None, 14.1, false, status.none));
        pokeList.put("Victreebel", new SpeciesClass(80, 80, 105, 65, 100, 70, 70, pkmnType.Grass, pkmnType.Poison, abilityList.Chlorophyll, itemList.None, 34.2, false, status.none));
        pokeList.put("Tentacool", new SpeciesClass(40, 40, 40, 35, 50, 100, 70, pkmnType.Water, pkmnType.Poison, abilityList.Clear_Body, itemList.None, 100.3, false, status.none));
        pokeList.put("Tentacruel", new SpeciesClass(80, 80, 70, 65, 80, 120, 100, pkmnType.Water, pkmnType.Poison, abilityList.Clear_Body, itemList.None, 121.3, false, status.none));
        pokeList.put("Geodude", new SpeciesClass(40, 40, 80, 100, 30, 30, 20, pkmnType.Rock, pkmnType.Ground, abilityList.Rock_Head, itemList.None, 44.1, false, status.none));
        pokeList.put("Geodude-A", new SpeciesClass(40, 40, 80, 100, 30, 30, 20, pkmnType.Rock, pkmnType.Electric, abilityList.Magnet_Pull, itemList.None, 44.8, false, status.none));
        pokeList.put("Graveler", new SpeciesClass(55, 55, 95, 115, 45, 45, 35, pkmnType.Rock, pkmnType.Ground, abilityList.Rock_Head, itemList.None, 231.5, false, status.none));
        pokeList.put("Graveler-A", new SpeciesClass(55, 55, 95, 115, 45, 45, 35, pkmnType.Rock, pkmnType.Electric, abilityList.Magnet_Pull, itemList.None, 242.5, false, status.none));
        pokeList.put("Golem", new SpeciesClass(80, 80, 120, 130, 55, 65, 45, pkmnType.Rock, pkmnType.Ground, abilityList.Rock_Head, itemList.None, 661.4, false, status.none));
        pokeList.put("Golem-A", new SpeciesClass(80, 80, 120, 130, 55, 65, 45, pkmnType.Rock, pkmnType.Electric, abilityList.Magnet_Pull, itemList.None, 696.7, false, status.none));
        pokeList.put("Ponyta", new SpeciesClass(50, 50, 85, 55, 65, 65, 90, pkmnType.Fire, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 66.1, false, status.none));
        pokeList.put("Ponyta-G", new SpeciesClass(50, 50, 85, 55, 65, 65, 90, pkmnType.Psychic, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 52.9, false, status.none));
        pokeList.put("Rapidash", new SpeciesClass(65, 65, 100, 70, 80, 80, 105, pkmnType.Fire, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 209.4, false, status.none));
        pokeList.put("Rapidash-G", new SpeciesClass(65, 65, 100, 70, 80, 80, 105, pkmnType.Psychic, pkmnType.Fairy, abilityList.Run_Away, itemList.None, 176.4, false, status.none));
        pokeList.put("Slowpoke", new SpeciesClass(90, 90, 65, 65, 40, 40, 15, pkmnType.Water, pkmnType.Psychic, abilityList.Oblivious, itemList.None, 79.4, false, status.none));
        pokeList.put("Slowpoke-G", new SpeciesClass(90, 90, 65, 65, 40, 40, 15, pkmnType.Psychic, pkmnType.Typeless, abilityList.Gluttony, itemList.None, 79.4, false, status.none));
        pokeList.put("Slowbro", new SpeciesClass(95, 95, 75, 110, 100, 80, 30, pkmnType.Water, pkmnType.Psychic, abilityList.Oblivious, itemList.None, 173.1, false, status.none));
        pokeList.put("Slowbro-M", new SpeciesClass(95, 95, 75, 180, 130, 80, 30, pkmnType.Water, pkmnType.Psychic, abilityList.Shell_Armor, itemList.None, 264.6, false, status.none));
        pokeList.put("Slowbro-G", new SpeciesClass(95, 95, 100, 95, 100, 70, 30, pkmnType.Poison, pkmnType.Psychic, abilityList.Quick_Draw, itemList.None, 155.4, false, status.none));
        pokeList.put("Magnemite", new SpeciesClass(25, 25, 35, 70, 95, 55, 45, pkmnType.Electric, pkmnType.Steel, abilityList.Magnet_Pull, itemList.None, 13.2, false, status.none));
        pokeList.put("Magneton", new SpeciesClass(50, 50, 60, 95, 120, 70, 70, pkmnType.Electric, pkmnType.Steel, abilityList.Magnet_Pull, itemList.None, 132.3, false, status.none));
        pokeList.put("Farfetchd", new SpeciesClass(52, 52, 90, 55, 58, 62, 60, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 33.1, false, status.none));
        pokeList.put("Farfetchd-G", new SpeciesClass(52, 52, 95, 55, 58, 62, 55, pkmnType.Fighting, pkmnType.Typeless, abilityList.Steadfast, itemList.None, 92.6, false, status.none));
        pokeList.put("Doduo", new SpeciesClass(35, 35, 85, 45, 35, 35, 75, pkmnType.Normal, pkmnType.Flying, abilityList.Run_Away, itemList.None, 86.4, false, status.none));
        pokeList.put("Dodrio", new SpeciesClass(60, 60, 110, 70, 60, 60, 110, pkmnType.Normal, pkmnType.Flying, abilityList.Run_Away, itemList.None, 187.8, false, status.none));
        pokeList.put("Seel", new SpeciesClass(65, 65, 45, 55, 45, 70, 45, pkmnType.Water, pkmnType.Typeless, abilityList.Thick_Fat, itemList.None, 198.4, false, status.none));
        pokeList.put("Dewgong", new SpeciesClass(90, 90, 70, 80, 70, 95, 70, pkmnType.Water, pkmnType.Ice, abilityList.Thick_Fat, itemList.None, 264.6, false, status.none));
        pokeList.put("Grimer", new SpeciesClass(80, 80, 80, 50, 40, 50, 25, pkmnType.Poison, pkmnType.Typeless, abilityList.Stench, itemList.None, 66.1, false, status.none));
        pokeList.put("Grimer-A", new SpeciesClass(80, 80, 80, 50, 40, 50, 25, pkmnType.Poison, pkmnType.Dark, abilityList.Poison_Touch, itemList.None, 92.6, false, status.none));
        pokeList.put("Muk", new SpeciesClass(105, 105, 105, 75, 65, 100, 50, pkmnType.Poison, pkmnType.Typeless, abilityList.Stench, itemList.None, 66.1, false, status.none));
        pokeList.put("Muk-A", new SpeciesClass(105, 105, 105, 75, 65, 100, 50, pkmnType.Poison, pkmnType.Dark, abilityList.Poison_Touch, itemList.None, 114.6, false, status.none));
        pokeList.put("Shellder", new SpeciesClass(30, 30, 65, 100, 45, 25, 40, pkmnType.Water, pkmnType.Typeless, abilityList.Shell_Armor, itemList.None, 8.8, false, status.none));
        pokeList.put("Cloyster", new SpeciesClass(50, 50, 95, 180, 85, 45, 70, pkmnType.Water, pkmnType.Ice, abilityList.Shell_Armor, itemList.None, 292.1, false, status.none));
        pokeList.put("Gastly", new SpeciesClass(30, 30, 35, 30, 100, 35, 80, pkmnType.Ghost, pkmnType.Poison, abilityList.Levitate, itemList.None, 0.2, false, status.none));
        pokeList.put("Haunter", new SpeciesClass(45, 45, 50, 45, 115, 55, 95, pkmnType.Ghost, pkmnType.Poison, abilityList.Levitate, itemList.None, 0.2, false, status.none));
        pokeList.put("Gengar", new SpeciesClass(60, 60, 65, 60, 130, 75, 110, pkmnType.Ghost, pkmnType.Poison, abilityList.Cursed_Body, itemList.None, 89.3, false, status.none));
        pokeList.put("Gengar-M", new SpeciesClass(60, 60, 65, 80, 170, 95, 130, pkmnType.Ghost, pkmnType.Poison, abilityList.Shadow_Tag, itemList.None, 89.3, false, status.none));
        pokeList.put("Onix", new SpeciesClass(35, 35, 45, 160, 30, 45, 70, pkmnType.Rock, pkmnType.Ground, abilityList.Rock_Head, itemList.None, 463.0, false, status.none));
        pokeList.put("Drowzee", new SpeciesClass(60, 60, 48, 45, 43, 90, 42, pkmnType.Psychic, pkmnType.Typeless, abilityList.Insomnia, itemList.None, 71.4, false, status.none));
        pokeList.put("Hypno", new SpeciesClass(85, 85, 73, 70, 73, 115, 67, pkmnType.Psychic, pkmnType.Typeless, abilityList.Insomnia, itemList.None, 166.7, false, status.none));
        pokeList.put("Krabby", new SpeciesClass(30, 30, 105, 90, 25, 25, 50, pkmnType.Water, pkmnType.Typeless, abilityList.Hyper_Cutter, itemList.None, 14.3, false, status.none));
        pokeList.put("Kingler", new SpeciesClass(55, 55, 130, 115, 50, 50, 75, pkmnType.Water, pkmnType.Typeless, abilityList.Hyper_Cutter, itemList.None, 132.3, false, status.none));
        pokeList.put("Voltorb", new SpeciesClass(40, 40, 30, 50, 55, 55, 100, pkmnType.Electric, pkmnType.Typeless, abilityList.Soundproof, itemList.None, 22.9, false, status.none));
        pokeList.put("Voltorb-H", new SpeciesClass(40, 40, 30, 50, 55, 55, 100, pkmnType.Electric, pkmnType.Grass, abilityList.Soundproof, itemList.None, 28.7, false, status.none));
        pokeList.put("Electrode", new SpeciesClass(60, 60, 50, 70, 80, 80, 150, pkmnType.Electric, pkmnType.Typeless, abilityList.Soundproof, itemList.None, 146.8, false, status.none));
        pokeList.put("Electrode-H", new SpeciesClass(60, 60, 50, 70, 80, 80, 150, pkmnType.Electric, pkmnType.Grass, abilityList.Soundproof, itemList.None, 156.5, false, status.none));
        pokeList.put("Exeggcute", new SpeciesClass(60, 60, 40, 80, 60, 45, 40, pkmnType.Grass, pkmnType.Psychic, abilityList.Chlorophyll, itemList.None, 5.5, false, status.none));
        pokeList.put("Exeggutor", new SpeciesClass(95, 95, 95, 85, 125, 75, 55, pkmnType.Grass, pkmnType.Psychic, abilityList.Chlorophyll, itemList.None, 264.6, false, status.none));
        pokeList.put("Exeggutor-A", new SpeciesClass(95, 95, 105, 85, 125, 75, 45, pkmnType.Grass, pkmnType.Dragon, abilityList.Frisk, itemList.None, 916.2, false, status.none));
        pokeList.put("Cubone", new SpeciesClass(50, 50, 50, 95, 40, 50, 35, pkmnType.Ground, pkmnType.Typeless, abilityList.Rock_Head, itemList.None, 14.3, false, status.none));
        pokeList.put("Marowak", new SpeciesClass(60, 60, 80, 110, 50, 80, 45, pkmnType.Ground, pkmnType.Typeless, abilityList.Rock_Head, itemList.None, 99.2, false, status.none));
        pokeList.put("Marowak-A", new SpeciesClass(60, 60, 80, 110, 50, 80, 45, pkmnType.Fire, pkmnType.Ghost, abilityList.Cursed_Body, itemList.None, 75.0, false, status.none));
        pokeList.put("Hitmonlee", new SpeciesClass(50, 50, 120, 53, 35, 110, 87, pkmnType.Fighting, pkmnType.Typeless, abilityList.Limber, itemList.None, 109.8, false, status.none));
        pokeList.put("Hitmonchan", new SpeciesClass(50, 50, 105, 79, 35, 110, 76, pkmnType.Fighting, pkmnType.Typeless, abilityList.Keen_Eye, itemList.None, 110.7, false, status.none));
        pokeList.put("Lickitung", new SpeciesClass(90, 90, 55, 75, 60, 75, 30, pkmnType.Normal, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 144.4, false, status.none));
        pokeList.put("Koffing", new SpeciesClass(40, 40, 65, 95, 60, 45, 35, pkmnType.Poison, pkmnType.Typeless, abilityList.Levitate, itemList.None, 2.2, false, status.none));
        pokeList.put("Weezing", new SpeciesClass(65, 65, 90, 120, 85, 70, 60, pkmnType.Poison, pkmnType.Typeless, abilityList.Levitate, itemList.None, 20.9, false, status.none));
        pokeList.put("Weezing-G", new SpeciesClass(65, 65, 90, 120, 85, 70, 60, pkmnType.Poison, pkmnType.Fairy, abilityList.Levitate, itemList.None, 35.3, false, status.none));
        pokeList.put("Rhyhorn", new SpeciesClass(80, 80, 85, 95, 30, 30, 25, pkmnType.Ground, pkmnType.Rock, abilityList.Lightning_Rod, itemList.None, 253.5, false, status.none));
        pokeList.put("Rhydon", new SpeciesClass(105, 105, 130, 120, 45, 45, 40, pkmnType.Ground, pkmnType.Rock, abilityList.Lightning_Rod, itemList.None, 264.6, false, status.none));
        pokeList.put("Chansey", new SpeciesClass(250, 250, 5, 5, 35, 105, 50, pkmnType.Normal, pkmnType.Typeless, abilityList.Natural_Cure, itemList.None, 76.3, false, status.none));
        pokeList.put("Tangela", new SpeciesClass(65, 65, 55, 115, 100, 40, 60, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 77.2, false, status.none));
        pokeList.put("Kangaskhan", new SpeciesClass(105, 105, 95, 80, 40, 80, 90, pkmnType.Normal, pkmnType.Typeless, abilityList.Early_Bird, itemList.None, 176.4, false, status.none));
        pokeList.put("Kangaskhan-M", new SpeciesClass(105, 105, 125, 100, 60, 100, 100, pkmnType.Normal, pkmnType.Typeless, abilityList.Parental_Bond, itemList.None, 220.5, false, status.none));
        pokeList.put("Horsea", new SpeciesClass(30, 30, 40, 70, 70, 25, 60, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 17.6, false, status.none));
        pokeList.put("Seadra", new SpeciesClass(55, 55, 65, 95, 95, 45, 85, pkmnType.Water, pkmnType.Typeless, abilityList.Poison_Point, itemList.None, 55.1, false, status.none));
        pokeList.put("Goldeen", new SpeciesClass(45, 45, 67, 60, 35, 50, 63, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 33.1, false, status.none));
        pokeList.put("Seaking", new SpeciesClass(80, 80, 92, 65, 65, 80, 68, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 86.0, false, status.none));
        pokeList.put("Staryu", new SpeciesClass(30, 30, 45, 55, 70, 55, 85, pkmnType.Water, pkmnType.Typeless, abilityList.Illuminate, itemList.None, 76.1, false, status.none));
        pokeList.put("Starmie", new SpeciesClass(60, 60, 75, 85, 100, 85, 115, pkmnType.Water, pkmnType.Psychic, abilityList.Illuminate, itemList.None, 176.4, false, status.none));
        pokeList.put("Mr. Mime", new SpeciesClass(40, 40, 45, 65, 100, 120, 90, pkmnType.Psychic, pkmnType.Fairy, abilityList.Soundproof, itemList.None, 120.2, false, status.none));
        pokeList.put("Mr. Mime-G", new SpeciesClass(50, 50, 65, 65, 90, 90, 100, pkmnType.Ice, pkmnType.Psychic, abilityList.Vital_Spirit, itemList.None, 125.2, false, status.none));
        pokeList.put("Scyther", new SpeciesClass(70, 70, 110, 80, 55, 80, 105, pkmnType.Bug, pkmnType.Flying, abilityList.Swarm, itemList.None, 123.5, false, status.none));
        pokeList.put("Jynx", new SpeciesClass(65, 65, 50, 35, 115, 95, 95, pkmnType.Ice, pkmnType.Psychic, abilityList.Oblivious, itemList.None, 89.5, false, status.none));
        pokeList.put("Electabuzz", new SpeciesClass(65, 65, 83, 57, 95, 85, 105, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 66.1, false, status.none));
        pokeList.put("Magmar", new SpeciesClass(65, 65, 95, 57, 100, 85, 93, pkmnType.Fire, pkmnType.Typeless, abilityList.Flame_Body, itemList.None, 98.1, false, status.none));
        pokeList.put("Pinsir", new SpeciesClass(65, 65, 125, 100, 55, 70, 85, pkmnType.Bug, pkmnType.Typeless, abilityList.Hyper_Cutter, itemList.None, 121.3, false, status.none));
        pokeList.put("Pinsir-M", new SpeciesClass(65, 65, 155, 120, 65, 90, 105, pkmnType.Bug, pkmnType.Flying, abilityList.Aerilate, itemList.None, 130.1, false, status.none));
        pokeList.put("Tauros", new SpeciesClass(75, 75, 100, 95, 40, 70, 110, pkmnType.Normal, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 194.9, false, status.none));
        pokeList.put("Tauros-C", new SpeciesClass(75, 75, 110, 105, 30, 70, 100, pkmnType.Fighting, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 253.5, false, status.none));
        pokeList.put("Tauros-B", new SpeciesClass(75, 75, 110, 105, 30, 70, 100, pkmnType.Fighting, pkmnType.Fire, abilityList.Intimidate, itemList.None, 187.4, false, status.none));
        pokeList.put("Tauros-A", new SpeciesClass(75, 75, 110, 105, 30, 70, 100, pkmnType.Fighting, pkmnType.Water, abilityList.Intimidate, itemList.None, 242.5, false, status.none));
        pokeList.put("Magikarp", new SpeciesClass(20, 20, 10, 55, 15, 20, 80, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 22.0, false, status.none));
        pokeList.put("Gyarados", new SpeciesClass(95, 95, 125, 79, 60, 100, 81, pkmnType.Water, pkmnType.Flying, abilityList.Intimidate, itemList.None, 518.1, false, status.none));
        pokeList.put("Gyarados-M", new SpeciesClass(95, 95, 155, 109, 70, 130, 81, pkmnType.Water, pkmnType.Dark, abilityList.Mold_Breaker, itemList.None, 672.4, false, status.none));
        pokeList.put("Lapras", new SpeciesClass(130, 130, 85, 80, 85, 95, 60, pkmnType.Water, pkmnType.Ice, abilityList.Water_Absorb, itemList.None, 485.0, false, status.none));
        pokeList.put("Ditto", new SpeciesClass(48, 48, 48, 48, 48, 48, 48, pkmnType.Normal, pkmnType.Typeless, abilityList.Limber, itemList.None, 8.8, false, status.none));
        pokeList.put("Eevee", new SpeciesClass(55, 55, 55, 50, 45, 65, 55, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 14.3, false, status.none));
        pokeList.put("Eevee-P", new SpeciesClass(65, 65, 75, 70, 65, 85, 75, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 14.3, false, status.none));
        pokeList.put("Vaporeon", new SpeciesClass(130, 130, 65, 60, 110, 95, 65, pkmnType.Water, pkmnType.Typeless, abilityList.Water_Absorb, itemList.None, 63.9, false, status.none));
        pokeList.put("Jolteon", new SpeciesClass(65, 65, 65, 60, 110, 95, 130, pkmnType.Electric, pkmnType.Typeless, abilityList.Volt_Absorb, itemList.None, 54.0, false, status.none));
        pokeList.put("Flareon", new SpeciesClass(65, 65, 130, 60, 95, 110, 65, pkmnType.Fire, pkmnType.Typeless, abilityList.Flash_Fire, itemList.None, 55.1, false, status.none));
        pokeList.put("Porygon", new SpeciesClass(65, 65, 60, 70, 85, 75, 40, pkmnType.Normal, pkmnType.Typeless, abilityList.Trace, itemList.None, 80.5, false, status.none));
        pokeList.put("Omanyte", new SpeciesClass(35, 35, 40, 100, 90, 55, 35, pkmnType.Rock, pkmnType.Water, abilityList.Swift_Swim, itemList.None, 16.5, false, status.none));
        pokeList.put("Omastar", new SpeciesClass(70, 70, 60, 125, 115, 70, 55, pkmnType.Rock, pkmnType.Water, abilityList.Swift_Swim, itemList.None, 77.2, false, status.none));
        pokeList.put("Kabuto", new SpeciesClass(30, 30, 80, 90, 55, 45, 55, pkmnType.Rock, pkmnType.Water, abilityList.Swift_Swim, itemList.None, 25.4, false, status.none));
        pokeList.put("Kabutops", new SpeciesClass(60, 60, 115, 105, 65, 70, 80, pkmnType.Rock, pkmnType.Water, abilityList.Swift_Swim, itemList.None, 89.3, false, status.none));
        pokeList.put("Aerodactyl", new SpeciesClass(80, 80, 105, 65, 60, 75, 130, pkmnType.Rock, pkmnType.Flying, abilityList.Rock_Head, itemList.None, 130.1, false, status.none));
        pokeList.put("Aerodactyl-M", new SpeciesClass(80, 80, 135, 85, 70, 95, 150, pkmnType.Rock, pkmnType.Flying, abilityList.Tough_Claws, itemList.None, 174.2, false, status.none));
        pokeList.put("Snorlax", new SpeciesClass(160, 160, 110, 65, 65, 110, 30, pkmnType.Normal, pkmnType.Typeless, abilityList.Immunity, itemList.None, 1014.1, false, status.none));
        pokeList.put("Articuno", new SpeciesClass(90, 90, 85, 100, 95, 125, 85, pkmnType.Ice, pkmnType.Flying, abilityList.Pressure, itemList.None, 122.1, false, status.none));
        pokeList.put("Articuno-G", new SpeciesClass(90, 90, 85, 85, 125, 100, 95, pkmnType.Psychic, pkmnType.Flying, abilityList.Competitive, itemList.None, 112.2, false, status.none));
        pokeList.put("Zapdos", new SpeciesClass(90, 90, 90, 85, 125, 90, 100, pkmnType.Electric, pkmnType.Flying, abilityList.Pressure, itemList.None, 116.0, false, status.none));
        pokeList.put("Zapdos-G", new SpeciesClass(90, 90, 125, 90, 85, 90, 100, pkmnType.Fighting, pkmnType.Flying, abilityList.Defiant, itemList.None, 128.3, false, status.none));
        pokeList.put("Moltres", new SpeciesClass(90, 90, 100, 90, 125, 85, 90, pkmnType.Fire, pkmnType.Flying, abilityList.Pressure, itemList.None, 132.3, false, status.none));
        pokeList.put("Moltres-G", new SpeciesClass(90, 90, 85, 90, 100, 125, 90, pkmnType.Dark, pkmnType.Flying, abilityList.Berserk, itemList.None, 145.5, false, status.none));
        pokeList.put("Dratini", new SpeciesClass(41, 41, 64, 45, 50, 50, 50, pkmnType.Dragon, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 7.3, false, status.none));
        pokeList.put("Dragonair", new SpeciesClass(61, 61, 84, 65, 70, 70, 70, pkmnType.Dragon, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 36.4, false, status.none));
        pokeList.put("Dragonite", new SpeciesClass(91, 91, 134, 95, 100, 100, 80, pkmnType.Dragon, pkmnType.Flying, abilityList.Inner_Focus, itemList.None, 463.0, false, status.none));
        pokeList.put("Mewtwo", new SpeciesClass(106, 106, 110, 90, 154, 90, 130, pkmnType.Psychic, pkmnType.Typeless, abilityList.Pressure, itemList.None, 269.0, false, status.none));
        pokeList.put("Mewtwo-M", new SpeciesClass(106, 106, 190, 100, 154, 100, 130, pkmnType.Psychic, pkmnType.Fighting, abilityList.Steadfast, itemList.None, 280.0, false, status.none));
        pokeList.put("Mewtwo-M", new SpeciesClass(106, 106, 150, 70, 194, 120, 140, pkmnType.Psychic, pkmnType.Typeless, abilityList.Insomnia, itemList.None, 72.8, false, status.none));
        pokeList.put("Mew", new SpeciesClass(100, 100, 100, 100, 100, 100, 100, pkmnType.Psychic, pkmnType.Typeless, abilityList.Synchronize, itemList.None, 8.8, false, status.none));
        pokeList.put("Chikorita", new SpeciesClass(45, 45, 49, 65, 49, 65, 45, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 14.1, false, status.none));
        pokeList.put("Bayleef", new SpeciesClass(60, 60, 62, 80, 63, 80, 60, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 34.8, false, status.none));
        pokeList.put("Meganium", new SpeciesClass(80, 80, 82, 100, 83, 100, 80, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 221.6, false, status.none));
        pokeList.put("Cyndaquil", new SpeciesClass(39, 39, 52, 43, 60, 50, 65, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 17.4, false, status.none));
        pokeList.put("Quilava", new SpeciesClass(58, 58, 64, 58, 80, 65, 80, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 41.9, false, status.none));
        pokeList.put("Typhlosion", new SpeciesClass(78, 78, 84, 78, 109, 85, 100, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 175.3, false, status.none));
        pokeList.put("Typhlosion-H", new SpeciesClass(73, 73, 84, 78, 119, 85, 95, pkmnType.Fire, pkmnType.Ghost, abilityList.Blaze, itemList.None, 153.9, false, status.none));
        pokeList.put("Totodile", new SpeciesClass(50, 50, 65, 64, 44, 48, 43, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 20.9, false, status.none));
        pokeList.put("Croconaw", new SpeciesClass(65, 65, 80, 80, 59, 63, 58, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 55.1, false, status.none));
        pokeList.put("Feraligatr", new SpeciesClass(85, 85, 105, 100, 79, 83, 78, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 195.8, false, status.none));
        pokeList.put("Sentret", new SpeciesClass(35, 35, 46, 34, 35, 45, 20, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 13.2, false, status.none));
        pokeList.put("Furret", new SpeciesClass(85, 85, 76, 64, 45, 55, 90, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 71.7, false, status.none));
        pokeList.put("Hoothoot", new SpeciesClass(60, 60, 30, 30, 36, 56, 50, pkmnType.Normal, pkmnType.Flying, abilityList.Insomnia, itemList.None, 46.7, false, status.none));
        pokeList.put("Noctowl", new SpeciesClass(100, 100, 50, 50, 86, 96, 70, pkmnType.Normal, pkmnType.Flying, abilityList.Insomnia, itemList.None, 89.9, false, status.none));
        pokeList.put("Ledyba", new SpeciesClass(40, 40, 20, 30, 40, 80, 55, pkmnType.Bug, pkmnType.Flying, abilityList.Swarm, itemList.None, 23.8, false, status.none));
        pokeList.put("Ledian", new SpeciesClass(55, 55, 35, 50, 55, 110, 85, pkmnType.Bug, pkmnType.Flying, abilityList.Swarm, itemList.None, 78.5, false, status.none));
        pokeList.put("Spinarak", new SpeciesClass(40, 40, 60, 40, 40, 40, 30, pkmnType.Bug, pkmnType.Poison, abilityList.Swarm, itemList.None, 18.7, false, status.none));
        pokeList.put("Ariados", new SpeciesClass(70, 70, 90, 70, 60, 70, 40, pkmnType.Bug, pkmnType.Poison, abilityList.Swarm, itemList.None, 73.9, false, status.none));
        pokeList.put("Crobat", new SpeciesClass(85, 85, 90, 80, 70, 80, 130, pkmnType.Poison, pkmnType.Flying, abilityList.Volt_Absorb, itemList.None, 165.3, false, status.none));
        pokeList.put("Chinchou", new SpeciesClass(75, 75, 38, 38, 56, 56, 67, pkmnType.Water, pkmnType.Electric, abilityList.Volt_Absorb, itemList.None, 26.5, false, status.none));
        pokeList.put("Lanturn", new SpeciesClass(125, 125, 58, 58, 76, 76, 67, pkmnType.Water, pkmnType.Electric, abilityList.Static, itemList.None, 49.6, false, status.none));
        pokeList.put("Pichu", new SpeciesClass(20, 20, 40, 15, 35, 35, 60, pkmnType.Electric, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 4.4, false, status.none));
        pokeList.put("Cleffa", new SpeciesClass(50, 50, 25, 28, 45, 55, 15, pkmnType.Fairy, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 6.6, false, status.none));
        pokeList.put("Igglybuff", new SpeciesClass(90, 90, 30, 15, 40, 20, 15, pkmnType.Normal, pkmnType.Fairy, abilityList.Hustle, itemList.None, 2.2, false, status.none));
        pokeList.put("Togepi", new SpeciesClass(35, 35, 20, 65, 40, 65, 20, pkmnType.Fairy, pkmnType.Typeless, abilityList.Hustle, itemList.None, 3.3, false, status.none));
        pokeList.put("Togetic", new SpeciesClass(55, 55, 40, 85, 80, 105, 40, pkmnType.Fairy, pkmnType.Flying, abilityList.Hustle, itemList.None, 7.1, false, status.none));
        pokeList.put("Natu", new SpeciesClass(40, 40, 50, 45, 70, 45, 70, pkmnType.Psychic, pkmnType.Flying, abilityList.Synchronize, itemList.None, 4.4, false, status.none));
        pokeList.put("Xatu", new SpeciesClass(65, 65, 75, 70, 95, 70, 95, pkmnType.Psychic, pkmnType.Flying, abilityList.Synchronize, itemList.None, 33.1, false, status.none));
        pokeList.put("Mareep", new SpeciesClass(55, 55, 40, 40, 65, 45, 35, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 17.2, false, status.none));
        pokeList.put("Flaaffy", new SpeciesClass(70, 70, 55, 55, 80, 60, 45, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 29.3, false, status.none));
        pokeList.put("Ampharos", new SpeciesClass(90, 90, 75, 85, 115, 90, 55, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 135.6, false, status.none));
        pokeList.put("Ampharos-M", new SpeciesClass(90, 90, 95, 105, 165, 110, 45, pkmnType.Electric, pkmnType.Dragon, abilityList.Mold_Breaker, itemList.None, 135.6, false, status.none));
        pokeList.put("Bellossom", new SpeciesClass(75, 75, 80, 95, 90, 100, 50, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 12.8, false, status.none));
        pokeList.put("Marill", new SpeciesClass(70, 70, 20, 50, 20, 50, 40, pkmnType.Water, pkmnType.Fairy, abilityList.Thick_Fat, itemList.None, 18.7, false, status.none));
        pokeList.put("Azumarill", new SpeciesClass(100, 100, 50, 80, 60, 80, 50, pkmnType.Water, pkmnType.Fairy, abilityList.Thick_Fat, itemList.None, 62.8, false, status.none));
        pokeList.put("Sudowoodo", new SpeciesClass(70, 70, 100, 115, 30, 65, 30, pkmnType.Rock, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 83.8, false, status.none));
        pokeList.put("Politoed", new SpeciesClass(90, 90, 75, 75, 90, 100, 70, pkmnType.Water, pkmnType.Typeless, abilityList.Water_Absorb, itemList.None, 74.7, false, status.none));
        pokeList.put("Hoppip", new SpeciesClass(35, 35, 35, 40, 35, 55, 50, pkmnType.Grass, pkmnType.Flying, abilityList.Chlorophyll, itemList.None, 1.1, false, status.none));
        pokeList.put("Skiploom", new SpeciesClass(55, 55, 45, 50, 45, 65, 80, pkmnType.Grass, pkmnType.Flying, abilityList.Chlorophyll, itemList.None, 2.2, false, status.none));
        pokeList.put("Jumpluff", new SpeciesClass(75, 75, 55, 70, 55, 95, 110, pkmnType.Grass, pkmnType.Flying, abilityList.Chlorophyll, itemList.None, 6.6, false, status.none));
        pokeList.put("Aipom", new SpeciesClass(55, 55, 70, 55, 40, 55, 85, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 25.4, false, status.none));
        pokeList.put("Sunkern", new SpeciesClass(30, 30, 30, 30, 30, 30, 30, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 4.0, false, status.none));
        pokeList.put("Sunflora", new SpeciesClass(75, 75, 75, 55, 105, 85, 30, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 18.7, false, status.none));
        pokeList.put("Yanma", new SpeciesClass(65, 65, 65, 45, 75, 45, 95, pkmnType.Bug, pkmnType.Flying, abilityList.Speed_Boost, itemList.None, 83.8, false, status.none));
        pokeList.put("Wooper", new SpeciesClass(55, 55, 45, 45, 25, 25, 15, pkmnType.Water, pkmnType.Ground, abilityList.Damp, itemList.None, 18.7, false, status.none));
        pokeList.put("Wooper-P", new SpeciesClass(55, 55, 45, 45, 25, 25, 15, pkmnType.Poison, pkmnType.Ground, abilityList.Poison_Point, itemList.None, 24.3, false, status.none));
        pokeList.put("Quagsire", new SpeciesClass(95, 95, 85, 85, 65, 65, 35, pkmnType.Water, pkmnType.Ground, abilityList.Damp, itemList.None, 165.3, false, status.none));
        pokeList.put("Espeon", new SpeciesClass(65, 65, 65, 60, 130, 95, 110, pkmnType.Psychic, pkmnType.Typeless, abilityList.Synchronize, itemList.None, 58.4, false, status.none));
        pokeList.put("Umbreon", new SpeciesClass(95, 95, 65, 110, 60, 130, 65, pkmnType.Dark, pkmnType.Typeless, abilityList.Synchronize, itemList.None, 59.5, false, status.none));
        pokeList.put("Murkrow", new SpeciesClass(60, 60, 85, 42, 85, 42, 91, pkmnType.Dark, pkmnType.Flying, abilityList.Insomnia, itemList.None, 4.6, false, status.none));
        pokeList.put("Slowking", new SpeciesClass(95, 95, 75, 80, 100, 110, 30, pkmnType.Water, pkmnType.Psychic, abilityList.Oblivious, itemList.None, 175.3, false, status.none));
        pokeList.put("Slowking-G", new SpeciesClass(95, 95, 65, 80, 110, 110, 30, pkmnType.Poison, pkmnType.Psychic, abilityList.Curious_Medicine, itemList.None, 175.3, false, status.none));
        pokeList.put("Misdreavus", new SpeciesClass(60, 60, 60, 60, 85, 85, 85, pkmnType.Ghost, pkmnType.Typeless, abilityList.Levitate, itemList.None, 2.2, false, status.none));
        pokeList.put("Unown", new SpeciesClass(48, 48, 72, 48, 72, 48, 48, pkmnType.Psychic, pkmnType.Typeless, abilityList.Levitate, itemList.None, 11.0, false, status.none));
        pokeList.put("Wobbuffet", new SpeciesClass(190, 190, 33, 58, 33, 58, 33, pkmnType.Psychic, pkmnType.Typeless, abilityList.Shadow_Tag, itemList.None, 62.8, false, status.none));
        pokeList.put("Girafarig", new SpeciesClass(70, 70, 80, 65, 90, 65, 85, pkmnType.Normal, pkmnType.Psychic, abilityList.Inner_Focus, itemList.None, 91.5, false, status.none));
        pokeList.put("Pineco", new SpeciesClass(50, 50, 65, 90, 35, 35, 15, pkmnType.Bug, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 15.9, false, status.none));
        pokeList.put("Forretress", new SpeciesClass(75, 75, 90, 140, 60, 60, 40, pkmnType.Bug, pkmnType.Steel, abilityList.Sturdy, itemList.None, 277.3, false, status.none));
        pokeList.put("Dunsparce", new SpeciesClass(100, 100, 70, 70, 65, 65, 45, pkmnType.Normal, pkmnType.Typeless, abilityList.Serene_Grace, itemList.None, 30.9, false, status.none));
        pokeList.put("Gligar", new SpeciesClass(65, 65, 75, 105, 35, 65, 85, pkmnType.Ground, pkmnType.Flying, abilityList.Hyper_Cutter, itemList.None, 142.9, false, status.none));
        pokeList.put("Steelix", new SpeciesClass(75, 75, 85, 200, 55, 65, 30, pkmnType.Steel, pkmnType.Ground, abilityList.Rock_Head, itemList.None, 881.8, false, status.none));
        pokeList.put("Steelix-M", new SpeciesClass(75, 75, 125, 230, 55, 95, 30, pkmnType.Steel, pkmnType.Ground, abilityList.Sand_Force, itemList.None, 1631.4, false, status.none));
        pokeList.put("Snubbull", new SpeciesClass(60, 60, 80, 50, 40, 40, 30, pkmnType.Fairy, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 17.2, false, status.none));
        pokeList.put("Granbull", new SpeciesClass(90, 90, 120, 75, 60, 60, 45, pkmnType.Fairy, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 107.4, false, status.none));
        pokeList.put("Qwilfish", new SpeciesClass(65, 65, 95, 85, 55, 55, 85, pkmnType.Water, pkmnType.Poison, abilityList.Poison_Point, itemList.None, 8.6, false, status.none));
        pokeList.put("Qwilfish-H", new SpeciesClass(65, 65, 95, 85, 55, 55, 85, pkmnType.Dark, pkmnType.Poison, abilityList.Poison_Point, itemList.None, 8.6, false, status.none));
        pokeList.put("Scizor", new SpeciesClass(70, 70, 130, 100, 55, 80, 65, pkmnType.Bug, pkmnType.Steel, abilityList.Swarm, itemList.None, 260.1, false, status.none));
        pokeList.put("Scizor-M", new SpeciesClass(70, 70, 150, 140, 65, 100, 75, pkmnType.Bug, pkmnType.Steel, abilityList.Technician, itemList.None, 275.6, false, status.none));
        pokeList.put("Shuckle", new SpeciesClass(20, 20, 10, 230, 10, 230, 5, pkmnType.Bug, pkmnType.Rock, abilityList.Sturdy, itemList.None, 45.2, false, status.none));
        pokeList.put("Heracross", new SpeciesClass(80, 80, 125, 75, 40, 95, 85, pkmnType.Bug, pkmnType.Fighting, abilityList.Swarm, itemList.None, 119.0, false, status.none));
        pokeList.put("Heracross-M", new SpeciesClass(80, 80, 185, 115, 40, 105, 75, pkmnType.Bug, pkmnType.Fighting, abilityList.Skill_Link, itemList.None, 137.8, false, status.none));
        pokeList.put("Sneasel", new SpeciesClass(55, 55, 95, 55, 35, 75, 115, pkmnType.Dark, pkmnType.Ice, abilityList.Inner_Focus, itemList.None, 61.7, false, status.none));
        pokeList.put("Sneasel-H", new SpeciesClass(55, 55, 95, 55, 35, 75, 115, pkmnType.Fighting, pkmnType.Poison, abilityList.Inner_Focus, itemList.None, 59.5, false, status.none));
        pokeList.put("Teddiursa", new SpeciesClass(60, 60, 80, 50, 50, 50, 40, pkmnType.Normal, pkmnType.Typeless, abilityList.Pickup, itemList.None, 19.4, false, status.none));
        pokeList.put("Ursaring", new SpeciesClass(90, 90, 130, 75, 75, 75, 55, pkmnType.Normal, pkmnType.Typeless, abilityList.Guts, itemList.None, 277.3, false, status.none));
        pokeList.put("Slugma", new SpeciesClass(40, 40, 40, 40, 70, 40, 20, pkmnType.Fire, pkmnType.Typeless, abilityList.Magma_Armor, itemList.None, 77.2, false, status.none));
        pokeList.put("Magcargo", new SpeciesClass(60, 60, 50, 120, 90, 80, 30, pkmnType.Fire, pkmnType.Rock, abilityList.Magma_Armor, itemList.None, 121.3, false, status.none));
        pokeList.put("Swinub", new SpeciesClass(50, 50, 50, 40, 30, 30, 50, pkmnType.Ice, pkmnType.Ground, abilityList.Oblivious, itemList.None, 14.3, false, status.none));
        pokeList.put("Piloswine", new SpeciesClass(100, 100, 100, 80, 60, 60, 50, pkmnType.Ice, pkmnType.Ground, abilityList.Oblivious, itemList.None, 123.0, false, status.none));
        pokeList.put("Corsola", new SpeciesClass(65, 65, 55, 95, 65, 95, 35, pkmnType.Water, pkmnType.Rock, abilityList.Hustle, itemList.None, 11.0, false, status.none));
        pokeList.put("Corsola-G", new SpeciesClass(60, 60, 55, 100, 65, 100, 30, pkmnType.Ghost, pkmnType.Typeless, abilityList.Weak_Armor, itemList.None, 1.1, false, status.none));
        pokeList.put("Remoraid", new SpeciesClass(35, 35, 65, 35, 65, 35, 65, pkmnType.Water, pkmnType.Typeless, abilityList.Hustle, itemList.None, 26.5, false, status.none));
        pokeList.put("Octillery", new SpeciesClass(75, 75, 105, 75, 105, 75, 45, pkmnType.Water, pkmnType.Typeless, abilityList.Suction_Cups, itemList.None, 62.8, false, status.none));
        pokeList.put("Delibird", new SpeciesClass(45, 45, 55, 45, 65, 45, 75, pkmnType.Ice, pkmnType.Flying, abilityList.Vital_Spirit, itemList.None, 35.3, false, status.none));
        pokeList.put("Mantine", new SpeciesClass(85, 85, 40, 70, 80, 140, 70, pkmnType.Water, pkmnType.Flying, abilityList.Swift_Swim, itemList.None, 485.0, false, status.none));
        pokeList.put("Skarmory", new SpeciesClass(65, 65, 80, 140, 40, 70, 70, pkmnType.Steel, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 111.3, false, status.none));
        pokeList.put("Houndour", new SpeciesClass(45, 45, 60, 30, 80, 50, 65, pkmnType.Dark, pkmnType.Fire, abilityList.Early_Bird, itemList.None, 23.8, false, status.none));
        pokeList.put("Houndoom", new SpeciesClass(75, 75, 90, 50, 110, 80, 95, pkmnType.Dark, pkmnType.Fire, abilityList.Early_Bird, itemList.None, 77.2, false, status.none));
        pokeList.put("Houndoom-M", new SpeciesClass(75, 75, 90, 90, 140, 90, 115, pkmnType.Dark, pkmnType.Fire, abilityList.Solar_Power, itemList.None, 109.1, false, status.none));
        pokeList.put("Kingdra", new SpeciesClass(75, 75, 95, 95, 95, 95, 85, pkmnType.Water, pkmnType.Dragon, abilityList.Swift_Swim, itemList.None, 335.1, false, status.none));
        pokeList.put("Phanpy", new SpeciesClass(90, 90, 60, 60, 40, 40, 40, pkmnType.Ground, pkmnType.Typeless, abilityList.Pickup, itemList.None, 73.9, false, status.none));
        pokeList.put("Donphan", new SpeciesClass(90, 90, 120, 120, 60, 60, 50, pkmnType.Ground, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 264.6, false, status.none));
        pokeList.put("Porygon2", new SpeciesClass(85, 85, 80, 90, 105, 95, 60, pkmnType.Normal, pkmnType.Typeless, abilityList.Trace, itemList.None, 71.7, false, status.none));
        pokeList.put("Stantler", new SpeciesClass(73, 73, 95, 62, 85, 65, 85, pkmnType.Normal, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 157.0, false, status.none));
        pokeList.put("Smeargle", new SpeciesClass(55, 55, 20, 35, 20, 45, 75, pkmnType.Normal, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 127.9, false, status.none));
        pokeList.put("Tyrogue", new SpeciesClass(35, 35, 35, 35, 35, 35, 35, pkmnType.Fighting, pkmnType.Typeless, abilityList.Guts, itemList.None, 46.3, false, status.none));
        pokeList.put("Hitmontop", new SpeciesClass(50, 50, 95, 95, 35, 110, 70, pkmnType.Fighting, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 105.8, false, status.none));
        pokeList.put("Smoochum", new SpeciesClass(45, 45, 30, 15, 85, 65, 65, pkmnType.Ice, pkmnType.Psychic, abilityList.Oblivious, itemList.None, 13.2, false, status.none));
        pokeList.put("Elekid", new SpeciesClass(45, 45, 63, 37, 65, 55, 95, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 51.8, false, status.none));
        pokeList.put("Magby", new SpeciesClass(45, 45, 75, 37, 70, 55, 83, pkmnType.Fire, pkmnType.Typeless, abilityList.Flame_Body, itemList.None, 47.2, false, status.none));
        pokeList.put("Miltank", new SpeciesClass(95, 95, 80, 105, 40, 70, 100, pkmnType.Normal, pkmnType.Typeless, abilityList.Thick_Fat, itemList.None, 166.4, false, status.none));
        pokeList.put("Blissey", new SpeciesClass(255, 255, 10, 10, 75, 135, 55, pkmnType.Normal, pkmnType.Typeless, abilityList.Natural_Cure, itemList.None, 103.2, false, status.none));
        pokeList.put("Raikou", new SpeciesClass(90, 90, 85, 75, 115, 100, 115, pkmnType.Electric, pkmnType.Typeless, abilityList.Pressure, itemList.None, 392.4, false, status.none));
        pokeList.put("Entei", new SpeciesClass(115, 115, 115, 85, 90, 75, 100, pkmnType.Fire, pkmnType.Typeless, abilityList.Pressure, itemList.None, 436.5, false, status.none));
        pokeList.put("Suicune", new SpeciesClass(100, 100, 75, 115, 90, 115, 85, pkmnType.Water, pkmnType.Typeless, abilityList.Pressure, itemList.None, 412.3, false, status.none));
        pokeList.put("Larvitar", new SpeciesClass(50, 50, 64, 50, 45, 50, 41, pkmnType.Rock, pkmnType.Ground, abilityList.Guts, itemList.None, 158.7, false, status.none));
        pokeList.put("Pupitar", new SpeciesClass(70, 70, 84, 70, 65, 70, 51, pkmnType.Rock, pkmnType.Ground, abilityList.Shed_Skin, itemList.None, 335.1, false, status.none));
        pokeList.put("Tyranitar", new SpeciesClass(100, 100, 134, 110, 95, 100, 61, pkmnType.Rock, pkmnType.Dark, abilityList.Sand_Stream, itemList.None, 445.3, false, status.none));
        pokeList.put("Tyranitar-M", new SpeciesClass(100, 100, 164, 150, 95, 120, 71, pkmnType.Rock, pkmnType.Dark, abilityList.Sand_Stream, itemList.None, 562.2, false, status.none));
        pokeList.put("Lugia", new SpeciesClass(106, 106, 90, 130, 90, 154, 110, pkmnType.Psychic, pkmnType.Flying, abilityList.Pressure, itemList.None, 476.2, false, status.none));
        pokeList.put("Ho-oh", new SpeciesClass(106, 106, 130, 90, 110, 154, 90, pkmnType.Fire, pkmnType.Flying, abilityList.Pressure, itemList.None, 438.7, false, status.none));
        pokeList.put("Celebi", new SpeciesClass(100, 100, 100, 100, 100, 100, 100, pkmnType.Psychic, pkmnType.Grass, abilityList.Natural_Cure, itemList.None, 11.0, false, status.none));
        pokeList.put("Treecko", new SpeciesClass(40, 40, 45, 35, 65, 55, 70, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 11.0, false, status.none));
        pokeList.put("Grovyle", new SpeciesClass(50, 50, 65, 45, 85, 65, 95, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 47.6, false, status.none));
        pokeList.put("Sceptile", new SpeciesClass(70, 70, 85, 65, 105, 85, 120, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 115.1, false, status.none));
        pokeList.put("Sceptile-M", new SpeciesClass(70, 70, 110, 75, 145, 85, 145, pkmnType.Grass, pkmnType.Dragon, abilityList.Lightning_Rod, itemList.None, 121.7, false, status.none));
        pokeList.put("Torchic", new SpeciesClass(45, 45, 60, 40, 70, 50, 45, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 5.5, false, status.none));
        pokeList.put("Combusken", new SpeciesClass(60, 60, 85, 60, 85, 60, 55, pkmnType.Fire, pkmnType.Fighting, abilityList.Blaze, itemList.None, 43.0, false, status.none));
        pokeList.put("Blaziken", new SpeciesClass(80, 80, 120, 70, 110, 70, 80, pkmnType.Fire, pkmnType.Fighting, abilityList.Blaze, itemList.None, 114.6, false, status.none));
        pokeList.put("Blaziken-M", new SpeciesClass(80, 80, 160, 80, 130, 80, 100, pkmnType.Fire, pkmnType.Fighting, abilityList.Speed_Boost, itemList.None, 114.6, false, status.none));
        pokeList.put("Mudkip", new SpeciesClass(50, 50, 70, 50, 50, 50, 40, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 16.8, false, status.none));
        pokeList.put("Marshtomp", new SpeciesClass(70, 70, 85, 70, 60, 70, 50, pkmnType.Water, pkmnType.Ground, abilityList.Torrent, itemList.None, 61.7, false, status.none));
        pokeList.put("Swampert", new SpeciesClass(100, 100, 110, 90, 85, 90, 60, pkmnType.Water, pkmnType.Ground, abilityList.Torrent, itemList.None, 180.6, false, status.none));
        pokeList.put("Swampert-M", new SpeciesClass(100, 100, 150, 110, 95, 110, 70, pkmnType.Water, pkmnType.Ground, abilityList.Swift_Swim, itemList.None, 224.9, false, status.none));
        pokeList.put("Poochyena", new SpeciesClass(35, 35, 55, 35, 30, 30, 35, pkmnType.Dark, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 30.0, false, status.none));
        pokeList.put("Mightyena", new SpeciesClass(70, 70, 90, 70, 60, 60, 70, pkmnType.Dark, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 81.6, false, status.none));
        pokeList.put("Zigzagoon", new SpeciesClass(38, 38, 30, 41, 30, 41, 60, pkmnType.Normal, pkmnType.Typeless, abilityList.Pickup, itemList.None, 38.6, false, status.none));
        pokeList.put("Zigzagoon-G", new SpeciesClass(38, 38, 30, 41, 30, 41, 60, pkmnType.Dark, pkmnType.Normal, abilityList.Pickup, itemList.None, 38.6, false, status.none));
        pokeList.put("Linoone", new SpeciesClass(78, 78, 70, 61, 50, 61, 100, pkmnType.Normal, pkmnType.Typeless, abilityList.Pickup, itemList.None, 71.7, false, status.none));
        pokeList.put("Linoone-G", new SpeciesClass(78, 78, 70, 61, 50, 61, 100, pkmnType.Dark, pkmnType.Normal, abilityList.Pickup, itemList.None, 71.7, false, status.none));
        pokeList.put("Wurmple", new SpeciesClass(45, 45, 45, 35, 20, 30, 20, pkmnType.Bug, pkmnType.Typeless, abilityList.Shield_Dust, itemList.None, 7.9, false, status.none));
        pokeList.put("Silcoon", new SpeciesClass(50, 50, 35, 55, 25, 25, 15, pkmnType.Bug, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 22.0, false, status.none));
        pokeList.put("Beautifly", new SpeciesClass(60, 60, 70, 50, 100, 50, 65, pkmnType.Bug, pkmnType.Flying, abilityList.Swarm, itemList.None, 62.6, false, status.none));
        pokeList.put("Cascoon", new SpeciesClass(50, 50, 35, 55, 25, 25, 15, pkmnType.Bug, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 25.4, false, status.none));
        pokeList.put("Dustox", new SpeciesClass(60, 60, 50, 70, 50, 90, 65, pkmnType.Bug, pkmnType.Poison, abilityList.Shield_Dust, itemList.None, 69.7, false, status.none));
        pokeList.put("Lotad", new SpeciesClass(40, 40, 30, 30, 40, 50, 30, pkmnType.Water, pkmnType.Grass, abilityList.Swift_Swim, itemList.None, 5.7, false, status.none));
        pokeList.put("Lombre", new SpeciesClass(60, 60, 50, 50, 60, 70, 50, pkmnType.Water, pkmnType.Grass, abilityList.Swift_Swim, itemList.None, 71.7, false, status.none));
        pokeList.put("Ludicolo", new SpeciesClass(80, 80, 70, 70, 90, 100, 70, pkmnType.Water, pkmnType.Grass, abilityList.Swift_Swim, itemList.None, 121.3, false, status.none));
        pokeList.put("Seedot", new SpeciesClass(40, 40, 40, 50, 30, 30, 30, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 8.8, false, status.none));
        pokeList.put("Nuzleaf", new SpeciesClass(70, 70, 70, 40, 60, 40, 60, pkmnType.Grass, pkmnType.Dark, abilityList.Chlorophyll, itemList.None, 61.7, false, status.none));
        pokeList.put("Shiftry", new SpeciesClass(90, 90, 100, 60, 90, 60, 80, pkmnType.Grass, pkmnType.Dark, abilityList.Chlorophyll, itemList.None, 131.4, false, status.none));
        pokeList.put("Taillow", new SpeciesClass(40, 40, 55, 30, 30, 30, 85, pkmnType.Normal, pkmnType.Flying, abilityList.Guts, itemList.None, 5.1, false, status.none));
        pokeList.put("Swellow", new SpeciesClass(60, 60, 85, 60, 75, 50, 125, pkmnType.Normal, pkmnType.Flying, abilityList.Guts, itemList.None, 43.7, false, status.none));
        pokeList.put("Wingull", new SpeciesClass(40, 40, 30, 30, 55, 30, 85, pkmnType.Water, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 20.9, false, status.none));
        pokeList.put("Pelipper", new SpeciesClass(60, 60, 50, 100, 95, 70, 65, pkmnType.Water, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 61.7, false, status.none));
        pokeList.put("Ralts", new SpeciesClass(28, 28, 25, 25, 45, 35, 40, pkmnType.Psychic, pkmnType.Fairy, abilityList.Synchronize, itemList.None, 14.6, false, status.none));
        pokeList.put("Kirlia", new SpeciesClass(38, 38, 35, 35, 65, 55, 50, pkmnType.Psychic, pkmnType.Fairy, abilityList.Synchronize, itemList.None, 44.5, false, status.none));
        pokeList.put("Gardevoir", new SpeciesClass(68, 68, 65, 65, 125, 115, 80, pkmnType.Psychic, pkmnType.Fairy, abilityList.Synchronize, itemList.None, 106.7, false, status.none));
        pokeList.put("Gardevoir-M", new SpeciesClass(68, 68, 85, 65, 165, 135, 100, pkmnType.Psychic, pkmnType.Fairy, abilityList.Pixilate, itemList.None, 106.7, false, status.none));
        pokeList.put("Surskit", new SpeciesClass(40, 40, 30, 32, 50, 52, 65, pkmnType.Bug, pkmnType.Water, abilityList.Swift_Swim, itemList.None, 3.7, false, status.none));
        pokeList.put("Masquerain", new SpeciesClass(70, 70, 60, 62, 100, 82, 80, pkmnType.Bug, pkmnType.Flying, abilityList.Intimidate, itemList.None, 7.9, false, status.none));
        pokeList.put("Shroomish", new SpeciesClass(60, 60, 40, 60, 40, 60, 35, pkmnType.Grass, pkmnType.Typeless, abilityList.Effect_Spore, itemList.None, 9.9, false, status.none));
        pokeList.put("Breloom", new SpeciesClass(60, 60, 130, 80, 60, 60, 70, pkmnType.Grass, pkmnType.Fighting, abilityList.Effect_Spore, itemList.None, 86.4, false, status.none));
        pokeList.put("Slakoth", new SpeciesClass(60, 60, 60, 60, 35, 35, 30, pkmnType.Normal, pkmnType.Typeless, abilityList.Truant, itemList.None, 52.9, false, status.none));
        pokeList.put("Vigoroth", new SpeciesClass(80, 80, 80, 80, 55, 55, 90, pkmnType.Normal, pkmnType.Typeless, abilityList.Vital_Spirit, itemList.None, 102.5, false, status.none));
        pokeList.put("Slaking", new SpeciesClass(150, 150, 160, 100, 95, 65, 100, pkmnType.Normal, pkmnType.Typeless, abilityList.Truant, itemList.None, 287.7, false, status.none));
        pokeList.put("Nincada", new SpeciesClass(31, 31, 45, 90, 30, 30, 40, pkmnType.Bug, pkmnType.Ground, abilityList.Compound_Eyes, itemList.None, 12.1, false, status.none));
        pokeList.put("Ninjask", new SpeciesClass(61, 61, 90, 45, 50, 50, 160, pkmnType.Bug, pkmnType.Flying, abilityList.Speed_Boost, itemList.None, 26.5, false, status.none));
        pokeList.put("Shedinja", new SpeciesClass(1, 1, 90, 45, 30, 30, 40, pkmnType.Bug, pkmnType.Ghost, abilityList.Wonder_Guard, itemList.None, 2.6, false, status.none));
        pokeList.put("Whismur", new SpeciesClass(64, 64, 51, 23, 51, 23, 28, pkmnType.Normal, pkmnType.Typeless, abilityList.Soundproof, itemList.None, 35.9, false, status.none));
        pokeList.put("Loudred", new SpeciesClass(84, 84, 71, 43, 71, 43, 48, pkmnType.Normal, pkmnType.Typeless, abilityList.Soundproof, itemList.None, 89.3, false, status.none));
        pokeList.put("Exploud", new SpeciesClass(104, 104, 91, 63, 91, 73, 68, pkmnType.Normal, pkmnType.Typeless, abilityList.Soundproof, itemList.None, 185.2, false, status.none));
        pokeList.put("Makuhita", new SpeciesClass(72, 72, 60, 30, 20, 30, 25, pkmnType.Fighting, pkmnType.Typeless, abilityList.Thick_Fat, itemList.None, 190.5, false, status.none));
        pokeList.put("Hariyama", new SpeciesClass(144, 144, 120, 60, 40, 60, 50, pkmnType.Fighting, pkmnType.Typeless, abilityList.Thick_Fat, itemList.None, 559.5, false, status.none));
        pokeList.put("Azurill", new SpeciesClass(50, 50, 20, 40, 20, 40, 20, pkmnType.Normal, pkmnType.Fairy, abilityList.Thick_Fat, itemList.None, 4.4, false, status.none));
        pokeList.put("Nosepass", new SpeciesClass(30, 30, 45, 135, 45, 90, 30, pkmnType.Rock, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 213.8, false, status.none));
        pokeList.put("Skitty", new SpeciesClass(50, 50, 45, 45, 35, 35, 50, pkmnType.Normal, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 24.3, false, status.none));
        pokeList.put("Delcatty", new SpeciesClass(70, 70, 65, 65, 55, 55, 90, pkmnType.Normal, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 71.9, false, status.none));
        pokeList.put("Sableye", new SpeciesClass(50, 50, 75, 75, 65, 65, 50, pkmnType.Dark, pkmnType.Ghost, abilityList.Keen_Eye, itemList.None, 24.3, false, status.none));
        pokeList.put("Sableye-M", new SpeciesClass(50, 50, 85, 125, 85, 115, 20, pkmnType.Dark, pkmnType.Ghost, abilityList.Magic_Bounce, itemList.None, 354.9, false, status.none));
        pokeList.put("Mawile", new SpeciesClass(50, 50, 85, 85, 55, 55, 50, pkmnType.Steel, pkmnType.Fairy, abilityList.Hyper_Cutter, itemList.None, 25.4, false, status.none));
        pokeList.put("Mawile-M", new SpeciesClass(50, 50, 105, 125, 55, 95, 50, pkmnType.Steel, pkmnType.Fairy, abilityList.Huge_Power, itemList.None, 51.8, false, status.none));
        pokeList.put("Aron", new SpeciesClass(50, 50, 70, 100, 40, 40, 30, pkmnType.Steel, pkmnType.Rock, abilityList.Sturdy, itemList.None, 132.3, false, status.none));
        pokeList.put("Lairon", new SpeciesClass(60, 60, 90, 140, 50, 50, 40, pkmnType.Steel, pkmnType.Rock, abilityList.Sturdy, itemList.None, 264.6, false, status.none));
        pokeList.put("Aggron", new SpeciesClass(70, 70, 110, 180, 60, 60, 50, pkmnType.Steel, pkmnType.Rock, abilityList.Sturdy, itemList.None, 793.7, false, status.none));
        pokeList.put("Aggron-M", new SpeciesClass(70, 70, 140, 230, 60, 80, 50, pkmnType.Steel, pkmnType.Typeless, abilityList.Filter, itemList.None, 870.8, false, status.none));
        pokeList.put("Meditite", new SpeciesClass(30, 30, 40, 55, 40, 55, 60, pkmnType.Fighting, pkmnType.Psychic, abilityList.Pure_Power, itemList.None, 24.7, false, status.none));
        pokeList.put("Medicham", new SpeciesClass(60, 60, 60, 75, 60, 75, 80, pkmnType.Fighting, pkmnType.Psychic, abilityList.Pure_Power, itemList.None, 69.4, false, status.none));
        pokeList.put("Medicham-M", new SpeciesClass(60, 60, 100, 85, 80, 85, 100, pkmnType.Fighting, pkmnType.Psychic, abilityList.Pure_Power, itemList.None, 69.4, false, status.none));
        pokeList.put("Electrike", new SpeciesClass(40, 40, 45, 40, 65, 40, 65, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 33.5, false, status.none));
        pokeList.put("Manectric", new SpeciesClass(70, 70, 75, 60, 105, 60, 105, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 88.6, false, status.none));
        pokeList.put("Manectric-M", new SpeciesClass(70, 70, 75, 80, 135, 80, 135, pkmnType.Electric, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 97.0, false, status.none));
        pokeList.put("Plusle", new SpeciesClass(60, 60, 50, 40, 85, 75, 95, pkmnType.Electric, pkmnType.Typeless, abilityList.Plus, itemList.None, 9.3, false, status.none));
        pokeList.put("Minun", new SpeciesClass(60, 60, 40, 50, 75, 85, 95, pkmnType.Electric, pkmnType.Typeless, abilityList.Minus, itemList.None, 9.3, false, status.none));
        pokeList.put("Volbeat", new SpeciesClass(65, 65, 73, 75, 47, 85, 85, pkmnType.Bug, pkmnType.Typeless, abilityList.Illuminate, itemList.None, 39.0, false, status.none));
        pokeList.put("Illumise", new SpeciesClass(65, 65, 47, 75, 73, 85, 85, pkmnType.Bug, pkmnType.Typeless, abilityList.Oblivious, itemList.None, 39.0, false, status.none));
        pokeList.put("Roselia", new SpeciesClass(50, 50, 60, 45, 100, 80, 65, pkmnType.Grass, pkmnType.Poison, abilityList.Natural_Cure, itemList.None, 4.4, false, status.none));
        pokeList.put("Gulpin", new SpeciesClass(70, 70, 43, 53, 43, 53, 40, pkmnType.Poison, pkmnType.Typeless, abilityList.Liquid_Ooze, itemList.None, 22.7, false, status.none));
        pokeList.put("Swalot", new SpeciesClass(100, 100, 73, 83, 73, 83, 55, pkmnType.Poison, pkmnType.Typeless, abilityList.Liquid_Ooze, itemList.None, 176.4, false, status.none));
        pokeList.put("Carvanha", new SpeciesClass(45, 45, 90, 20, 65, 20, 65, pkmnType.Water, pkmnType.Dark, abilityList.Rough_Skin, itemList.None, 45.9, false, status.none));
        pokeList.put("Sharpedo", new SpeciesClass(70, 70, 120, 40, 95, 40, 95, pkmnType.Water, pkmnType.Dark, abilityList.Rough_Skin, itemList.None, 195.8, false, status.none));
        pokeList.put("Sharpedo-M", new SpeciesClass(70, 70, 140, 70, 110, 65, 105, pkmnType.Water, pkmnType.Dark, abilityList.Strong_Jaw, itemList.None, 287.3, false, status.none));
        pokeList.put("Wailmer", new SpeciesClass(130, 130, 70, 35, 70, 35, 60, pkmnType.Water, pkmnType.Typeless, abilityList.Water_Veil, itemList.None, 286.6, false, status.none));
        pokeList.put("Wailord", new SpeciesClass(170, 170, 90, 45, 90, 45, 60, pkmnType.Water, pkmnType.Typeless, abilityList.Water_Veil, itemList.None, 877.4, false, status.none));
        pokeList.put("Numel", new SpeciesClass(60, 60, 60, 40, 65, 45, 35, pkmnType.Fire, pkmnType.Ground, abilityList.Oblivious, itemList.None, 52.9, false, status.none));
        pokeList.put("Camerupt", new SpeciesClass(70, 70, 100, 70, 105, 75, 40, pkmnType.Fire, pkmnType.Ground, abilityList.Magma_Armor, itemList.None, 485.0, false, status.none));
        pokeList.put("Camerupt-M", new SpeciesClass(70, 70, 120, 100, 145, 105, 20, pkmnType.Fire, pkmnType.Ground, abilityList.Sheer_Force, itemList.None, 706.6, false, status.none));
        pokeList.put("Torkoal", new SpeciesClass(70, 70, 85, 140, 85, 70, 20, pkmnType.Fire, pkmnType.Typeless, abilityList.White_Smoke, itemList.None, 177.3, false, status.none));
        pokeList.put("Spoink", new SpeciesClass(60, 60, 25, 35, 70, 80, 60, pkmnType.Psychic, pkmnType.Typeless, abilityList.Thick_Fat, itemList.None, 67.5, false, status.none));
        pokeList.put("Grumpig", new SpeciesClass(80, 80, 45, 65, 90, 110, 80, pkmnType.Psychic, pkmnType.Typeless, abilityList.Thick_Fat, itemList.None, 157.6, false, status.none));
        pokeList.put("Spinda", new SpeciesClass(60, 60, 60, 60, 60, 60, 60, pkmnType.Normal, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 11.0, false, status.none));
        pokeList.put("Trapinch", new SpeciesClass(45, 45, 100, 45, 45, 45, 10, pkmnType.Ground, pkmnType.Typeless, abilityList.Hyper_Cutter, itemList.None, 33.1, false, status.none));
        pokeList.put("Vibrava", new SpeciesClass(50, 50, 70, 50, 50, 50, 70, pkmnType.Ground, pkmnType.Dragon, abilityList.Levitate, itemList.None, 33.7, false, status.none));
        pokeList.put("Flygon", new SpeciesClass(80, 80, 100, 80, 80, 80, 100, pkmnType.Ground, pkmnType.Dragon, abilityList.Levitate, itemList.None, 180.8, false, status.none));
        pokeList.put("Cacnea", new SpeciesClass(50, 50, 85, 40, 85, 40, 35, pkmnType.Grass, pkmnType.Typeless, abilityList.Sand_Veil, itemList.None, 113.1, false, status.none));
        pokeList.put("Cacturne", new SpeciesClass(70, 70, 115, 60, 115, 60, 55, pkmnType.Grass, pkmnType.Dark, abilityList.Sand_Veil, itemList.None, 170.6, false, status.none));
        pokeList.put("Swablu", new SpeciesClass(45, 45, 40, 60, 40, 75, 50, pkmnType.Normal, pkmnType.Flying, abilityList.Natural_Cure, itemList.None, 2.6, false, status.none));
        pokeList.put("Altaria", new SpeciesClass(75, 75, 70, 90, 70, 105, 80, pkmnType.Dragon, pkmnType.Flying, abilityList.Natural_Cure, itemList.None, 45.4, false, status.none));
        pokeList.put("Altaria-M", new SpeciesClass(75, 75, 110, 110, 110, 105, 80, pkmnType.Dragon, pkmnType.Fairy, abilityList.Pixilate, itemList.None, 45.4, false, status.none));
        pokeList.put("Zangoose", new SpeciesClass(73, 73, 115, 60, 60, 60, 90, pkmnType.Normal, pkmnType.Typeless, abilityList.Immunity, itemList.None, 88.8, false, status.none));
        pokeList.put("Seviper", new SpeciesClass(73, 73, 100, 60, 100, 60, 65, pkmnType.Poison, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 115.7, false, status.none));
        pokeList.put("Lunatone", new SpeciesClass(90, 90, 55, 65, 95, 85, 70, pkmnType.Rock, pkmnType.Psychic, abilityList.Levitate, itemList.None, 370.4, false, status.none));
        pokeList.put("Solrock", new SpeciesClass(90, 90, 95, 85, 55, 65, 70, pkmnType.Rock, pkmnType.Psychic, abilityList.Levitate, itemList.None, 339.5, false, status.none));
        pokeList.put("Barboach", new SpeciesClass(50, 50, 48, 43, 46, 41, 60, pkmnType.Water, pkmnType.Ground, abilityList.Oblivious, itemList.None, 4.2, false, status.none));
        pokeList.put("Whiscash", new SpeciesClass(110, 110, 78, 73, 76, 71, 60, pkmnType.Water, pkmnType.Ground, abilityList.Oblivious, itemList.None, 52.0, false, status.none));
        pokeList.put("Corphish", new SpeciesClass(43, 43, 80, 65, 50, 35, 35, pkmnType.Water, pkmnType.Typeless, abilityList.Hyper_Cutter, itemList.None, 25.4, false, status.none));
        pokeList.put("Crawdaunt", new SpeciesClass(63, 63, 120, 85, 90, 55, 55, pkmnType.Water, pkmnType.Dark, abilityList.Hyper_Cutter, itemList.None, 72.3, false, status.none));
        pokeList.put("Baltoy", new SpeciesClass(40, 40, 40, 55, 40, 70, 55, pkmnType.Ground, pkmnType.Psychic, abilityList.Levitate, itemList.None, 47.4, false, status.none));
        pokeList.put("Claydol", new SpeciesClass(60, 60, 70, 105, 70, 120, 75, pkmnType.Ground, pkmnType.Psychic, abilityList.Levitate, itemList.None, 238.1, false, status.none));
        pokeList.put("Lileep", new SpeciesClass(66, 66, 41, 77, 61, 87, 23, pkmnType.Rock, pkmnType.Grass, abilityList.Suction_Cups, itemList.None, 52.5, false, status.none));
        pokeList.put("Cradily", new SpeciesClass(86, 86, 81, 97, 81, 107, 43, pkmnType.Rock, pkmnType.Grass, abilityList.Suction_Cups, itemList.None, 133.2, false, status.none));
        pokeList.put("Anorith", new SpeciesClass(45, 45, 95, 50, 40, 50, 75, pkmnType.Rock, pkmnType.Bug, abilityList.Battle_Armor, itemList.None, 27.6, false, status.none));
        pokeList.put("Armaldo", new SpeciesClass(75, 75, 125, 100, 70, 80, 45, pkmnType.Rock, pkmnType.Bug, abilityList.Battle_Armor, itemList.None, 150.4, false, status.none));
        pokeList.put("Feebas", new SpeciesClass(20, 20, 15, 20, 10, 55, 80, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 16.3, false, status.none));
        pokeList.put("Milotic", new SpeciesClass(95, 95, 60, 79, 100, 125, 81, pkmnType.Water, pkmnType.Typeless, abilityList.Marvel_Scale, itemList.None, 357.1, false, status.none));
        pokeList.put("Castform", new SpeciesClass(70, 70, 70, 70, 70, 70, 70, pkmnType.Normal, pkmnType.Typeless, abilityList.Forecast, itemList.None, 1.8, false, status.none));
        pokeList.put("Castform-S", new SpeciesClass(70, 70, 70, 70, 70, 70, 70, pkmnType.Fire, pkmnType.Typeless, abilityList.Forecast, itemList.None, 1.8, false, status.none));
        pokeList.put("Castform-R", new SpeciesClass(70, 70, 70, 70, 70, 70, 70, pkmnType.Water, pkmnType.Typeless, abilityList.Forecast, itemList.None, 1.8, false, status.none));
        pokeList.put("Castform-S", new SpeciesClass(70, 70, 70, 70, 70, 70, 70, pkmnType.Ice, pkmnType.Typeless, abilityList.Forecast, itemList.None, 1.8, false, status.none));
        pokeList.put("Kecleon", new SpeciesClass(60, 60, 90, 70, 60, 120, 40, pkmnType.Normal, pkmnType.Typeless, abilityList.Color_Change, itemList.None, 48.5, false, status.none));
        pokeList.put("Shuppet", new SpeciesClass(44, 44, 75, 35, 63, 33, 45, pkmnType.Ghost, pkmnType.Typeless, abilityList.Insomnia, itemList.None, 5.1, false, status.none));
        pokeList.put("Banette", new SpeciesClass(64, 64, 115, 65, 83, 63, 65, pkmnType.Ghost, pkmnType.Typeless, abilityList.Insomnia, itemList.None, 27.6, false, status.none));
        pokeList.put("Banette-M", new SpeciesClass(64, 64, 165, 75, 93, 83, 75, pkmnType.Ghost, pkmnType.Typeless, abilityList.Prankster, itemList.None, 28.7, false, status.none));
        pokeList.put("Duskull", new SpeciesClass(20, 20, 40, 90, 30, 90, 25, pkmnType.Ghost, pkmnType.Typeless, abilityList.Levitate, itemList.None, 33.1, false, status.none));
        pokeList.put("Dusclops", new SpeciesClass(40, 40, 70, 130, 60, 130, 25, pkmnType.Ghost, pkmnType.Typeless, abilityList.Pressure, itemList.None, 67.5, false, status.none));
        pokeList.put("Tropius", new SpeciesClass(99, 99, 68, 83, 72, 87, 51, pkmnType.Grass, pkmnType.Flying, abilityList.Chlorophyll, itemList.None, 220.5, false, status.none));
        pokeList.put("Chimecho", new SpeciesClass(75, 75, 50, 80, 95, 90, 65, pkmnType.Psychic, pkmnType.Typeless, abilityList.Levitate, itemList.None, 2.2, false, status.none));
        pokeList.put("Absol", new SpeciesClass(65, 65, 130, 60, 75, 60, 75, pkmnType.Dark, pkmnType.Typeless, abilityList.Pressure, itemList.None, 103.6, false, status.none));
        pokeList.put("Absol-M", new SpeciesClass(65, 65, 150, 60, 115, 60, 115, pkmnType.Dark, pkmnType.Typeless, abilityList.Magic_Bounce, itemList.None, 108.0, false, status.none));
        pokeList.put("Wynaut", new SpeciesClass(95, 95, 23, 48, 23, 48, 23, pkmnType.Psychic, pkmnType.Typeless, abilityList.Shadow_Tag, itemList.None, 30.9, false, status.none));
        pokeList.put("Snorunt", new SpeciesClass(50, 50, 50, 50, 50, 50, 50, pkmnType.Ice, pkmnType.Typeless, abilityList.Inner_Focus, itemList.None, 37.0, false, status.none));
        pokeList.put("Glalie", new SpeciesClass(80, 80, 80, 80, 80, 80, 80, pkmnType.Ice, pkmnType.Typeless, abilityList.Inner_Focus, itemList.None, 565.5, false, status.none));
        pokeList.put("Glalie-M", new SpeciesClass(80, 80, 120, 80, 120, 80, 100, pkmnType.Ice, pkmnType.Typeless, abilityList.Refrigerate, itemList.None, 772.1, false, status.none));
        pokeList.put("Spheal", new SpeciesClass(70, 70, 40, 50, 55, 50, 25, pkmnType.Ice, pkmnType.Water, abilityList.Thick_Fat, itemList.None, 87.1, false, status.none));
        pokeList.put("Sealeo", new SpeciesClass(90, 90, 60, 70, 75, 70, 45, pkmnType.Ice, pkmnType.Water, abilityList.Thick_Fat, itemList.None, 193.1, false, status.none));
        pokeList.put("Walrein", new SpeciesClass(110, 110, 80, 90, 95, 90, 65, pkmnType.Ice, pkmnType.Water, abilityList.Thick_Fat, itemList.None, 332.0, false, status.none));
        pokeList.put("Clamperl", new SpeciesClass(35, 35, 64, 85, 74, 55, 32, pkmnType.Water, pkmnType.Typeless, abilityList.Shell_Armor, itemList.None, 115.7, false, status.none));
        pokeList.put("Huntail", new SpeciesClass(55, 55, 104, 105, 94, 75, 52, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 59.5, false, status.none));
        pokeList.put("Gorebyss", new SpeciesClass(55, 55, 84, 105, 114, 75, 52, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 49.8, false, status.none));
        pokeList.put("Relicanth", new SpeciesClass(100, 100, 90, 130, 45, 65, 55, pkmnType.Water, pkmnType.Rock, abilityList.Swift_Swim, itemList.None, 51.6, false, status.none));
        pokeList.put("Luvdisc", new SpeciesClass(43, 43, 30, 55, 40, 65, 97, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 19.2, false, status.none));
        pokeList.put("Bagon", new SpeciesClass(45, 45, 75, 60, 40, 30, 50, pkmnType.Dragon, pkmnType.Typeless, abilityList.Rock_Head, itemList.None, 92.8, false, status.none));
        pokeList.put("Shelgon", new SpeciesClass(65, 65, 95, 100, 60, 50, 50, pkmnType.Dragon, pkmnType.Typeless, abilityList.Rock_Head, itemList.None, 243.6, false, status.none));
        pokeList.put("Salamence", new SpeciesClass(95, 95, 135, 80, 110, 80, 100, pkmnType.Dragon, pkmnType.Flying, abilityList.Intimidate, itemList.None, 226.2, false, status.none));
        pokeList.put("Salamence-M", new SpeciesClass(95, 95, 145, 130, 120, 90, 120, pkmnType.Dragon, pkmnType.Flying, abilityList.Aerilate, itemList.None, 248.2, false, status.none));
        pokeList.put("Beldum", new SpeciesClass(40, 40, 55, 80, 35, 60, 30, pkmnType.Steel, pkmnType.Psychic, abilityList.Clear_Body, itemList.None, 209.9, false, status.none));
        pokeList.put("Metang", new SpeciesClass(60, 60, 75, 100, 55, 80, 50, pkmnType.Steel, pkmnType.Psychic, abilityList.Clear_Body, itemList.None, 446.4, false, status.none));
        pokeList.put("Metagross", new SpeciesClass(80, 80, 135, 130, 95, 90, 70, pkmnType.Steel, pkmnType.Psychic, abilityList.Clear_Body, itemList.None, 1212.5, false, status.none));
        pokeList.put("Metagross-M", new SpeciesClass(80, 80, 145, 150, 105, 110, 110, pkmnType.Steel, pkmnType.Psychic, abilityList.Tough_Claws, itemList.None, 2078.7, false, status.none));
        pokeList.put("Regirock", new SpeciesClass(80, 80, 100, 200, 50, 100, 50, pkmnType.Rock, pkmnType.Typeless, abilityList.Clear_Body, itemList.None, 507.1, false, status.none));
        pokeList.put("Regice", new SpeciesClass(80, 80, 50, 100, 100, 200, 50, pkmnType.Ice, pkmnType.Typeless, abilityList.Clear_Body, itemList.None, 385.8, false, status.none));
        pokeList.put("Registeel", new SpeciesClass(80, 80, 75, 150, 75, 150, 50, pkmnType.Steel, pkmnType.Typeless, abilityList.Clear_Body, itemList.None, 451.9, false, status.none));
        pokeList.put("Latias", new SpeciesClass(80, 80, 80, 90, 110, 130, 110, pkmnType.Dragon, pkmnType.Psychic, abilityList.Levitate, itemList.None, 88.2, false, status.none));
        pokeList.put("Latias-M", new SpeciesClass(80, 80, 100, 120, 140, 150, 110, pkmnType.Dragon, pkmnType.Psychic, abilityList.Levitate, itemList.None, 114.6, false, status.none));
        pokeList.put("Latios", new SpeciesClass(80, 80, 90, 80, 130, 110, 110, pkmnType.Dragon, pkmnType.Psychic, abilityList.Levitate, itemList.None, 132.3, false, status.none));
        pokeList.put("Latios-M", new SpeciesClass(80, 80, 130, 100, 160, 120, 110, pkmnType.Dragon, pkmnType.Psychic, abilityList.Levitate, itemList.None, 154.3, false, status.none));
        pokeList.put("Kyogre", new SpeciesClass(100, 100, 100, 90, 150, 140, 90, pkmnType.Water, pkmnType.Typeless, abilityList.Drizzle, itemList.None, 776.0, false, status.none));
        pokeList.put("Kyogre-P", new SpeciesClass(100, 100, 150, 90, 180, 160, 90, pkmnType.Water, pkmnType.Typeless, abilityList.Primordial_Sea, itemList.None, 948.0, false, status.none));
        pokeList.put("Groudon", new SpeciesClass(100, 100, 150, 140, 100, 90, 90, pkmnType.Ground, pkmnType.Typeless, abilityList.Drought, itemList.None, 2094.4, false, status.none));
        pokeList.put("Groudon-P", new SpeciesClass(100, 100, 180, 160, 150, 90, 90, pkmnType.Ground, pkmnType.Fire, abilityList.Desolate_Land, itemList.None, 2204.0, false, status.none));
        pokeList.put("Rayquaza", new SpeciesClass(105, 105, 150, 90, 150, 90, 95, pkmnType.Dragon, pkmnType.Flying, abilityList.Air_Lock, itemList.None, 455.3, false, status.none));
        pokeList.put("Rayquaza-M", new SpeciesClass(105, 105, 180, 100, 180, 100, 115, pkmnType.Dragon, pkmnType.Flying, abilityList.Delta_Stream, itemList.None, 864.2, false, status.none));
        pokeList.put("Jirachi", new SpeciesClass(100, 100, 100, 100, 100, 100, 100, pkmnType.Steel, pkmnType.Psychic, abilityList.Serene_Grace, itemList.None, 2.4, false, status.none));
        pokeList.put("Deoxys-N", new SpeciesClass(50, 50, 150, 50, 150, 50, 150, pkmnType.Psychic, pkmnType.Typeless, abilityList.Pressure, itemList.None, 134.0, false, status.none));
        pokeList.put("Deoxys-A", new SpeciesClass(50, 50, 180, 20, 180, 20, 150, pkmnType.Psychic, pkmnType.Typeless, abilityList.Pressure, itemList.None, 134.0, false, status.none));
        pokeList.put("Deoxys-D", new SpeciesClass(50, 50, 70, 160, 70, 160, 90, pkmnType.Psychic, pkmnType.Typeless, abilityList.Pressure, itemList.None, 134.0, false, status.none));
        pokeList.put("Deoxys-S", new SpeciesClass(50, 50, 95, 90, 95, 90, 180, pkmnType.Psychic, pkmnType.Typeless, abilityList.Pressure, itemList.None, 134.0, false, status.none));
        pokeList.put("Turtwig", new SpeciesClass(55, 55, 68, 64, 45, 55, 31, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 22.5, false, status.none));
        pokeList.put("Grotle", new SpeciesClass(75, 75, 89, 85, 55, 65, 36, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 213.8, false, status.none));
        pokeList.put("Torterra", new SpeciesClass(95, 95, 109, 105, 75, 85, 56, pkmnType.Grass, pkmnType.Ground, abilityList.Overgrow, itemList.None, 683.4, false, status.none));
        pokeList.put("Chimchar", new SpeciesClass(44, 44, 58, 44, 58, 44, 61, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 13.7, false, status.none));
        pokeList.put("Monferno", new SpeciesClass(64, 64, 78, 52, 78, 52, 81, pkmnType.Fire, pkmnType.Fighting, abilityList.Blaze, itemList.None, 48.5, false, status.none));
        pokeList.put("Infernape", new SpeciesClass(76, 76, 104, 71, 104, 71, 108, pkmnType.Fire, pkmnType.Fighting, abilityList.Blaze, itemList.None, 121.3, false, status.none));
        pokeList.put("Piplup", new SpeciesClass(53, 53, 51, 53, 61, 56, 40, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 11.5, false, status.none));
        pokeList.put("Prinplup", new SpeciesClass(64, 64, 66, 68, 81, 76, 50, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 50.7, false, status.none));
        pokeList.put("Empoleon", new SpeciesClass(84, 84, 86, 88, 111, 101, 60, pkmnType.Water, pkmnType.Steel, abilityList.Torrent, itemList.None, 186.3, false, status.none));
        pokeList.put("Starly", new SpeciesClass(40, 40, 55, 30, 30, 30, 60, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 4.4, false, status.none));
        pokeList.put("Staravia", new SpeciesClass(55, 55, 75, 50, 40, 40, 80, pkmnType.Normal, pkmnType.Flying, abilityList.Intimidate, itemList.None, 34.2, false, status.none));
        pokeList.put("Staraptor", new SpeciesClass(85, 85, 120, 70, 50, 60, 100, pkmnType.Normal, pkmnType.Flying, abilityList.Intimidate, itemList.None, 54.9, false, status.none));
        pokeList.put("Bidoof", new SpeciesClass(59, 59, 45, 40, 35, 40, 31, pkmnType.Normal, pkmnType.Typeless, abilityList.Simple, itemList.None, 44.1, false, status.none));
        pokeList.put("Bibarel", new SpeciesClass(79, 79, 85, 60, 55, 60, 71, pkmnType.Normal, pkmnType.Water, abilityList.Simple, itemList.None, 69.4, false, status.none));
        pokeList.put("Kricketot", new SpeciesClass(37, 37, 25, 41, 25, 41, 25, pkmnType.Bug, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 4.9, false, status.none));
        pokeList.put("Kricketune", new SpeciesClass(77, 77, 85, 51, 55, 51, 65, pkmnType.Bug, pkmnType.Typeless, abilityList.Swarm, itemList.None, 56.2, false, status.none));
        pokeList.put("Shinx", new SpeciesClass(45, 45, 65, 34, 40, 34, 45, pkmnType.Electric, pkmnType.Typeless, abilityList.Rivalry, itemList.None, 20.9, false, status.none));
        pokeList.put("Luxio", new SpeciesClass(60, 60, 85, 49, 60, 49, 60, pkmnType.Electric, pkmnType.Typeless, abilityList.Rivalry, itemList.None, 67.2, false, status.none));
        pokeList.put("Luxray", new SpeciesClass(80, 80, 120, 79, 95, 79, 70, pkmnType.Electric, pkmnType.Typeless, abilityList.Rivalry, itemList.None, 92.6, false, status.none));
        pokeList.put("Budew", new SpeciesClass(40, 40, 30, 35, 50, 70, 55, pkmnType.Grass, pkmnType.Poison, abilityList.Natural_Cure, itemList.None, 2.6, false, status.none));
        pokeList.put("Roserade", new SpeciesClass(60, 60, 70, 65, 125, 105, 90, pkmnType.Grass, pkmnType.Poison, abilityList.Natural_Cure, itemList.None, 32.0, false, status.none));
        pokeList.put("Cranidos", new SpeciesClass(67, 67, 125, 40, 30, 30, 58, pkmnType.Rock, pkmnType.Typeless, abilityList.Mold_Breaker, itemList.None, 69.4, false, status.none));
        pokeList.put("Rampardos", new SpeciesClass(97, 97, 165, 60, 65, 50, 58, pkmnType.Rock, pkmnType.Typeless, abilityList.Mold_Breaker, itemList.None, 226.0, false, status.none));
        pokeList.put("Shieldon", new SpeciesClass(30, 30, 42, 118, 42, 88, 30, pkmnType.Rock, pkmnType.Steel, abilityList.Sturdy, itemList.None, 125.7, false, status.none));
        pokeList.put("Bastiodon", new SpeciesClass(60, 60, 52, 168, 47, 138, 30, pkmnType.Rock, pkmnType.Steel, abilityList.Sturdy, itemList.None, 329.6, false, status.none));
        pokeList.put("Burmy-P", new SpeciesClass(40, 40, 29, 45, 29, 45, 36, pkmnType.Bug, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 7.5, false, status.none));
        pokeList.put("Burmy-S", new SpeciesClass(40, 40, 29, 45, 29, 45, 36, pkmnType.Bug, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 7.5, false, status.none));
        pokeList.put("Burmy-T", new SpeciesClass(40, 40, 29, 45, 29, 45, 36, pkmnType.Bug, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 7.5, false, status.none));
        pokeList.put("Wormadam-P", new SpeciesClass(60, 60, 59, 85, 79, 105, 36, pkmnType.Bug, pkmnType.Grass, abilityList.Anticipation, itemList.None, 14.3, false, status.none));
        pokeList.put("Wormadam-S", new SpeciesClass(60, 60, 79, 105, 59, 85, 36, pkmnType.Bug, pkmnType.Ground, abilityList.Anticipation, itemList.None, 14.3, false, status.none));
        pokeList.put("Wormadam-T", new SpeciesClass(60, 60, 69, 95, 69, 95, 36, pkmnType.Bug, pkmnType.Steel, abilityList.Anticipation, itemList.None, 14.3, false, status.none));
        pokeList.put("Mothim", new SpeciesClass(70, 70, 94, 50, 94, 50, 66, pkmnType.Bug, pkmnType.Flying, abilityList.Swarm, itemList.None, 51.4, false, status.none));
        pokeList.put("Combee", new SpeciesClass(30, 30, 30, 42, 30, 42, 70, pkmnType.Bug, pkmnType.Flying, abilityList.Honey_Gather, itemList.None, 12.1, false, status.none));
        pokeList.put("Vespiquen", new SpeciesClass(70, 70, 80, 102, 80, 102, 40, pkmnType.Bug, pkmnType.Flying, abilityList.Pressure, itemList.None, 84.9, false, status.none));
        pokeList.put("Pachirisu", new SpeciesClass(60, 60, 45, 70, 45, 90, 95, pkmnType.Electric, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 8.6, false, status.none));
        pokeList.put("Buizel", new SpeciesClass(55, 55, 65, 35, 60, 30, 85, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 65.0, false, status.none));
        pokeList.put("Floatzel", new SpeciesClass(85, 85, 105, 55, 85, 50, 115, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 73.9, false, status.none));
        pokeList.put("Cherubi", new SpeciesClass(45, 45, 35, 45, 62, 53, 35, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 7.3, false, status.none));
        pokeList.put("Cherrim", new SpeciesClass(70, 70, 60, 70, 87, 78, 85, pkmnType.Grass, pkmnType.Typeless, abilityList.Flower_Gift, itemList.None, 20.5, false, status.none));
        pokeList.put("Shellos", new SpeciesClass(76, 76, 48, 48, 57, 62, 34, pkmnType.Water, pkmnType.Typeless, abilityList.Sticky_Hold, itemList.None, 13.9, false, status.none));
        pokeList.put("Gastrodon", new SpeciesClass(111, 111, 83, 68, 92, 82, 39, pkmnType.Water, pkmnType.Ground, abilityList.Sticky_Hold, itemList.None, 65.9, false, status.none));
        pokeList.put("Ambipom", new SpeciesClass(75, 75, 100, 66, 60, 66, 115, pkmnType.Normal, pkmnType.Typeless, abilityList.Technician, itemList.None, 44.8, false, status.none));
        pokeList.put("Drifloon", new SpeciesClass(90, 90, 50, 34, 60, 44, 70, pkmnType.Ghost, pkmnType.Flying, abilityList.Aftermath, itemList.None, 2.6, false, status.none));
        pokeList.put("Drifblim", new SpeciesClass(150, 150, 80, 44, 90, 54, 80, pkmnType.Ghost, pkmnType.Flying, abilityList.Aftermath, itemList.None, 33.1, false, status.none));
        pokeList.put("Buneary", new SpeciesClass(55, 55, 66, 44, 44, 56, 85, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 12.1, false, status.none));
        pokeList.put("Lopunny", new SpeciesClass(65, 65, 76, 84, 54, 96, 105, pkmnType.Normal, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 73.4, false, status.none));
        pokeList.put("Lopunny-M", new SpeciesClass(65, 65, 136, 94, 54, 96, 135, pkmnType.Normal, pkmnType.Fighting, abilityList.Scrappy, itemList.None, 62.4, false, status.none));
        pokeList.put("Mismagius", new SpeciesClass(60, 60, 60, 60, 105, 105, 105, pkmnType.Ghost, pkmnType.Typeless, abilityList.Levitate, itemList.None, 9.7, false, status.none));
        pokeList.put("Honchkrow", new SpeciesClass(100, 100, 125, 52, 105, 52, 71, pkmnType.Dark, pkmnType.Flying, abilityList.Insomnia, itemList.None, 60.2, false, status.none));
        pokeList.put("Glameow", new SpeciesClass(49, 49, 55, 42, 42, 37, 85, pkmnType.Normal, pkmnType.Typeless, abilityList.Limber, itemList.None, 8.6, false, status.none));
        pokeList.put("Purugly", new SpeciesClass(71, 71, 82, 64, 64, 59, 112, pkmnType.Normal, pkmnType.Typeless, abilityList.Thick_Fat, itemList.None, 96.6, false, status.none));
        pokeList.put("Chingling", new SpeciesClass(45, 45, 30, 50, 65, 50, 45, pkmnType.Psychic, pkmnType.Typeless, abilityList.Levitate, itemList.None, 1.3, false, status.none));
        pokeList.put("Stunky", new SpeciesClass(63, 63, 63, 47, 41, 41, 74, pkmnType.Poison, pkmnType.Dark, abilityList.Stench, itemList.None, 42.3, false, status.none));
        pokeList.put("Skuntank", new SpeciesClass(103, 103, 93, 67, 71, 61, 84, pkmnType.Poison, pkmnType.Dark, abilityList.Stench, itemList.None, 83.8, false, status.none));
        pokeList.put("Bronzor", new SpeciesClass(57, 57, 24, 86, 24, 86, 23, pkmnType.Steel, pkmnType.Psychic, abilityList.Levitate, itemList.None, 133.4, false, status.none));
        pokeList.put("Bronzong", new SpeciesClass(67, 67, 89, 116, 79, 116, 33, pkmnType.Steel, pkmnType.Psychic, abilityList.Levitate, itemList.None, 412.3, false, status.none));
        pokeList.put("Bonsly", new SpeciesClass(50, 50, 80, 95, 10, 45, 10, pkmnType.Rock, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 33.1, false, status.none));
        pokeList.put("Mime Jr.", new SpeciesClass(20, 20, 25, 45, 70, 90, 60, pkmnType.Psychic, pkmnType.Fairy, abilityList.Soundproof, itemList.None, 28.7, false, status.none));
        pokeList.put("Happiny", new SpeciesClass(100, 100, 5, 5, 15, 65, 30, pkmnType.Normal, pkmnType.Typeless, abilityList.Natural_Cure, itemList.None, 53.8, false, status.none));
        pokeList.put("Chatot", new SpeciesClass(76, 76, 65, 45, 92, 42, 91, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 4.2, false, status.none));
        pokeList.put("Spiritomb", new SpeciesClass(50, 50, 92, 108, 92, 108, 35, pkmnType.Ghost, pkmnType.Dark, abilityList.Pressure, itemList.None, 238.1, false, status.none));
        pokeList.put("Gible", new SpeciesClass(58, 58, 70, 45, 40, 45, 42, pkmnType.Dragon, pkmnType.Ground, abilityList.Sand_Veil, itemList.None, 45.2, false, status.none));
        pokeList.put("Gabite", new SpeciesClass(68, 68, 90, 65, 50, 55, 82, pkmnType.Dragon, pkmnType.Ground, abilityList.Sand_Veil, itemList.None, 123.5, false, status.none));
        pokeList.put("Garchomp", new SpeciesClass(108, 108, 130, 95, 80, 85, 102, pkmnType.Dragon, pkmnType.Ground, abilityList.Sand_Veil, itemList.None, 209.4, false, status.none));
        pokeList.put("Garchomp-M", new SpeciesClass(108, 108, 170, 115, 120, 95, 92, pkmnType.Dragon, pkmnType.Ground, abilityList.Sand_Force, itemList.None, 209.4, false, status.none));
        pokeList.put("Munchlax", new SpeciesClass(135, 135, 85, 40, 40, 85, 5, pkmnType.Normal, pkmnType.Typeless, abilityList.Pickup, itemList.None, 231.5, false, status.none));
        pokeList.put("Riolu", new SpeciesClass(40, 40, 70, 40, 35, 40, 60, pkmnType.Fighting, pkmnType.Typeless, abilityList.Steadfast, itemList.None, 44.5, false, status.none));
        pokeList.put("Lucario", new SpeciesClass(70, 70, 110, 70, 115, 70, 90, pkmnType.Fighting, pkmnType.Steel, abilityList.Steadfast, itemList.None, 119.0, false, status.none));
        pokeList.put("Lucario-M", new SpeciesClass(70, 70, 145, 88, 140, 70, 112, pkmnType.Fighting, pkmnType.Steel, abilityList.Adaptability, itemList.None, 126.8, false, status.none));
        pokeList.put("Hippopotas", new SpeciesClass(68, 68, 72, 78, 38, 42, 32, pkmnType.Ground, pkmnType.Typeless, abilityList.Sand_Stream, itemList.None, 109.1, false, status.none));
        pokeList.put("Hippowdon", new SpeciesClass(108, 108, 112, 118, 68, 72, 47, pkmnType.Ground, pkmnType.Typeless, abilityList.Sand_Stream, itemList.None, 661.4, false, status.none));
        pokeList.put("Skorupi", new SpeciesClass(40, 40, 50, 90, 30, 55, 65, pkmnType.Poison, pkmnType.Bug, abilityList.Battle_Armor, itemList.None, 26.5, false, status.none));
        pokeList.put("Drapion", new SpeciesClass(70, 70, 90, 110, 60, 75, 95, pkmnType.Poison, pkmnType.Dark, abilityList.Battle_Armor, itemList.None, 135.6, false, status.none));
        pokeList.put("Croagunk", new SpeciesClass(48, 48, 61, 40, 61, 40, 50, pkmnType.Poison, pkmnType.Fighting, abilityList.Anticipation, itemList.None, 50.7, false, status.none));
        pokeList.put("Toxicroak", new SpeciesClass(83, 83, 106, 65, 86, 65, 85, pkmnType.Poison, pkmnType.Fighting, abilityList.Anticipation, itemList.None, 97.9, false, status.none));
        pokeList.put("Carnivine", new SpeciesClass(74, 74, 100, 72, 90, 72, 46, pkmnType.Grass, pkmnType.Typeless, abilityList.Levitate, itemList.None, 59.5, false, status.none));
        pokeList.put("Finneon", new SpeciesClass(49, 49, 49, 56, 49, 61, 66, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 15.4, false, status.none));
        pokeList.put("Lumineon", new SpeciesClass(69, 69, 69, 76, 69, 86, 91, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 52.9, false, status.none));
        pokeList.put("Mantyke", new SpeciesClass(45, 45, 20, 50, 60, 120, 50, pkmnType.Water, pkmnType.Flying, abilityList.Swift_Swim, itemList.None, 143.3, false, status.none));
        pokeList.put("Snover", new SpeciesClass(60, 60, 62, 50, 62, 60, 40, pkmnType.Grass, pkmnType.Ice, abilityList.Snow_Warning, itemList.None, 111.3, false, status.none));
        pokeList.put("Abomasnow", new SpeciesClass(90, 90, 92, 75, 92, 85, 60, pkmnType.Grass, pkmnType.Ice, abilityList.Snow_Warning, itemList.None, 298.7, false, status.none));
        pokeList.put("Abomasnow-M", new SpeciesClass(90, 90, 132, 105, 132, 105, 30, pkmnType.Grass, pkmnType.Ice, abilityList.Snow_Warning, itemList.None, 407.9, false, status.none));
        pokeList.put("Weavile", new SpeciesClass(70, 70, 120, 65, 45, 85, 125, pkmnType.Dark, pkmnType.Ice, abilityList.Pressure, itemList.None, 75.0, false, status.none));
        pokeList.put("Magnezone", new SpeciesClass(70, 70, 70, 115, 130, 90, 60, pkmnType.Electric, pkmnType.Steel, abilityList.Magnet_Pull, itemList.None, 396.8, false, status.none));
        pokeList.put("Lickilicky", new SpeciesClass(110, 110, 85, 95, 80, 95, 50, pkmnType.Normal, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 308.6, false, status.none));
        pokeList.put("Rhyperior", new SpeciesClass(115, 115, 140, 130, 55, 55, 40, pkmnType.Ground, pkmnType.Rock, abilityList.Lightning_Rod, itemList.None, 623.5, false, status.none));
        pokeList.put("Tangrowth", new SpeciesClass(100, 100, 100, 125, 110, 50, 50, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 283.5, false, status.none));
        pokeList.put("Electivire", new SpeciesClass(75, 75, 123, 67, 95, 85, 95, pkmnType.Electric, pkmnType.Typeless, abilityList.Motor_Drive, itemList.None, 305.6, false, status.none));
        pokeList.put("Magmortar", new SpeciesClass(75, 75, 95, 67, 125, 95, 83, pkmnType.Fire, pkmnType.Typeless, abilityList.Flame_Body, itemList.None, 149.9, false, status.none));
        pokeList.put("Togekiss", new SpeciesClass(85, 85, 50, 95, 120, 115, 80, pkmnType.Fairy, pkmnType.Flying, abilityList.Hustle, itemList.None, 83.8, false, status.none));
        pokeList.put("Yanmega", new SpeciesClass(86, 86, 76, 86, 116, 56, 95, pkmnType.Bug, pkmnType.Flying, abilityList.Speed_Boost, itemList.None, 113.5, false, status.none));
        pokeList.put("Leafeon", new SpeciesClass(65, 65, 110, 130, 60, 65, 95, pkmnType.Grass, pkmnType.Typeless, abilityList.Leaf_Guard, itemList.None, 56.2, false, status.none));
        pokeList.put("Glaceon", new SpeciesClass(65, 65, 60, 110, 130, 95, 65, pkmnType.Ice, pkmnType.Typeless, abilityList.Snow_Cloak, itemList.None, 57.1, false, status.none));
        pokeList.put("Gliscor", new SpeciesClass(75, 75, 95, 125, 45, 75, 95, pkmnType.Ground, pkmnType.Flying, abilityList.Hyper_Cutter, itemList.None, 93.7, false, status.none));
        pokeList.put("Mamoswine", new SpeciesClass(110, 110, 130, 80, 70, 60, 80, pkmnType.Ice, pkmnType.Ground, abilityList.Oblivious, itemList.None, 641.5, false, status.none));
        pokeList.put("Porygon-Z", new SpeciesClass(85, 85, 80, 70, 135, 75, 90, pkmnType.Normal, pkmnType.Typeless, abilityList.Adaptability, itemList.None, 75.0, false, status.none));
        pokeList.put("Gallade", new SpeciesClass(68, 68, 125, 65, 65, 115, 80, pkmnType.Psychic, pkmnType.Fighting, abilityList.Steadfast, itemList.None, 114.6, false, status.none));
        pokeList.put("Gallade-M", new SpeciesClass(68, 68, 165, 95, 65, 115, 110, pkmnType.Psychic, pkmnType.Fighting, abilityList.Inner_Focus, itemList.None, 124.3, false, status.none));
        pokeList.put("Probopass", new SpeciesClass(60, 60, 55, 145, 75, 150, 40, pkmnType.Rock, pkmnType.Steel, abilityList.Sturdy, itemList.None, 749.6, false, status.none));
        pokeList.put("Dusknoir", new SpeciesClass(45, 45, 100, 135, 65, 135, 45, pkmnType.Ghost, pkmnType.Typeless, abilityList.Pressure, itemList.None, 235.0, false, status.none));
        pokeList.put("Froslass", new SpeciesClass(70, 70, 80, 70, 80, 70, 110, pkmnType.Ice, pkmnType.Ghost, abilityList.Snow_Cloak, itemList.None, 58.6, false, status.none));
        pokeList.put("Rotom", new SpeciesClass(50, 50, 50, 77, 95, 77, 91, pkmnType.Electric, pkmnType.Ghost, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Rotom-H", new SpeciesClass(50, 50, 65, 107, 105, 107, 86, pkmnType.Electric, pkmnType.Fire, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Rotom-W", new SpeciesClass(50, 50, 65, 107, 105, 107, 86, pkmnType.Electric, pkmnType.Water, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Rotom-F", new SpeciesClass(50, 50, 65, 107, 105, 107, 86, pkmnType.Electric, pkmnType.Ice, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Rotom-F", new SpeciesClass(50, 50, 65, 107, 105, 107, 86, pkmnType.Electric, pkmnType.Flying, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Rotom-M", new SpeciesClass(50, 50, 65, 107, 105, 107, 86, pkmnType.Electric, pkmnType.Grass, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Uxie", new SpeciesClass(75, 75, 75, 130, 75, 130, 95, pkmnType.Psychic, pkmnType.Typeless, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Mesprit", new SpeciesClass(80, 80, 105, 105, 105, 105, 80, pkmnType.Psychic, pkmnType.Typeless, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Azelf", new SpeciesClass(75, 75, 125, 70, 125, 70, 115, pkmnType.Psychic, pkmnType.Typeless, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Dialga", new SpeciesClass(100, 100, 120, 120, 150, 100, 90, pkmnType.Steel, pkmnType.Dragon, abilityList.Pressure, itemList.None, 1505.8, false, status.none));
        pokeList.put("Dialga-O", new SpeciesClass(100, 100, 100, 120, 150, 120, 90, pkmnType.Steel, pkmnType.Dragon, abilityList.Pressure, itemList.None, 1873.9, false, status.none));
        pokeList.put("Palkia", new SpeciesClass(90, 90, 120, 100, 150, 120, 100, pkmnType.Water, pkmnType.Dragon, abilityList.Pressure, itemList.None, 740.8, false, status.none));
        pokeList.put("Palkia-O", new SpeciesClass(90, 90, 100, 100, 150, 120, 120, pkmnType.Water, pkmnType.Dragon, abilityList.Pressure, itemList.None, 1455.0, false, status.none));
        pokeList.put("Heatran", new SpeciesClass(91, 91, 90, 106, 130, 106, 77, pkmnType.Fire, pkmnType.Steel, abilityList.Flash_Fire, itemList.None, 948.0, false, status.none));
        pokeList.put("Regigigas", new SpeciesClass(110, 110, 160, 110, 80, 110, 100, pkmnType.Normal, pkmnType.Typeless, abilityList.Slow_Start, itemList.None, 925.9, false, status.none));
        pokeList.put("Giratina-A", new SpeciesClass(150, 150, 100, 120, 100, 120, 90, pkmnType.Ghost, pkmnType.Dragon, abilityList.Pressure, itemList.None, 1653.5, false, status.none));
        pokeList.put("Giratina-O", new SpeciesClass(150, 150, 120, 100, 120, 100, 90, pkmnType.Ghost, pkmnType.Dragon, abilityList.Levitate, itemList.None, 1433.0, false, status.none));
        pokeList.put("Cresselia", new SpeciesClass(120, 120, 70, 110, 75, 120, 85, pkmnType.Psychic, pkmnType.Typeless, abilityList.Levitate, itemList.None, 188.7, false, status.none));
        pokeList.put("Phione", new SpeciesClass(80, 80, 80, 80, 80, 80, 80, pkmnType.Water, pkmnType.Typeless, abilityList.Hydration, itemList.None, 6.8, false, status.none));
        pokeList.put("Manaphy", new SpeciesClass(100, 100, 100, 100, 100, 100, 100, pkmnType.Water, pkmnType.Typeless, abilityList.Hydration, itemList.None, 3.1, false, status.none));
        pokeList.put("Darkrai", new SpeciesClass(70, 70, 90, 90, 135, 90, 125, pkmnType.Dark, pkmnType.Typeless, abilityList.Bad_Dreams, itemList.None, 111.3, false, status.none));
        pokeList.put("Shaymin-L", new SpeciesClass(100, 100, 100, 100, 100, 100, 100, pkmnType.Grass, pkmnType.Typeless, abilityList.Natural_Cure, itemList.None, 4.6, false, status.none));
        pokeList.put("Shaymin-S", new SpeciesClass(100, 100, 103, 75, 120, 75, 127, pkmnType.Grass, pkmnType.Flying, abilityList.Serene_Grace, itemList.None, 11.5, false, status.none));
        pokeList.put("Arceus", new SpeciesClass(120, 120, 120, 120, 120, 120, 120, pkmnType.Normal, pkmnType.Typeless, abilityList.Multitype, itemList.None, 705.5, false, status.none));
        pokeList.put("Victini", new SpeciesClass(100, 100, 100, 100, 100, 100, 100, pkmnType.Psychic, pkmnType.Fire, abilityList.Victory_Star, itemList.None, 8.8, false, status.none));
        pokeList.put("Snivy", new SpeciesClass(45, 45, 45, 55, 45, 55, 63, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 17.9, false, status.none));
        pokeList.put("Servine", new SpeciesClass(60, 60, 60, 75, 60, 75, 83, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 35.3, false, status.none));
        pokeList.put("Serperior", new SpeciesClass(75, 75, 75, 95, 75, 95, 113, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 138.9, false, status.none));
        pokeList.put("Tepig", new SpeciesClass(65, 65, 63, 45, 45, 45, 45, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 21.8, false, status.none));
        pokeList.put("Pignite", new SpeciesClass(90, 90, 93, 55, 70, 55, 55, pkmnType.Fire, pkmnType.Fighting, abilityList.Blaze, itemList.None, 122.4, false, status.none));
        pokeList.put("Emboar", new SpeciesClass(110, 110, 123, 65, 100, 65, 65, pkmnType.Fire, pkmnType.Fighting, abilityList.Blaze, itemList.None, 330.7, false, status.none));
        pokeList.put("Oshawott", new SpeciesClass(55, 55, 55, 45, 63, 45, 45, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 13.0, false, status.none));
        pokeList.put("Dewott", new SpeciesClass(75, 75, 75, 60, 83, 60, 60, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 54.0, false, status.none));
        pokeList.put("Samurott", new SpeciesClass(95, 95, 100, 85, 108, 70, 70, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 208.6, false, status.none));
        pokeList.put("Samurott-H", new SpeciesClass(90, 90, 108, 80, 100, 65, 85, pkmnType.Water, pkmnType.Dark, abilityList.Torrent, itemList.None, 128.3, false, status.none));
        pokeList.put("Patrat", new SpeciesClass(45, 45, 55, 39, 35, 39, 42, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 25.6, false, status.none));
        pokeList.put("Watchog", new SpeciesClass(60, 60, 85, 69, 60, 69, 77, pkmnType.Normal, pkmnType.Typeless, abilityList.Illuminate, itemList.None, 59.5, false, status.none));
        pokeList.put("Lillipup", new SpeciesClass(45, 45, 60, 45, 25, 45, 55, pkmnType.Normal, pkmnType.Typeless, abilityList.Vital_Spirit, itemList.None, 9.0, false, status.none));
        pokeList.put("Herdier", new SpeciesClass(65, 65, 80, 65, 35, 65, 60, pkmnType.Normal, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 32.4, false, status.none));
        pokeList.put("Stoutland", new SpeciesClass(85, 85, 110, 90, 45, 90, 80, pkmnType.Normal, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 134.5, false, status.none));
        pokeList.put("Purrloin", new SpeciesClass(41, 41, 50, 37, 50, 37, 66, pkmnType.Dark, pkmnType.Typeless, abilityList.Limber, itemList.None, 22.3, false, status.none));
        pokeList.put("Liepard", new SpeciesClass(64, 64, 88, 50, 88, 50, 106, pkmnType.Dark, pkmnType.Typeless, abilityList.Limber, itemList.None, 82.7, false, status.none));
        pokeList.put("Pansage", new SpeciesClass(50, 50, 53, 48, 53, 48, 64, pkmnType.Grass, pkmnType.Typeless, abilityList.Gluttony, itemList.None, 23.1, false, status.none));
        pokeList.put("Simisage", new SpeciesClass(75, 75, 98, 63, 98, 63, 101, pkmnType.Grass, pkmnType.Typeless, abilityList.Gluttony, itemList.None, 67.2, false, status.none));
        pokeList.put("Pansear", new SpeciesClass(50, 50, 53, 48, 53, 48, 64, pkmnType.Fire, pkmnType.Typeless, abilityList.Gluttony, itemList.None, 24.3, false, status.none));
        pokeList.put("Simisear", new SpeciesClass(75, 75, 98, 63, 98, 63, 101, pkmnType.Fire, pkmnType.Typeless, abilityList.Gluttony, itemList.None, 61.7, false, status.none));
        pokeList.put("Panpour", new SpeciesClass(50, 50, 53, 48, 53, 48, 64, pkmnType.Water, pkmnType.Typeless, abilityList.Gluttony, itemList.None, 29.8, false, status.none));
        pokeList.put("Simipour", new SpeciesClass(75, 75, 98, 63, 98, 63, 101, pkmnType.Water, pkmnType.Typeless, abilityList.Gluttony, itemList.None, 63.9, false, status.none));
        pokeList.put("Munna", new SpeciesClass(76, 76, 25, 45, 67, 55, 24, pkmnType.Psychic, pkmnType.Typeless, abilityList.Forewarn, itemList.None, 51.4, false, status.none));
        pokeList.put("Musharna", new SpeciesClass(116, 116, 55, 85, 107, 95, 29, pkmnType.Psychic, pkmnType.Typeless, abilityList.Forewarn, itemList.None, 133.4, false, status.none));
        pokeList.put("Pidove", new SpeciesClass(50, 50, 55, 50, 36, 30, 43, pkmnType.Normal, pkmnType.Flying, abilityList.Big_Pecks, itemList.None, 4.6, false, status.none));
        pokeList.put("Tranquill", new SpeciesClass(62, 62, 77, 62, 50, 42, 65, pkmnType.Normal, pkmnType.Flying, abilityList.Big_Pecks, itemList.None, 33.1, false, status.none));
        pokeList.put("Unfezant", new SpeciesClass(80, 80, 115, 80, 65, 55, 93, pkmnType.Normal, pkmnType.Flying, abilityList.Big_Pecks, itemList.None, 63.9, false, status.none));
        pokeList.put("Blitzle", new SpeciesClass(45, 45, 60, 32, 50, 32, 76, pkmnType.Electric, pkmnType.Typeless, abilityList.Lightning_Rod, itemList.None, 65.7, false, status.none));
        pokeList.put("Zebstrika", new SpeciesClass(75, 75, 100, 63, 80, 63, 116, pkmnType.Electric, pkmnType.Typeless, abilityList.Lightning_Rod, itemList.None, 175.3, false, status.none));
        pokeList.put("Roggenrola", new SpeciesClass(55, 55, 75, 85, 25, 25, 15, pkmnType.Rock, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 39.7, false, status.none));
        pokeList.put("Boldore", new SpeciesClass(70, 70, 105, 105, 50, 40, 20, pkmnType.Rock, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 224.9, false, status.none));
        pokeList.put("Gigalith", new SpeciesClass(85, 85, 135, 130, 60, 80, 25, pkmnType.Rock, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 573.2, false, status.none));
        pokeList.put("Woobat", new SpeciesClass(65, 65, 45, 43, 55, 43, 72, pkmnType.Psychic, pkmnType.Flying, abilityList.Unaware, itemList.None, 4.6, false, status.none));
        pokeList.put("Swoobat", new SpeciesClass(67, 67, 57, 55, 77, 55, 114, pkmnType.Psychic, pkmnType.Flying, abilityList.Unaware, itemList.None, 23.1, false, status.none));
        pokeList.put("Drilbur", new SpeciesClass(60, 60, 85, 40, 30, 45, 68, pkmnType.Ground, pkmnType.Typeless, abilityList.Sand_Rush, itemList.None, 18.7, false, status.none));
        pokeList.put("Excadrill", new SpeciesClass(110, 110, 135, 60, 50, 65, 88, pkmnType.Ground, pkmnType.Steel, abilityList.Sand_Rush, itemList.None, 89.1, false, status.none));
        pokeList.put("Audino", new SpeciesClass(103, 103, 60, 86, 60, 86, 50, pkmnType.Normal, pkmnType.Typeless, abilityList.Healer, itemList.None, 68.3, false, status.none));
        pokeList.put("Audino-M", new SpeciesClass(103, 103, 60, 126, 80, 126, 50, pkmnType.Normal, pkmnType.Fairy, abilityList.Healer, itemList.None, 70.5, false, status.none));
        pokeList.put("Timburr", new SpeciesClass(75, 75, 80, 55, 25, 35, 35, pkmnType.Fighting, pkmnType.Typeless, abilityList.Guts, itemList.None, 27.6, false, status.none));
        pokeList.put("Gurdurr", new SpeciesClass(85, 85, 105, 85, 40, 50, 40, pkmnType.Fighting, pkmnType.Typeless, abilityList.Guts, itemList.None, 88.2, false, status.none));
        pokeList.put("Conkeldurr", new SpeciesClass(105, 105, 140, 95, 55, 65, 45, pkmnType.Fighting, pkmnType.Typeless, abilityList.Guts, itemList.None, 191.8, false, status.none));
        pokeList.put("Tympole", new SpeciesClass(50, 50, 50, 40, 50, 40, 64, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 9.9, false, status.none));
        pokeList.put("Palpitoad", new SpeciesClass(75, 75, 65, 55, 65, 55, 69, pkmnType.Water, pkmnType.Ground, abilityList.Swift_Swim, itemList.None, 37.5, false, status.none));
        pokeList.put("Seismitoad", new SpeciesClass(105, 105, 95, 75, 85, 75, 74, pkmnType.Water, pkmnType.Ground, abilityList.Swift_Swim, itemList.None, 136.7, false, status.none));
        pokeList.put("Throh", new SpeciesClass(120, 120, 100, 85, 30, 85, 45, pkmnType.Fighting, pkmnType.Typeless, abilityList.Guts, itemList.None, 122.4, false, status.none));
        pokeList.put("Sawk", new SpeciesClass(75, 75, 125, 75, 30, 75, 85, pkmnType.Fighting, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 112.4, false, status.none));
        pokeList.put("Sewaddle", new SpeciesClass(45, 45, 53, 70, 40, 60, 42, pkmnType.Bug, pkmnType.Grass, abilityList.Swarm, itemList.None, 5.5, false, status.none));
        pokeList.put("Swadloon", new SpeciesClass(55, 55, 63, 90, 50, 80, 42, pkmnType.Bug, pkmnType.Grass, abilityList.Leaf_Guard, itemList.None, 16.1, false, status.none));
        pokeList.put("Leavanny", new SpeciesClass(75, 75, 103, 80, 70, 80, 92, pkmnType.Bug, pkmnType.Grass, abilityList.Swarm, itemList.None, 45.2, false, status.none));
        pokeList.put("Venipede", new SpeciesClass(30, 30, 45, 59, 30, 39, 57, pkmnType.Bug, pkmnType.Poison, abilityList.Poison_Point, itemList.None, 11.7, false, status.none));
        pokeList.put("Whirlipede", new SpeciesClass(40, 40, 55, 99, 40, 79, 47, pkmnType.Bug, pkmnType.Poison, abilityList.Poison_Point, itemList.None, 129.0, false, status.none));
        pokeList.put("Scolipede", new SpeciesClass(60, 60, 100, 89, 55, 69, 112, pkmnType.Bug, pkmnType.Poison, abilityList.Poison_Point, itemList.None, 442.0, false, status.none));
        pokeList.put("Cottonee", new SpeciesClass(40, 40, 27, 60, 37, 50, 66, pkmnType.Grass, pkmnType.Fairy, abilityList.Prankster, itemList.None, 1.3, false, status.none));
        pokeList.put("Whimsicott", new SpeciesClass(60, 60, 67, 85, 77, 75, 116, pkmnType.Grass, pkmnType.Fairy, abilityList.Prankster, itemList.None, 14.6, false, status.none));
        pokeList.put("Petilil", new SpeciesClass(45, 45, 35, 50, 70, 50, 30, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 14.6, false, status.none));
        pokeList.put("Lilligant", new SpeciesClass(70, 70, 60, 75, 110, 75, 90, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 35.9, false, status.none));
        pokeList.put("Lilligant-H", new SpeciesClass(70, 70, 105, 75, 50, 75, 105, pkmnType.Grass, pkmnType.Fighting, abilityList.Chlorophyll, itemList.None, 42.3, false, status.none));
        pokeList.put("Basculin-R", new SpeciesClass(70, 70, 92, 65, 80, 55, 98, pkmnType.Water, pkmnType.Typeless, abilityList.Reckless, itemList.None, 39.7, false, status.none));
        pokeList.put("Basculin-B", new SpeciesClass(70, 70, 92, 65, 80, 55, 98, pkmnType.Water, pkmnType.Typeless, abilityList.Rock_Head, itemList.None, 39.7, false, status.none));
        pokeList.put("Basculin-W", new SpeciesClass(70, 70, 92, 65, 80, 55, 98, pkmnType.Water, pkmnType.Typeless, abilityList.Rattled, itemList.None, 39.7, false, status.none));
        pokeList.put("Sandile", new SpeciesClass(50, 50, 72, 35, 35, 35, 65, pkmnType.Ground, pkmnType.Dark, abilityList.Intimidate, itemList.None, 33.5, false, status.none));
        pokeList.put("Krokorok", new SpeciesClass(60, 60, 82, 45, 45, 45, 74, pkmnType.Ground, pkmnType.Dark, abilityList.Intimidate, itemList.None, 73.6, false, status.none));
        pokeList.put("Krookodile", new SpeciesClass(95, 95, 117, 80, 65, 70, 92, pkmnType.Ground, pkmnType.Dark, abilityList.Intimidate, itemList.None, 212.3, false, status.none));
        pokeList.put("Darumaka", new SpeciesClass(70, 70, 90, 45, 15, 45, 50, pkmnType.Fire, pkmnType.Typeless, abilityList.Hustle, itemList.None, 82.7, false, status.none));
        pokeList.put("Darumaka-G", new SpeciesClass(70, 70, 90, 45, 15, 45, 50, pkmnType.Ice, pkmnType.Typeless, abilityList.Hustle, itemList.None, 88.2, false, status.none));
        pokeList.put("Darmanitan-S", new SpeciesClass(105, 105, 140, 55, 30, 55, 95, pkmnType.Fire, pkmnType.Typeless, abilityList.Sheer_Force, itemList.None, 204.8, false, status.none));
        pokeList.put("Darmanitan-Z", new SpeciesClass(105, 105, 30, 105, 140, 105, 55, pkmnType.Fire, pkmnType.Psychic, abilityList.Zen_Mode, itemList.None, 204.8, false, status.none));
        pokeList.put("Darmanitan-G", new SpeciesClass(105, 105, 140, 55, 30, 55, 95, pkmnType.Ice, pkmnType.Typeless, abilityList.Gorilla_Tactics, itemList.None, 264.6, false, status.none));
        pokeList.put("Darmanitan-G", new SpeciesClass(105, 105, 160, 55, 30, 55, 135, pkmnType.Ice, pkmnType.Fire, abilityList.Zen_Mode, itemList.None, 264.6, false, status.none));
        pokeList.put("Maractus", new SpeciesClass(75, 75, 86, 67, 106, 67, 60, pkmnType.Grass, pkmnType.Typeless, abilityList.Water_Absorb, itemList.None, 61.7, false, status.none));
        pokeList.put("Dwebble", new SpeciesClass(50, 50, 65, 85, 35, 35, 55, pkmnType.Bug, pkmnType.Rock, abilityList.Sturdy, itemList.None, 32.0, false, status.none));
        pokeList.put("Crustle", new SpeciesClass(70, 70, 105, 125, 65, 75, 45, pkmnType.Bug, pkmnType.Rock, abilityList.Sturdy, itemList.None, 440.9, false, status.none));
        pokeList.put("Scraggy", new SpeciesClass(50, 50, 75, 70, 35, 70, 48, pkmnType.Dark, pkmnType.Fighting, abilityList.Shed_Skin, itemList.None, 26.0, false, status.none));
        pokeList.put("Scrafty", new SpeciesClass(65, 65, 90, 115, 45, 115, 58, pkmnType.Dark, pkmnType.Fighting, abilityList.Shed_Skin, itemList.None, 66.1, false, status.none));
        pokeList.put("Sigilyph", new SpeciesClass(72, 72, 58, 80, 103, 80, 97, pkmnType.Psychic, pkmnType.Flying, abilityList.Wonder_Skin, itemList.None, 30.9, false, status.none));
        pokeList.put("Yamask", new SpeciesClass(38, 38, 30, 85, 55, 65, 30, pkmnType.Ghost, pkmnType.Typeless, abilityList.Mummy, itemList.None, 3.3, false, status.none));
        pokeList.put("Yamask-G", new SpeciesClass(38, 38, 55, 85, 30, 65, 30, pkmnType.Ground, pkmnType.Ghost, abilityList.Wandering_Spirit, itemList.None, 3.3, false, status.none));
        pokeList.put("Cofagrigus", new SpeciesClass(58, 58, 50, 145, 95, 105, 30, pkmnType.Ghost, pkmnType.Typeless, abilityList.Mummy, itemList.None, 168.7, false, status.none));
        pokeList.put("Tirtouga", new SpeciesClass(54, 54, 78, 103, 53, 45, 22, pkmnType.Water, pkmnType.Rock, abilityList.Solid_Rock, itemList.None, 36.4, false, status.none));
        pokeList.put("Carracosta", new SpeciesClass(74, 74, 108, 133, 83, 65, 32, pkmnType.Water, pkmnType.Rock, abilityList.Solid_Rock, itemList.None, 178.6, false, status.none));
        pokeList.put("Archen", new SpeciesClass(55, 55, 112, 45, 74, 45, 70, pkmnType.Rock, pkmnType.Flying, abilityList.Defeatist, itemList.None, 20.9, false, status.none));
        pokeList.put("Archeops", new SpeciesClass(75, 75, 140, 65, 112, 65, 110, pkmnType.Rock, pkmnType.Flying, abilityList.Defeatist, itemList.None, 70.5, false, status.none));
        pokeList.put("Trubbish", new SpeciesClass(50, 50, 50, 62, 40, 62, 65, pkmnType.Poison, pkmnType.Typeless, abilityList.Stench, itemList.None, 68.3, false, status.none));
        pokeList.put("Garbodor", new SpeciesClass(80, 80, 95, 82, 60, 82, 75, pkmnType.Poison, pkmnType.Typeless, abilityList.Stench, itemList.None, 236.6, false, status.none));
        pokeList.put("Zorua", new SpeciesClass(40, 40, 65, 40, 80, 40, 65, pkmnType.Dark, pkmnType.Typeless, abilityList.Illusion, itemList.None, 27.6, false, status.none));
        pokeList.put("Zorua-H", new SpeciesClass(35, 35, 60, 40, 85, 40, 70, pkmnType.Normal, pkmnType.Ghost, abilityList.Illusion, itemList.None, 27.6, false, status.none));
        pokeList.put("Zoroark", new SpeciesClass(60, 60, 105, 60, 120, 60, 105, pkmnType.Dark, pkmnType.Typeless, abilityList.Illusion, itemList.None, 178.8, false, status.none));
        pokeList.put("Zoroark-H", new SpeciesClass(55, 55, 100, 60, 125, 60, 110, pkmnType.Normal, pkmnType.Ghost, abilityList.Illusion, itemList.None, 160.9, false, status.none));
        pokeList.put("Minccino", new SpeciesClass(55, 55, 50, 40, 40, 40, 75, pkmnType.Normal, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 12.8, false, status.none));
        pokeList.put("Cinccino", new SpeciesClass(75, 75, 95, 60, 65, 60, 115, pkmnType.Normal, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 16.5, false, status.none));
        pokeList.put("Gothita", new SpeciesClass(45, 45, 30, 50, 55, 65, 45, pkmnType.Psychic, pkmnType.Typeless, abilityList.Frisk, itemList.None, 12.8, false, status.none));
        pokeList.put("Gothorita", new SpeciesClass(60, 60, 45, 70, 75, 85, 55, pkmnType.Psychic, pkmnType.Typeless, abilityList.Frisk, itemList.None, 39.7, false, status.none));
        pokeList.put("Gothitelle", new SpeciesClass(70, 70, 55, 95, 95, 110, 65, pkmnType.Psychic, pkmnType.Typeless, abilityList.Frisk, itemList.None, 97.0, false, status.none));
        pokeList.put("Solosis", new SpeciesClass(45, 45, 30, 40, 105, 50, 20, pkmnType.Psychic, pkmnType.Typeless, abilityList.Overcoat, itemList.None, 2.2, false, status.none));
        pokeList.put("Duosion", new SpeciesClass(65, 65, 40, 50, 125, 60, 30, pkmnType.Psychic, pkmnType.Typeless, abilityList.Overcoat, itemList.None, 17.6, false, status.none));
        pokeList.put("Reuniclus", new SpeciesClass(110, 110, 65, 75, 125, 85, 30, pkmnType.Psychic, pkmnType.Typeless, abilityList.Overcoat, itemList.None, 44.3, false, status.none));
        pokeList.put("Ducklett", new SpeciesClass(62, 62, 44, 50, 44, 50, 55, pkmnType.Water, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 12.1, false, status.none));
        pokeList.put("Swanna", new SpeciesClass(75, 75, 87, 63, 87, 63, 98, pkmnType.Water, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 53.4, false, status.none));
        pokeList.put("Vanillite", new SpeciesClass(36, 36, 50, 50, 65, 60, 44, pkmnType.Ice, pkmnType.Typeless, abilityList.Ice_Body, itemList.None, 12.6, false, status.none));
        pokeList.put("Vanillish", new SpeciesClass(51, 51, 65, 65, 80, 75, 59, pkmnType.Ice, pkmnType.Typeless, abilityList.Ice_Body, itemList.None, 90.4, false, status.none));
        pokeList.put("Vanilluxe", new SpeciesClass(71, 71, 95, 85, 110, 95, 79, pkmnType.Ice, pkmnType.Typeless, abilityList.Ice_Body, itemList.None, 126.8, false, status.none));
        pokeList.put("Deerling", new SpeciesClass(60, 60, 60, 50, 40, 50, 75, pkmnType.Normal, pkmnType.Grass, abilityList.Chlorophyll, itemList.None, 43.0, false, status.none));
        pokeList.put("Sawsbuck", new SpeciesClass(80, 80, 100, 70, 60, 70, 95, pkmnType.Normal, pkmnType.Grass, abilityList.Chlorophyll, itemList.None, 203.9, false, status.none));
        pokeList.put("Emolga", new SpeciesClass(55, 55, 75, 60, 75, 60, 103, pkmnType.Electric, pkmnType.Flying, abilityList.Static, itemList.None, 11.0, false, status.none));
        pokeList.put("Karrablast", new SpeciesClass(50, 50, 75, 45, 40, 45, 60, pkmnType.Bug, pkmnType.Typeless, abilityList.Swarm, itemList.None, 13.0, false, status.none));
        pokeList.put("Escavalier", new SpeciesClass(70, 70, 135, 105, 60, 105, 20, pkmnType.Bug, pkmnType.Steel, abilityList.Swarm, itemList.None, 72.8, false, status.none));
        pokeList.put("Foongus", new SpeciesClass(69, 69, 55, 45, 55, 55, 15, pkmnType.Grass, pkmnType.Poison, abilityList.Effect_Spore, itemList.None, 2.2, false, status.none));
        pokeList.put("Amoonguss", new SpeciesClass(114, 114, 85, 70, 85, 80, 30, pkmnType.Grass, pkmnType.Poison, abilityList.Effect_Spore, itemList.None, 23.1, false, status.none));
        pokeList.put("Frillish", new SpeciesClass(55, 55, 40, 50, 65, 85, 40, pkmnType.Water, pkmnType.Ghost, abilityList.Water_Absorb, itemList.None, 72.8, false, status.none));
        pokeList.put("Jellicent", new SpeciesClass(100, 100, 60, 70, 85, 105, 60, pkmnType.Water, pkmnType.Ghost, abilityList.Water_Absorb, itemList.None, 297.6, false, status.none));
        pokeList.put("Alomomola", new SpeciesClass(165, 165, 75, 80, 40, 45, 65, pkmnType.Water, pkmnType.Typeless, abilityList.Healer, itemList.None, 69.7, false, status.none));
        pokeList.put("Joltik", new SpeciesClass(50, 50, 47, 50, 57, 50, 65, pkmnType.Bug, pkmnType.Electric, abilityList.Compound_Eyes, itemList.None, 1.3, false, status.none));
        pokeList.put("Galvantula", new SpeciesClass(70, 70, 77, 60, 97, 60, 108, pkmnType.Bug, pkmnType.Electric, abilityList.Compound_Eyes, itemList.None, 31.5, false, status.none));
        pokeList.put("Ferroseed", new SpeciesClass(44, 44, 50, 91, 24, 86, 10, pkmnType.Grass, pkmnType.Steel, abilityList.Iron_Barbs, itemList.None, 41.4, false, status.none));
        pokeList.put("Ferrothorn", new SpeciesClass(74, 74, 94, 131, 54, 116, 20, pkmnType.Grass, pkmnType.Steel, abilityList.Iron_Barbs, itemList.None, 242.5, false, status.none));
        pokeList.put("Klink", new SpeciesClass(40, 40, 55, 70, 45, 60, 30, pkmnType.Steel, pkmnType.Typeless, abilityList.Plus, itemList.None, 46.3, false, status.none));
        pokeList.put("Klang", new SpeciesClass(60, 60, 80, 95, 70, 85, 50, pkmnType.Steel, pkmnType.Typeless, abilityList.Plus, itemList.None, 112.4, false, status.none));
        pokeList.put("Klinklang", new SpeciesClass(60, 60, 100, 115, 70, 85, 90, pkmnType.Steel, pkmnType.Typeless, abilityList.Plus, itemList.None, 178.6, false, status.none));
        pokeList.put("Tynamo", new SpeciesClass(35, 35, 55, 40, 45, 40, 60, pkmnType.Electric, pkmnType.Typeless, abilityList.Levitate, itemList.None, 0.7, false, status.none));
        pokeList.put("Eelektrik", new SpeciesClass(65, 65, 85, 70, 75, 70, 40, pkmnType.Electric, pkmnType.Typeless, abilityList.Levitate, itemList.None, 48.5, false, status.none));
        pokeList.put("Eelektross", new SpeciesClass(85, 85, 115, 80, 105, 80, 50, pkmnType.Electric, pkmnType.Typeless, abilityList.Levitate, itemList.None, 177.5, false, status.none));
        pokeList.put("Elgyem", new SpeciesClass(55, 55, 55, 55, 85, 55, 30, pkmnType.Psychic, pkmnType.Typeless, abilityList.Telepathy, itemList.None, 19.8, false, status.none));
        pokeList.put("Beheeyem", new SpeciesClass(75, 75, 75, 75, 125, 95, 40, pkmnType.Psychic, pkmnType.Typeless, abilityList.Telepathy, itemList.None, 76.1, false, status.none));
        pokeList.put("Litwick", new SpeciesClass(50, 50, 30, 55, 65, 55, 20, pkmnType.Ghost, pkmnType.Fire, abilityList.Flash_Fire, itemList.None, 6.8, false, status.none));
        pokeList.put("Lampent", new SpeciesClass(60, 60, 40, 60, 95, 60, 55, pkmnType.Ghost, pkmnType.Fire, abilityList.Flash_Fire, itemList.None, 28.7, false, status.none));
        pokeList.put("Chandelure", new SpeciesClass(60, 60, 55, 90, 145, 90, 80, pkmnType.Ghost, pkmnType.Fire, abilityList.Flash_Fire, itemList.None, 75.6, false, status.none));
        pokeList.put("Axew", new SpeciesClass(46, 46, 87, 60, 30, 40, 57, pkmnType.Dragon, pkmnType.Typeless, abilityList.Rivalry, itemList.None, 39.7, false, status.none));
        pokeList.put("Fraxure", new SpeciesClass(66, 66, 117, 70, 40, 50, 67, pkmnType.Dragon, pkmnType.Typeless, abilityList.Rivalry, itemList.None, 79.4, false, status.none));
        pokeList.put("Haxorus", new SpeciesClass(76, 76, 147, 90, 60, 70, 97, pkmnType.Dragon, pkmnType.Typeless, abilityList.Rivalry, itemList.None, 232.6, false, status.none));
        pokeList.put("Cubchoo", new SpeciesClass(55, 55, 70, 40, 60, 40, 40, pkmnType.Ice, pkmnType.Typeless, abilityList.Snow_Cloak, itemList.None, 18.7, false, status.none));
        pokeList.put("Beartic", new SpeciesClass(95, 95, 130, 80, 70, 80, 50, pkmnType.Ice, pkmnType.Typeless, abilityList.Snow_Cloak, itemList.None, 573.2, false, status.none));
        pokeList.put("Cryogonal", new SpeciesClass(80, 80, 50, 50, 95, 135, 105, pkmnType.Ice, pkmnType.Typeless, abilityList.Levitate, itemList.None, 326.3, false, status.none));
        pokeList.put("Shelmet", new SpeciesClass(50, 50, 40, 85, 40, 65, 25, pkmnType.Bug, pkmnType.Typeless, abilityList.Hydration, itemList.None, 17.0, false, status.none));
        pokeList.put("Accelgor", new SpeciesClass(80, 80, 70, 40, 100, 60, 145, pkmnType.Bug, pkmnType.Typeless, abilityList.Hydration, itemList.None, 55.8, false, status.none));
        pokeList.put("Stunfisk", new SpeciesClass(109, 109, 66, 84, 81, 99, 32, pkmnType.Ground, pkmnType.Electric, abilityList.Static, itemList.None, 24.3, false, status.none));
        pokeList.put("Stunfisk-G", new SpeciesClass(109, 109, 81, 99, 66, 84, 32, pkmnType.Ground, pkmnType.Steel, abilityList.Mimicry, itemList.None, 45.2, false, status.none));
        pokeList.put("Mienfoo", new SpeciesClass(45, 45, 85, 50, 55, 50, 65, pkmnType.Fighting, pkmnType.Typeless, abilityList.Inner_Focus, itemList.None, 44.1, false, status.none));
        pokeList.put("Mienshao", new SpeciesClass(65, 65, 125, 60, 95, 60, 105, pkmnType.Fighting, pkmnType.Typeless, abilityList.Inner_Focus, itemList.None, 78.3, false, status.none));
        pokeList.put("Druddigon", new SpeciesClass(77, 77, 120, 90, 60, 90, 48, pkmnType.Dragon, pkmnType.Typeless, abilityList.Rough_Skin, itemList.None, 306.4, false, status.none));
        pokeList.put("Golett", new SpeciesClass(59, 59, 74, 50, 35, 50, 35, pkmnType.Ground, pkmnType.Ghost, abilityList.Iron_Fist, itemList.None, 202.8, false, status.none));
        pokeList.put("Golurk", new SpeciesClass(89, 89, 124, 80, 55, 80, 55, pkmnType.Ground, pkmnType.Ghost, abilityList.Iron_Fist, itemList.None, 727.5, false, status.none));
        pokeList.put("Pawniard", new SpeciesClass(45, 45, 85, 70, 40, 40, 60, pkmnType.Dark, pkmnType.Steel, abilityList.Defiant, itemList.None, 22.5, false, status.none));
        pokeList.put("Bisharp", new SpeciesClass(65, 65, 125, 100, 60, 70, 70, pkmnType.Dark, pkmnType.Steel, abilityList.Defiant, itemList.None, 154.3, false, status.none));
        pokeList.put("Bouffalant", new SpeciesClass(95, 95, 110, 95, 40, 95, 55, pkmnType.Normal, pkmnType.Typeless, abilityList.Reckless, itemList.None, 208.6, false, status.none));
        pokeList.put("Rufflet", new SpeciesClass(70, 70, 83, 50, 37, 50, 60, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 23.1, false, status.none));
        pokeList.put("Braviary", new SpeciesClass(100, 100, 123, 75, 57, 75, 80, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 90.4, false, status.none));
        pokeList.put("Braviary-H", new SpeciesClass(110, 110, 83, 70, 112, 70, 65, pkmnType.Psychic, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 95.7, false, status.none));
        pokeList.put("Vullaby", new SpeciesClass(70, 70, 55, 75, 45, 65, 60, pkmnType.Dark, pkmnType.Flying, abilityList.Big_Pecks, itemList.None, 19.8, false, status.none));
        pokeList.put("Mandibuzz", new SpeciesClass(110, 110, 65, 105, 55, 95, 80, pkmnType.Dark, pkmnType.Flying, abilityList.Big_Pecks, itemList.None, 87.1, false, status.none));
        pokeList.put("Heatmor", new SpeciesClass(85, 85, 97, 66, 105, 66, 65, pkmnType.Fire, pkmnType.Typeless, abilityList.Gluttony, itemList.None, 127.9, false, status.none));
        pokeList.put("Durant", new SpeciesClass(58, 58, 109, 112, 48, 48, 109, pkmnType.Bug, pkmnType.Steel, abilityList.Swarm, itemList.None, 72.8, false, status.none));
        pokeList.put("Deino", new SpeciesClass(52, 52, 65, 50, 45, 50, 38, pkmnType.Dark, pkmnType.Dragon, abilityList.Hustle, itemList.None, 38.1, false, status.none));
        pokeList.put("Zweilous", new SpeciesClass(72, 72, 85, 70, 65, 70, 58, pkmnType.Dark, pkmnType.Dragon, abilityList.Hustle, itemList.None, 110.2, false, status.none));
        pokeList.put("Hydreigon", new SpeciesClass(92, 92, 105, 90, 125, 90, 98, pkmnType.Dark, pkmnType.Dragon, abilityList.Levitate, itemList.None, 352.7, false, status.none));
        pokeList.put("Larvesta", new SpeciesClass(55, 55, 85, 55, 50, 55, 60, pkmnType.Bug, pkmnType.Fire, abilityList.Flame_Body, itemList.None, 63.5, false, status.none));
        pokeList.put("Volcarona", new SpeciesClass(85, 85, 60, 65, 135, 105, 100, pkmnType.Bug, pkmnType.Fire, abilityList.Flame_Body, itemList.None, 101.4, false, status.none));
        pokeList.put("Cobalion", new SpeciesClass(91, 91, 90, 129, 90, 72, 108, pkmnType.Steel, pkmnType.Fighting, abilityList.Justified, itemList.None, 551.2, false, status.none));
        pokeList.put("Terrakion", new SpeciesClass(91, 91, 129, 90, 72, 90, 108, pkmnType.Rock, pkmnType.Fighting, abilityList.Justified, itemList.None, 573.2, false, status.none));
        pokeList.put("Virizion", new SpeciesClass(91, 91, 90, 72, 90, 129, 108, pkmnType.Grass, pkmnType.Fighting, abilityList.Justified, itemList.None, 440.9, false, status.none));
        pokeList.put("Tornadus-I", new SpeciesClass(79, 79, 115, 70, 125, 80, 111, pkmnType.Flying, pkmnType.Typeless, abilityList.Prankster, itemList.None, 138.9, false, status.none));
        pokeList.put("Tornadus-T", new SpeciesClass(79, 79, 100, 80, 110, 90, 121, pkmnType.Flying, pkmnType.Typeless, abilityList.Regenerator, itemList.None, 138.9, false, status.none));
        pokeList.put("Thundurus-I", new SpeciesClass(79, 79, 115, 70, 125, 80, 111, pkmnType.Electric, pkmnType.Flying, abilityList.Prankster, itemList.None, 134.5, false, status.none));
        pokeList.put("Thundurus-T", new SpeciesClass(79, 79, 105, 70, 145, 80, 101, pkmnType.Electric, pkmnType.Flying, abilityList.Volt_Absorb, itemList.None, 134.5, false, status.none));
        pokeList.put("Reshiram", new SpeciesClass(100, 100, 120, 100, 150, 120, 90, pkmnType.Dragon, pkmnType.Fire, abilityList.Turboblaze, itemList.None, 727.5, false, status.none));
        pokeList.put("Zekrom", new SpeciesClass(100, 100, 150, 120, 120, 100, 90, pkmnType.Dragon, pkmnType.Electric, abilityList.Teravolt, itemList.None, 760.6, false, status.none));
        pokeList.put("Landorus-I", new SpeciesClass(89, 89, 125, 90, 115, 80, 101, pkmnType.Ground, pkmnType.Flying, abilityList.Sand_Force, itemList.None, 149.9, false, status.none));
        pokeList.put("Landorus-T", new SpeciesClass(89, 89, 145, 90, 105, 80, 91, pkmnType.Ground, pkmnType.Flying, abilityList.Intimidate, itemList.None, 149.9, false, status.none));
        pokeList.put("Kyurem", new SpeciesClass(125, 125, 130, 90, 130, 90, 95, pkmnType.Dragon, pkmnType.Ice, abilityList.Pressure, itemList.None, 716.5, false, status.none));
        pokeList.put("Kyurem-W", new SpeciesClass(125, 125, 120, 90, 170, 100, 95, pkmnType.Dragon, pkmnType.Ice, abilityList.Turboblaze, itemList.None, 716.5, false, status.none));
        pokeList.put("Kyurem-B", new SpeciesClass(125, 125, 170, 100, 120, 90, 95, pkmnType.Dragon, pkmnType.Ice, abilityList.Teravolt, itemList.None, 716.5, false, status.none));
        pokeList.put("Keldeo-O", new SpeciesClass(91, 91, 72, 90, 129, 90, 108, pkmnType.Water, pkmnType.Fighting, abilityList.Justified, itemList.None, 106.9, false, status.none));
        pokeList.put("Keldeo-R", new SpeciesClass(91, 91, 72, 90, 129, 90, 108, pkmnType.Water, pkmnType.Fighting, abilityList.Justified, itemList.None, 106.9, false, status.none));
        pokeList.put("Meloetta-A", new SpeciesClass(100, 100, 77, 77, 128, 128, 90, pkmnType.Normal, pkmnType.Psychic, abilityList.Serene_Grace, itemList.None, 14.3, false, status.none));
        pokeList.put("Meloetta-P", new SpeciesClass(100, 100, 128, 90, 77, 77, 128, pkmnType.Normal, pkmnType.Fighting, abilityList.Serene_Grace, itemList.None, 14.3, false, status.none));
        pokeList.put("Genesect", new SpeciesClass(71, 71, 120, 95, 120, 95, 99, pkmnType.Bug, pkmnType.Steel, abilityList.Download, itemList.None, 181.9, false, status.none));
        pokeList.put("Chespin", new SpeciesClass(56, 56, 61, 65, 48, 45, 38, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 19.8, false, status.none));
        pokeList.put("Quilladin", new SpeciesClass(61, 61, 78, 95, 56, 58, 57, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 63.9, false, status.none));
        pokeList.put("Chesnaught", new SpeciesClass(88, 88, 107, 122, 74, 75, 64, pkmnType.Grass, pkmnType.Fighting, abilityList.Overgrow, itemList.None, 198.4, false, status.none));
        pokeList.put("Fennekin", new SpeciesClass(40, 40, 45, 40, 62, 60, 60, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 20.7, false, status.none));
        pokeList.put("Braixen", new SpeciesClass(59, 59, 59, 58, 90, 70, 73, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 32.0, false, status.none));
        pokeList.put("Delphox", new SpeciesClass(75, 75, 69, 72, 114, 100, 104, pkmnType.Fire, pkmnType.Psychic, abilityList.Blaze, itemList.None, 86.0, false, status.none));
        pokeList.put("Froakie", new SpeciesClass(41, 41, 56, 40, 62, 44, 71, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 15.4, false, status.none));
        pokeList.put("Frogadier", new SpeciesClass(54, 54, 63, 52, 83, 56, 97, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 24.0, false, status.none));
        pokeList.put("Greninja", new SpeciesClass(72, 72, 95, 67, 103, 71, 122, pkmnType.Water, pkmnType.Dark, abilityList.Torrent, itemList.None, 88.2, false, status.none));
        pokeList.put("Greninja-A", new SpeciesClass(72, 72, 145, 67, 153, 71, 132, pkmnType.Water, pkmnType.Dark, abilityList.Battle_Bond, itemList.None, 88.2, false, status.none));
        pokeList.put("Bunnelby", new SpeciesClass(38, 38, 36, 38, 32, 36, 57, pkmnType.Normal, pkmnType.Typeless, abilityList.Pickup, itemList.None, 11.0, false, status.none));
        pokeList.put("Diggersby", new SpeciesClass(85, 85, 56, 77, 50, 77, 78, pkmnType.Normal, pkmnType.Ground, abilityList.Pickup, itemList.None, 93.5, false, status.none));
        pokeList.put("Fletchling", new SpeciesClass(45, 45, 50, 43, 40, 38, 62, pkmnType.Normal, pkmnType.Flying, abilityList.Big_Pecks, itemList.None, 3.7, false, status.none));
        pokeList.put("Fletchinder", new SpeciesClass(62, 62, 73, 55, 56, 52, 84, pkmnType.Fire, pkmnType.Flying, abilityList.Flame_Body, itemList.None, 35.3, false, status.none));
        pokeList.put("Talonflame", new SpeciesClass(78, 78, 81, 71, 74, 69, 126, pkmnType.Fire, pkmnType.Flying, abilityList.Flame_Body, itemList.None, 54.0, false, status.none));
        pokeList.put("Scatterbug", new SpeciesClass(38, 38, 35, 40, 27, 25, 35, pkmnType.Bug, pkmnType.Typeless, abilityList.Shield_Dust, itemList.None, 5.5, false, status.none));
        pokeList.put("Spewpa", new SpeciesClass(45, 45, 22, 60, 27, 30, 29, pkmnType.Bug, pkmnType.Typeless, abilityList.Shed_Skin, itemList.None, 18.5, false, status.none));
        pokeList.put("Vivillon", new SpeciesClass(80, 80, 52, 50, 90, 50, 89, pkmnType.Bug, pkmnType.Flying, abilityList.Shield_Dust, itemList.None, 37.5, false, status.none));
        pokeList.put("Litleo", new SpeciesClass(62, 62, 50, 58, 73, 54, 72, pkmnType.Fire, pkmnType.Normal, abilityList.Rivalry, itemList.None, 29.8, false, status.none));
        pokeList.put("Pyroar", new SpeciesClass(86, 86, 68, 72, 109, 66, 106, pkmnType.Fire, pkmnType.Normal, abilityList.Rivalry, itemList.None, 179.7, false, status.none));
        pokeList.put("Flabébé", new SpeciesClass(44, 44, 38, 39, 61, 79, 42, pkmnType.Fairy, pkmnType.Typeless, abilityList.Flower_Veil, itemList.None, 0.2, false, status.none));
        pokeList.put("Floette", new SpeciesClass(54, 54, 45, 47, 75, 98, 52, pkmnType.Fairy, pkmnType.Typeless, abilityList.Flower_Veil, itemList.None, 2.0, false, status.none));
        pokeList.put("Florges", new SpeciesClass(78, 78, 65, 68, 112, 154, 75, pkmnType.Fairy, pkmnType.Typeless, abilityList.Flower_Veil, itemList.None, 22.0, false, status.none));
        pokeList.put("Skiddo", new SpeciesClass(66, 66, 65, 48, 62, 57, 52, pkmnType.Grass, pkmnType.Typeless, abilityList.Sap_Sipper, itemList.None, 68.3, false, status.none));
        pokeList.put("Gogoat", new SpeciesClass(123, 123, 100, 62, 97, 81, 68, pkmnType.Grass, pkmnType.Typeless, abilityList.Sap_Sipper, itemList.None, 200.6, false, status.none));
        pokeList.put("Pancham", new SpeciesClass(67, 67, 82, 62, 46, 48, 43, pkmnType.Fighting, pkmnType.Typeless, abilityList.Iron_Fist, itemList.None, 17.6, false, status.none));
        pokeList.put("Pangoro", new SpeciesClass(95, 95, 124, 78, 69, 71, 58, pkmnType.Fighting, pkmnType.Dark, abilityList.Iron_Fist, itemList.None, 299.8, false, status.none));
        pokeList.put("Furfrou", new SpeciesClass(75, 75, 80, 60, 65, 90, 102, pkmnType.Normal, pkmnType.Typeless, abilityList.Fur_Coat, itemList.None, 61.7, false, status.none));
        pokeList.put("Espurr", new SpeciesClass(62, 62, 48, 54, 63, 60, 68, pkmnType.Psychic, pkmnType.Typeless, abilityList.Keen_Eye, itemList.None, 7.7, false, status.none));
        pokeList.put("Meowstic-M", new SpeciesClass(74, 74, 48, 76, 83, 81, 104, pkmnType.Psychic, pkmnType.Typeless, abilityList.Keen_Eye, itemList.None, 18.7, false, status.none));
        pokeList.put("Meowstic-F", new SpeciesClass(74, 74, 48, 76, 83, 81, 104, pkmnType.Psychic, pkmnType.Typeless, abilityList.Keen_Eye, itemList.None, 18.7, false, status.none));
        pokeList.put("Honedge", new SpeciesClass(45, 45, 80, 100, 35, 37, 28, pkmnType.Steel, pkmnType.Ghost, abilityList.No_Guard, itemList.None, 4.4, false, status.none));
        pokeList.put("Doublade", new SpeciesClass(59, 59, 110, 150, 45, 49, 35, pkmnType.Steel, pkmnType.Ghost, abilityList.No_Guard, itemList.None, 9.9, false, status.none));
        pokeList.put("Aegislash-S", new SpeciesClass(60, 60, 50, 140, 50, 140, 60, pkmnType.Steel, pkmnType.Ghost, abilityList.Stance_Change, itemList.None, 116.8, false, status.none));
        pokeList.put("Aegislash-B", new SpeciesClass(60, 60, 140, 50, 140, 50, 60, pkmnType.Steel, pkmnType.Ghost, abilityList.Stance_Change, itemList.None, 116.8, false, status.none));
        pokeList.put("Spritzee", new SpeciesClass(78, 78, 52, 60, 63, 65, 23, pkmnType.Fairy, pkmnType.Typeless, abilityList.Healer, itemList.None, 1.1, false, status.none));
        pokeList.put("Aromatisse", new SpeciesClass(101, 101, 72, 72, 99, 89, 29, pkmnType.Fairy, pkmnType.Typeless, abilityList.Healer, itemList.None, 34.2, false, status.none));
        pokeList.put("Swirlix", new SpeciesClass(62, 62, 48, 66, 59, 57, 49, pkmnType.Fairy, pkmnType.Typeless, abilityList.Sweet_Veil, itemList.None, 7.7, false, status.none));
        pokeList.put("Slurpuff", new SpeciesClass(82, 82, 80, 86, 85, 75, 72, pkmnType.Fairy, pkmnType.Typeless, abilityList.Sweet_Veil, itemList.None, 11.0, false, status.none));
        pokeList.put("Inkay", new SpeciesClass(53, 53, 54, 53, 37, 46, 45, pkmnType.Dark, pkmnType.Psychic, abilityList.Contrary, itemList.None, 7.7, false, status.none));
        pokeList.put("Malamar", new SpeciesClass(86, 86, 92, 88, 68, 75, 73, pkmnType.Dark, pkmnType.Psychic, abilityList.Contrary, itemList.None, 103.6, false, status.none));
        pokeList.put("Binacle", new SpeciesClass(42, 42, 52, 67, 39, 56, 50, pkmnType.Rock, pkmnType.Water, abilityList.Tough_Claws, itemList.None, 68.3, false, status.none));
        pokeList.put("Barbaracle", new SpeciesClass(72, 72, 105, 115, 54, 86, 68, pkmnType.Rock, pkmnType.Water, abilityList.Tough_Claws, itemList.None, 211.6, false, status.none));
        pokeList.put("Skrelp", new SpeciesClass(50, 50, 60, 60, 60, 60, 30, pkmnType.Poison, pkmnType.Water, abilityList.Poison_Point, itemList.None, 16.1, false, status.none));
        pokeList.put("Dragalge", new SpeciesClass(65, 65, 75, 90, 97, 123, 44, pkmnType.Poison, pkmnType.Dragon, abilityList.Poison_Point, itemList.None, 179.7, false, status.none));
        pokeList.put("Clauncher", new SpeciesClass(50, 50, 53, 62, 58, 63, 44, pkmnType.Water, pkmnType.Typeless, abilityList.Mega_Launcher, itemList.None, 18.3, false, status.none));
        pokeList.put("Clawitzer", new SpeciesClass(71, 71, 73, 88, 120, 89, 59, pkmnType.Water, pkmnType.Typeless, abilityList.Mega_Launcher, itemList.None, 77.8, false, status.none));
        pokeList.put("Helioptile", new SpeciesClass(44, 44, 38, 33, 61, 43, 70, pkmnType.Electric, pkmnType.Normal, abilityList.Dry_Skin, itemList.None, 13.2, false, status.none));
        pokeList.put("Heliolisk", new SpeciesClass(62, 62, 55, 52, 109, 94, 109, pkmnType.Electric, pkmnType.Normal, abilityList.Dry_Skin, itemList.None, 46.3, false, status.none));
        pokeList.put("Tyrunt", new SpeciesClass(58, 58, 89, 77, 45, 45, 48, pkmnType.Rock, pkmnType.Dragon, abilityList.Strong_Jaw, itemList.None, 57.3, false, status.none));
        pokeList.put("Tyrantrum", new SpeciesClass(82, 82, 121, 119, 69, 59, 71, pkmnType.Rock, pkmnType.Dragon, abilityList.Strong_Jaw, itemList.None, 595.2, false, status.none));
        pokeList.put("Amaura", new SpeciesClass(77, 77, 59, 50, 67, 63, 46, pkmnType.Rock, pkmnType.Ice, abilityList.Refrigerate, itemList.None, 55.6, false, status.none));
        pokeList.put("Aurorus", new SpeciesClass(123, 123, 77, 72, 99, 92, 58, pkmnType.Rock, pkmnType.Ice, abilityList.Refrigerate, itemList.None, 496.0, false, status.none));
        pokeList.put("Sylveon", new SpeciesClass(95, 95, 65, 65, 110, 130, 60, pkmnType.Fairy, pkmnType.Typeless, abilityList.Cute_Charm, itemList.None, 51.8, false, status.none));
        pokeList.put("Hawlucha", new SpeciesClass(78, 78, 92, 75, 74, 63, 118, pkmnType.Fighting, pkmnType.Flying, abilityList.Limber, itemList.None, 47.4, false, status.none));
        pokeList.put("Dedenne", new SpeciesClass(67, 67, 58, 57, 81, 67, 101, pkmnType.Electric, pkmnType.Fairy, abilityList.Cheek_Pouch, itemList.None, 4.9, false, status.none));
        pokeList.put("Carbink", new SpeciesClass(50, 50, 50, 150, 50, 150, 50, pkmnType.Rock, pkmnType.Fairy, abilityList.Clear_Body, itemList.None, 12.6, false, status.none));
        pokeList.put("Goomy", new SpeciesClass(45, 45, 50, 35, 55, 75, 40, pkmnType.Dragon, pkmnType.Typeless, abilityList.Sap_Sipper, itemList.None, 6.2, false, status.none));
        pokeList.put("Sliggoo", new SpeciesClass(68, 68, 75, 53, 83, 113, 60, pkmnType.Dragon, pkmnType.Typeless, abilityList.Sap_Sipper, itemList.None, 38.6, false, status.none));
        pokeList.put("Sliggoo-H", new SpeciesClass(58, 58, 75, 83, 83, 113, 40, pkmnType.Steel, pkmnType.Dragon, abilityList.Sap_Sipper, itemList.None, 151.0, false, status.none));
        pokeList.put("Goodra", new SpeciesClass(90, 90, 100, 70, 110, 150, 80, pkmnType.Dragon, pkmnType.Typeless, abilityList.Sap_Sipper, itemList.None, 331.8, false, status.none));
        pokeList.put("Goodra-H", new SpeciesClass(80, 80, 100, 100, 110, 150, 60, pkmnType.Steel, pkmnType.Dragon, abilityList.Sap_Sipper, itemList.None, 736.6, false, status.none));
        pokeList.put("Klefki", new SpeciesClass(57, 57, 80, 91, 80, 87, 75, pkmnType.Steel, pkmnType.Fairy, abilityList.Prankster, itemList.None, 6.6, false, status.none));
        pokeList.put("Phantump", new SpeciesClass(43, 43, 70, 48, 50, 60, 38, pkmnType.Ghost, pkmnType.Grass, abilityList.Natural_Cure, itemList.None, 15.4, false, status.none));
        pokeList.put("Trevenant", new SpeciesClass(85, 85, 110, 76, 65, 82, 56, pkmnType.Ghost, pkmnType.Grass, abilityList.Natural_Cure, itemList.None, 156.5, false, status.none));
        pokeList.put("Pumpkaboo-A", new SpeciesClass(49, 49, 66, 70, 44, 55, 51, pkmnType.Ghost, pkmnType.Grass, abilityList.Pickup, itemList.None, 11.0, false, status.none));
        pokeList.put("Pumpkaboo-S", new SpeciesClass(44, 44, 66, 70, 44, 55, 56, pkmnType.Ghost, pkmnType.Grass, abilityList.Pickup, itemList.None, 7.7, false, status.none));
        pokeList.put("Pumpkaboo-L", new SpeciesClass(54, 54, 66, 70, 44, 55, 46, pkmnType.Ghost, pkmnType.Grass, abilityList.Pickup, itemList.None, 16.5, false, status.none));
        pokeList.put("Pumpkaboo-S", new SpeciesClass(59, 59, 66, 70, 44, 55, 41, pkmnType.Ghost, pkmnType.Grass, abilityList.Pickup, itemList.None, 33.1, false, status.none));
        pokeList.put("Gourgeist-A", new SpeciesClass(65, 65, 90, 122, 58, 75, 84, pkmnType.Ghost, pkmnType.Grass, abilityList.Pickup, itemList.None, 27.6, false, status.none));
        pokeList.put("Gourgeist-S", new SpeciesClass(55, 55, 85, 122, 58, 75, 99, pkmnType.Ghost, pkmnType.Grass, abilityList.Pickup, itemList.None, 20.9, false, status.none));
        pokeList.put("Gourgeist-L", new SpeciesClass(75, 75, 95, 122, 58, 75, 69, pkmnType.Ghost, pkmnType.Grass, abilityList.Pickup, itemList.None, 30.9, false, status.none));
        pokeList.put("Gourgeist-S", new SpeciesClass(85, 85, 100, 122, 58, 75, 54, pkmnType.Ghost, pkmnType.Grass, abilityList.Pickup, itemList.None, 86.0, false, status.none));
        pokeList.put("Bergmite", new SpeciesClass(55, 55, 69, 85, 32, 35, 28, pkmnType.Ice, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 219.4, false, status.none));
        pokeList.put("Avalugg", new SpeciesClass(95, 95, 117, 184, 44, 46, 28, pkmnType.Ice, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 1113.3, false, status.none));
        pokeList.put("Avalugg-H", new SpeciesClass(95, 95, 127, 184, 34, 36, 38, pkmnType.Ice, pkmnType.Rock, abilityList.Strong_Jaw, itemList.None, 578.5, false, status.none));
        pokeList.put("Noibat", new SpeciesClass(40, 40, 30, 35, 45, 40, 55, pkmnType.Flying, pkmnType.Dragon, abilityList.Frisk, itemList.None, 17.6, false, status.none));
        pokeList.put("Noivern", new SpeciesClass(85, 85, 70, 80, 97, 80, 123, pkmnType.Flying, pkmnType.Dragon, abilityList.Frisk, itemList.None, 187.4, false, status.none));
        pokeList.put("Xerneas", new SpeciesClass(126, 126, 131, 95, 131, 98, 99, pkmnType.Fairy, pkmnType.Typeless, abilityList.Fairy_Aura, itemList.None, 474.0, false, status.none));
        pokeList.put("Yveltal", new SpeciesClass(126, 126, 131, 95, 131, 98, 99, pkmnType.Dark, pkmnType.Flying, abilityList.Dark_Aura, itemList.None, 447.5, false, status.none));
        pokeList.put("Zygarde-5", new SpeciesClass(108, 108, 100, 121, 81, 95, 95, pkmnType.Dragon, pkmnType.Ground, abilityList.Aura_Break, itemList.None, 672.4, false, status.none));
        pokeList.put("Zygarde-1", new SpeciesClass(54, 54, 100, 71, 61, 85, 115, pkmnType.Dragon, pkmnType.Ground, abilityList.Aura_Break, itemList.None, 73.9, false, status.none));
        pokeList.put("Zygarde-C", new SpeciesClass(216, 216, 100, 121, 91, 95, 85, pkmnType.Dragon, pkmnType.Ground, abilityList.Power_Construct, itemList.None, 1344.8, false, status.none));
        pokeList.put("Diancie", new SpeciesClass(50, 50, 100, 150, 100, 150, 50, pkmnType.Rock, pkmnType.Fairy, abilityList.Clear_Body, itemList.None, 19.4, false, status.none));
        pokeList.put("Diancie-M", new SpeciesClass(50, 50, 160, 110, 160, 110, 110, pkmnType.Rock, pkmnType.Fairy, abilityList.Magic_Bounce, itemList.None, 61.3, false, status.none));
        pokeList.put("Hoopa-C", new SpeciesClass(80, 80, 110, 60, 150, 130, 70, pkmnType.Psychic, pkmnType.Ghost, abilityList.Magician, itemList.None, 19.8, false, status.none));
        pokeList.put("Hoopa-U", new SpeciesClass(80, 80, 160, 60, 170, 130, 80, pkmnType.Psychic, pkmnType.Dark, abilityList.Magician, itemList.None, 1080.3, false, status.none));
        pokeList.put("Volcanion", new SpeciesClass(80, 80, 110, 120, 130, 90, 70, pkmnType.Fire, pkmnType.Water, abilityList.Water_Absorb, itemList.None, 429.9, false, status.none));
        pokeList.put("Rowlet", new SpeciesClass(68, 68, 55, 55, 50, 50, 42, pkmnType.Grass, pkmnType.Flying, abilityList.Overgrow, itemList.None, 3.3, false, status.none));
        pokeList.put("Dartrix", new SpeciesClass(78, 78, 75, 75, 70, 70, 52, pkmnType.Grass, pkmnType.Flying, abilityList.Overgrow, itemList.None, 35.3, false, status.none));
        pokeList.put("Decidueye", new SpeciesClass(78, 78, 107, 75, 100, 100, 70, pkmnType.Grass, pkmnType.Ghost, abilityList.Overgrow, itemList.None, 80.7, false, status.none));
        pokeList.put("Decidueye-H", new SpeciesClass(88, 88, 112, 80, 95, 95, 60, pkmnType.Grass, pkmnType.Fighting, abilityList.Overgrow, itemList.None, 81.6, false, status.none));
        pokeList.put("Litten", new SpeciesClass(45, 45, 65, 40, 60, 40, 70, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 9.5, false, status.none));
        pokeList.put("Torracat", new SpeciesClass(65, 65, 85, 50, 80, 50, 90, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 55.1, false, status.none));
        pokeList.put("Incineroar", new SpeciesClass(95, 95, 115, 90, 80, 90, 60, pkmnType.Fire, pkmnType.Dark, abilityList.Blaze, itemList.None, 183.0, false, status.none));
        pokeList.put("Popplio", new SpeciesClass(50, 50, 54, 54, 66, 56, 40, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 16.5, false, status.none));
        pokeList.put("Brionne", new SpeciesClass(60, 60, 69, 69, 91, 81, 50, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 38.6, false, status.none));
        pokeList.put("Primarina", new SpeciesClass(80, 80, 74, 74, 126, 116, 60, pkmnType.Water, pkmnType.Fairy, abilityList.Torrent, itemList.None, 97.0, false, status.none));
        pokeList.put("Pikipek", new SpeciesClass(35, 35, 75, 30, 30, 30, 65, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 2.6, false, status.none));
        pokeList.put("Trumbeak", new SpeciesClass(55, 55, 85, 50, 40, 50, 75, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 32.6, false, status.none));
        pokeList.put("Toucannon", new SpeciesClass(80, 80, 120, 75, 75, 75, 60, pkmnType.Normal, pkmnType.Flying, abilityList.Keen_Eye, itemList.None, 57.3, false, status.none));
        pokeList.put("Yungoos", new SpeciesClass(48, 48, 70, 30, 30, 30, 45, pkmnType.Normal, pkmnType.Typeless, abilityList.Stakeout, itemList.None, 13.2, false, status.none));
        pokeList.put("Gumshoos", new SpeciesClass(88, 88, 110, 60, 55, 60, 45, pkmnType.Normal, pkmnType.Typeless, abilityList.Stakeout, itemList.None, 31.3, false, status.none));
        pokeList.put("Grubbin", new SpeciesClass(47, 47, 62, 45, 55, 45, 46, pkmnType.Bug, pkmnType.Typeless, abilityList.Swarm, itemList.None, 9.7, false, status.none));
        pokeList.put("Charjabug", new SpeciesClass(57, 57, 82, 95, 55, 75, 36, pkmnType.Bug, pkmnType.Electric, abilityList.Battery, itemList.None, 23.1, false, status.none));
        pokeList.put("Vikavolt", new SpeciesClass(77, 77, 70, 90, 145, 75, 43, pkmnType.Bug, pkmnType.Electric, abilityList.Levitate, itemList.None, 99.2, false, status.none));
        pokeList.put("Crabrawler", new SpeciesClass(47, 47, 82, 57, 42, 47, 63, pkmnType.Fighting, pkmnType.Typeless, abilityList.Hyper_Cutter, itemList.None, 15.4, false, status.none));
        pokeList.put("Crabominable", new SpeciesClass(97, 97, 132, 77, 62, 67, 43, pkmnType.Fighting, pkmnType.Ice, abilityList.Hyper_Cutter, itemList.None, 396.8, false, status.none));
        pokeList.put("Oricorio-B", new SpeciesClass(75, 75, 70, 70, 98, 70, 93, pkmnType.Fire, pkmnType.Flying, abilityList.Dancer, itemList.None, 7.5, false, status.none));
        pokeList.put("Oricorio-P", new SpeciesClass(75, 75, 70, 70, 98, 70, 93, pkmnType.Electric, pkmnType.Flying, abilityList.Dancer, itemList.None, 7.5, false, status.none));
        pokeList.put("Oricorio-P", new SpeciesClass(75, 75, 70, 70, 98, 70, 93, pkmnType.Psychic, pkmnType.Flying, abilityList.Dancer, itemList.None, 7.5, false, status.none));
        pokeList.put("Oricorio-S", new SpeciesClass(75, 75, 70, 70, 98, 70, 93, pkmnType.Ghost, pkmnType.Flying, abilityList.Dancer, itemList.None, 7.5, false, status.none));
        pokeList.put("Cutiefly", new SpeciesClass(40, 40, 45, 40, 55, 40, 84, pkmnType.Bug, pkmnType.Fairy, abilityList.Honey_Gather, itemList.None, 0.4, false, status.none));
        pokeList.put("Ribombee", new SpeciesClass(60, 60, 55, 60, 95, 70, 124, pkmnType.Bug, pkmnType.Fairy, abilityList.Honey_Gather, itemList.None, 1.1, false, status.none));
        pokeList.put("Rockruff", new SpeciesClass(45, 45, 65, 40, 30, 40, 60, pkmnType.Rock, pkmnType.Typeless, abilityList.Keen_Eye, itemList.None, 20.3, false, status.none));
        pokeList.put("Rockruff-O", new SpeciesClass(45, 45, 65, 40, 30, 40, 60, pkmnType.Rock, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 20.3, false, status.none));
        pokeList.put("Lycanroc-M", new SpeciesClass(75, 75, 115, 65, 55, 65, 112, pkmnType.Rock, pkmnType.Typeless, abilityList.Keen_Eye, itemList.None, 55.1, false, status.none));
        pokeList.put("Lycanroc-M", new SpeciesClass(85, 85, 115, 75, 55, 75, 82, pkmnType.Rock, pkmnType.Typeless, abilityList.Keen_Eye, itemList.None, 55.1, false, status.none));
        pokeList.put("Lycanroc-D", new SpeciesClass(75, 75, 117, 65, 55, 65, 110, pkmnType.Rock, pkmnType.Typeless, abilityList.Tough_Claws, itemList.None, 55.1, false, status.none));
        pokeList.put("Wishiwashi-S", new SpeciesClass(45, 45, 20, 20, 25, 25, 40, pkmnType.Water, pkmnType.Typeless, abilityList.Schooling, itemList.None, 0.7, false, status.none));
        pokeList.put("Wishiwashi-S", new SpeciesClass(45, 45, 140, 130, 140, 135, 30, pkmnType.Water, pkmnType.Typeless, abilityList.Schooling, itemList.None, 173.3, false, status.none));
        pokeList.put("Mareanie", new SpeciesClass(50, 50, 53, 62, 43, 52, 45, pkmnType.Poison, pkmnType.Water, abilityList.Merciless, itemList.None, 17.6, false, status.none));
        pokeList.put("Toxapex", new SpeciesClass(50, 50, 63, 152, 53, 142, 35, pkmnType.Poison, pkmnType.Water, abilityList.Merciless, itemList.None, 32.0, false, status.none));
        pokeList.put("Mudbray", new SpeciesClass(70, 70, 100, 70, 45, 55, 45, pkmnType.Ground, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 242.5, false, status.none));
        pokeList.put("Mudsdale", new SpeciesClass(100, 100, 125, 100, 55, 85, 35, pkmnType.Ground, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 2028.3, false, status.none));
        pokeList.put("Dewpider", new SpeciesClass(38, 38, 40, 52, 40, 72, 27, pkmnType.Water, pkmnType.Bug, abilityList.Water_Bubble, itemList.None, 8.8, false, status.none));
        pokeList.put("Araquanid", new SpeciesClass(68, 68, 70, 92, 50, 132, 42, pkmnType.Water, pkmnType.Bug, abilityList.Water_Bubble, itemList.None, 180.8, false, status.none));
        pokeList.put("Fomantis", new SpeciesClass(40, 40, 55, 35, 50, 35, 35, pkmnType.Grass, pkmnType.Typeless, abilityList.Leaf_Guard, itemList.None, 3.3, false, status.none));
        pokeList.put("Lurantis", new SpeciesClass(70, 70, 105, 90, 80, 90, 45, pkmnType.Grass, pkmnType.Typeless, abilityList.Leaf_Guard, itemList.None, 40.8, false, status.none));
        pokeList.put("Morelull", new SpeciesClass(40, 40, 35, 55, 65, 75, 15, pkmnType.Grass, pkmnType.Fairy, abilityList.Illuminate, itemList.None, 3.3, false, status.none));
        pokeList.put("Shiinotic", new SpeciesClass(60, 60, 45, 80, 90, 100, 30, pkmnType.Grass, pkmnType.Fairy, abilityList.Illuminate, itemList.None, 25.4, false, status.none));
        pokeList.put("Salandit", new SpeciesClass(48, 48, 44, 40, 71, 40, 77, pkmnType.Poison, pkmnType.Fire, abilityList.Corrosion, itemList.None, 10.6, false, status.none));
        pokeList.put("Salazzle", new SpeciesClass(68, 68, 64, 60, 111, 60, 117, pkmnType.Poison, pkmnType.Fire, abilityList.Corrosion, itemList.None, 48.9, false, status.none));
        pokeList.put("Stufful", new SpeciesClass(70, 70, 75, 50, 45, 50, 50, pkmnType.Normal, pkmnType.Fighting, abilityList.Fluffy, itemList.None, 15.0, false, status.none));
        pokeList.put("Bewear", new SpeciesClass(120, 120, 125, 80, 55, 60, 60, pkmnType.Normal, pkmnType.Fighting, abilityList.Fluffy, itemList.None, 297.6, false, status.none));
        pokeList.put("Bounsweet", new SpeciesClass(42, 42, 30, 38, 30, 38, 32, pkmnType.Grass, pkmnType.Typeless, abilityList.Leaf_Guard, itemList.None, 7.1, false, status.none));
        pokeList.put("Steenee", new SpeciesClass(52, 52, 40, 48, 40, 48, 62, pkmnType.Grass, pkmnType.Typeless, abilityList.Leaf_Guard, itemList.None, 18.1, false, status.none));
        pokeList.put("Tsareena", new SpeciesClass(72, 72, 120, 98, 50, 98, 72, pkmnType.Grass, pkmnType.Typeless, abilityList.Leaf_Guard, itemList.None, 47.2, false, status.none));
        pokeList.put("Comfey", new SpeciesClass(51, 51, 52, 90, 82, 110, 100, pkmnType.Fairy, pkmnType.Typeless, abilityList.Flower_Veil, itemList.None, 0.7, false, status.none));
        pokeList.put("Oranguru", new SpeciesClass(90, 90, 60, 80, 90, 110, 60, pkmnType.Normal, pkmnType.Psychic, abilityList.Inner_Focus, itemList.None, 167.6, false, status.none));
        pokeList.put("Passimian", new SpeciesClass(100, 100, 120, 90, 40, 60, 80, pkmnType.Fighting, pkmnType.Typeless, abilityList.Receiver, itemList.None, 182.5, false, status.none));
        pokeList.put("Wimpod", new SpeciesClass(25, 25, 35, 40, 20, 30, 80, pkmnType.Bug, pkmnType.Water, abilityList.Wimp_Out, itemList.None, 26.5, false, status.none));
        pokeList.put("Golisopod", new SpeciesClass(75, 75, 125, 140, 60, 90, 40, pkmnType.Bug, pkmnType.Water, abilityList.Emergency_Exit, itemList.None, 238.1, false, status.none));
        pokeList.put("Sandygast", new SpeciesClass(55, 55, 55, 80, 70, 45, 15, pkmnType.Ghost, pkmnType.Ground, abilityList.Water_Compaction, itemList.None, 154.3, false, status.none));
        pokeList.put("Palossand", new SpeciesClass(85, 85, 75, 110, 100, 75, 35, pkmnType.Ghost, pkmnType.Ground, abilityList.Water_Compaction, itemList.None, 551.2, false, status.none));
        pokeList.put("Pyukumuku", new SpeciesClass(55, 55, 60, 130, 30, 130, 5, pkmnType.Water, pkmnType.Typeless, abilityList.Innards_Out, itemList.None, 2.6, false, status.none));
        pokeList.put("Type: Null", new SpeciesClass(95, 95, 95, 95, 95, 95, 59, pkmnType.Normal, pkmnType.Typeless, abilityList.Battle_Armor, itemList.None, 265.7, false, status.none));
        pokeList.put("Silvally", new SpeciesClass(95, 95, 95, 95, 95, 95, 95, pkmnType.Normal, pkmnType.Typeless, abilityList.RKS_System, itemList.None, 221.6, false, status.none));
        pokeList.put("Minior-M", new SpeciesClass(60, 60, 60, 100, 60, 100, 60, pkmnType.Rock, pkmnType.Flying, abilityList.Shields_Down, itemList.None, 88.2, false, status.none));
        pokeList.put("Minior-C", new SpeciesClass(60, 60, 100, 60, 100, 60, 120, pkmnType.Rock, pkmnType.Flying, abilityList.Shields_Down, itemList.None, 0.7, false, status.none));
        pokeList.put("Komala", new SpeciesClass(65, 65, 115, 65, 75, 95, 65, pkmnType.Normal, pkmnType.Typeless, abilityList.Comatose, itemList.None, 43.9, false, status.none));
        pokeList.put("Turtonator", new SpeciesClass(60, 60, 78, 135, 91, 85, 36, pkmnType.Fire, pkmnType.Dragon, abilityList.Shell_Armor, itemList.None, 467.4, false, status.none));
        pokeList.put("Togedemaru", new SpeciesClass(65, 65, 98, 63, 40, 73, 96, pkmnType.Electric, pkmnType.Steel, abilityList.Iron_Barbs, itemList.None, 7.3, false, status.none));
        pokeList.put("Mimikyu", new SpeciesClass(55, 55, 90, 80, 50, 105, 96, pkmnType.Ghost, pkmnType.Fairy, abilityList.Disguise, itemList.None, 1.5, false, status.none));
        pokeList.put("Bruxish", new SpeciesClass(68, 68, 105, 70, 70, 70, 92, pkmnType.Water, pkmnType.Psychic, abilityList.Dazzling, itemList.None, 41.9, false, status.none));
        pokeList.put("Drampa", new SpeciesClass(78, 78, 60, 85, 135, 91, 36, pkmnType.Normal, pkmnType.Dragon, abilityList.Berserk, itemList.None, 407.9, false, status.none));
        pokeList.put("Dhelmise", new SpeciesClass(70, 70, 131, 100, 86, 90, 40, pkmnType.Ghost, pkmnType.Grass, abilityList.Steelworker, itemList.None, 463.0, false, status.none));
        pokeList.put("Jangmo-o", new SpeciesClass(45, 45, 55, 65, 45, 45, 45, pkmnType.Dragon, pkmnType.Typeless, abilityList.Bulletproof, itemList.None, 65.5, false, status.none));
        pokeList.put("Hakamo-o", new SpeciesClass(55, 55, 75, 90, 65, 70, 65, pkmnType.Dragon, pkmnType.Fighting, abilityList.Bulletproof, itemList.None, 103.6, false, status.none));
        pokeList.put("Kommo-o", new SpeciesClass(75, 75, 110, 125, 100, 105, 85, pkmnType.Dragon, pkmnType.Fighting, abilityList.Bulletproof, itemList.None, 172.4, false, status.none));
        pokeList.put("Tapu Koko", new SpeciesClass(70, 70, 115, 85, 95, 75, 130, pkmnType.Electric, pkmnType.Fairy, abilityList.Electric_Surge, itemList.None, 45.2, false, status.none));
        pokeList.put("Tapu Lele", new SpeciesClass(70, 70, 85, 75, 130, 115, 95, pkmnType.Psychic, pkmnType.Fairy, abilityList.Psychic_Surge, itemList.None, 41.0, false, status.none));
        pokeList.put("Tapu Bulu", new SpeciesClass(70, 70, 130, 115, 85, 95, 75, pkmnType.Grass, pkmnType.Fairy, abilityList.Grassy_Surge, itemList.None, 100.3, false, status.none));
        pokeList.put("Tapu Fini", new SpeciesClass(70, 70, 75, 115, 95, 130, 85, pkmnType.Water, pkmnType.Fairy, abilityList.Misty_Surge, itemList.None, 46.7, false, status.none));
        pokeList.put("Cosmog", new SpeciesClass(43, 43, 29, 31, 29, 31, 37, pkmnType.Psychic, pkmnType.Typeless, abilityList.Unaware, itemList.None, 0.2, false, status.none));
        pokeList.put("Cosmoem", new SpeciesClass(43, 43, 29, 131, 29, 131, 37, pkmnType.Psychic, pkmnType.Typeless, abilityList.Sturdy, itemList.None, 2204.4, false, status.none));
        pokeList.put("Solgaleo", new SpeciesClass(137, 137, 137, 107, 113, 89, 97, pkmnType.Psychic, pkmnType.Steel, abilityList.Full_Metal_Body, itemList.None, 507.1, false, status.none));
        pokeList.put("Lunala", new SpeciesClass(137, 137, 113, 89, 137, 107, 97, pkmnType.Psychic, pkmnType.Ghost, abilityList.Shadow_Shield, itemList.None, 264.6, false, status.none));
        pokeList.put("Nihilego", new SpeciesClass(109, 109, 53, 47, 127, 131, 103, pkmnType.Rock, pkmnType.Poison, abilityList.Beast_Boost, itemList.None, 122.4, false, status.none));
        pokeList.put("Buzzwole", new SpeciesClass(107, 107, 139, 139, 53, 53, 79, pkmnType.Bug, pkmnType.Fighting, abilityList.Beast_Boost, itemList.None, 735.5, false, status.none));
        pokeList.put("Pheromosa", new SpeciesClass(71, 71, 137, 37, 137, 37, 151, pkmnType.Bug, pkmnType.Fighting, abilityList.Beast_Boost, itemList.None, 55.1, false, status.none));
        pokeList.put("Xurkitree", new SpeciesClass(83, 83, 89, 71, 173, 71, 83, pkmnType.Electric, pkmnType.Typeless, abilityList.Beast_Boost, itemList.None, 220.5, false, status.none));
        pokeList.put("Celesteela", new SpeciesClass(97, 97, 101, 103, 107, 101, 61, pkmnType.Steel, pkmnType.Flying, abilityList.Beast_Boost, itemList.None, 2204.4, false, status.none));
        pokeList.put("Kartana", new SpeciesClass(59, 59, 181, 131, 59, 31, 109, pkmnType.Grass, pkmnType.Steel, abilityList.Beast_Boost, itemList.None, 0.2, false, status.none));
        pokeList.put("Guzzlord", new SpeciesClass(223, 223, 101, 53, 97, 53, 43, pkmnType.Dark, pkmnType.Dragon, abilityList.Beast_Boost, itemList.None, 1957.7, false, status.none));
        pokeList.put("Necrozma", new SpeciesClass(97, 97, 107, 101, 127, 89, 79, pkmnType.Psychic, pkmnType.Typeless, abilityList.Prism_Armor, itemList.None, 507.1, false, status.none));
        pokeList.put("Necrozma-D", new SpeciesClass(97, 97, 157, 127, 113, 109, 77, pkmnType.Psychic, pkmnType.Steel, abilityList.Prism_Armor, itemList.None, 1014.1, false, status.none));
        pokeList.put("Necrozma-D", new SpeciesClass(97, 97, 113, 109, 157, 127, 77, pkmnType.Psychic, pkmnType.Ghost, abilityList.Prism_Armor, itemList.None, 771.6, false, status.none));
        pokeList.put("Necrozma-U", new SpeciesClass(97, 97, 167, 97, 167, 97, 129, pkmnType.Psychic, pkmnType.Dragon, abilityList.Neuroforce, itemList.None, 507.1, false, status.none));
        pokeList.put("Magearna", new SpeciesClass(80, 80, 95, 115, 130, 115, 65, pkmnType.Steel, pkmnType.Fairy, abilityList.SoulHeart, itemList.None, 177.5, false, status.none));
        pokeList.put("Marshadow", new SpeciesClass(90, 90, 125, 80, 90, 90, 125, pkmnType.Fighting, pkmnType.Ghost, abilityList.Technician, itemList.None, 48.9, false, status.none));
        pokeList.put("Poipole", new SpeciesClass(67, 67, 73, 67, 73, 67, 73, pkmnType.Poison, pkmnType.Typeless, abilityList.Beast_Boost, itemList.None, 4.0, false, status.none));
        pokeList.put("Naganadel", new SpeciesClass(73, 73, 73, 73, 127, 73, 121, pkmnType.Poison, pkmnType.Dragon, abilityList.Beast_Boost, itemList.None, 330.7, false, status.none));
        pokeList.put("Stakataka", new SpeciesClass(61, 61, 131, 211, 53, 101, 13, pkmnType.Rock, pkmnType.Steel, abilityList.Beast_Boost, itemList.None, 1807.8, false, status.none));
        pokeList.put("Blacephalon", new SpeciesClass(53, 53, 127, 53, 151, 79, 107, pkmnType.Fire, pkmnType.Ghost, abilityList.Beast_Boost, itemList.None, 28.7, false, status.none));
        pokeList.put("Zeraora", new SpeciesClass(88, 88, 112, 75, 102, 80, 143, pkmnType.Electric, pkmnType.Typeless, abilityList.Volt_Absorb, itemList.None, 98.1, false, status.none));
        pokeList.put("Meltan", new SpeciesClass(46, 46, 65, 65, 55, 35, 34, pkmnType.Steel, pkmnType.Typeless, abilityList.Magnet_Pull, itemList.None, 17.6, false, status.none));
        pokeList.put("Melmetal", new SpeciesClass(135, 135, 143, 143, 80, 65, 34, pkmnType.Steel, pkmnType.Typeless, abilityList.Iron_Fist, itemList.None, 1763.7, false, status.none));
        pokeList.put("Grookey", new SpeciesClass(50, 50, 65, 50, 40, 40, 65, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 11.0, false, status.none));
        pokeList.put("Thwackey", new SpeciesClass(70, 70, 85, 70, 55, 60, 80, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 30.9, false, status.none));
        pokeList.put("Rillaboom", new SpeciesClass(100, 100, 125, 90, 60, 70, 85, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 198.4, false, status.none));
        pokeList.put("Scorbunny", new SpeciesClass(50, 50, 71, 40, 40, 40, 69, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 9.9, false, status.none));
        pokeList.put("Raboot", new SpeciesClass(65, 65, 86, 60, 55, 60, 94, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 19.8, false, status.none));
        pokeList.put("Cinderace", new SpeciesClass(80, 80, 116, 75, 65, 75, 119, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 72.8, false, status.none));
        pokeList.put("Sobble", new SpeciesClass(50, 50, 40, 40, 70, 40, 70, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 8.8, false, status.none));
        pokeList.put("Drizzile", new SpeciesClass(65, 65, 60, 55, 95, 55, 90, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 25.4, false, status.none));
        pokeList.put("Inteleon", new SpeciesClass(70, 70, 85, 65, 125, 65, 120, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 99.6, false, status.none));
        pokeList.put("Skwovet", new SpeciesClass(70, 70, 55, 55, 35, 35, 25, pkmnType.Normal, pkmnType.Typeless, abilityList.Cheek_Pouch, itemList.None, 5.5, false, status.none));
        pokeList.put("Greedent", new SpeciesClass(120, 120, 95, 95, 55, 75, 20, pkmnType.Normal, pkmnType.Typeless, abilityList.Cheek_Pouch, itemList.None, 13.2, false, status.none));
        pokeList.put("Rookidee", new SpeciesClass(38, 38, 47, 35, 33, 35, 57, pkmnType.Flying, pkmnType.Typeless, abilityList.Keen_Eye, itemList.None, 4.0, false, status.none));
        pokeList.put("Corvisquire", new SpeciesClass(68, 68, 67, 55, 43, 55, 77, pkmnType.Flying, pkmnType.Typeless, abilityList.Keen_Eye, itemList.None, 35.3, false, status.none));
        pokeList.put("Corviknight", new SpeciesClass(98, 98, 87, 105, 53, 85, 67, pkmnType.Flying, pkmnType.Steel, abilityList.Pressure, itemList.None, 165.3, false, status.none));
        pokeList.put("Blipbug", new SpeciesClass(25, 25, 20, 20, 25, 45, 45, pkmnType.Bug, pkmnType.Typeless, abilityList.Swarm, itemList.None, 17.6, false, status.none));
        pokeList.put("Dottler", new SpeciesClass(50, 50, 35, 80, 50, 90, 30, pkmnType.Bug, pkmnType.Psychic, abilityList.Swarm, itemList.None, 43.0, false, status.none));
        pokeList.put("Orbeetle", new SpeciesClass(60, 60, 45, 110, 80, 120, 90, pkmnType.Bug, pkmnType.Psychic, abilityList.Swarm, itemList.None, 89.9, false, status.none));
        pokeList.put("Nickit", new SpeciesClass(40, 40, 28, 28, 47, 52, 50, pkmnType.Dark, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 19.6, false, status.none));
        pokeList.put("Thievul", new SpeciesClass(70, 70, 58, 58, 87, 92, 90, pkmnType.Dark, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 43.9, false, status.none));
        pokeList.put("Gossifleur", new SpeciesClass(40, 40, 40, 60, 40, 60, 10, pkmnType.Grass, pkmnType.Typeless, abilityList.Cotton_Down, itemList.None, 4.9, false, status.none));
        pokeList.put("Eldegoss", new SpeciesClass(60, 60, 50, 90, 80, 120, 60, pkmnType.Grass, pkmnType.Typeless, abilityList.Cotton_Down, itemList.None, 5.5, false, status.none));
        pokeList.put("Wooloo", new SpeciesClass(42, 42, 40, 55, 40, 45, 48, pkmnType.Normal, pkmnType.Typeless, abilityList.Fluffy, itemList.None, 13.2, false, status.none));
        pokeList.put("Dubwool", new SpeciesClass(72, 72, 80, 100, 60, 90, 88, pkmnType.Normal, pkmnType.Typeless, abilityList.Fluffy, itemList.None, 94.8, false, status.none));
        pokeList.put("Chewtle", new SpeciesClass(50, 50, 64, 50, 38, 38, 44, pkmnType.Water, pkmnType.Typeless, abilityList.Strong_Jaw, itemList.None, 18.7, false, status.none));
        pokeList.put("Drednaw", new SpeciesClass(90, 90, 115, 90, 48, 68, 74, pkmnType.Water, pkmnType.Rock, abilityList.Strong_Jaw, itemList.None, 254.6, false, status.none));
        pokeList.put("Yamper", new SpeciesClass(59, 59, 45, 50, 40, 50, 26, pkmnType.Electric, pkmnType.Typeless, abilityList.Ball_Fetch, itemList.None, 29.8, false, status.none));
        pokeList.put("Boltund", new SpeciesClass(69, 69, 90, 60, 90, 60, 121, pkmnType.Electric, pkmnType.Typeless, abilityList.Strong_Jaw, itemList.None, 75.0, false, status.none));
        pokeList.put("Rolycoly", new SpeciesClass(30, 30, 40, 50, 40, 50, 30, pkmnType.Rock, pkmnType.Typeless, abilityList.Steam_Engine, itemList.None, 26.5, false, status.none));
        pokeList.put("Carkol", new SpeciesClass(80, 80, 60, 90, 60, 70, 50, pkmnType.Rock, pkmnType.Fire, abilityList.Steam_Engine, itemList.None, 172.0, false, status.none));
        pokeList.put("Coalossal", new SpeciesClass(110, 110, 80, 120, 80, 90, 30, pkmnType.Rock, pkmnType.Fire, abilityList.Steam_Engine, itemList.None, 684.5, false, status.none));
        pokeList.put("Applin", new SpeciesClass(40, 40, 40, 80, 40, 40, 20, pkmnType.Grass, pkmnType.Dragon, abilityList.Ripen, itemList.None, 1.1, false, status.none));
        pokeList.put("Flapple", new SpeciesClass(70, 70, 110, 80, 95, 60, 70, pkmnType.Grass, pkmnType.Dragon, abilityList.Ripen, itemList.None, 2.2, false, status.none));
        pokeList.put("Appletun", new SpeciesClass(110, 110, 85, 80, 100, 80, 30, pkmnType.Grass, pkmnType.Dragon, abilityList.Ripen, itemList.None, 28.7, false, status.none));
        pokeList.put("Silicobra", new SpeciesClass(52, 52, 57, 75, 35, 50, 46, pkmnType.Ground, pkmnType.Typeless, abilityList.Sand_Spit, itemList.None, 16.8, false, status.none));
        pokeList.put("Sandaconda", new SpeciesClass(72, 72, 107, 125, 65, 70, 71, pkmnType.Ground, pkmnType.Typeless, abilityList.Sand_Spit, itemList.None, 144.4, false, status.none));
        pokeList.put("Cramorant", new SpeciesClass(70, 70, 85, 55, 85, 95, 85, pkmnType.Flying, pkmnType.Water, abilityList.Gulp_Missile, itemList.None, 39.7, false, status.none));
        pokeList.put("Arrokuda", new SpeciesClass(41, 41, 63, 40, 40, 30, 66, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 2.2, false, status.none));
        pokeList.put("Barraskewda", new SpeciesClass(61, 61, 123, 60, 60, 50, 136, pkmnType.Water, pkmnType.Typeless, abilityList.Swift_Swim, itemList.None, 66.1, false, status.none));
        pokeList.put("Toxel", new SpeciesClass(40, 40, 38, 35, 54, 35, 40, pkmnType.Electric, pkmnType.Poison, abilityList.Rattled, itemList.None, 24.3, false, status.none));
        pokeList.put("Toxtricity-A", new SpeciesClass(75, 75, 98, 70, 114, 70, 75, pkmnType.Electric, pkmnType.Poison, abilityList.Punk_Rock, itemList.None, 88.2, false, status.none));
        pokeList.put("Toxtricity-L", new SpeciesClass(75, 75, 98, 70, 114, 70, 75, pkmnType.Electric, pkmnType.Poison, abilityList.Punk_Rock, itemList.None, 88.2, false, status.none));
        pokeList.put("Sizzlipede", new SpeciesClass(50, 50, 65, 45, 50, 50, 45, pkmnType.Fire, pkmnType.Bug, abilityList.Flash_Fire, itemList.None, 2.2, false, status.none));
        pokeList.put("Centiskorch", new SpeciesClass(100, 100, 115, 65, 90, 90, 65, pkmnType.Fire, pkmnType.Bug, abilityList.Flash_Fire, itemList.None, 264.6, false, status.none));
        pokeList.put("Clobbopus", new SpeciesClass(50, 50, 68, 60, 50, 50, 32, pkmnType.Fighting, pkmnType.Typeless, abilityList.Limber, itemList.None, 8.8, false, status.none));
        pokeList.put("Grapploct", new SpeciesClass(80, 80, 118, 90, 70, 80, 42, pkmnType.Fighting, pkmnType.Typeless, abilityList.Limber, itemList.None, 86.0, false, status.none));
        pokeList.put("Sinistea", new SpeciesClass(40, 40, 45, 45, 74, 54, 50, pkmnType.Ghost, pkmnType.Typeless, abilityList.Weak_Armor, itemList.None, 0.4, false, status.none));
        pokeList.put("Polteageist", new SpeciesClass(60, 60, 65, 65, 134, 114, 70, pkmnType.Ghost, pkmnType.Typeless, abilityList.Weak_Armor, itemList.None, 0.9, false, status.none));
        pokeList.put("Hatenna", new SpeciesClass(42, 42, 30, 45, 56, 53, 39, pkmnType.Psychic, pkmnType.Typeless, abilityList.Healer, itemList.None, 7.5, false, status.none));
        pokeList.put("Hattrem", new SpeciesClass(57, 57, 40, 65, 86, 73, 49, pkmnType.Psychic, pkmnType.Typeless, abilityList.Healer, itemList.None, 10.6, false, status.none));
        pokeList.put("Hatterene", new SpeciesClass(57, 57, 90, 95, 136, 103, 29, pkmnType.Psychic, pkmnType.Fairy, abilityList.Healer, itemList.None, 11.2, false, status.none));
        pokeList.put("Impidimp", new SpeciesClass(45, 45, 45, 30, 55, 40, 50, pkmnType.Dark, pkmnType.Fairy, abilityList.Prankster, itemList.None, 12.1, false, status.none));
        pokeList.put("Morgrem", new SpeciesClass(65, 65, 60, 45, 75, 55, 70, pkmnType.Dark, pkmnType.Fairy, abilityList.Prankster, itemList.None, 27.6, false, status.none));
        pokeList.put("Grimmsnarl", new SpeciesClass(95, 95, 120, 65, 95, 75, 60, pkmnType.Dark, pkmnType.Fairy, abilityList.Prankster, itemList.None, 134.5, false, status.none));
        pokeList.put("Obstagoon", new SpeciesClass(93, 93, 90, 101, 60, 81, 95, pkmnType.Dark, pkmnType.Normal, abilityList.Reckless, itemList.None, 101.4, false, status.none));
        pokeList.put("Perrserker", new SpeciesClass(70, 70, 110, 100, 50, 60, 50, pkmnType.Steel, pkmnType.Typeless, abilityList.Battle_Armor, itemList.None, 61.7, false, status.none));
        pokeList.put("Cursola", new SpeciesClass(60, 60, 95, 50, 145, 130, 30, pkmnType.Ghost, pkmnType.Typeless, abilityList.Weak_Armor, itemList.None, 0.9, false, status.none));
        pokeList.put("Sirfetch'd", new SpeciesClass(62, 62, 135, 95, 68, 82, 65, pkmnType.Fighting, pkmnType.Typeless, abilityList.Steadfast, itemList.None, 257.9, false, status.none));
        pokeList.put("Mr. Rime", new SpeciesClass(80, 80, 85, 75, 110, 100, 70, pkmnType.Ice, pkmnType.Psychic, abilityList.Tangled_Feet, itemList.None, 128.3, false, status.none));
        pokeList.put("Runerigus", new SpeciesClass(58, 58, 95, 145, 50, 105, 30, pkmnType.Ground, pkmnType.Ghost, abilityList.Wandering_Spirit, itemList.None, 146.8, false, status.none));
        pokeList.put("Milcery", new SpeciesClass(45, 45, 40, 40, 50, 61, 34, pkmnType.Fairy, pkmnType.Typeless, abilityList.Sweet_Veil, itemList.None, 0.7, false, status.none));
        pokeList.put("Alcremie", new SpeciesClass(65, 65, 60, 75, 110, 121, 64, pkmnType.Fairy, pkmnType.Typeless, abilityList.Sweet_Veil, itemList.None, 1.1, false, status.none));
        pokeList.put("Falinks", new SpeciesClass(65, 65, 100, 100, 70, 60, 75, pkmnType.Fighting, pkmnType.Typeless, abilityList.Battle_Armor, itemList.None, 136.7, false, status.none));
        pokeList.put("Pincurchin", new SpeciesClass(48, 48, 101, 95, 91, 85, 15, pkmnType.Electric, pkmnType.Typeless, abilityList.Lightning_Rod, itemList.None, 2.2, false, status.none));
        pokeList.put("Snom", new SpeciesClass(30, 30, 25, 35, 45, 30, 20, pkmnType.Ice, pkmnType.Bug, abilityList.Shield_Dust, itemList.None, 8.4, false, status.none));
        pokeList.put("Frosmoth", new SpeciesClass(70, 70, 65, 60, 125, 90, 65, pkmnType.Ice, pkmnType.Bug, abilityList.Shield_Dust, itemList.None, 92.6, false, status.none));
        pokeList.put("Stonjourner", new SpeciesClass(100, 100, 125, 135, 20, 20, 70, pkmnType.Rock, pkmnType.Typeless, abilityList.Power_Spot, itemList.None, 1146.4, false, status.none));
        pokeList.put("Eiscue-I", new SpeciesClass(75, 75, 80, 110, 65, 90, 50, pkmnType.Ice, pkmnType.Typeless, abilityList.Ice_Face, itemList.None, 196.2, false, status.none));
        pokeList.put("Eiscue-N", new SpeciesClass(75, 75, 80, 70, 65, 50, 130, pkmnType.Ice, pkmnType.Typeless, abilityList.Ice_Face, itemList.None, 196.2, false, status.none));
        pokeList.put("Indeedee-M", new SpeciesClass(60, 60, 65, 55, 105, 95, 95, pkmnType.Psychic, pkmnType.Normal, abilityList.Inner_Focus, itemList.None, 61.7, false, status.none));
        pokeList.put("Indeedee-F", new SpeciesClass(70, 70, 55, 65, 95, 105, 85, pkmnType.Psychic, pkmnType.Normal, abilityList.Own_Tempo, itemList.None, 61.7, false, status.none));
        pokeList.put("Morpeko-F", new SpeciesClass(58, 58, 95, 58, 70, 58, 97, pkmnType.Electric, pkmnType.Dark, abilityList.Hunger_Switch, itemList.None, 6.6, false, status.none));
        pokeList.put("Morpeko-H", new SpeciesClass(58, 58, 95, 58, 70, 58, 97, pkmnType.Electric, pkmnType.Dark, abilityList.Hunger_Switch, itemList.None, 6.6, false, status.none));
        pokeList.put("Cufant", new SpeciesClass(72, 72, 80, 49, 40, 49, 40, pkmnType.Steel, pkmnType.Typeless, abilityList.Sheer_Force, itemList.None, 220.5, false, status.none));
        pokeList.put("Copperajah", new SpeciesClass(122, 122, 130, 69, 80, 69, 30, pkmnType.Steel, pkmnType.Typeless, abilityList.Sheer_Force, itemList.None, 1433.0, false, status.none));
        pokeList.put("Dracozolt", new SpeciesClass(90, 90, 100, 90, 80, 70, 75, pkmnType.Electric, pkmnType.Dragon, abilityList.Volt_Absorb, itemList.None, 418.9, false, status.none));
        pokeList.put("Arctozolt", new SpeciesClass(90, 90, 100, 90, 90, 80, 55, pkmnType.Electric, pkmnType.Ice, abilityList.Volt_Absorb, itemList.None, 330.7, false, status.none));
        pokeList.put("Dracovish", new SpeciesClass(90, 90, 90, 100, 70, 80, 75, pkmnType.Water, pkmnType.Dragon, abilityList.Water_Absorb, itemList.None, 474.0, false, status.none));
        pokeList.put("Arctovish", new SpeciesClass(90, 90, 90, 100, 80, 90, 55, pkmnType.Water, pkmnType.Ice, abilityList.Water_Absorb, itemList.None, 385.8, false, status.none));
        pokeList.put("Duraludon", new SpeciesClass(70, 70, 95, 115, 120, 50, 85, pkmnType.Steel, pkmnType.Dragon, abilityList.Light_Metal, itemList.None, 88.2, false, status.none));
        pokeList.put("Dreepy", new SpeciesClass(28, 28, 60, 30, 40, 30, 82, pkmnType.Dragon, pkmnType.Ghost, abilityList.Clear_Body, itemList.None, 4.4, false, status.none));
        pokeList.put("Drakloak", new SpeciesClass(68, 68, 80, 50, 60, 50, 102, pkmnType.Dragon, pkmnType.Ghost, abilityList.Clear_Body, itemList.None, 24.3, false, status.none));
        pokeList.put("Dragapult", new SpeciesClass(88, 88, 120, 75, 100, 75, 142, pkmnType.Dragon, pkmnType.Ghost, abilityList.Clear_Body, itemList.None, 110.2, false, status.none));
        pokeList.put("Zacian-H", new SpeciesClass(92, 92, 120, 115, 80, 115, 138, pkmnType.Fairy, pkmnType.Typeless, abilityList.Intrepid_Sword, itemList.None, 242.5, false, status.none));
        pokeList.put("Zacian-C", new SpeciesClass(92, 92, 150, 115, 80, 115, 148, pkmnType.Fairy, pkmnType.Steel, abilityList.Intrepid_Sword, itemList.None, 782.6, false, status.none));
        pokeList.put("Zamazenta-H", new SpeciesClass(92, 92, 120, 115, 80, 115, 138, pkmnType.Fighting, pkmnType.Typeless, abilityList.Dauntless_Shield, itemList.None, 463.0, false, status.none));
        pokeList.put("Zamazenta-C", new SpeciesClass(92, 92, 120, 140, 80, 140, 128, pkmnType.Fighting, pkmnType.Steel, abilityList.Dauntless_Shield, itemList.None, 1730.6, false, status.none));
        pokeList.put("Eternatus", new SpeciesClass(140, 140, 85, 95, 145, 95, 130, pkmnType.Poison, pkmnType.Dragon, abilityList.Pressure, itemList.None, 2094.4, false, status.none));
        pokeList.put("Eternatus-E", new SpeciesClass(255, 255, 115, 250, 125, 250, 130, pkmnType.Poison, pkmnType.Dragon, abilityList.Pressure, itemList.None, 9999.9, false, status.none));
        pokeList.put("Kubfu", new SpeciesClass(60, 60, 90, 60, 53, 50, 72, pkmnType.Fighting, pkmnType.Typeless, abilityList.Inner_Focus, itemList.None, 26.5, false, status.none));
        pokeList.put("Urshifu-S", new SpeciesClass(100, 100, 130, 100, 63, 60, 97, pkmnType.Fighting, pkmnType.Dark, abilityList.Unseen_Fist, itemList.None, 231.5, false, status.none));
        pokeList.put("Urshifu-R", new SpeciesClass(100, 100, 130, 100, 63, 60, 97, pkmnType.Fighting, pkmnType.Water, abilityList.Unseen_Fist, itemList.None, 231.5, false, status.none));
        pokeList.put("Zarude", new SpeciesClass(105, 105, 120, 105, 70, 95, 105, pkmnType.Dark, pkmnType.Grass, abilityList.Leaf_Guard, itemList.None, 154.3, false, status.none));
        pokeList.put("Regieleki", new SpeciesClass(80, 80, 100, 50, 100, 50, 200, pkmnType.Electric, pkmnType.Typeless, abilityList.Transistor, itemList.None, 319.7, false, status.none));
        pokeList.put("Regidrago", new SpeciesClass(200, 200, 100, 50, 100, 50, 80, pkmnType.Dragon, pkmnType.Typeless, abilityList.Dragons_Maw, itemList.None, 440.9, false, status.none));
        pokeList.put("Glastrier", new SpeciesClass(100, 100, 145, 130, 65, 110, 30, pkmnType.Ice, pkmnType.Typeless, abilityList.Chilling_Neigh, itemList.None, 1763.7, false, status.none));
        pokeList.put("Spectrier", new SpeciesClass(100, 100, 65, 60, 145, 80, 130, pkmnType.Ghost, pkmnType.Typeless, abilityList.Grim_Neigh, itemList.None, 98.1, false, status.none));
        pokeList.put("Calyrex", new SpeciesClass(100, 100, 80, 80, 80, 80, 80, pkmnType.Psychic, pkmnType.Grass, abilityList.Unnerve, itemList.None, 17.0, false, status.none));
        pokeList.put("Calyrex-I", new SpeciesClass(100, 100, 165, 150, 85, 130, 50, pkmnType.Psychic, pkmnType.Ice, abilityList.As_One, itemList.None, 1783.8, false, status.none));
        pokeList.put("Calyrex-S", new SpeciesClass(100, 100, 85, 80, 165, 100, 150, pkmnType.Psychic, pkmnType.Ghost, abilityList.As_One, itemList.None, 118.2, false, status.none));
        pokeList.put("Wyrdeer", new SpeciesClass(103, 103, 105, 72, 105, 75, 65, pkmnType.Normal, pkmnType.Psychic, abilityList.Intimidate, itemList.None, 209.7, false, status.none));
        pokeList.put("Kleavor", new SpeciesClass(70, 70, 135, 95, 45, 70, 85, pkmnType.Bug, pkmnType.Rock, abilityList.Swarm, itemList.None, 196.2, false, status.none));
        pokeList.put("Ursaluna", new SpeciesClass(130, 130, 140, 105, 45, 80, 50, pkmnType.Ground, pkmnType.Normal, abilityList.Guts, itemList.None, 639.3, false, status.none));
        pokeList.put("Ursaluna-B", new SpeciesClass(113, 113, 70, 120, 135, 65, 52, pkmnType.Ground, pkmnType.Normal, abilityList.Minds_Eye, itemList.None, 734.1, false, status.none));
        pokeList.put("Basculegion-M", new SpeciesClass(120, 120, 112, 65, 80, 75, 78, pkmnType.Water, pkmnType.Ghost, abilityList.Swift_Swim, itemList.None, 242.5, false, status.none));
        pokeList.put("Basculegion-F", new SpeciesClass(120, 120, 92, 65, 100, 75, 78, pkmnType.Water, pkmnType.Ghost, abilityList.Swift_Swim, itemList.None, 242.5, false, status.none));
        pokeList.put("Sneasler", new SpeciesClass(80, 80, 130, 60, 40, 80, 120, pkmnType.Fighting, pkmnType.Poison, abilityList.Pressure, itemList.None, 94.8, false, status.none));
        pokeList.put("Overqwil", new SpeciesClass(85, 85, 115, 95, 65, 65, 85, pkmnType.Dark, pkmnType.Poison, abilityList.Poison_Point, itemList.None, 133.4, false, status.none));
        pokeList.put("Enamorus-I", new SpeciesClass(74, 74, 115, 70, 135, 80, 106, pkmnType.Fairy, pkmnType.Flying, abilityList.Cute_Charm, itemList.None, 105.8, false, status.none));
        pokeList.put("Enamorus-T", new SpeciesClass(74, 74, 115, 110, 135, 100, 46, pkmnType.Fairy, pkmnType.Flying, abilityList.Overcoat, itemList.None, 105.8, false, status.none));
        pokeList.put("Sprigatito", new SpeciesClass(40, 40, 61, 54, 45, 45, 65, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 9.0, false, status.none));
        pokeList.put("Floragato", new SpeciesClass(61, 61, 80, 63, 60, 63, 83, pkmnType.Grass, pkmnType.Typeless, abilityList.Overgrow, itemList.None, 26.9, false, status.none));
        pokeList.put("Meowscarada", new SpeciesClass(76, 76, 110, 70, 81, 70, 123, pkmnType.Grass, pkmnType.Dark, abilityList.Overgrow, itemList.None, 68.8, false, status.none));
        pokeList.put("Fuecoco", new SpeciesClass(67, 67, 45, 59, 63, 40, 36, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 21.6, false, status.none));
        pokeList.put("Crocalor", new SpeciesClass(81, 81, 55, 78, 90, 58, 49, pkmnType.Fire, pkmnType.Typeless, abilityList.Blaze, itemList.None, 67.7, false, status.none));
        pokeList.put("Skeledirge", new SpeciesClass(104, 104, 75, 100, 110, 75, 66, pkmnType.Fire, pkmnType.Ghost, abilityList.Blaze, itemList.None, 719.8, false, status.none));
        pokeList.put("Quaxly", new SpeciesClass(55, 55, 65, 45, 50, 45, 50, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 13.4, false, status.none));
        pokeList.put("Quaxwell", new SpeciesClass(70, 70, 85, 65, 65, 60, 65, pkmnType.Water, pkmnType.Typeless, abilityList.Torrent, itemList.None, 47.4, false, status.none));
        pokeList.put("Quaquaval", new SpeciesClass(85, 85, 120, 80, 85, 75, 85, pkmnType.Water, pkmnType.Fighting, abilityList.Torrent, itemList.None, 136.5, false, status.none));
        pokeList.put("Lechonk", new SpeciesClass(54, 54, 45, 40, 35, 45, 35, pkmnType.Normal, pkmnType.Typeless, abilityList.Aroma_Veil, itemList.None, 22.5, false, status.none));
        pokeList.put("Oinkologne-M", new SpeciesClass(110, 110, 100, 75, 59, 80, 65, pkmnType.Normal, pkmnType.Typeless, abilityList.Lingering_Aroma, itemList.None, 264.6, false, status.none));
        pokeList.put("Oinkologne-F", new SpeciesClass(115, 115, 90, 70, 59, 90, 65, pkmnType.Normal, pkmnType.Typeless, abilityList.Aroma_Veil, itemList.None, 264.6, false, status.none));
        pokeList.put("Tarountula", new SpeciesClass(35, 35, 41, 45, 29, 40, 20, pkmnType.Bug, pkmnType.Typeless, abilityList.Insomnia, itemList.None, 8.8, false, status.none));
        pokeList.put("Spidops", new SpeciesClass(60, 60, 79, 92, 52, 86, 35, pkmnType.Bug, pkmnType.Typeless, abilityList.Insomnia, itemList.None, 36.4, false, status.none));
        pokeList.put("Nymble", new SpeciesClass(33, 33, 46, 40, 21, 25, 45, pkmnType.Bug, pkmnType.Typeless, abilityList.Swarm, itemList.None, 2.2, false, status.none));
        pokeList.put("Lokix", new SpeciesClass(71, 71, 102, 78, 52, 55, 92, pkmnType.Bug, pkmnType.Dark, abilityList.Swarm, itemList.None, 38.6, false, status.none));
        pokeList.put("Pawmi", new SpeciesClass(45, 45, 50, 20, 40, 25, 60, pkmnType.Electric, pkmnType.Typeless, abilityList.Static, itemList.None, 5.5, false, status.none));
        pokeList.put("Pawmo", new SpeciesClass(60, 60, 75, 40, 50, 40, 85, pkmnType.Electric, pkmnType.Fighting, abilityList.Volt_Absorb, itemList.None, 14.3, false, status.none));
        pokeList.put("Pawmot", new SpeciesClass(70, 70, 115, 70, 70, 60, 105, pkmnType.Electric, pkmnType.Fighting, abilityList.Volt_Absorb, itemList.None, 90.4, false, status.none));
        pokeList.put("Tandemaus", new SpeciesClass(50, 50, 50, 45, 40, 45, 75, pkmnType.Normal, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 4.0, false, status.none));
        pokeList.put("Maushold-4", new SpeciesClass(74, 74, 75, 70, 65, 75, 111, pkmnType.Normal, pkmnType.Typeless, abilityList.Friend_Guard, itemList.None, 6.2, false, status.none));
        pokeList.put("Maushold-3", new SpeciesClass(74, 74, 75, 70, 65, 75, 111, pkmnType.Normal, pkmnType.Typeless, abilityList.Friend_Guard, itemList.None, 5.1, false, status.none));
        pokeList.put("Fidough", new SpeciesClass(37, 37, 55, 70, 30, 55, 65, pkmnType.Fairy, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 24.0, false, status.none));
        pokeList.put("Dachsbun", new SpeciesClass(57, 57, 80, 115, 50, 80, 95, pkmnType.Fairy, pkmnType.Typeless, abilityList.Well_Baked_Body, itemList.None, 32.8, false, status.none));
        pokeList.put("Smoliv", new SpeciesClass(41, 41, 35, 45, 58, 51, 30, pkmnType.Grass, pkmnType.Normal, abilityList.Early_Bird, itemList.None, 14.3, false, status.none));
        pokeList.put("Dolliv", new SpeciesClass(52, 52, 53, 60, 78, 78, 33, pkmnType.Grass, pkmnType.Normal, abilityList.Early_Bird, itemList.None, 26.2, false, status.none));
        pokeList.put("Arboliva", new SpeciesClass(78, 78, 69, 90, 125, 109, 39, pkmnType.Grass, pkmnType.Normal, abilityList.Seed_Sower, itemList.None, 106.3, false, status.none));
        pokeList.put("Squawkabilly-G", new SpeciesClass(82, 82, 96, 51, 45, 51, 92, pkmnType.Normal, pkmnType.Flying, abilityList.Intimidate, itemList.None, 5.3, false, status.none));
        pokeList.put("Squawkabilly-B", new SpeciesClass(82, 82, 96, 51, 45, 51, 92, pkmnType.Normal, pkmnType.Flying, abilityList.Intimidate, itemList.None, 5.3, false, status.none));
        pokeList.put("Squawkabilly-Y", new SpeciesClass(82, 82, 96, 51, 45, 51, 92, pkmnType.Normal, pkmnType.Flying, abilityList.Intimidate, itemList.None, 5.3, false, status.none));
        pokeList.put("Squawkabilly-W", new SpeciesClass(82, 82, 96, 51, 45, 51, 92, pkmnType.Normal, pkmnType.Flying, abilityList.Intimidate, itemList.None, 5.3, false, status.none));
        pokeList.put("Nacli", new SpeciesClass(55, 55, 55, 75, 35, 35, 25, pkmnType.Rock, pkmnType.Typeless, abilityList.Purifying_Salt, itemList.None, 35.3, false, status.none));
        pokeList.put("Naclstack", new SpeciesClass(60, 60, 60, 100, 35, 65, 35, pkmnType.Rock, pkmnType.Typeless, abilityList.Purifying_Salt, itemList.None, 231.5, false, status.none));
        pokeList.put("Garganacl", new SpeciesClass(100, 100, 100, 130, 45, 90, 35, pkmnType.Rock, pkmnType.Typeless, abilityList.Purifying_Salt, itemList.None, 529.1, false, status.none));
        pokeList.put("Charcadet", new SpeciesClass(40, 40, 50, 40, 50, 40, 35, pkmnType.Fire, pkmnType.Typeless, abilityList.Flash_Fire, itemList.None, 23.1, false, status.none));
        pokeList.put("Armarouge", new SpeciesClass(85, 85, 60, 100, 125, 80, 75, pkmnType.Fire, pkmnType.Psychic, abilityList.Flash_Fire, itemList.None, 187.4, false, status.none));
        pokeList.put("Ceruledge", new SpeciesClass(75, 75, 125, 80, 60, 100, 85, pkmnType.Fire, pkmnType.Ghost, abilityList.Flash_Fire, itemList.None, 136.7, false, status.none));
        pokeList.put("Tadbulb", new SpeciesClass(61, 61, 31, 41, 59, 35, 45, pkmnType.Electric, pkmnType.Typeless, abilityList.Own_Tempo, itemList.None, 0.9, false, status.none));
        pokeList.put("Bellibolt", new SpeciesClass(109, 109, 64, 91, 103, 83, 45, pkmnType.Electric, pkmnType.Typeless, abilityList.Electromorphosis, itemList.None, 249.1, false, status.none));
        pokeList.put("Wattrel", new SpeciesClass(40, 40, 40, 35, 55, 40, 70, pkmnType.Electric, pkmnType.Flying, abilityList.Wind_Power, itemList.None, 7.9, false, status.none));
        pokeList.put("Kilowattrel", new SpeciesClass(70, 70, 70, 60, 105, 60, 125, pkmnType.Electric, pkmnType.Flying, abilityList.Wind_Power, itemList.None, 85.1, false, status.none));
        pokeList.put("Maschiff", new SpeciesClass(60, 60, 78, 60, 40, 51, 51, pkmnType.Dark, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 35.3, false, status.none));
        pokeList.put("Mabosstiff", new SpeciesClass(80, 80, 120, 90, 60, 70, 85, pkmnType.Dark, pkmnType.Typeless, abilityList.Intimidate, itemList.None, 134.5, false, status.none));
        pokeList.put("Shroodle", new SpeciesClass(40, 40, 65, 35, 40, 35, 75, pkmnType.Poison, pkmnType.Normal, abilityList.Unburden, itemList.None, 1.5, false, status.none));
        pokeList.put("Grafaiai", new SpeciesClass(63, 63, 95, 65, 80, 72, 110, pkmnType.Poison, pkmnType.Normal, abilityList.Unburden, itemList.None, 60.0, false, status.none));
        pokeList.put("Bramblin", new SpeciesClass(40, 40, 65, 30, 45, 35, 60, pkmnType.Grass, pkmnType.Ghost, abilityList.Wind_Rider, itemList.None, 1.3, false, status.none));
        pokeList.put("Brambleghast", new SpeciesClass(55, 55, 115, 70, 80, 70, 90, pkmnType.Grass, pkmnType.Ghost, abilityList.Wind_Rider, itemList.None, 13.2, false, status.none));
        pokeList.put("Toedscool", new SpeciesClass(40, 40, 40, 35, 50, 100, 70, pkmnType.Ground, pkmnType.Grass, abilityList.Mycelium_Might, itemList.None, 72.8, false, status.none));
        pokeList.put("Toedscruel", new SpeciesClass(80, 80, 70, 65, 80, 120, 100, pkmnType.Ground, pkmnType.Grass, abilityList.Mycelium_Might, itemList.None, 127.9, false, status.none));
        pokeList.put("Klawf", new SpeciesClass(70, 70, 100, 115, 35, 55, 75, pkmnType.Rock, pkmnType.Typeless, abilityList.Anger_Shell, itemList.None, 174.2, false, status.none));
        pokeList.put("Capsakid", new SpeciesClass(50, 50, 62, 40, 62, 40, 50, pkmnType.Grass, pkmnType.Typeless, abilityList.Chlorophyll, itemList.None, 6.6, false, status.none));
        pokeList.put("Scovillain", new SpeciesClass(65, 65, 108, 65, 108, 65, 75, pkmnType.Grass, pkmnType.Fire, abilityList.Chlorophyll, itemList.None, 33.1, false, status.none));
        pokeList.put("Rellor", new SpeciesClass(41, 41, 50, 60, 31, 58, 30, pkmnType.Bug, pkmnType.Typeless, abilityList.Compound_Eyes, itemList.None, 2.2, false, status.none));
        pokeList.put("Rabsca", new SpeciesClass(75, 75, 50, 85, 115, 100, 45, pkmnType.Bug, pkmnType.Psychic, abilityList.Synchronize, itemList.None, 7.7, false, status.none));
        pokeList.put("Flittle", new SpeciesClass(30, 30, 35, 30, 55, 30, 75, pkmnType.Psychic, pkmnType.Typeless, abilityList.Anticipation, itemList.None, 3.3, false, status.none));
        pokeList.put("Espathra", new SpeciesClass(95, 95, 60, 60, 101, 60, 105, pkmnType.Psychic, pkmnType.Typeless, abilityList.Opportunist, itemList.None, 198.4, false, status.none));
        pokeList.put("Tinkatink", new SpeciesClass(50, 50, 45, 45, 35, 64, 58, pkmnType.Fairy, pkmnType.Steel, abilityList.Mold_Breaker, itemList.None, 19.6, false, status.none));
        pokeList.put("Tinkatuff", new SpeciesClass(65, 65, 55, 55, 45, 82, 78, pkmnType.Fairy, pkmnType.Steel, abilityList.Mold_Breaker, itemList.None, 130.3, false, status.none));
        pokeList.put("Tinkaton", new SpeciesClass(85, 85, 75, 77, 70, 105, 94, pkmnType.Fairy, pkmnType.Steel, abilityList.Mold_Breaker, itemList.None, 248.7, false, status.none));
        pokeList.put("Wiglett", new SpeciesClass(10, 10, 55, 25, 35, 25, 95, pkmnType.Water, pkmnType.Typeless, abilityList.Gooey, itemList.None, 4.0, false, status.none));
        pokeList.put("Wugtrio", new SpeciesClass(35, 35, 100, 50, 50, 70, 120, pkmnType.Water, pkmnType.Typeless, abilityList.Gooey, itemList.None, 11.9, false, status.none));
        pokeList.put("Bombirdier", new SpeciesClass(70, 70, 103, 85, 60, 85, 82, pkmnType.Flying, pkmnType.Dark, abilityList.Big_Pecks, itemList.None, 94.6, false, status.none));
        pokeList.put("Finizen", new SpeciesClass(70, 70, 45, 40, 45, 40, 75, pkmnType.Water, pkmnType.Typeless, abilityList.Water_Veil, itemList.None, 132.7, false, status.none));
        pokeList.put("Palafin-Z", new SpeciesClass(100, 100, 70, 72, 53, 62, 100, pkmnType.Water, pkmnType.Typeless, abilityList.Zero_to_Hero, itemList.None, 132.7, false, status.none));
        pokeList.put("Palafin-H", new SpeciesClass(100, 100, 160, 97, 106, 87, 100, pkmnType.Water, pkmnType.Typeless, abilityList.Zero_to_Hero, itemList.None, 214.7, false, status.none));
        pokeList.put("Varoom", new SpeciesClass(45, 45, 70, 63, 30, 45, 47, pkmnType.Steel, pkmnType.Poison, abilityList.Overcoat, itemList.None, 77.2, false, status.none));
        pokeList.put("Revavroom", new SpeciesClass(80, 80, 119, 90, 54, 67, 90, pkmnType.Steel, pkmnType.Poison, abilityList.Overcoat, itemList.None, 264.6, false, status.none));
        pokeList.put("Cyclizar", new SpeciesClass(70, 70, 95, 65, 85, 65, 121, pkmnType.Dragon, pkmnType.Normal, abilityList.Shed_Skin, itemList.None, 138.9, false, status.none));
        pokeList.put("Orthworm", new SpeciesClass(70, 70, 85, 145, 60, 55, 65, pkmnType.Steel, pkmnType.Typeless, abilityList.Earth_Eater, itemList.None, 683.4, false, status.none));
        pokeList.put("Glimmet", new SpeciesClass(48, 48, 35, 42, 105, 60, 60, pkmnType.Rock, pkmnType.Poison, abilityList.Toxic_Debris, itemList.None, 17.6, false, status.none));
        pokeList.put("Glimmora", new SpeciesClass(83, 83, 55, 90, 130, 81, 86, pkmnType.Rock, pkmnType.Poison, abilityList.Toxic_Debris, itemList.None, 99.2, false, status.none));
        pokeList.put("Greavard", new SpeciesClass(50, 50, 61, 60, 30, 55, 34, pkmnType.Ghost, pkmnType.Typeless, abilityList.Pickup, itemList.None, 77.2, false, status.none));
        pokeList.put("Houndstone", new SpeciesClass(72, 72, 101, 100, 50, 97, 68, pkmnType.Ghost, pkmnType.Typeless, abilityList.Sand_Rush, itemList.None, 33.1, false, status.none));
        pokeList.put("Flamigo", new SpeciesClass(82, 82, 115, 74, 75, 64, 90, pkmnType.Flying, pkmnType.Fighting, abilityList.Scrappy, itemList.None, 81.6, false, status.none));
        pokeList.put("Cetoddle", new SpeciesClass(108, 108, 68, 45, 30, 40, 43, pkmnType.Ice, pkmnType.Typeless, abilityList.Thick_Fat, itemList.None, 99.2, false, status.none));
        pokeList.put("Cetitan", new SpeciesClass(170, 170, 113, 65, 45, 55, 73, pkmnType.Ice, pkmnType.Typeless, abilityList.Thick_Fat, itemList.None, 1543.2, false, status.none));
        pokeList.put("Veluza", new SpeciesClass(90, 90, 102, 73, 78, 65, 70, pkmnType.Water, pkmnType.Psychic, abilityList.Mold_Breaker, itemList.None, 198.4, false, status.none));
        pokeList.put("Dondozo", new SpeciesClass(150, 150, 100, 115, 65, 65, 35, pkmnType.Water, pkmnType.Typeless, abilityList.Unaware, itemList.None, 485.0, false, status.none));
        pokeList.put("Tatsugiri-C", new SpeciesClass(68, 68, 50, 60, 120, 95, 82, pkmnType.Dragon, pkmnType.Water, abilityList.Commander, itemList.None, 17.6, false, status.none));
        pokeList.put("Tatsugiri-D", new SpeciesClass(68, 68, 50, 60, 120, 95, 82, pkmnType.Dragon, pkmnType.Water, abilityList.Commander, itemList.None, 17.6, false, status.none));
        pokeList.put("Tatsugiri-S", new SpeciesClass(68, 68, 50, 60, 120, 95, 82, pkmnType.Dragon, pkmnType.Water, abilityList.Commander, itemList.None, 17.6, false, status.none));
        pokeList.put("Annihilape", new SpeciesClass(110, 110, 115, 80, 50, 90, 90, pkmnType.Fighting, pkmnType.Ghost, abilityList.Vital_Spirit, itemList.None, 123.5, false, status.none));
        pokeList.put("Clodsire", new SpeciesClass(130, 130, 75, 60, 45, 100, 20, pkmnType.Poison, pkmnType.Ground, abilityList.Poison_Point, itemList.None, 491.6, false, status.none));
        pokeList.put("Farigiraf", new SpeciesClass(120, 120, 90, 70, 110, 70, 60, pkmnType.Normal, pkmnType.Psychic, abilityList.Cud_Chew, itemList.None, 352.7, false, status.none));
        pokeList.put("Dudunsparce-2", new SpeciesClass(125, 125, 100, 80, 85, 75, 55, pkmnType.Normal, pkmnType.Typeless, abilityList.Serene_Grace, itemList.None, 86.4, false, status.none));
        pokeList.put("Dudunsparce-3", new SpeciesClass(125, 125, 100, 80, 85, 75, 55, pkmnType.Normal, pkmnType.Typeless, abilityList.Serene_Grace, itemList.None, 104.5, false, status.none));
        pokeList.put("Kingambit", new SpeciesClass(100, 100, 135, 120, 60, 85, 50, pkmnType.Dark, pkmnType.Steel, abilityList.Defiant, itemList.None, 264.6, false, status.none));
        pokeList.put("Great Tusk", new SpeciesClass(115, 115, 131, 131, 53, 53, 87, pkmnType.Ground, pkmnType.Fighting, abilityList.Protosynthesis, itemList.None, 705.5, false, status.none));
        pokeList.put("Scream Tail", new SpeciesClass(115, 115, 65, 99, 65, 115, 111, pkmnType.Fairy, pkmnType.Psychic, abilityList.Protosynthesis, itemList.None, 17.6, false, status.none));
        pokeList.put("Brute Bonnet", new SpeciesClass(111, 111, 127, 99, 79, 99, 55, pkmnType.Grass, pkmnType.Dark, abilityList.Protosynthesis, itemList.None, 46.3, false, status.none));
        pokeList.put("Flutter Mane", new SpeciesClass(55, 55, 55, 55, 135, 135, 135, pkmnType.Ghost, pkmnType.Fairy, abilityList.Protosynthesis, itemList.None, 8.8, false, status.none));
        pokeList.put("Slither Wing", new SpeciesClass(85, 85, 135, 79, 85, 105, 81, pkmnType.Bug, pkmnType.Fighting, abilityList.Protosynthesis, itemList.None, 202.8, false, status.none));
        pokeList.put("Sandy Shocks", new SpeciesClass(85, 85, 81, 97, 121, 85, 101, pkmnType.Electric, pkmnType.Ground, abilityList.Protosynthesis, itemList.None, 132.3, false, status.none));
        pokeList.put("Iron Treads", new SpeciesClass(90, 90, 112, 120, 72, 70, 106, pkmnType.Ground, pkmnType.Steel, abilityList.Quark_Drive, itemList.None, 529.1, false, status.none));
        pokeList.put("Iron Bundle", new SpeciesClass(56, 56, 80, 114, 124, 60, 136, pkmnType.Ice, pkmnType.Water, abilityList.Quark_Drive, itemList.None, 24.3, false, status.none));
        pokeList.put("Iron Hands", new SpeciesClass(154, 154, 140, 108, 50, 68, 50, pkmnType.Fighting, pkmnType.Electric, abilityList.Quark_Drive, itemList.None, 839.3, false, status.none));
        pokeList.put("Iron Jugulis", new SpeciesClass(94, 94, 80, 86, 122, 80, 108, pkmnType.Dark, pkmnType.Flying, abilityList.Quark_Drive, itemList.None, 244.7, false, status.none));
        pokeList.put("Iron Moth", new SpeciesClass(80, 80, 70, 60, 140, 110, 110, pkmnType.Fire, pkmnType.Poison, abilityList.Quark_Drive, itemList.None, 79.4, false, status.none));
        pokeList.put("Iron Thorns", new SpeciesClass(100, 100, 134, 110, 70, 84, 72, pkmnType.Rock, pkmnType.Electric, abilityList.Quark_Drive, itemList.None, 668.0, false, status.none));
        pokeList.put("Frigibax", new SpeciesClass(65, 65, 75, 45, 35, 45, 55, pkmnType.Dragon, pkmnType.Ice, abilityList.Thermal_Exchange, itemList.None, 37.5, false, status.none));
        pokeList.put("Arctibax", new SpeciesClass(90, 90, 95, 66, 45, 65, 62, pkmnType.Dragon, pkmnType.Ice, abilityList.Thermal_Exchange, itemList.None, 66.1, false, status.none));
        pokeList.put("Baxcalibur", new SpeciesClass(115, 115, 145, 92, 75, 86, 87, pkmnType.Dragon, pkmnType.Ice, abilityList.Thermal_Exchange, itemList.None, 463.0, false, status.none));
        pokeList.put("Gimmighoul-C", new SpeciesClass(45, 45, 30, 70, 75, 70, 10, pkmnType.Ghost, pkmnType.Typeless, abilityList.Rattled, itemList.None, 11.0, false, status.none));
        pokeList.put("Gimmighoul-R", new SpeciesClass(45, 45, 30, 25, 75, 45, 80, pkmnType.Ghost, pkmnType.Typeless, abilityList.Run_Away, itemList.None, 0.2, false, status.none));
        pokeList.put("Gholdengo", new SpeciesClass(87, 87, 60, 95, 133, 91, 84, pkmnType.Steel, pkmnType.Ghost, abilityList.Good_as_Gold, itemList.None, 66.1, false, status.none));
        pokeList.put("Wo-Chien", new SpeciesClass(85, 85, 85, 100, 95, 135, 70, pkmnType.Dark, pkmnType.Grass, abilityList.Tablets_of_Ruin, itemList.None, 163.6, false, status.none));
        pokeList.put("Chien-Pao", new SpeciesClass(80, 80, 120, 80, 90, 65, 135, pkmnType.Dark, pkmnType.Ice, abilityList.Sword_of_Ruin, itemList.None, 335.5, false, status.none));
        pokeList.put("Ting-Lu", new SpeciesClass(155, 155, 110, 125, 55, 80, 45, pkmnType.Dark, pkmnType.Ground, abilityList.Vessel_of_Ruin, itemList.None, 1542.6, false, status.none));
        pokeList.put("Chi-Yu", new SpeciesClass(55, 55, 80, 80, 135, 120, 100, pkmnType.Dark, pkmnType.Fire, abilityList.Beads_of_Ruin, itemList.None, 10.8, false, status.none));
        pokeList.put("Roaring Moon", new SpeciesClass(105, 105, 139, 71, 55, 101, 119, pkmnType.Dragon, pkmnType.Dark, abilityList.Protosynthesis, itemList.None, 837.8, false, status.none));
        pokeList.put("Iron Valiant", new SpeciesClass(74, 74, 130, 90, 120, 60, 116, pkmnType.Fairy, pkmnType.Fighting, abilityList.Quark_Drive, itemList.None, 77.2, false, status.none));
        pokeList.put("Koraidon", new SpeciesClass(100, 100, 135, 115, 85, 100, 135, pkmnType.Fighting, pkmnType.Dragon, abilityList.Orichalcum_Pulse, itemList.None, 668.0, false, status.none));
        pokeList.put("Miraidon", new SpeciesClass(100, 100, 85, 100, 135, 115, 135, pkmnType.Electric, pkmnType.Dragon, abilityList.Hadron_Engine, itemList.None, 529.1, false, status.none));
        pokeList.put("Walking Wake", new SpeciesClass(99, 99, 83, 91, 125, 83, 109, pkmnType.Water, pkmnType.Dragon, abilityList.Protosynthesis, itemList.None, 617.3, false, status.none));
        pokeList.put("Iron Leaves", new SpeciesClass(90, 90, 130, 88, 70, 108, 104, pkmnType.Grass, pkmnType.Psychic, abilityList.Quark_Drive, itemList.None, 275.6, false, status.none));
        pokeList.put("Dipplin", new SpeciesClass(80, 80, 80, 110, 95, 80, 40, pkmnType.Grass, pkmnType.Dragon, abilityList.Supersweet_Syrup, itemList.None, 9.7, false, status.none));
        pokeList.put("Poltchageist", new SpeciesClass(40, 40, 45, 45, 74, 54, 50, pkmnType.Grass, pkmnType.Ghost, abilityList.Hospitality, itemList.None, 2.4, false, status.none));
        pokeList.put("Sinistcha", new SpeciesClass(71, 71, 60, 106, 121, 80, 70, pkmnType.Grass, pkmnType.Ghost, abilityList.Hospitality, itemList.None, 4.9, false, status.none));
        pokeList.put("Okidogi", new SpeciesClass(88, 88, 128, 115, 58, 86, 80, pkmnType.Poison, pkmnType.Fighting, abilityList.Toxic_Chain, itemList.None, 202.8, false, status.none));
        pokeList.put("Munkidori", new SpeciesClass(88, 88, 75, 66, 130, 90, 106, pkmnType.Poison, pkmnType.Psychic, abilityList.Toxic_Chain, itemList.None, 26.9, false, status.none));
        pokeList.put("Fezandipiti", new SpeciesClass(88, 88, 91, 82, 70, 125, 99, pkmnType.Poison, pkmnType.Fairy, abilityList.Toxic_Chain, itemList.None, 66.4, false, status.none));
        pokeList.put("Ogerpon-T", new SpeciesClass(80, 80, 120, 84, 60, 96, 110, pkmnType.Grass, pkmnType.Typeless, abilityList.Defiant, itemList.None, 87.7, false, status.none));
        pokeList.put("Ogerpon-W", new SpeciesClass(80, 80, 120, 84, 60, 96, 110, pkmnType.Grass, pkmnType.Water, abilityList.Water_Absorb, itemList.None, 87.7, false, status.none));
        pokeList.put("Ogerpon-H", new SpeciesClass(80, 80, 120, 84, 60, 96, 110, pkmnType.Grass, pkmnType.Fire, abilityList.Mold_Breaker, itemList.None, 87.7, false, status.none));
        pokeList.put("Ogerpon-C", new SpeciesClass(80, 80, 120, 84, 60, 96, 110, pkmnType.Grass, pkmnType.Rock, abilityList.Sturdy, itemList.None, 87.7, false, status.none));
        pokeList.put("Archaludon", new SpeciesClass(90, 90, 105, 130, 125, 65, 85, pkmnType.Steel, pkmnType.Dragon, abilityList.Stamina, itemList.None, 132.3, false, status.none));
        pokeList.put("Hydrapple", new SpeciesClass(106, 106, 80, 110, 120, 80, 44, pkmnType.Grass, pkmnType.Dragon, abilityList.Supersweet_Syrup, itemList.None, 205.0, false, status.none));
        pokeList.put("Gouging Fire", new SpeciesClass(105, 105, 115, 121, 65, 93, 91, pkmnType.Fire, pkmnType.Dragon, abilityList.Protosynthesis, itemList.None, 1300.7, false, status.none));
        pokeList.put("Raging Bolt", new SpeciesClass(125, 125, 73, 91, 137, 89, 75, pkmnType.Electric, pkmnType.Dragon, abilityList.Protosynthesis, itemList.None, 1058.2, false, status.none));
        pokeList.put("Iron Boulder", new SpeciesClass(90, 90, 120, 80, 68, 108, 124, pkmnType.Rock, pkmnType.Psychic, abilityList.Quark_Drive, itemList.None, 358.3, false, status.none));
        pokeList.put("Iron Crown", new SpeciesClass(90, 90, 72, 100, 122, 108, 98, pkmnType.Steel, pkmnType.Psychic, abilityList.Quark_Drive, itemList.None, 343.9, false, status.none));
        pokeList.put("Terapagos-N", new SpeciesClass(90, 90, 65, 85, 65, 85, 60, pkmnType.Normal, pkmnType.Typeless, abilityList.Tera_Shift, itemList.None, 14.3, false, status.none));
        pokeList.put("Terapagos-T", new SpeciesClass(95, 95, 95, 110, 105, 110, 85, pkmnType.Normal, pkmnType.Typeless, abilityList.Tera_Shell, itemList.None, 35.3, false, status.none));
        pokeList.put("Terapagos-S", new SpeciesClass(160, 160, 105, 110, 130, 110, 85, pkmnType.Normal, pkmnType.Typeless, abilityList.Teraform_Zero, itemList.None, 169.8, false, status.none));
        pokeList.put("Pecharunt", new SpeciesClass(88, 88, 88, 160, 88, 88, 88, pkmnType.Poison, pkmnType.Ghost, abilityList.Poison_Puppeteer, itemList.None, 0.7, false, status.none));


        return pokeList;
    }

    public static void saveEffectivenessChart() {
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
    }

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

    static double totalCalc2(double totalDamage, double targets, double pb, double weather, double glaiveRush, double critical, double rndm, double stab, double type, double burn, double other, double zMove, double teraShield) {
        double calculation2;
        calculation2 = (totalDamage * targets);
        calculation2 = (calculation2 * pb);
        calculation2 = (calculation2 * weather);
        calculation2 = (calculation2 * glaiveRush);
        calculation2 = (calculation2 * critical);
        calculation2 = (calculation2 * rndm);
        calculation2 = (calculation2 * stab);
        calculation2 = (calculation2 * type);
        calculation2 = (calculation2 * burn);
        calculation2 = (calculation2 * other);
        calculation2 = (calculation2 * zMove);
        calculation2 = (calculation2 * teraShield);

        calculation2 = Math.round(calculation2);

        return calculation2;
    }
}
