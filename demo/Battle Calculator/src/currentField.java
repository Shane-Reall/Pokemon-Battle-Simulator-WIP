public class currentField {
    private weatherType weather = weatherType.none;
    private terrainType terrain = terrainType.none;
    private roomType[] room = {roomType.none, roomType.none, roomType.none, roomType.none};
    private battleType battle = battleType.single;

    public currentField () {

    }

    public weatherType getWeather() {
        return weather;
    }
}
