package del_1_refactor;

import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    private ArrayList<Item> items;

    private ArrayList<Enemy> enemies;
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public Enemy findEnemy(String shortName) {
        for (Enemy enemy : enemies) {
            if (enemy.getShortName().equalsIgnoreCase(shortName)) {
                return enemy;
            }
        }
        return null;
    }

        public Item findItem (String shortName){
            for (Item item : items) {
                if (item.getShortName().equalsIgnoreCase(shortName)) {
                    return item;
                }
            }
            return null;
        }

        public Room getNorth () {
            return north;
        }

        public Room getSouth () {
            return south;
        }

        public Room getEast () {
            return east;
        }

        public Room getWest () {
            return west;
        }

        public void setNorth (Room north){
            this.north = north;
        }

        public void setSouth (Room south){
            this.south = south;
        }

        public void setEast (Room east){
            this.east = east;
        }

        public void setWest (Room west){
            this.west = west;
        }
    }

