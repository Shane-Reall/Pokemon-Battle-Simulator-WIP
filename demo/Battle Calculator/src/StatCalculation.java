public class StatCalculation extends flingList {
    static int calcHP (int baseHP, int IV, int EV, int level) {

        return (((2*baseHP+IV+(EV/4))*level)/100) + level + 10;
    }

    static int statCalc (int base, int IV, int EV, int level, String[] nature, String statName) {
        int stat = 0;

        int clacStat = (((2 * base + IV + (EV / 4)) * level) / 100) + 5;

        if (nature[0].equals(statName)) {
            stat = (int) (clacStat * 1.1);
        } else if (nature[1].equals(statName)) {
            stat = (int) (clacStat * 0.9);
        } else {
            stat = clacStat;
        }

        return stat;
    }

    static String[] natureGet(String nature) {
        String[] natureStat = {"None", "None"};

        switch (nature) {
            case "Lonely":
                natureStat[0] = "Attack";
                natureStat[1] = "Defense";
                break;
            case "Brave":
                natureStat[0] = "Attack";
                natureStat[1] = "Speed";
                break;
            case "Adamant":
                natureStat[0] = "Attack";
                natureStat[1] = "Sp.Attack";
                break;
            case "Naughty":
                natureStat[0] = "Attack";
                natureStat[1] = "Sp.Defense";
                break;
            case "Bold":
                natureStat[0] = "Defense";
                natureStat[1] = "Attack";
                break;
            case "Relaxed":
                natureStat[0] = "Defense";
                natureStat[1] = "Speed";
                break;
            case "Impish":
                natureStat[0] = "Defense";
                natureStat[1] = "Sp.Attack";
                break;
            case "Lax":
                natureStat[0] = "Defense";
                natureStat[1] = "Sp.Defense";
                break;
            case "Timid":
                natureStat[0] = "Speed";
                natureStat[1] = "Attack";
                break;
            case "Hasty":
                natureStat[0] = "Speed";
                natureStat[1] = "Defense";
                break;
            case "Jolly":
                natureStat[0] = "Speed";
                natureStat[1] = "Sp.Attack";
                break;
            case "Naive":
                natureStat[0] = "Speed";
                natureStat[1] = "Sp.Defense";
                break;
            case "Modest":
                natureStat[0] = "Sp.Attack";
                natureStat[1] = "Attack";
                break;
            case "Mild":
                natureStat[0] = "Sp.Attack";
                natureStat[1] = "Defense";
                break;
            case "Quiet":
                natureStat[0] = "Sp.Attack";
                natureStat[1] = "Speed";
                break;
            case "Rash":
                natureStat[0] = "Sp.Attack";
                natureStat[1] = "Sp.Defense";
                break;
            case "Calm":
                natureStat[0] = "Sp.Defense";
                natureStat[1] = "Attack";
                break;
            case "Gentle":
                natureStat[0] = "Sp.Defense";
                natureStat[1] = "Defense";
                break;
            case "Sassy":
                natureStat[0] = "Sp.Defense";
                natureStat[1] = "Speed";
                break;
            case "Careful":
                natureStat[0] = "Sp.Defense";
                natureStat[1] = "Sp.Attack";
                break;
            default:
                break;
        }

        return natureStat;
    }

    static boolean grounded(pkmnType type1, pkmnType type2, abilityList ability) {
        if (type1 == pkmnType.Flying || type2 == pkmnType.Flying || ability == abilityList.Levitate) {
            return true;
        }
        return false;
    }
}
