import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {

    public Character player;
    public List<Room> rooms;

    public boolean elevatorEnt;
    public boolean bedShort;
    public boolean stairsShort;
    public boolean exitShort;
    public boolean wareShort;

    public int moved;
    public IKEORK.TimeDay time;

    public BoxGeneric<Item> box;

    public GameState(
            Character player,
            List<Room> rooms,
            boolean elevatorEnt,
            boolean bedShort,
            boolean stairsShort,
            boolean exitShort,
            boolean wareShort,
            int moved,
            IKEORK.TimeDay time,
            BoxGeneric<Item> box
    ) {
        this.player = player;
        this.rooms = rooms;

        this.elevatorEnt = elevatorEnt;
        this.bedShort = bedShort;
        this.stairsShort = stairsShort;
        this.exitShort = exitShort;
        this.wareShort = wareShort;

        this.moved = moved;
        this.time = time;

    }
}
