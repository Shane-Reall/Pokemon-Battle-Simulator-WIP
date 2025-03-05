import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MoveFunctions implements Serializable {

    private static final HashMap<String, SerializableBiConsumer<MoveClass, BattleContext>> moveEffects = new HashMap<>();
    private final HashMap flingPowerMap = BattleFunctions.loadMapFL();

    public MoveFunctions() {
        initializeMoveEffects();
    }

    public static HashMap<String, SerializableBiConsumer<MoveClass, BattleContext>> getHashMap() {
        return moveEffects;
    }

    @FunctionalInterface
    public interface SerializableBiConsumer<T, U> extends Serializable {
        void accept(T t, U u);
    }

    private void initializeMoveEffects() {
        moveEffects.put("Acrobatics", (move, context) -> {
            if (context.getPokemon().getItem().equals(itemList.None)) {
                move.setBase(move.getBase() * 2);
            }
        });

        moveEffects.put("Barb_Barrage", (move, context) -> {
            if (context.getOpponent().getStated() == status.Poison || context.getOpponent().getStated() == status.Toxiced) {
                move.setBase(move.getBase() * 2);
            }
        });

        moveEffects.put("Bolt_Beak", (move, context) -> {
            if (context.getPokemon().getSpd() > context.getOpponent().getSpd()) {
                move.setBase(move.getBase() * 2);
            }
        });

        moveEffects.put("Crush_Grip", (move, context) -> move.setBase(120 * (context.getOpponent().getCurrentHp() / context.getOpponent().getHp())));

        moveEffects.put("Facade", (move, context) -> {
            if (EnumSet.of(status.Paralysis, status.Burn, status.Poison, status.Toxiced).contains(context.getPokemon().getStated())) {
                move.setBase(move.getBase() * 2);
            }
        });

        moveEffects.put("Fishious_Rend", (move, context) -> {
            if ((context.getPokemon().getSpd() > context.getOpponent().getSpd()) ^ context.getChecks().isTrickRoom()) {
                move.setBase(move.getBase() * 2);
            }
        });

        moveEffects.put("Flail", this::handleHpBasedMove);
        moveEffects.put("Revenge", this::handleHpBasedMove);

        moveEffects.put("Fling", (move, context) -> {
            move.setBase((Double) flingPowerMap.get(context.getPokemon().getItem()));
        });

        moveEffects.put("Frustration", (move, context) -> move.setBase(102));
        moveEffects.put("Return", (move, context) -> move.setBase(102));

        moveEffects.put("Fury_Cutter", (move, context) -> {
            move.setBase(Math.min(160, (int) (move.getBase() * Math.pow(2, context.getChecks().getContinueCounter()))));
        });

        moveEffects.put("Gyro_Ball", (move, context) -> move.setBase(Math.min(150, Math.round((25 * context.getOpponent().getSpd()) / context.getPokemon().getSpd()) + 1)));

        moveEffects.put("Heat_Crash", this::handleWeightBasedMove);
        moveEffects.put("Heavy_Slam", this::handleWeightBasedMove);

        moveEffects.put("Ice_Ball", this::handleRolloutMoves);
        moveEffects.put("Rollout", this::handleRolloutMoves);

        moveEffects.put("Last_Respects", (move, context) -> move.setBase(140));

        moveEffects.put("Low_Kick", this::handleWeightBasedMove);

        moveEffects.put("Natural_Gift", (move, context) -> {
            if (context.getPokemon().getItem().equals(itemList.Figy_Berry) || context.getPokemon().getItem().equals(itemList.Cornn_Berry) || context.getPokemon().getItem().equals(itemList.Tanga_Berry) || context.getPokemon().getItem().equals(itemList.Enigma_Berry)) {
                move.setType(pkmnType.Bug);
                if (context.getPokemon().getItem().equals(itemList.Figy_Berry) || context.getPokemon().getItem().equals(itemList.Tanga_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Cornn_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Enigma_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Iapapa_Berry) || context.getPokemon().getItem().equals(itemList.Spelon_Berry) || context.getPokemon().getItem().equals(itemList.Colbur_Berry) || context.getPokemon().getItem().equals(itemList.Rowap_Berry) || context.getPokemon().getItem().equals(itemList.Maranga_Berry)) {
                move.setType(pkmnType.Dark);
                if (context.getPokemon().getItem().equals(itemList.Iapapa_Berry) || context.getPokemon().getItem().equals(itemList.Colbur_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Spelon_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Rowap_Berry) || context.getPokemon().getItem().equals(itemList.Maranga_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Aguav_Berry) || context.getPokemon().getItem().equals(itemList.Nomel_Berry) || context.getPokemon().getItem().equals(itemList.Haban_Berry) || context.getPokemon().getItem().equals(itemList.Jaboca_Berry)) {
                move.setType(pkmnType.Dragon);
                if (context.getPokemon().getItem().equals(itemList.Aguav_Berry) || context.getPokemon().getItem().equals(itemList.Haban_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Nomel_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Jaboca_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Pecha_Berry) || context.getPokemon().getItem().equals(itemList.Wepear_Berry) || context.getPokemon().getItem().equals(itemList.Belue_Berry) || context.getPokemon().getItem().equals(itemList.Wacan_Berry)) {
                move.setType(pkmnType.Electric);
                if (context.getPokemon().getItem().equals(itemList.Pecha_Berry) || context.getPokemon().getItem().equals(itemList.Wacan_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Wepear_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Belue_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Roseli_Berry) || context.getPokemon().getItem().equals(itemList.Kee_Berry)) {
                move.setType(pkmnType.Fairy);
                if (context.getPokemon().getItem().equals(itemList.Roseli_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Kee_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Leppa_Berry) || context.getPokemon().getItem().equals(itemList.Kelpsy_Berry) || context.getPokemon().getItem().equals(itemList.Chople_Berry) || context.getPokemon().getItem().equals(itemList.Salac_Berry)) {
                move.setType(pkmnType.Fighting);
                if (context.getPokemon().getItem().equals(itemList.Leppa_Berry) || context.getPokemon().getItem().equals(itemList.Chople_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Kelpsy_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Salac_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Cheri_Berry) || context.getPokemon().getItem().equals(itemList.Bluk_Berry) || context.getPokemon().getItem().equals(itemList.Watmel_Berry) || context.getPokemon().getItem().equals(itemList.Occa_Berry)) {
                move.setType(pkmnType.Fire);
                if (context.getPokemon().getItem().equals(itemList.Cheri_Berry) || context.getPokemon().getItem().equals(itemList.Occa_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Bluk_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Watmel_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Lum_Berry) || context.getPokemon().getItem().equals(itemList.Grepa_Berry) || context.getPokemon().getItem().equals(itemList.Coba_Berry) || context.getPokemon().getItem().equals(itemList.Lansat_Berry)) {
                move.setType(pkmnType.Flying);
                if (context.getPokemon().getItem().equals(itemList.Lum_Berry) || context.getPokemon().getItem().equals(itemList.Coba_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Grepa_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Lansat_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Mago_Berry) || context.getPokemon().getItem().equals(itemList.Rabuta_Berry) || context.getPokemon().getItem().equals(itemList.Kasib_Berry) || context.getPokemon().getItem().equals(itemList.Custap_Berry)) {
                move.setType(pkmnType.Ghost);
                if (context.getPokemon().getItem().equals(itemList.Mago_Berry) || context.getPokemon().getItem().equals(itemList.Kasib_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Rabuta_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Custap_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Rawst_Berry) || context.getPokemon().getItem().equals(itemList.Pinap_Berry) || context.getPokemon().getItem().equals(itemList.Rindo_Berry) || context.getPokemon().getItem().equals(itemList.Liechi_Berry)) {
                move.setType(pkmnType.Grass);
                if (context.getPokemon().getItem().equals(itemList.Rawst_Berry) || context.getPokemon().getItem().equals(itemList.Rindo_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Pinap_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Liechi_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Persim_Berry) || context.getPokemon().getItem().equals(itemList.Hondew_Berry) || context.getPokemon().getItem().equals(itemList.Shuca_Berry) || context.getPokemon().getItem().equals(itemList.Apicot_Berry)) {
                move.setType(pkmnType.Ground);
                if (context.getPokemon().getItem().equals(itemList.Persim_Berry) || context.getPokemon().getItem().equals(itemList.Shuca_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Hondew_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Apicot_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Aspear_Berry) || context.getPokemon().getItem().equals(itemList.Pomeg_Berry) || context.getPokemon().getItem().equals(itemList.Yache_Berry) || context.getPokemon().getItem().equals(itemList.Ganlon_Berry)) {
                move.setType(pkmnType.Ice);
                if (context.getPokemon().getItem().equals(itemList.Aspear_Berry) || context.getPokemon().getItem().equals(itemList.Yache_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Pomeg_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Ganlon_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Chilan_Berry)) {
                move.setType(pkmnType.Normal);
                move.setBase(80);
            } else if (context.getPokemon().getItem().equals(itemList.Oran_Berry) || context.getPokemon().getItem().equals(itemList.Qualot_Berry) || context.getPokemon().getItem().equals(itemList.Kebia_Berry) || context.getPokemon().getItem().equals(itemList.Petaya_Berry)) {
                move.setType(pkmnType.Poison);
                if (context.getPokemon().getItem().equals(itemList.Oran_Berry) || context.getPokemon().getItem().equals(itemList.Kebia_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Qualot_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Petaya_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Sitrus_Berry) || context.getPokemon().getItem().equals(itemList.Tamato_Berry) || context.getPokemon().getItem().equals(itemList.Payapa_Berry) || context.getPokemon().getItem().equals(itemList.Starf_Berry)) {
                move.setType(pkmnType.Psychic);
                if (context.getPokemon().getItem().equals(itemList.Payapa_Berry) || context.getPokemon().getItem().equals(itemList.Sitrus_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Tamato_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Starf_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Wiki_Berry) || context.getPokemon().getItem().equals(itemList.Magost_Berry) || context.getPokemon().getItem().equals(itemList.Charti_Berry) || context.getPokemon().getItem().equals(itemList.Micle_Berry)) {
                move.setType(pkmnType.Rock);
                if (context.getPokemon().getItem().equals(itemList.Wiki_Berry) || context.getPokemon().getItem().equals(itemList.Charti_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Magost_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Micle_Berry)) {
                    move.setBase(100);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Razz_Berry) || context.getPokemon().getItem().equals(itemList.Pamtre_Berry) || context.getPokemon().getItem().equals(itemList.Babiri_Berry)) {
                move.setType(pkmnType.Steel);
                if (context.getPokemon().getItem().equals(itemList.Razz_Berry) || context.getPokemon().getItem().equals(itemList.Babiri_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Pamtre_Berry)) {
                    move.setBase(90);
                }
            } else if (context.getPokemon().getItem().equals(itemList.Chesto_Berry) || context.getPokemon().getItem().equals(itemList.Nanab_Berry) || context.getPokemon().getItem().equals(itemList.Durin_Berry) || context.getPokemon().getItem().equals(itemList.Passho_Berry)) {
                move.setType(pkmnType.Water);
                if (context.getPokemon().getItem().equals(itemList.Chesto_Berry) || context.getPokemon().getItem().equals(itemList.Passho_Berry)) {
                    move.setBase(80);
                } else if (context.getPokemon().getItem().equals(itemList.Nanab_Berry)) {
                    move.setBase(90);
                } else if (context.getPokemon().getItem().equals(itemList.Durin_Berry)) {
                    move.setBase(100);
                }
            } else {
                move.setBase(0);
            }
        });

        moveEffects.put("Payback", (move, context) -> {
            if (context.getOpponent().getSpd() >= context.getPokemon().getSpd()) {
                move.setBase(move.getBase() * 2);
            }
        });

        moveEffects.put("Psyblade", (move, context) -> {
            if (context.getChecks().getTerrain() == terrainType.Electric) {
                move.setBase(120);
            }
        });

        moveEffects.put("Pursuit", (move, context) -> {
            if (context.getChecks().isSwitching()) {
                move.setBase(move.getBase() * 2);
            }
        });

        moveEffects.put("Smelling_Salts", (move, context) -> {
            if (context.getOpponent().getStated().equals(status.Paralysis)) {
                move.setBase(move.getBase() * 2);
            }
        });

        moveEffects.put("Wake_Up_Slap", (move, context) -> {
            if (context.getOpponent().getStated().equals(status.Sleep)) {
                move.setBase(move.getBase() * 2);
            }
        });

        moveEffects.put("Steel_Roller", (move, context) -> {
            if (context.getChecks().getTerrain() == terrainType.none) {
                move.setBase(0);
            }
        });

        moveEffects.put("Veevee_Volley", (move, context) -> move.setBase(102));


    }

    public static void processMove(String moveName, MoveClass move, SpeciesClass pokemon, SpeciesClass opponent, Checks checks, HashMap moveFunctions) {
        BattleContext context = new BattleContext(pokemon, opponent, checks);
        SerializableBiConsumer<MoveClass, BattleContext> moveFunction = (SerializableBiConsumer<MoveClass, BattleContext>) moveFunctions.get(moveName);

        if (moveFunction != null) {
            moveFunction.accept(move, context);
        } else {
            System.out.println("Move function not found for: " + moveName);
        }
    }

    private void handleHpBasedMove(MoveClass move, BattleContext context) {
        double hpRatio = (double) context.getPokemon().getCurrentHp() / context.getPokemon().getHp();
        int basePower = hpRatio >= 0.688 ? 20 :
                hpRatio >= 0.354 ? 40 :
                        hpRatio >= 0.208 ? 80 :
                                hpRatio >= 0.104 ? 100 :
                                        hpRatio >= 0.042 ? 150 : 200;
        move.setBase(basePower);
    }

    private void handleWeightBasedMove(MoveClass move, BattleContext context) {
        double weightRatio = context.getPokemon().getWeight() / context.getOpponent().getWeight();
        int basePower = weightRatio >= 2.0 ? 120 :
                weightRatio >= 1.5 ? 100 :
                        weightRatio >= 1.0 ? 80 :
                                weightRatio >= 0.5 ? 60 : 40;
        move.setBase(basePower);
    }

    private void handleRolloutMoves(MoveClass move, BattleContext context) {
        move.setBase(Math.min(480, (int) (move.getBase() * Math.pow(2, context.getChecks().getContinueCounter()))));
    }
}
