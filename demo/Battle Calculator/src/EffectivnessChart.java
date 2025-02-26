import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class EffectivnessChart {
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

    public static void saveEffectivenessChart() {
        try {
            // Create directories if they don't exist
            new File("Serialisation").mkdirs();

            // Create and save the chart
            HashMap<pkmnType, HashMap<pkmnType, Double>> chart = createEffectivenessChart();

            FileOutputStream fileOut = new FileOutputStream("Serialisation/effectivenessChart.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(chart);
            out.close();
            fileOut.close();
            System.out.println("Effectiveness chart has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
