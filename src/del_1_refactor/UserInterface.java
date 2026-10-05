package del_1_refactor;

import java.util.Scanner;

public class UserInterface {

    private Adventure adventure = new Adventure();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the adventure! Type 'help' for commands.");
        System.out.println(adventure.look());

        boolean running = true;
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            running = handleInput(input);
        }
    }

    // Returns false when the game should end.
    private boolean handleInput(String input) {
        if (input.startsWith("go ")) {
            go(input.substring(3).trim());
        } else if (input.startsWith("take ")) {
            take(input.substring(5).trim());
        } else if (input.startsWith("drop ")) {
            drop(input.substring(5).trim());
        } else if (input.startsWith("eat ")) {
            eat(input.substring(4).trim());
        } else if (input.startsWith("equip ")) {
            equip(input.substring(6).trim());
        } else {

            switch (input) {
                case "look" -> System.out.println(adventure.look());
                case "inventory", "inv", "invent" -> showInventory();
                case "health" -> showHealth();
                case "attack" -> attack();
                case "help" -> printHelp();
                case "quit" -> {
                    System.out.println("Goodbye!");
                    return false;
                }
                case "" -> { }
                default -> System.out.println("Unknown command. Type 'help' for commands.");
            }
        }
        return true;
    }

    private void go(String input) {
        String direction = translateDirection(input);
        if (direction == null) {
            System.out.println("Unknown direction: " + input);
        } else if (adventure.go(direction)) {
            System.out.println("You go " + direction + ".");
            System.out.println(adventure.look());
        } else {
            System.out.println("You can't go that way.");
        }
    }

    private void take(String name) {
        Item item = adventure.take(name);

        if (item != null) {
            System.out.println("You have taken " + item.getLongName());
        } else {
            System.out.println("There is nothing like " + name + " to take around here");
        }
    }

    private void drop(String name) {
        Item item = adventure.drop(name);

        if (item != null) {
            System.out.println("You have dropped " + item.getLongName());
        } else {
            System.out.println("You don't have anything like " + name + " in your inventory");
        }
    }
    private void eat(String name) {
        EatOutcome outcome = adventure.eat(name);

        switch (outcome.getResult()) {
            case NOT_FOUND ->
                    System.out.println("You don't have anything like " + name + " in your inventory");

            case NOT_FOOD ->
                    System.out.println("You can't eat " + outcome.getItemName());

            case EATEN ->
                    System.out.println("You ate " + outcome.getItemName() +
                            " and your health changed by " + outcome.getHealthChange());
        }
    }
    private void equip(String name) {
        EquipResult result = adventure.equip(name);

        switch (result) {
            case EQUIPPED ->
                    System.out.println("You have equipped " + adventure.getEquipped().getLongName());

            case NOT_FOUND ->
                    System.out.println("You don't have anything like " + name + " in your inventory");

            case NOT_WEAPON ->
                    System.out.println(name + " is not a weapon");
        }
    }
    private void attack() {
        AttackResult result = adventure.attack();

        switch (result) {
            case NO_WEAPON ->
                    System.out.println("You don't have a weapon equipped.");

            case EMPTY ->
                    System.out.println("Your weapon is empty.");

            case ATTACKED -> {
                Weapon weapon = adventure.getEquipped();

                System.out.println("You " + weapon.getAttackVerb()
                        + " " + weapon.getLongName() + " at the empty air. "
                        + weapon.getUsesLeftText());
            }
        }
    }
    private void showInventory() {
        System.out.println("You are carrying:");

        for (Item item : adventure.getInventory()) {
            System.out.println(item.getLongName());
        }

        Weapon equipped = adventure.getEquipped();

        if (equipped != null) {
            System.out.println("Equipped: " + equipped.getLongName());
        }
    }
    private void showHealth() {
        int health = adventure.getPlayerHealth();

        if (health >= 100) {
            System.out.println("health: " + health + " - you are in perfect health");
        } else if (health >= 50) {
            System.out.println("health: " + health + " - you are in good health, but avoid fighting right now");
        } else if (health >= 25) {
            System.out.println("health: " + health + " - you are wounded - find something healthy to eat");
        } else if (health >= 1) {
            System.out.println("health: " + health + " - you are barely alive");
        } else {
            System.out.println("health: " + health + " - you should be dead");
        }
    }

    private String translateDirection(String input) {
        return switch (input) {
            case "n", "north" -> "north";
            case "s", "south" -> "south";
            case "e", "east" -> "east";
            case "w", "west" -> "west";
            default -> null;
        };
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("  go north/south/east/west (or n/s/e/w) - move");
        System.out.println("  look - describe the current room");
        System.out.println("  help - show this help");
        System.out.println("  quit - end the game");
        System.out.println("  take <item> - pick up an item");
        System.out.println("  drop <item> - drop an item");
        System.out.println("  inventory (or inv/invent) - show your inventory");
        System.out.println("  health - show your current health");
        System.out.println("  eat <food> - eat something");
        System.out.println("  equip <weapon> - equip a weapon");
        System.out.println("  attack - attack with your equipped weapon");
    }
}
