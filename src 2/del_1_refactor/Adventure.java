package del_1_refactor;

import java.util.ArrayList;

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
    public Item take(String name) {
        return player.takeItem(name);
    }

    public Item drop(String name) {
        return player.dropItem(name);
    }

    public ArrayList<Item> getInventory() {
        return player.getInventory();
    }

    public String look() {
        String result = "You are in " + player.getCurrentRoom().getName()
                + "\n" + player.getCurrentRoom().getDescription();

        for (Item item : player.getCurrentRoom().getItems()) {
            result += "\nHere you see: " + item.getLongName();
        }

        return result;
    }

    }

