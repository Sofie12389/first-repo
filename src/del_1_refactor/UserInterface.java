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
        } else {
            switch (input) {
                case "look" -> System.out.println(adventure.look());
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
    }
}
