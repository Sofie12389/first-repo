package del_1_refactor;

import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();

    private int PlayerHealth = 100;
    public int getPlayerHealth(){
        return PlayerHealth;
    }
    public void changeHealth(int amount) {
        PlayerHealth += amount;
    }
    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public Item findItem(String name) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public Item takeItem(String name) {

        Item item = currentRoom.findItem(name);

        if (item != null) {
            currentRoom.removeItem(item);
            inventory.add(item);
        }

        return item;
    }
    public Item dropItem(String name) {

        Item item = findItem(name);

        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
        }

        return item;
    }
    public EatOutcome eat(String itemName) {

        Item item = findItem(itemName);

        if (item == null) {
            return new EatOutcome(EatResult.NOT_FOUND, itemName, 0);
        }

        if (!(item instanceof Food)) {
            return new EatOutcome(EatResult.NOT_FOOD, itemName, 0);
        }

        Food food = (Food) item;
        int healthChange = food.getHealthPoints();

        changeHealth(healthChange);
        removeItem(food);

        return new EatOutcome(EatResult.EATEN, itemName, healthChange);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }


    public Player(Room startRoom) {
        currentRoom = startRoom;
    }

    public boolean move(String direction) {

        Room desiredRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "south" -> currentRoom.getSouth();
            case "east" -> currentRoom.getEast();
            case "west" -> currentRoom.getWest();
            default -> null;
        };

        if (desiredRoom != null) {
            currentRoom = desiredRoom;
            return true;
        }

        return false;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}