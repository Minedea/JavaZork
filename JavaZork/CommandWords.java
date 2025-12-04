import java.util.HashMap;
import java.util.Map;

public class CommandWords {
    private Map<String, String> validCommands;

    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go", "Move to another room");
        validCommands.put("save", "Save the game");
        validCommands.put("load", "Load a saved game");
        validCommands.put("help", "Show help");
        validCommands.put("look", "Look around");
        validCommands.put("eat", "Eat something");
        validCommands.put("inventory", "Display inventory");
        validCommands.put("drop", "Drop an item");
        validCommands.put("pickup", "Pickup an item");
        validCommands.put("talk", "Talk to a person");
        validCommands.put("examine", "Examine an object");
        validCommands.put("place", "Place an item in a box");
    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

    public void showAll() {
        Printer.addText("Valid commands are: ");
        for (String command : validCommands.keySet()) {
            Printer.addText(command + " - " + validCommands.get(command));
        }
    }
}
