import java.io.Serializable;
import java.util.*;

public class AbilityFunctions implements Serializable {

    private static final HashMap<abilityList, SerializableBiConsumer<abilityList, BattleContext>> abilityEffects = new HashMap<>();

    private String bullectList[] = {
            "Acid_Spray", "Aura_Sphere", "Barrage" ,"Beak_Blast",
            "Bullet_Seed","Egg_Bomb","Electro_Ball","Energy_Ball",
            "Focus_Blast","Gyro_Ball","Ice_Ball","Magnet_Bomb",
            "Mist_Ball","Mud_Bomb","Octazooka","Pollen_Puff",
            "Rock_Blast","Rock_Wrecker","Searing_Shot","Seed_Bomb",
            "Shadow_Ball","Sludge_Bomb","Weather_Ball","Zap_Cannon"
    };

    private String priorityList[] = {
            "Baneful_Bunker", "Burning_Bulwark", "Detect", "Endure",
            "Kings_Shield", "Obstruct", "Protect", "Spiky_Shield",
            "Silk_Trap", "Fake_Out", "Quick_Guard", "Upper_Hand",
            "Wide_Guard_,Ally_Switch", "Extreme_Speed", "Feint",
            "First_Impression", "Follow_Me", "Rage_Powder", "Accelerock",
            "Aqua_Jet", "Baby-Doll_Eyes", "Bullet_Punch", "Ice_Shard",
            "Jet_Punch", "Mach_Punch", "Quick_Attack", "Shadow_Sneak",
            "Sucker_Punch", "Thunderclap", "Vacuum_Wave", "Water_Shuriken"
    };

    private String punchList[] = {
            "Bullet_Punch", "Comet_Punch", "Dizzy_Punch", "Double_Iron_Bash",
            "Drain_Punch", "Dynamic_Punch", "Fire_Punch", "Focus_Punch",
            "Hammer_Arm", "Headlong_Rush", "Ice_Hammer", "Ice_Punch",
            "Jet_Punch", "Mach_Punch", "Mega_Punch", "Meteor_Mash",
            "Plasma_Fists", "Power_Up_Punch", "Rage_Fist", "Shadow_Punch",
            "Sky_Uppercut", "Surging_Strikes", "Thunder_Punch", "Wicked_Blow"
    };

    private String pulseList[] = {
            "Aura_Sphere",  "Dark_Pulse",  "Dragon_Pulse",  "Origin_Pulse",
            "Terrain_Pulse",  "Water_Pulse",  "Heal_Pulse"
    };

    private String recoilList[] = {
            "Brave_Bird", "Chloroblast", "Double-Edge", "Flare_Blitz", "Head_Charge",
            "Head_Smash", "Light_of_Ruin", "Self-Destruct", "Shadow_End",
            "Shadow_Rush", "Struggle", "Submission", "Take_Down",
            "Volt_Tackle", "Wave_Crash", "Wild_Charge", "Wood_Hammer",
            "Axe_Kick", "High_Jump_Kick", "Jump_Kick", "Supercell_Slam"
    };

    private String sliceList[] = {
            "Aerial_Ace", "Air_Cutter",
            "Air_Slash", "Aqua_Cutter", "Behemoth_Blade", "Bitter_Blade",
            "Ceaseless_Edge", "Cross_Poison", "Cut", "Fury_Cutter",
            "Kowtow_Cleave", "Leaf_Blade", "Mighty_Cleave", "Night_Slash",
            "Population_Bomb", "Psyblade", "Psycho_Cut", "Razor_Leaf",
            "Razor_Shell", "Sacred_Sword", "Secret_Sword", "Slash",
            "Solar_Blade", "Stone_Axe", "Tachyon_Cutter", "X_Scissor"
    };

    private String sheerForceList[] = {
            "Acid","Acid_Spray","Air_Slash","Anchor_Shot",
            "Ancient_Power","Astonish","Aurora_Beam","Bite",
            "Blaze_Kick","Blizzard","Blue_Flare","Body_Slam",
            "Bolt_Strike","Bone_Club","Bounce","Bubble",
            "Bubble_Beam","Bug_Buzz","Bulldoze","Charge_Beam",
            "Chatter","Confusion","Constrict","Cross_Poison",
            "Crunch","Crush_Claw","Dark_Pulse","Diamond_Storm",
            "Discharge","Dizzy_Punch","Dragon_Breath","Dragon_Rush",
            "Dynamic_Punch","Earth_Power","Electroweb","Ember",
            "Energy_Ball","Extrasensory","Fake_Out","Fiery_Dance",
            "Fire_Blast","Fire_Fang","Fire_Lash","Fire_Punch",
            "Flame_Charge","Flame_Wheel","Flamethrower","Flare_Blitz",
            "Flash_Cannon","Focus_Blast","Force_Palm","Freeze-Dry",
            "Freeze_Shock","Genesis_Supernova","Glaciate","Gunk_Shot",
            "Headbutt","Heart_Stamp","Heat_Wave","Hurricane",
            "Hyper_Fang","Ice_Beam","Ice_Burn","Ice_Fang",
            "Ice_Punch","Icicle_Crash","Icy_Wind","Inferno",
            "Iron_Head","Iron_Tail","Lava_Plume","Leaf_Tornado",
            "Lick","Liquidation","Low_Sweep","Lunge",
            "Luster_Purge","Metal_Claw","Meteor_Mash",
            "Mirror_Shot","Mist_Ball","Moonblast","Mud_Bomb",
            "Mud_Shot","Mud-Slap","Muddy_Water","Mystical_Fire",
            "Needle_Arm","Night_Daze","Nuzzle","Octazooka",
            "Ominous_Wind","Play_Rough","Poison_Fang","Poison_Jab",
            "Poison_Sting","Poison_Tail","Powder_Snow","Power-Up_Punch",
            "Psybeam","Psychic","Razor_Shell","Relic_Song",
            "Rock_Climb","Rock_Slide","Rock_Smash","Rock_Tomb",
            "Rolling_Kick","Sacred_Fire","Scald","Searing_Shot",
            "Secret_Power","Seed_Flare","Shadow_Ball","Shadow_Bone",
            "Signal_Beam","Silver_Wind","Sky_Attack","Sludge",
            "Sludge_Bomb","Sludge_Wave","Smog","Snarl",
            "Snore","Spark","Spirit_Shackle","Steam_Eruption",
            "Steamroller","Steel_Wing","Stoked_Sparksurfer","Stomp",
            "Struggle_Bug","Thunder","Thunder_Fang","Thunder_Punch",
            "Thunder_Shock","Thunderbolt","Tri_Attack","Trop_Kick",
            "Twineedle","Twister","Volt_Tackle","Water_Pulse",
            "Waterfall","Zap_Cannon","Zen_Headbutt","Zing_Zap"
    };

    private String biteList[] = {
            "Bite", "Crunch", "Fire_Fang", "Fishious_Rend",
            "Hyper_Fang", "Ice_Fang", "Jaw_Lock", "Poison_Fang",
            "Psychic_Fangs", "Thunder_Fang"
    };

    private String OHKOList[] = {
            "Fissure", "Guillotine", "Horn_Drill", "Sheer_Cold"
    };

    public AbilityFunctions() {
        initializeAbilityEffects();
    }

    public static HashMap<abilityList, SerializableBiConsumer<abilityList, BattleContext>> getHashMap() {
        return abilityEffects;
    }

    @FunctionalInterface
    public interface SerializableBiConsumer<T, U> extends Serializable {
        void accept(T t, U u);
    }

    private void initializeAbilityEffects() {

        abilityEffects.put(abilityList.Aerilate, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Aerilate) {
                if (context.getMove().getType() == pkmnType.Normal) {
                    context.getMove().setBase(context.getMove().getBase() * 1.2);
                    context.getMove().setType(pkmnType.Flying);
                }
            }
        });

        abilityEffects.put(abilityList.Analytic, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Analytic) {
                if ((context.getPokemon().getSpd() < context.getOpponent().getSpd()) ^ context.getChecks().isTrickRoom()) {
                    context.getMove().setBase(context.getMove().getBase() * 1.3);
                }
            }
        });

        abilityEffects.put(abilityList.Aura_Break, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Fairy_Aura || context.getPokemon().getAbility() == abilityList.Fairy_Aura) {
                if (context.getMove().getType() == pkmnType.Fairy) {
                    context.getMove().setBase((context.getMove().getBase() / (4.0/3.0)) * 0.75);
                }
            } else if (context.getOpponent().getAbility() == abilityList.Dark_Aura || context.getPokemon().getAbility() == abilityList.Dark_Aura) {
                if (context.getMove().getType() == pkmnType.Dark) {
                    context.getMove().setBase((context.getMove().getBase() / (4.0/3.0)) * 0.75);
                }
            }
        });

        abilityEffects.put(abilityList.Beads_of_Ruin, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Beads_of_Ruin) {
                context.getOpponent().setSpdef(context.getOpponent().getSpdef() * 0.75);
            }
            if (context.getOpponent().getAbility() == abilityList.Beads_of_Ruin) {
                context.getPokemon().setSpdef(context.getPokemon().getSpdef() * 0.75);
            }
        });

        abilityEffects.put(abilityList.Blaze, (_, context) -> {
            if ((context.getPokemon().getCurrentHp() <= context.getPokemon().getHp() * (1.0/3.0)) && context.getMove().getType() == pkmnType.Fire) {
                context.getMove().setBase(context.getMove().getBase() * 1.5);
            }
        });

        abilityEffects.put(abilityList.Bulletproof, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Bulletproof) {
                if (Arrays.stream(bullectList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Chlorophyll, (_, context) -> {
            if (sunCheck(context)) {
                if (context.getPokemon().getAbility() == abilityList.Chlorophyll) {
                    context.getPokemon().setSpd(context.getPokemon().getSpd() * 2);
                }
                if (context.getOpponent().getAbility() == abilityList.Chlorophyll) {
                    context.getOpponent().setSpd(context.getOpponent().getSpd() * 2);
                }
            }
        });

        abilityEffects.put(abilityList.Damp, (_, context) -> {
            if (context.getCurrentMove().equals("Self-Destruct") || context.getCurrentMove().equals("Explosion") || context.getCurrentMove().equals("Mind_Blown") || context.getCurrentMove().equals("Misty_Explosion")) {
                context.getMove().setBase(0);
            }
        });

        abilityEffects.put(abilityList.Dark_Aura, (_, context) -> {
            if (context.getMove().getType() == pkmnType.Dark) {
                context.getMove().setBase(context.getMove().getBase() * 1.33);
            }
        });

        abilityEffects.put(abilityList.Dazzling, (_, context) -> {
            if (Arrays.stream(priorityList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getOpponent().getAbility() == abilityList.Dazzling) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Armor_Tail, (_, context) -> {
            if (Arrays.stream(priorityList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getOpponent().getAbility() == abilityList.Armor_Tail) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Queenly_Majesty, (_, context) -> {
            if (Arrays.stream(priorityList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getOpponent().getAbility() == abilityList.Queenly_Majesty) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Defeatist, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Defeatist) {
                if (context.getPokemon().getCurrentHp() <= context.getPokemon().getHp() * 0.5) {
                    context.getPokemon().setAtk(context.getPokemon().getAtk() * 0.5);
                    context.getPokemon().setSpatk(context.getPokemon().getSpatk() * 0.5);
                }
            }
        });

        abilityEffects.put(abilityList.Dragons_Maw, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Dragons_Maw) {
                if (context.getMove().getType() == pkmnType.Dragon) {
                    context.getPokemon().setAtk(context.getPokemon().getAtk() * 1.5);
                    context.getPokemon().setSpatk(context.getPokemon().getSpatk() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Dry_Skin, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Dry_Skin) {
                if (context.getMove().getType() == pkmnType.Water) {
                    context.getMove().setBase(0);
                } else if (context.getMove().getType() == pkmnType.Fire) {
                    context.getMove().setBase(context.getMove().getBase() * 1.25);
                }
            }
        });

        abilityEffects.put(abilityList.Earth_Eater, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Earth_Eater) {
                if (context.getMove().getType() == pkmnType.Ground) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Levitate, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Levitate) {
                if (context.getMove().getType() == pkmnType.Ground) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Fairy_Aura, (_, context) -> {
            if (context.getMove().getType() == pkmnType.Fairy) {
                context.getMove().setBase(context.getMove().getBase() * 1.33);
            }
        });

        abilityEffects.put(abilityList.Flare_Boost, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Flare_Boost) {
                if (context.getPokemon().getStated() == status.Burn) {
                    context.getPokemon().setSpatk(context.getPokemon().getSpatk() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Flash_Fire, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Flash_Fire) {
                if (context.getMove().getType() == pkmnType.Fire) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Galvanize, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Galvanize) {
                if (context.getMove().getType() == pkmnType.Normal) {
                    context.getMove().setBase(context.getMove().getBase() * 1.2);
                    context.getMove().setType(pkmnType.Electric);
                }
            }
        });

        abilityEffects.put(abilityList.Gorilla_Tactics, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Gorilla_Tactics) {
                context.getPokemon().setAtk(context.getPokemon().getAtk() * 1.5);
            }
        });

        abilityEffects.put(abilityList.Grass_Pelt, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Grass_Pelt) {
                if (context.getChecks().getTerrain() == terrainType.Grass) {
                    context.getPokemon().setDef(context.getPokemon().getDef() * 1.5);
                }
            }
            if (context.getOpponent().getAbility() == abilityList.Grass_Pelt) {
                if (context.getChecks().getTerrain() == terrainType.Grass) {
                    context.getOpponent().setDef(context.getOpponent().getDef() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Guts, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Guts) {
                if (context.getPokemon().getStated() != status.none) {
                    context.getPokemon().setAtk(context.getPokemon().getAtk() * 1.5);
                }
            }
            if (context.getOpponent().getAbility() == abilityList.Guts) {
                if (context.getOpponent().getStated() != status.none) {
                    context.getOpponent().setAtk(context.getOpponent().getAtk() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Heatproof, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Heatproof) {
                if (context.getMove().getType() == pkmnType.Fire) {
                    context.getPokemon().setAtk(context.getPokemon().getAtk() * 0.5);
                    context.getPokemon().setSpatk(context.getPokemon().getSpatk() * 0.5);
                }
            }
        });

        abilityEffects.put(abilityList.Heavy_Metal, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Heavy_Metal) {
                context.getPokemon().setWeight(context.getPokemon().getWeight() * 2);
            }
            if (context.getOpponent().getAbility() == abilityList.Heavy_Metal) {
                context.getOpponent().setWeight(context.getOpponent().getWeight() * 2);
            }
        });

        abilityEffects.put(abilityList.Huge_Power, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Huge_Power) {
                context.getPokemon().setAtk(context.getPokemon().getAtk() * 2);
            }
            if (context.getOpponent().getAbility() == abilityList.Huge_Power) {
                context.getOpponent().setAtk(context.getOpponent().getAtk() * 2);
            }
        });

        abilityEffects.put(abilityList.Hustle, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Hustle) {
                context.getPokemon().setAtk(context.getPokemon().getAtk() * 1.5);
            }
            if (context.getOpponent().getAbility() == abilityList.Hustle) {
                context.getOpponent().setAtk(context.getOpponent().getAtk() * 1.5);
            }
        });

        abilityEffects.put(abilityList.Iron_Fist, (_, context) -> {
            if (Arrays.stream(punchList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getPokemon().getAbility() == abilityList.Iron_Fist) {
                    context.getMove().setBase(context.getMove().getBase() * 1.2);
                }
            }
        });

        abilityEffects.put(abilityList.Libero, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Libero) {
                context.getPokemon().setType1(context.getMove().getType());
                context.getPokemon().setType2(pkmnType.Typeless);
            }
        });

        abilityEffects.put(abilityList.Light_Metal, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Light_Metal) {
                context.getPokemon().setWeight(context.getPokemon().getWeight() * 0.5);
            }
            if (context.getOpponent().getAbility() == abilityList.Light_Metal) {
                context.getOpponent().setWeight(context.getOpponent().getWeight() * 0.5);
            }
        });

        abilityEffects.put(abilityList.Lightning_Rod, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Lightning_Rod) {
                if (context.getMove().getType() == pkmnType.Electric) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Liquid_Voice, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Liquid_Voice) {
                if (context.getMove().isSound()) {
                    context.getMove().setType(pkmnType.Water);
                }
            }
        });

        abilityEffects.put(abilityList.Long_Reach, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Long_Reach) {
                context.getMove().setContact(false);
            }
        });

        abilityEffects.put(abilityList.Marvel_Scale, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Marvel_Scale) {
                if (context.getOpponent().getStated() != status.none) {
                    context.getOpponent().setDef(context.getOpponent().getDef() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Mega_Launcher, (_, context) -> {
            if (Arrays.stream(pulseList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getPokemon().getAbility() == abilityList.Mega_Launcher) {
                    context.getMove().setBase(context.getMove().getBase() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Motor_Drive, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Motor_Drive) {
                if (context.getMove().getType() == pkmnType.Electric) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Normalize, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Normalize) {
                context.getMove().setBase(context.getMove().getBase() * 1.2);
                context.getMove().setType(pkmnType.Normal);
            }
        });

        abilityEffects.put(abilityList.Hadron_Engine, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Hadron_Engine && context.getChecks().getTerrain() == terrainType.Electric) {
                context.getPokemon().setSpatk(Math.round(context.getPokemon().getSpatk() * (4.0/3.0)));
            }
        });

        abilityEffects.put(abilityList.Orichalcum_Pulse, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Orichalcum_Pulse && (sunCheck(context))) {
                context.getPokemon().setAtk(Math.round(context.getPokemon().getAtk() * (4.0/3.0)));
            }
        });

        abilityEffects.put(abilityList.Overgrow, (_, context) -> {
            if ((context.getPokemon().getCurrentHp() <= context.getPokemon().getHp() * (1.0/3.0)) && context.getMove().getType() == pkmnType.Grass) {
                context.getMove().setBase(context.getMove().getBase() * 1.5);
            }
        });

        abilityEffects.put(abilityList.Pixilate, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Pixilate) {
                if (context.getMove().getType() == pkmnType.Normal) {
                    context.getMove().setBase(context.getMove().getBase() * 1.2);
                    context.getMove().setType(pkmnType.Fairy);
                }
            }
        });

        abilityEffects.put(abilityList.Protosynthesis, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Protosynthesis && (sunCheck(context))) {
                double atk = context.getPokemon().getAtk();
                double def = context.getPokemon().getDef();
                double spatk = context.getPokemon().getSpatk();
                double spdef = context.getPokemon().getSpdef();
                double spd = context.getPokemon().getSpd();

                double max = Math.max(atk, Math.max(def, Math.max(spatk, Math.max(spdef, spd))));

                if (max == atk) {
                    context.getPokemon().setAtk(Math.round(context.getPokemon().getAtk() * 1.3));
                } else if (max == def) {
                    context.getPokemon().setDef(Math.round(context.getPokemon().getDef() * 1.3));
                } else if (max == spatk) {
                    context.getPokemon().setSpatk(Math.round(context.getPokemon().getSpatk() * 1.3));
                } else if (max == spdef) {
                    context.getPokemon().setSpdef(Math.round(context.getPokemon().getSpdef() * 1.3));
                } else if (max == spd) {
                    context.getPokemon().setSpd(Math.round(context.getPokemon().getSpd() * 1.5));
                }
            } else if (context.getOpponent().getAbility() == abilityList.Protosynthesis && (sunCheck(context))) {
                double atk = context.getOpponent().getAtk();
                double def = context.getOpponent().getDef();
                double spatk = context.getOpponent().getSpatk();
                double spdef = context.getOpponent().getSpdef();
                double spd = context.getOpponent().getSpd();

                double max = Math.max(atk, Math.max(def, Math.max(spatk, Math.max(spdef, spd))));

                if (max == atk) {
                    context.getPokemon().setAtk(Math.round(context.getPokemon().getAtk() * 1.3));
                } else if (max == def) {
                    context.getPokemon().setDef(Math.round(context.getPokemon().getDef() * 1.3));
                } else if (max == spatk) {
                    context.getPokemon().setSpatk(Math.round(context.getPokemon().getSpatk() * 1.3));
                } else if (max == spdef) {
                    context.getPokemon().setSpdef(Math.round(context.getPokemon().getSpdef() * 1.3));
                } else if (max == spd) {
                    context.getPokemon().setSpd(Math.round(context.getPokemon().getSpd() * 1.5));
                }
            }
        });

        abilityEffects.put(abilityList.Quark_Drive, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Quark_Drive && context.getChecks().getTerrain() == terrainType.Electric) {
                double atk = context.getPokemon().getAtk();
                double def = context.getPokemon().getDef();
                double spatk = context.getPokemon().getSpatk();
                double spdef = context.getPokemon().getSpdef();
                double spd = context.getPokemon().getSpd();

                double max = Math.max(atk, Math.max(def, Math.max(spatk, Math.max(spdef, spd))));

                if (max == atk) {
                    context.getPokemon().setAtk(Math.round(context.getPokemon().getAtk() * 1.3));
                } else if (max == def) {
                    context.getPokemon().setDef(Math.round(context.getPokemon().getDef() * 1.3));
                } else if (max == spatk) {
                    context.getPokemon().setSpatk(Math.round(context.getPokemon().getSpatk() * 1.3));
                } else if (max == spdef) {
                    context.getPokemon().setSpdef(Math.round(context.getPokemon().getSpdef() * 1.3));
                } else if (max == spd) {
                    context.getPokemon().setSpd(Math.round(context.getPokemon().getSpd() * 1.5));
                }
            } else if (context.getOpponent().getAbility() == abilityList.Protosynthesis && context.getChecks().getTerrain() == terrainType.Electric) {
                double atk = context.getOpponent().getAtk();
                double def = context.getOpponent().getDef();
                double spatk = context.getOpponent().getSpatk();
                double spdef = context.getOpponent().getSpdef();
                double spd = context.getOpponent().getSpd();

                double max = Math.max(atk, Math.max(def, Math.max(spatk, Math.max(spdef, spd))));

                if (max == atk) {
                    context.getPokemon().setAtk(Math.round(context.getPokemon().getAtk() * 1.3));
                } else if (max == def) {
                    context.getPokemon().setDef(Math.round(context.getPokemon().getDef() * 1.3));
                } else if (max == spatk) {
                    context.getPokemon().setSpatk(Math.round(context.getPokemon().getSpatk() * 1.3));
                } else if (max == spdef) {
                    context.getPokemon().setSpdef(Math.round(context.getPokemon().getSpdef() * 1.3));
                } else if (max == spd) {
                    context.getPokemon().setSpd(Math.round(context.getPokemon().getSpd() * 1.5));
                }
            }
        });

        abilityEffects.put(abilityList.Punk_Rock, (_, context) -> {
            if (context.getMove().isSound()) {
                if (context.getPokemon().getAbility() == abilityList.Punk_Rock) {
                    context.getMove().setBase(context.getMove().getBase() * 1.3);
                } else {
                    context.getMove().setBase(context.getMove().getBase() * 0.5);
                }
            }
        });

        abilityEffects.put(abilityList.Pure_Power, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Pure_Power) {
                context.getPokemon().setAtk(context.getPokemon().getAtk() * 2);
            }
            if (context.getOpponent().getAbility() == abilityList.Pure_Power) {
                context.getOpponent().setAtk(context.getOpponent().getAtk() * 2);
            }
        });

        abilityEffects.put(abilityList.Purifying_Salt, (_, context) -> {
            if (context.getMove().getType() == pkmnType.Ghost) {
                if (context.getOpponent().getAbility() == abilityList.Purifying_Salt) {
                    context.getPokemon().setSpatk(context.getPokemon().getSpatk() * 0.5);
                    context.getPokemon().setAtk(context.getPokemon().getAtk() * 0.5);
                }
            }
        });

        abilityEffects.put(abilityList.Quick_Feet, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Quick_Feet && context.getOpponent().getStated() != status.none) {
                context.getOpponent().setSpd(context.getOpponent().getSpd() * 1.5);
            }
            if (context.getPokemon().getAbility() == abilityList.Quick_Feet && context.getPokemon().getStated() != status.none) {
                context.getPokemon().setSpd(context.getPokemon().getSpd() * 1.5);
            }
        });

        abilityEffects.put(abilityList.Reckless, (_, context) -> {
            if (Arrays.stream(recoilList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getPokemon().getAbility() == abilityList.Reckless) {
                    context.getMove().setBase(context.getMove().getBase() * 1.2);
                }
            }
        });

        abilityEffects.put(abilityList.Refrigerate, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Refrigerate) {
                if (context.getMove().getType() == pkmnType.Normal) {
                    context.getMove().setBase(context.getMove().getBase() * 1.2);
                    context.getMove().setType(pkmnType.Ice);
                }
            }
        });

        abilityEffects.put(abilityList.Sand_Force, (_, context) -> {
            if (context.getChecks().getWeather() == weatherType.sand) {
                if (context.getPokemon().getAbility() == abilityList.Sand_Force) {
                    if (context.getMove().getType() == pkmnType.Ground || context.getMove().getType() == pkmnType.Rock || context.getMove().getType() == pkmnType.Steel) {
                        context.getMove().setBase(context.getMove().getBase() * 1.3);
                    }
                }
            }
        });

        abilityEffects.put(abilityList.Sand_Rush, (_, context) -> {
            if (context.getChecks().getWeather() == weatherType.sand) {
                if (context.getPokemon().getAbility() == abilityList.Sand_Rush) {
                    context.getPokemon().setSpd(context.getPokemon().getSpd() * 2);
                }
            }
        });

        abilityEffects.put(abilityList.Sap_Sipper, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Sap_Sipper) {
                if (context.getMove().getType() == pkmnType.Grass) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Sharpness, (_, context) -> {
            if (Arrays.stream(sliceList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getPokemon().getAbility() == abilityList.Sharpness) {
                    context.getMove().setBase(context.getMove().getBase() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Sheer_Force, (_, context) -> {
            if (Arrays.stream(sheerForceList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getPokemon().getAbility() == abilityList.Sheer_Force) {
                    context.getMove().setBase(context.getMove().getBase() * 1.3);
                }
            }
        });

        abilityEffects.put(abilityList.Slow_Start, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Slow_Start) {
                context.getOpponent().setSpd(context.getOpponent().getSpd() * 0.5);
                context.getOpponent().setAtk(context.getOpponent().getAtk() * 0.5);
            }
            if (context.getPokemon().getAbility() == abilityList.Slow_Start) {
                context.getPokemon().setSpd(context.getPokemon().getSpd() * 0.5);
                context.getPokemon().setAtk(context.getPokemon().getAtk() * 0.5);
            }
        });

        abilityEffects.put(abilityList.Slush_Rush, (_, context) -> {
            if (context.getChecks().getWeather() == weatherType.snow) {
                if (context.getOpponent().getAbility() == abilityList.Slush_Rush) {
                    context.getOpponent().setSpd(context.getOpponent().getSpd() * 2);
                }
                if (context.getPokemon().getAbility() == abilityList.Slush_Rush) {
                    context.getPokemon().setSpd(context.getPokemon().getSpd() * 2);
                }
            }
        });

        abilityEffects.put(abilityList.Sniper, (_, context) -> {
            if (context.getChecks().isCrit() && context.getPokemon().getAbility() == abilityList.Sniper) {
                context.getMove().setBase(context.getMove().getBase() * 1.5);
            }
        });

        abilityEffects.put(abilityList.Solar_Power, (_, context) -> {
            if (sunCheck(context)) {
                if (context.getOpponent().getAbility() == abilityList.Solar_Power) {
                    context.getOpponent().setSpatk(context.getOpponent().getSpatk() * 1.5);
                }
                if (context.getPokemon().getAbility() == abilityList.Solar_Power) {
                    context.getPokemon().setSpatk(context.getPokemon().getSpatk() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Soundproof, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Soundproof) {
                if (context.getMove().isSound()) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Stakeout, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Stakeout) {
                if (context.getChecks().isSwitching()) {
                    context.getMove().setBase(context.getMove().getBase() * 2);
                }
            }
        });

        abilityEffects.put(abilityList.Storm_Drain, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Storm_Drain) {
                if (context.getMove().getType() == pkmnType.Water) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Strong_Jaw, (_, context) -> {
            if (Arrays.stream(biteList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getPokemon().getAbility() == abilityList.Strong_Jaw) {
                    context.getMove().setBase(context.getMove().getBase() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Sturdy, (_, context) -> {
            if (Arrays.stream(OHKOList).anyMatch(i -> i.equals(context.getCurrentMove()))) {
                if (context.getOpponent().getAbility() == abilityList.Sturdy) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Surge_Surfer, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Surge_Surfer) {
                if (context.getChecks().getTerrain() == terrainType.Electric) {
                    context.getPokemon().setSpd(context.getPokemon().getSpd() * 2);
                }
            }
            if (context.getOpponent().getAbility() == abilityList.Surge_Surfer) {
                if (context.getChecks().getTerrain() == terrainType.Electric) {
                    context.getOpponent().setSpd(context.getOpponent().getSpd() * 2);
                }
            }
        });

        abilityEffects.put(abilityList.Swarm, (_, context) -> {
            if ((context.getPokemon().getCurrentHp() <= context.getPokemon().getHp() * (1.0/3.0)) && context.getMove().getType() == pkmnType.Bug) {
                context.getMove().setBase(context.getMove().getBase() * 1.5);
            }
        });

        abilityEffects.put(abilityList.Swift_Swim, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Swift_Swim) {
                if (rainCheck(context)) {
                    context.getPokemon().setSpd(context.getPokemon().getSpd() * 2);
                }
            }
            if (context.getOpponent().getAbility() == abilityList.Swift_Swim) {
                if (rainCheck(context)) {
                    context.getOpponent().setSpd(context.getOpponent().getSpd() * 2);
                }
            }
        });

        abilityEffects.put(abilityList.Sword_of_Ruin, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Sword_of_Ruin) {
                context.getOpponent().setDef(context.getOpponent().getDef() * 0.75);
            }
            if (context.getOpponent().getAbility() == abilityList.Sword_of_Ruin) {
                context.getPokemon().setDef(context.getPokemon().getDef() * 0.75);
            }
        });

        abilityEffects.put(abilityList.Tablets_of_Ruin, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Tablets_of_Ruin) {
                context.getOpponent().setAtk(context.getOpponent().getAtk() * 0.75);
            }
            if (context.getOpponent().getAbility() == abilityList.Tablets_of_Ruin) {
                context.getPokemon().setAtk(context.getPokemon().getAtk() * 0.75);
            }
        });

        abilityEffects.put(abilityList.Technician, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Technician) {
                if (context.getMove().getBase() <= 60) {
                    context.getMove().setBase(context.getMove().getBase() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Thick_Fat, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Thick_Fat) {
                if ((context.getMove().getType() == pkmnType.Fire) || (context.getMove().getType() == pkmnType.Ice)) {
                    context.getPokemon().setAtk(context.getPokemon().getAtk() * 0.5);
                    context.getPokemon().setSpatk(context.getPokemon().getSpatk() * 0.5);
                }
            }
        });

        abilityEffects.put(abilityList.Torrent, (_, context) -> {
            if ((context.getPokemon().getCurrentHp() <= context.getPokemon().getHp() * (1.0/3.0)) && context.getMove().getType() == pkmnType.Water) {
                context.getMove().setBase(context.getMove().getBase() * 1.5);
            }
        });

        abilityEffects.put(abilityList.Tough_Claws, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Tough_Claws) {
                if ((context.getMove().isContact())) {
                    context.getMove().setBase(Math.round(context.getMove().getBase() * 1.3));
                }
            }
        });

        abilityEffects.put(abilityList.Toxic_Boost, (_, context) -> {
            if ((context.getPokemon().getStated() == status.Poison) || (context.getPokemon().getStated() == status.Toxiced)) {
                if (context.getMove().getCategory() == moveCtgry.Physical) {
                    context.getMove().setBase(context.getMove().getBase() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Transistor, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Transistor) {
                if (context.getMove().getType() == pkmnType.Electric) {
                    context.getPokemon().setAtk(context.getPokemon().getAtk() * 1.5);
                    context.getPokemon().setSpatk(context.getPokemon().getSpatk() * 1.5);
                }
            }
        });

        abilityEffects.put(abilityList.Vessel_of_Ruin, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Vessel_of_Ruin) {
                context.getOpponent().setDef(context.getOpponent().getDef() * 0.75);
            }
            if (context.getOpponent().getAbility() == abilityList.Vessel_of_Ruin) {
                context.getPokemon().setDef(context.getPokemon().getDef() * 0.75);
            }
        });

        abilityEffects.put(abilityList.Volt_Absorb, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Volt_Absorb) {
                if (context.getMove().getType() == pkmnType.Electric) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Water_Absorb, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Water_Absorb) {
                if (context.getMove().getType() == pkmnType.Water) {
                    context.getMove().setBase(0);
                }
            }
        });

        abilityEffects.put(abilityList.Water_Bubble, (_, context) -> {
            if (context.getPokemon().getAbility() == abilityList.Water_Bubble) {
                if (context.getMove().getType() == pkmnType.Water) {
                    context.getMove().setBase(context.getMove().getBase() * 2);
                }
            }
            if (context.getOpponent().getAbility() == abilityList.Water_Bubble) {
                if (context.getMove().getType() == pkmnType.Fire) {
                    context.getMove().setBase(context.getMove().getBase() * 0.5);
                }
            }
        });

        abilityEffects.put(abilityList.Well_Baked_Body, (_, context) -> {
            if (context.getOpponent().getAbility() == abilityList.Well_Baked_Body) {
                if (context.getMove().getType() == pkmnType.Fire) {
                    context.getMove().setBase(0);
                }
            }
        });
    }

    private boolean sunCheck(BattleContext context) {
        return context.getChecks().getWeather() == weatherType.sun || context.getChecks().getWeather() == weatherType.DL;
    }

    private boolean rainCheck(BattleContext context) {
        return context.getChecks().getWeather() == weatherType.rain || context.getChecks().getWeather() == weatherType.PS;
    }

    public static void processAbility(MoveClass move, SpeciesClass pokemon, SpeciesClass opponent, Checks checks, HashMap moveFunctions, abilityList ability, String currentMove) {
        BattleContext context = new BattleContext(pokemon, opponent, checks, move, currentMove);
        SerializableBiConsumer<abilityList, BattleContext> moveFunction = (SerializableBiConsumer<abilityList, BattleContext>) moveFunctions.get(ability);

        if (moveFunction != null) {
            moveFunction.accept(ability, context);
        } else {
            System.out.println("Ability (" + ability + ") Not Found!");
        }
    }
}
