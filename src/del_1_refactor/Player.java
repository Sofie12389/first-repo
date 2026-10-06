package del_1_refactor;

import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();

    private Weapon equipped;

    private int PlayerHealth = 100;
    public int getPlayerHealth(){
        return PlayerHealth;
    }
    public void changeHealth(int amount) {
        PlayerHealth += amount;
    }
    public void hit(int damage) {
        PlayerHealth -= damage;
    }
    public boolean isDead() {
        return PlayerHealth <= 0;
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
            if (item == equipped) {
                equipped = null;
            }
        }

        return item;
    }
    public EatOutcome eat(String itemName) {

        Item item = findItem(itemName);
        boolean fromInventory = true;

        if (item == null) {
            item = currentRoom.findItem(itemName);
            fromInventory = false;
        }

        if (item == null) {
            return new EatOutcome(EatResult.NOT_FOUND, null, 0);
        }

        String longName = item.getLongName();

        if (!(item instanceof Food)) {
            return new EatOutcome(EatResult.NOT_FOOD, longName, 0);
        }

        Food food = (Food) item;
        int healthChange = food.getHealthPoints();

        changeHealth(healthChange);

        if (fromInventory) {
            inventory.remove(food);
        } else {
            currentRoom.removeItem(food);
        }

        return new EatOutcome(EatResult.EATEN, longName, healthChange);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Weapon getEquipped() {
        return equipped;
    }
    public EquipResult equip(String weaponName) {
        Item item = findItem(weaponName);

        if (item == null) {
            return EquipResult.NOT_FOUND;
        }

        if (!(item instanceof Weapon)) {
            return EquipResult.NOT_WEAPON;
        }

        equipped = (Weapon) item;
        return EquipResult.EQUIPPED;
    }
    public AttackResult attack(String enemyName) {

        Enemy enemy = currentRoom.findEnemy(enemyName);

        if (enemy == null) {
            return AttackResult.NO_ENEMY;
        }

        if (equipped == null) {
            return AttackResult.NO_WEAPON;
        }

        if (!equipped.canUse()) {
            return AttackResult.EMPTY;
        }

        equipped.use();

        return AttackResult.ATTACKED;
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