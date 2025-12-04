public class NPC extends Character {
    private String dialogue;
    private String questItem;
    private String questAsk;
    private String questNo;
    private String questDone;
    private boolean hadQuest;

    public NPC(String name, Room startingRoom, String dialogue, String questItem, String questAsk, String questNo, String questDone, boolean hadQuest) {
        super(name, startingRoom);
        this.dialogue = dialogue;
        this.questItem = questItem;
        this.questAsk = questAsk;
        this.questNo = questNo;
        this.questDone = questDone;
        this.hadQuest = hadQuest;
    }
    public String getDialogue() {
        return dialogue;
    }
    public String getQuestItem() {
        return questItem;
    }
    public String getQuestAsk() {
        return questAsk;
    }
    public String getQuestNo() {
        return questNo;
    }
    public String getQuestDone() {
        return questDone;
    }
    public boolean getHadQuest() {
        return hadQuest;
    }
    public void setHadQuest(boolean setQuest) {
        hadQuest = setQuest;
    }
    //completing quest
    public String takeQuest() {
        String answer = "null";
        boolean ans = false;
        while (ans == false) {
            Printer.addText("Would you like to give the " + getQuestItem() + "?\nY or N");
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