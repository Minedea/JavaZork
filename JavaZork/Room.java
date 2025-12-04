import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room implements Serializable {
    private String description;
    private String lookDescription;
    private Map<String, Room> exits; // Map direction to neighboring Room
    public ArrayList<Item> roomsInventory =  new ArrayList<Item>();
    public ArrayList<NPC> roomsNPCs = new ArrayList<NPC>();
    public ArrayList<Objects> roomsObjects = new ArrayList<Objects>();

    public Room(String description, String lookDescription) {
        this.description = description;
        this.lookDescription = lookDescription;
        exits = new HashMap<>();
        roomsInventory = new ArrayList<Item>();
        roomsNPCs = new ArrayList<NPC>();
        roomsObjects = new ArrayList<Objects>();
    }

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addRoomInventory(Item item) {
        roomsInventory.add(item);
    }

    public String getRoomInventory() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roomsInventory.size(); i++) {
            sb.append(roomsInventory.get(i).getName()).append(", ");
        }
        return sb.toString();
    }

    public void removeRoomInventory(Item item) {
        roomsInventory.remove(item);
    }

    public void addNPC(NPC npc) {
        roomsNPCs.add(npc);
    }

    public String getRoomNPCs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roomsNPCs.size(); i++) {
            sb.append(roomsNPCs.get(i).getName()).append(", ");
        }
        return sb.toString();
    }

    public void addObject(Objects object) {
        roomsObjects.add(object);
    }

    public String getRoomObjects() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roomsObjects.size(); i++) {
            sb.append(roomsObjects.get(i).getName()).append(", ");
        }
        return sb.toString();
    }

    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (String direction : exits.keySet()) {
            sb.append(direction).append(", ");
        }
        return sb.toString().trim();
    }

    public String getLongDescription() {
        return "You are " + description + ".\nItems: " + getRoomInventory() + "\nPeople: " + getRoomNPCs() + "\nObjects: " + getRoomObjects() + "\nExits: " + getExitString();
    }

    public String getLookDescription() {
        return lookDescription;
    }

}
