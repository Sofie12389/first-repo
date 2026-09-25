package del_1_refactor;

public class Map {

    public Room buildMap() {
        Room kitchen = new Room("Kitchen", "A small kitchen with a cold stove and a smell of old coffee.");
        Room hallway = new Room("Hallway", "A narrow hallway with creaky floorboards.");
        Room bedroom = new Room("Bedroom", "A dusty bedroom with an unmade bed.");

        kitchen.setEast(hallway);
        hallway.setWest(kitchen);

        hallway.setNorth(bedroom);
        bedroom.setSouth(hallway);

        return kitchen;
    }
}
