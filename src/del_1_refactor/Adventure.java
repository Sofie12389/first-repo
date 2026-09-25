package del_1_refactor;

public class Adventure {

    private Player player;
    private Map map;

    public Adventure() {
        map = new Map();
        player = new Player(map.buildMap());
    }

    public boolean go(String direction) {
        return player.move(direction);
    }

    public String look() {
        return player.getCurrentRoom().getDescription();
    }
}
