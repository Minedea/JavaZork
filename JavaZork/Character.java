import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Scanner;

public class Character implements Serializable, Inventory {
    private static final long serialVersionUID = 1L;
    protected String name;
    protected Room currentRoom;
    public ArrayList<Item> inventory = new ArrayList<Item>();
//    protected Scanner scanner = new Scanner(System.in);

    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
    }
    //inventory
    public void addInventory(Item item) {
        inventory.add(item);
    }
    public String takeObject() {
        return inventory.get(0).getName();
    }
    public void removeInventory(Item item) {
        inventory.remove(item);
    }
    public void displayInventory() {
        Printer.addText("Inventory:");
            for (int i = 0; i < inventory.size(); i++) {
                Printer.addText(inventory.get(i).getName() + ": " + inventory.get(i).getDescription());
        }
    }
    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            Printer.addText("You moved to: " + currentRoom.getDescription());
        } else {
            Printer.addText("You can't go that way!");
        }
    }
}
