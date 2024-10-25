public class currentField {
    private weatherType weather;
    private terrainType terrain;
    private roomType[] room = new roomType[4];
    private battleType battle;

    public currentField () {
        weather = weatherType.none;
        terrain = terrainType.none;
        for(int i = 0; i < 3; i++) {
            room[i] = roomType.none;
        }
        battle = battleType.single;
    }

    public weatherType getWeather() {
        return weather;
    }

    public void setWeather(weatherType weather) {
        this.weather = weather;
    }

    public terrainType getTerrain() {
        return terrain;
    }

    public void setTerrain(terrainType terrain) {
        this.terrain = terrain;
    }

    public roomType[] getRooms() {
        return room;
    }

    public void setRoom(roomType room, int roomCounter) {
        this.room[roomCounter] = room;
    }

    public battleType getBattle() {
        return battle;
    }

    public void setBattle(battleType battle) {
        this.battle = battle;
    }
}
