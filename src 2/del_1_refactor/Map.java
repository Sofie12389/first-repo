package del_1_refactor;

public class Map {  public Room buildMap() {

    Room room1 = new Room(
            "Room 1",
            "A room with no distinct features, except two doors."
    );

    Room room2 = new Room(
            "Room 2",
            "Water drips from the ceiling somewhere in the dark."
    );

    Room room3 = new Room(
            "Room 3",
            "A cold room with cracked walls and an old wooden floor."
    );

    Room room4 = new Room(
            "Room 4",
            "A narrow room filled with dust and broken furniture."
    );

    Room room5 = new Room(
            "Room 5",
            "A mysterious room hidden in the middle of the building."
    );

    Room room6 = new Room(
            "Room 6",
            "A dark room with a strange smell in the air."
    );

    Room room7 = new Room(
            "Room 7",
            "An old room with scratches covering the walls."
    );

    Room room8 = new Room(
            "Room 8",
            "A large room with several passages leading away."
    );

    Room room9 = new Room(
            "Room 9",
            "A quiet room at the end of the building."
    );

    // Items
    room1.addItem(new Item("lamp", "a shiny brass lamp"));
    room2.addItem(new Item("coins", "some gold coins"));
    room3.addItem(new Item("key", "an old rusty key"));
    room4.addItem(new Item("book", "a dusty old book"));
    room5.addItem(new Item("sword", "an ancient iron sword"));
    room6.addItem(new Item("bottle", "a mysterious glass bottle"));
    room7.addItem(new Item("ring", "a small golden ring"));
    room8.addItem(new Item("map", "an old treasure map"));
    room9.addItem(new Item("shoe", "a heavy shoe"));

    // Room 1 <-> Room 2
    room1.setEast(room2);
    room2.setWest(room1);

    // Room 1 <-> Room 4
    room1.setSouth(room4);
    room4.setNorth(room1);

    // Room 2 <-> Room 3
    room2.setEast(room3);
    room3.setWest(room2);

    // Room 3 <-> Room 6
    room3.setSouth(room6);
    room6.setNorth(room3);

    // Room 4 <-> Room 7
    room4.setSouth(room7);
    room7.setNorth(room4);

    // Room 7 <-> Room 8
    room7.setEast(room8);
    room8.setWest(room7);

    // Room 8 <-> Room 9
    room8.setEast(room9);
    room9.setWest(room8);

    // Room 6 <-> Room 9
    room6.setSouth(room9);
    room9.setNorth(room6);

    // Room 8 <-> Room 5
    room8.setNorth(room5);
    room5.setSouth(room8);

    return room1;
}
}