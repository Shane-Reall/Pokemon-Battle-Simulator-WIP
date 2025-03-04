import java.io.*;
import java.util.HashMap;
import java.util.Random;

import static java.lang.System.exit;

public class BattleFunctions extends StatCalculation {

    private static final long serialVersionUID = 1L;

    static MoveClass damageChanges(MoveClass move, String currentMove, SpeciesClass pokemon, String currentMon, SpeciesClass opponent, Checks checks) {

        // Items
        if (currentMove.equals("Techno_Blast") && currentMon.equals("Genesect")) {
            if (pokemon.getItem().equals(itemList.Douse_Drive)) {
                move.setType(pkmnType.Water);
            } else if (pokemon.getItem().equals(itemList.Burn_Drive)) {
                move.setType(pkmnType.Fire);
            } else if (pokemon.getItem().equals(itemList.Shock_Drive)) {
                move.setType(pkmnType.Electric);
            } else if (pokemon.getItem().equals(itemList.Chill_Drive)) {
                move.setType(pkmnType.Ice);
            }
        } else if (currentMove.equals("Multi-Attack") && currentMon.equals("Silvally")) {
            if (pokemon.getItem().toString().contains("_Memory")) {
                for (pkmnType typing : pkmnType.values()) {
                    if (pokemon.getItem().toString().contains(typing.toString())) {
                        move.setType(typing);
                    }
                }
            }
        }

        if (move.getType() == pkmnType.Fighting && (pokemon.getItem().equals(itemList.Black_Belt) || pokemon.getItem().equals(itemList.Fist_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Dark && (pokemon.getItem().equals(itemList.Dragon_Fang) || pokemon.getItem().equals(itemList.Draco_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Fire && (pokemon.getItem().equals(itemList.Black_Glasses) || pokemon.getItem().equals(itemList.Dread_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Dragon && (pokemon.getItem().equals(itemList.Dragon_Fang) || pokemon.getItem().equals(itemList.Draco_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Fairy && (pokemon.getItem().equals(itemList.Fairy_Feather) || pokemon.getItem().equals(itemList.Pixie_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Rock && (pokemon.getItem().equals(itemList.Hard_Stone) || pokemon.getItem().equals(itemList.Stone_Plate) || pokemon.getItem().equals(itemList.Rock_Incense))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Electric && (pokemon.getItem().equals(itemList.Magnet) || pokemon.getItem().equals(itemList.Zap_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Steel && (pokemon.getItem().equals(itemList.Metal_Coat) || pokemon.getItem().equals(itemList.Iron_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Grass && (pokemon.getItem().equals(itemList.Miracle_Seed) || pokemon.getItem().equals(itemList.Meadow_Plate) || pokemon.getItem().equals(itemList.Rose_Incense))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Water && (pokemon.getItem().equals(itemList.Mystic_Water) || pokemon.getItem().equals(itemList.Splash_Plate) || pokemon.getItem().equals(itemList.Sea_Incense) || pokemon.getItem().equals(itemList.Wave_Incense))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Ice && (pokemon.getItem().equals(itemList.NeverMelt_Ice) || pokemon.getItem().equals(itemList.Icicle_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Poison && (pokemon.getItem().equals(itemList.Poison_Barb) || pokemon.getItem().equals(itemList.Toxic_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Flying && (pokemon.getItem().equals(itemList.Sharp_Beak) || pokemon.getItem().equals(itemList.Sky_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Normal && pokemon.getItem().equals(itemList.Silk_Scarf)) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Bug && (pokemon.getItem().equals(itemList.Silver_Powder) || pokemon.getItem().equals(itemList.Insect_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Ground && (pokemon.getItem().equals(itemList.Soft_Sand) || pokemon.getItem().equals(itemList.Earth_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Ghost && (pokemon.getItem().equals(itemList.Spell_Tag) || pokemon.getItem().equals(itemList.Spooky_Plate))) {
            move.setBase((move.getBase() * 1.2));
        } else if (move.getType() == pkmnType.Psychic && (pokemon.getItem().equals(itemList.Twisted_Spoon) || pokemon.getItem().equals(itemList.Mind_Plate) || pokemon.getItem().equals(itemList.Odd_Incense))) {
            move.setBase((move.getBase() * 1.2));
        } else if ((currentMon.equals("Dialga") && pokemon.getItem().equals(itemList.Adamant_Orb))  || (currentMon.equals("Dialga-O") && pokemon.getItem().equals(itemList.Adamant_Crystal))  || (currentMon.equals("Giratina-A") && pokemon.getItem().equals(itemList.Griseous_Orb))  || (currentMon.equals("Giratina-O") && pokemon.getItem().equals(itemList.Griseous_Core))  || (currentMon.equals("Palkia") && pokemon.getItem().equals(itemList.Lustrous_Orb))  || (currentMon.equals("Palkia-O") && pokemon.getItem().equals(itemList.Lustrous_Globe))  || ((currentMon.equals("Latias") || currentMon.equals("Latios")) && pokemon.getItem().equals(itemList.Soul_Dew))) {
            if (move.getType() == pkmnType.Dragon) {
                move.setBase((move.getBase() * 1.2));
            } else if (currentMon.contains("Dialga") && move.getType() == pkmnType.Steel) {
                move.setBase((move.getBase() * 1.2));
            } else if (currentMon.contains("Giratina") && move.getType() == pkmnType.Ghost) {
                move.setBase((move.getBase() * 1.2));
            } else if (currentMon.contains("Palkia") && move.getType() == pkmnType.Water) {
                move.setBase((move.getBase() * 1.2));
            } else if (currentMon.contains("Lati") && move.getType() == pkmnType.Psychic) {
                move.setBase((move.getBase() * 1.2));
            }
        } else if (pokemon.getItem().toString().contains("_Gem")) {
            for (pkmnType typing : pkmnType.values()) {
                if (move.getType().equals(typing) && pokemon.getItem().toString().contains(typing.toString())) {
                    move.setBase((move.getBase() * 1.3));
                    pokemon.setItem(itemList.None);
                }
            }
        }

        //Move Effects
        if (currentMove.equals("Acrobatics") && pokemon.getItem().equals(itemList.None)) {
            move.setBase((move.getBase() * 2));
        } else if (currentMove.equals("Barb_Barrage") && (opponent.getStated().equals(status.Poison) || opponent.getStated().equals(status.Toxiced))) {
            move.setBase((move.getBase() * 2));
        } else if (currentMove.equals("Bolt_Beak") && (pokemon.getSpd() > opponent.getSpd())) {
            move.setBase((move.getBase() * 2));
        } else if (currentMove.equals("Crush_Grip")) {
            move.setBase(120 * (opponent.getCurrentHp()/opponent.getHp()));
        } else if ((pokemon.getStated().equals(status.Paralysis) || pokemon.getStated().equals(status.Burn) || pokemon.getStated().equals(status.Poison) || pokemon.getStated().equals(status.Toxiced)) && currentMove.equals("Facade")) {
            move.setBase((move.getBase() * 2));
        } else if (((pokemon.getSpd() > opponent.getSpd()) ^ checks.isTrickRoom()) && currentMove.equals("Fishious_Rend")) {
            move.setBase((move.getBase() * 2));
        } else if (currentMove.equals("Flail")) {
            double casting = (pokemon.getCurrentHp()/pokemon.getHp());
            if (casting >= 0.688) {
                move.setBase(20);
            } else if (casting >= 0.354) {
                move.setBase(40);
            } else if (casting >= 0.208) {
                move.setBase(80);
            } else if (casting >= 0.104) {
                move.setBase(100);
            } else if (casting >= 0.042) {
                move.setBase(150);
            } else {
                move.setBase(200);
            }
        } else if (currentMove.equals("Fling")) {
            if (!pokemon.getItem().equals(itemList.None)) {
                HashMap<itemList, Integer> listFling = loadMapFL();
                move.setBase(listFling.get(pokemon.getItem()));
            } else {
                move.setBase(0);
            }
        } else if (currentMove.equals("Frustration") || currentMove.equals("Return")) {
            move.setBase(102);
        } else if (currentMove.equals("Fury_Cutter")) {
            move.setBase(move.getBase() * Math.pow(2,checks.getContinueCounter()));
            if (move.getBase() > 160) {
                move.setBase(160);
            }
        } else if (currentMove.equals("Gyro_Ball")) {
            move.setBase(Math.min(150, Math.round((25 * opponent.getSpd()) / pokemon.getSpd()) + 1));
        } else if (currentMove.equals("Heat_Crash") || currentMove.equals("Heavy_Slam")) {
            if ((pokemon.getWeight() * 0.5) < opponent.getWeight()) {
                move.setBase(40);
            } else if ((pokemon.getWeight() * 0.3335) < opponent.getWeight()) {
                move.setBase(60);
            } else if ((pokemon.getWeight() * 0.2501) < opponent.getWeight()) {
                move.setBase(80);
            } else if ((pokemon.getWeight() * 0.2001) < opponent.getWeight()) {
                move.setBase(100);
            } else {
                move.setBase(120);
            }
        } else if (currentMove.equals("Ice_Ball") || currentMove.equals("Rollout")) {
            move.setBase(move.getBase() * Math.pow(2,checks.getContinueCounter()));
            if (move.getBase() > 480) {
                move.setBase(30);
            }
        } else if (currentMove.equals("Last_Respects")) {
            move.setBase(140);
        } else if (currentMove.equals("Low_Kick")) {
            if (opponent.getWeight() < 21.8) {
                move.setBase(20);
            } else if (opponent.getWeight() < 54.9) {
                move.setBase(40);
            } else if (opponent.getWeight() < 110.0) {
                move.setBase(60);
            } else if (opponent.getWeight() < 220.2) {
                move.setBase(80);
            } else if (opponent.getWeight() < 440.7) {
                move.setBase(100);
            } else {
                move.setBase(120);
            }
        } else if (currentMove.equals("Natural_Gift")) {
            if (pokemon.getItem().equals(itemList.Figy_Berry) || pokemon.getItem().equals(itemList.Cornn_Berry) || pokemon.getItem().equals(itemList.Tanga_Berry) || pokemon.getItem().equals(itemList.Enigma_Berry)) {
                move.setType(pkmnType.Bug);
                if (pokemon.getItem().equals(itemList.Figy_Berry) || pokemon.getItem().equals(itemList.Tanga_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Cornn_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Enigma_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Iapapa_Berry) || pokemon.getItem().equals(itemList.Spelon_Berry) || pokemon.getItem().equals(itemList.Colbur_Berry) || pokemon.getItem().equals(itemList.Rowap_Berry) || pokemon.getItem().equals(itemList.Maranga_Berry)) {
                move.setType(pkmnType.Dark);
                if (pokemon.getItem().equals(itemList.Iapapa_Berry) || pokemon.getItem().equals(itemList.Colbur_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Spelon_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Rowap_Berry) || pokemon.getItem().equals(itemList.Maranga_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Aguav_Berry) || pokemon.getItem().equals(itemList.Nomel_Berry) || pokemon.getItem().equals(itemList.Haban_Berry) || pokemon.getItem().equals(itemList.Jaboca_Berry)) {
                move.setType(pkmnType.Dragon);
                if (pokemon.getItem().equals(itemList.Aguav_Berry) || pokemon.getItem().equals(itemList.Haban_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Nomel_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Jaboca_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Pecha_Berry) || pokemon.getItem().equals(itemList.Wepear_Berry) || pokemon.getItem().equals(itemList.Belue_Berry) || pokemon.getItem().equals(itemList.Wacan_Berry)) {
                move.setType(pkmnType.Electric);
                if (pokemon.getItem().equals(itemList.Pecha_Berry) || pokemon.getItem().equals(itemList.Wacan_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Wepear_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Belue_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Roseli_Berry) || pokemon.getItem().equals(itemList.Kee_Berry)) {
                move.setType(pkmnType.Fairy);
                if (pokemon.getItem().equals(itemList.Roseli_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Kee_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Leppa_Berry) || pokemon.getItem().equals(itemList.Kelpsy_Berry) || pokemon.getItem().equals(itemList.Chople_Berry) || pokemon.getItem().equals(itemList.Salac_Berry)) {
                move.setType(pkmnType.Fighting);
                if (pokemon.getItem().equals(itemList.Leppa_Berry) || pokemon.getItem().equals(itemList.Chople_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Kelpsy_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Salac_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Cheri_Berry) || pokemon.getItem().equals(itemList.Bluk_Berry) || pokemon.getItem().equals(itemList.Watmel_Berry) || pokemon.getItem().equals(itemList.Occa_Berry)) {
                move.setType(pkmnType.Fire);
                if (pokemon.getItem().equals(itemList.Cheri_Berry) || pokemon.getItem().equals(itemList.Occa_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Bluk_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Watmel_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Lum_Berry) || pokemon.getItem().equals(itemList.Grepa_Berry) || pokemon.getItem().equals(itemList.Coba_Berry) || pokemon.getItem().equals(itemList.Lansat_Berry)) {
                move.setType(pkmnType.Flying);
                if (pokemon.getItem().equals(itemList.Lum_Berry) || pokemon.getItem().equals(itemList.Coba_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Grepa_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Lansat_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Mago_Berry) || pokemon.getItem().equals(itemList.Rabuta_Berry) || pokemon.getItem().equals(itemList.Kasib_Berry) || pokemon.getItem().equals(itemList.Custap_Berry)) {
                move.setType(pkmnType.Ghost);
                if (pokemon.getItem().equals(itemList.Mago_Berry) || pokemon.getItem().equals(itemList.Kasib_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Rabuta_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Custap_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Rawst_Berry) || pokemon.getItem().equals(itemList.Pinap_Berry) || pokemon.getItem().equals(itemList.Rindo_Berry) || pokemon.getItem().equals(itemList.Liechi_Berry)) {
                move.setType(pkmnType.Grass);
                if (pokemon.getItem().equals(itemList.Rawst_Berry) || pokemon.getItem().equals(itemList.Rindo_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Pinap_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Liechi_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Persim_Berry) || pokemon.getItem().equals(itemList.Hondew_Berry) || pokemon.getItem().equals(itemList.Shuca_Berry) || pokemon.getItem().equals(itemList.Apicot_Berry)) {
                move.setType(pkmnType.Ground);
                if (pokemon.getItem().equals(itemList.Persim_Berry) || pokemon.getItem().equals(itemList.Shuca_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Hondew_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Apicot_Berry)) {
                    move.setBase(100);
                }

            } else if (pokemon.getItem().equals(itemList.Aspear_Berry) || pokemon.getItem().equals(itemList.Pomeg_Berry) || pokemon.getItem().equals(itemList.Yache_Berry) || pokemon.getItem().equals(itemList.Ganlon_Berry)) {
                move.setType(pkmnType.Ice);
                if (pokemon.getItem().equals(itemList.Aspear_Berry) || pokemon.getItem().equals(itemList.Yache_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Pomeg_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Ganlon_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Chilan_Berry)) {
                move.setType(pkmnType.Normal);
                move.setBase(80);
            } else if (pokemon.getItem().equals(itemList.Oran_Berry) || pokemon.getItem().equals(itemList.Qualot_Berry) || pokemon.getItem().equals(itemList.Kebia_Berry) || pokemon.getItem().equals(itemList.Petaya_Berry)) {
                move.setType(pkmnType.Poison);
                if (pokemon.getItem().equals(itemList.Oran_Berry) || pokemon.getItem().equals(itemList.Kebia_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Qualot_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Petaya_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Sitrus_Berry) || pokemon.getItem().equals(itemList.Tamato_Berry) || pokemon.getItem().equals(itemList.Payapa_Berry) || pokemon.getItem().equals(itemList.Starf_Berry)) {
                move.setType(pkmnType.Psychic);
                if (pokemon.getItem().equals(itemList.Payapa_Berry) || pokemon.getItem().equals(itemList.Sitrus_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Tamato_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Starf_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Wiki_Berry) || pokemon.getItem().equals(itemList.Magost_Berry) || pokemon.getItem().equals(itemList.Charti_Berry) || pokemon.getItem().equals(itemList.Micle_Berry)) {
                move.setType(pkmnType.Rock);
                if (pokemon.getItem().equals(itemList.Wiki_Berry) || pokemon.getItem().equals(itemList.Charti_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Magost_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Micle_Berry)) {
                    move.setBase(100);
                }
            } else if (pokemon.getItem().equals(itemList.Razz_Berry) || pokemon.getItem().equals(itemList.Pamtre_Berry) || pokemon.getItem().equals(itemList.Babiri_Berry)) {
                move.setType(pkmnType.Steel);
                if (pokemon.getItem().equals(itemList.Razz_Berry) || pokemon.getItem().equals(itemList.Babiri_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Pamtre_Berry)) {
                    move.setBase(90);
                }
            } else if (pokemon.getItem().equals(itemList.Chesto_Berry) || pokemon.getItem().equals(itemList.Nanab_Berry) || pokemon.getItem().equals(itemList.Durin_Berry) || pokemon.getItem().equals(itemList.Passho_Berry)) {
                move.setType(pkmnType.Water);
                if (pokemon.getItem().equals(itemList.Chesto_Berry) || pokemon.getItem().equals(itemList.Passho_Berry)) {
                    move.setBase(80);
                } else if (pokemon.getItem().equals(itemList.Nanab_Berry)) {
                    move.setBase(90);
                } else if (pokemon.getItem().equals(itemList.Durin_Berry)) {
                    move.setBase(100);
                }
            } else {
                move.setBase(0);
            }
        }


        return move;
    }

    static SpeciesClass pokemonChanges(MoveClass move, String currentMove, SpeciesClass pokemon, String currentMon) {

        if ((pokemon.getItem().toString().contains("Power_") && !pokemon.getItem().equals(itemList.Power_Herb)) || pokemon.getItem().equals(itemList.Macho_Brace)) {
            pokemon.setSpd(pokemon.getSpd()/2);
        } else if (currentMon.equals("Clamperl")) {
            if (pokemon.getItem().equals(itemList.Deep_Sea_Scale)) {
                pokemon.setSpdef(pokemon.getSpdef()*2);
            } else if (pokemon.getItem().equals(itemList.Deep_Sea_Tooth)) {
                pokemon.setSpatk(pokemon.getSpatk()*2);
            }
        } else if (currentMon.contains("Pikachu") && pokemon.getItem().equals(itemList.Light_Ball)) {
            pokemon.setAtk(pokemon.getAtk() * 2);
            pokemon.setSpatk(pokemon.getSpatk() * 2);
        } else if (currentMon.equals("Ditto")) {
            if (pokemon.getItem().equals(itemList.Metal_Powder)) {
                pokemon.setDef(pokemon.getDef() * 2);
            } else if (pokemon.getItem().equals(itemList.Quick_Powder)) {
                pokemon.setSpd(pokemon.getSpd() * 2);
            }
        } else if ((currentMon.contains("Marowak") || currentMove.contains("Cubone")) && pokemon.getItem().equals(itemList.Thick_Club)) {
            pokemon.setAtk(pokemon.getAtk() * 2);
        } else if (pokemon.getItem().equals(itemList.Eviolite) && currentMon.equals(" ")) {
            pokemon.setDef(pokemon.getDef() * 1.5);
            pokemon.setSpdef(pokemon.getSpdef() * 1.5);
        } else if (pokemon.getItem().equals(itemList.Assault_Vest)) {
            pokemon.setSpdef(pokemon.getSpdef() * 1.5);
        } else if (pokemon.getItem().toString().contains("Choice_")) {
            if (pokemon.getItem().toString().contains("Band")) {
                pokemon.setAtk(pokemon.getAtk() * 1.5);
            } else if (pokemon.getItem().toString().contains("Scarf")) {
                pokemon.setSpd(pokemon.getSpd() * 1.5);
            } else if (pokemon.getItem().toString().contains("Specs")) {
                pokemon.setSpatk(pokemon.getSpatk() * 1.5);
            }
        } else if (pokemon.getItem().equals(itemList.Iron_Ball)) {
            pokemon.setSpd(pokemon.getSpd()/2);
            pokemon.setGrounded(false);
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
        if (checks.isReflect() && move.getCategory().equals(moveCtgry.Physical) && !defender.getAbility().equals(abilityList.Infiltrator)) {
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

    public static void saveEffectivenessChart() {
        try {
            // Create directories if they don't exist
            new File("Serialisation").mkdirs();

            // Create and save the chart
            HashMap<itemList, Integer> chart = createFlingList();

            FileOutputStream fileOut = new FileOutputStream("Serialisation/fling.ser");
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

    static HashMap loadMapFL() {

        try {
            FileInputStream fileInput = new FileInputStream("Serialisation/fling.ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            HashMap<itemList, Integer> moveList = (HashMap<itemList, Integer>) objectInput.readObject(); // Cast to the correct type

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
