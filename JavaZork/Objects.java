public class Objects extends Character {
    private String description;
    private String itemText;
    private String exchangeItem;
    private boolean exchanger;
    //constructor
    public Objects(String name, Room startingRoom, String description, String itemText, String exchangeItem, boolean exchanger) {
        super(name, startingRoom);
        this.description = description;
        this.itemText = itemText;
        this.exchangeItem = exchangeItem;
        this.exchanger = exchanger;
    }
    //getter desc
    public String getDescription() {
        return description;
    }
    public String getItemText() {
        return itemText;
    }
    public String getItemExchange() {
        return exchangeItem;
    }
    public boolean getExchange() {
        return exchanger;
    }
    //taking item from object
    @Override
    public String takeObject() {
        String answer = "null";
        boolean ans = false;
        while (ans == false) {
            Printer.addText("Would you like to take the " + inventory.get(0).getName() + "?\nY or N");
            answer = Parser.reader.next().toUpperCase();
                switch (answer) {
                    case "Y":
                        return "Y";
                    case "N":
                        return "N";
                    default:
                        Printer.addText("Make up your mind!\n");
                        break;
                }
            Parser.reader.nextLine();
            }
        return "null";
    }
    //giving item for item
    public String exchangeObject() {
        String answer = "null";
        boolean ans = false;
        while (ans == false) {
            Printer.addText("Would you like to give the " + getItemExchange() + " for the " + inventory.get(0).getName() + "?\nY or N");
            answer = Parser.reader.next().toUpperCase();
            switch (answer) {
                case "Y":
                    return "Y";
                case "N":
                    return "N";
                default:
                    Printer.addText("Make up your mind!\n");
                    break;
            }
            Parser.reader.nextLine();
        }
        return "null";
    }

}
