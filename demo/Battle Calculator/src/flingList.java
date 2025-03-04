import java.util.Arrays;
import java.util.HashMap;

public class flingList {
    public static HashMap<itemList, Integer> createFlingList() {
        HashMap<itemList, Integer> flingList = new HashMap<>();

        for (itemList item : itemList.values()) {
            flingList.put(item, itemChecker(item));
        }

        // Example: Print the map
        for (var entry : flingList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        return flingList;
    }

    private static Integer itemChecker(itemList item) {
        String[] item10 = {
                "Air_Balloon", "Big_Root", "Bright_Powder", "Choice_Band", "Choice_Scarf", "Choice_Specs",
                "Destiny_Knot", "Discount_Coupon", "Electric_Seed", "Expert_Belt", "Fairy_Feather", "Focus_Band",
                "Focus_Sash", "Grassy_Seed", "Lagging_Tail", "Leftovers", "Mental_Herb", "Metal_Powder",
                "Misty_Seed", "Muscle_Band", "Power_Herb", "Psychic_Seed", "Quick_Powder", "Reaper_Cloth",
                "Red_Card", "RingTarget", "Shed_Shell", "Silk_Scarf", "Silver_Powder", "Smooth_Rock",
                "Soft_Sand", "Soothe_Bell", "White_Herb", "Wide_Lens", "Wise_Glasses", "Zoom_Lens"
        };

        String[] item30 = {
                "Ability_Shield", "Absorb_Bulb", "Adrenaline_Orb", "Binding_Band", "Black_Belt", "Black_Glasses",
                "Black_Sludge", "Booster_Energy", "Bottle_Cap", "Cell_Battery", "Charcoal", "Clear_Amulet",
                "Covert_Cloak", "Deep_Sea_Scale", "Dragon_Scale", "Eject_Button", "Fire_Stone", "Flame_Orb",
                "Float_Stone", "Galarica_Cuff", "Galarica_Wreath", "Gold_Bottle_Cap", "Ice_Stone", "Leaf_Stone",
                "Life_Orb", "Light_Ball", "Light_Clay", "Loaded_Dice", "Luminous_Moss", "Magnet",
                "Malicious_Armor", "Metal_Coat", "Metronome", "Miracle_Seed", "Mirror_Herb", "Moon_Stone",
                "Mystic_Water", "Prism_Scale", "Protective_Pads", "Punching_Glove", "Razor_Fang", "Scope_Lens",
                "Shell_Bell", "Snowball", "Soul_Dew", "Spell_Tag", "Sun_Stone", "Sweet_Apple",
                "Syrupy_Apple", "Tart_Apple", "Throat_Spray", "Thunder_Stone", "Toxic_Orb", "Twisted_Spoon",
                "Water_Stone"
        };

        String[] item80 = {
                "Assault_Vest", "Blunder_Policy", "Chipped_Pot", "Cracked_Pot", "Dawn_Stone",
                "Dusk_Stone", "Electirizer", "Magmarizer", "Masterpiece_Teacup", "Oval_Stone",
                "Protector", "Quick_Claw", "Razor_Claw", "Sachet", "Safety_Goggles",
                "Shiny_Stone", "Sticky_Barb", "Unremarkable_Teacup", "Weakness_Policy"
        };

        if (item.toString().contains("_Berry") || item.toString().contains("_Incense") || item.toString().contains("_Sweet")) {
            return 10;
        } else if (Arrays.stream(item10).anyMatch(i -> i.equals(item.toString()))) {
            return 10;
        } else if (item.toString().contains("_Feather")) {
            return 20;
        } else if (Arrays.stream(item30).anyMatch(i -> i.equals(item.toString()))) {
            return 30;
        } else if (item.equals(itemList.Eviolite) || item.equals(itemList.Icy_Rock) || item.equals(itemList.Lucky_Punch)) {
            return 40;
        } else if (item.toString().contains("_Memory")) {
            return 50;
        } else if (item.equals(itemList.Dubious_Disc) || item.equals(itemList.Eject_Pack) || item.equals(itemList.Sharp_Beak)) {
            return 50;
        } else if (item.toString().contains("_Mask")) {
            return 60;
        } else if (item.equals(itemList.Adamant_Orb) || item.equals(itemList.Damp_Rock) || item.equals(itemList.Griseous_Orb) || item.equals(itemList.Heat_Rock) || item.equals(itemList.Leek) || item.equals(itemList.Lustrous_Orb) || item.equals(itemList.Macho_Brace) || item.equals(itemList.Rocky_Helmet)  || item.equals(itemList.Terrain_Extender) || item.equals(itemList.Utility_Umbrella)) {
            return 60;
        } else if ((item.toString().contains("Power_") && !item.toString().contains("Power_Herb")) || item.toString().contains("_Drive")) {
            return 70;
        } else if (item.equals(itemList.Dragon_Fang) || item.equals(itemList.Poison_Barb)) {
            return 70;
        } else if ((item.toString().contains("ite") && !(item.equals(itemList.White_Herb) || item.equals(itemList.Eviolite)))) {
            return 80;
        } else if (Arrays.stream(item80).anyMatch(i -> i.equals(item.toString()))) {
            return 80;
        } else if (item.toString().contains("_Plate")) {
            return 90;
        } else if (item.equals(itemList.Deep_Sea_Tooth) || item.equals(itemList.Grip_Claw) || item.equals(itemList.Thick_Club)) {
            return 90;
        } else if (item.toString().contains("_Fossil") || item.equals(itemList.Old_Amber)) {
            return 100;
        } else if (item.equals(itemList.Hard_Stone) || item.equals(itemList.Rare_Bone) || item.equals(itemList.Room_Service)) {
            return 100;
        } else if (item.equals(itemList.Iron_Ball) || item.equals(itemList.Big_Nugget)) {
            return 130;
        }

        return 0;
    }
}
