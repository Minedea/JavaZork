/* This game is a classic text-based adventure set in a university environment.
   The player starts outside the main entrance and can navigate through different rooms like a 
   lecture theatre, campus pub, computing lab, and admin office using simple text commands (e.g., "go east", "go west").
    The game provides descriptions of each location and lists possible exits.

Key features include:
Room navigation: Moving among interconnected rooms with named exits.
Simple command parser: Recognizes a limited set of commands like "go", "help", and "quit".
Player character: Tracks current location and handles moving between rooms.
Text descriptions: Provides immersive text output describing the player's surroundings and available options.
Help system: Lists valid commands to guide the player.
Overall, it recreates the classic Zork interactive fiction experience with a university-themed setting, 
emphasizing exploration and simple command-driven gameplay
*/

import java.io.*;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class IKEORK {
    private Parser parser;
    private Character player;
    boolean elevatorEnt = false;
    boolean bedShort = false;
    boolean stairsShort = false;
    boolean exitShort = false;
    boolean wareShort = false;
    int moved = 0;
    enum TimeDay {
        MORNING,
        NOON,
        EVENING
    }
    TimeDay time = TimeDay.MORNING;

    private List<Room> rooms;

    //Box with Generics
    BoxGeneric<Item> box = new BoxGeneric<>();

    public IKEORK() {
        createRooms();
        parser = new Parser();

    }

    public void parserInput(String words) {
        Command command = parser.getCommand(words);
        processCommand(command);
    }

    private void createRooms() {
        Room entrance, living, elevator1, kitchen, dining, bedroom, wardrobes, kids, restaurant, stairs1, stairs2, cookingUten, textiles, bathroom, homeOrg, lighting, wallDeco, homeDeco, warehouse1, warehouse2, warehouse3, aisle1, aisle2, aisle3, aisle4, aisle5, aisle6, checkouts, bistro, foodMark, customerServ, exit;

        // create rooms
        entrance = new Room("Outside IKEA", "Ominous grey clouds cover the sky, it's bound to rain soon. \nYou see the entrance doors ahead, with an escalator up to the showrooms, but have a feeling that once you go in you won't be able to come out the same way.");
        living = new Room("in the living room showroom", "Multiple model living rooms are setup, each one designed to appeal to a different aesthetic, a retro 80s style one in particular catches your attention. \nIn one of the models you see a dishevelled man upturning couch cushions and looking through drawers.");
        elevator1 = new Room("by the elevator", "An elevator with a sign that reads - \n----------------------------------\n           Out of Order\nPlease use the stairs to the west.\n----------------------------------\nThere is a row of vending machines lined against the wall next to it.");
        kitchen = new Room("in the kitchen showroom",  "Model kitchens with plastic food in pots and pans fill the room. In the middle of the room by a small standee stands a tall blonde employee in a blue shirt.");
        dining = new Room("in the dining showroom", "Tables upon tables line the room, each surrounded by chairs, waiting for a banquet. At the head of a table you notice an employee hunched over in a chair.");
        bedroom = new Room("in the bedroom showroom", "Kids run rampant jumping from bed to bed, employees look on with tired faces, knowing they will have to straighten all those sheets.\nBy one of the displays you notice one child not jumping but looking absentmindedly around.");
        wardrobes = new Room("in the wardrobe showroom", "A maze of wardrobes surrounds you on all sides, each one different from the last, maybe one of them holds something special.");
        kids = new Room("in the kids showroom", "Brightly coloured kids bedrooms filled with toys and game consoles blind you.\nIn a dark coloured display you see an older teenager(?) in a gothic dress playing with toys and texting.");
        restaurant = new Room("in the restaurant", "A long queue of customers snakes around the restaurant, grabbing cakes and waiting in anticipation for some delectable Swedish meatballs.\nAt the buffet you see a tired looking cook.");
        stairs1 = new Room("at the top of the stairs", "A mordernist staircase leading downstairs to the markethall.");
        stairs2 = new Room("at the bottom of the stairs","A brutalist staircase leading upstairs to the showrooms");
        cookingUten = new Room("in the cooking utensils section", "Pots, pans, silverware and dishes line the walls, shining in the warm light.");
        textiles = new Room("in the textiles section", "You find yourself entranced by ornate rugs and soft carpets. You get lost in the elegant quilts and the feel of the soft fluffy blankets. You feel at peace here, and a state of tranquility washes over you.");
        bathroom = new Room("in the bathroom section", "Toilets and towels everywhere. The sheer variety of toilets on display amazes you as you never thought they could have this much variation.");
        homeOrg = new Room("in the home organisation section", "Storage boxes and cupboards are abound here, surely there must be something hidden in one, it fits too well.");
        lighting = new Room("in the lighting section", "The room is split between many tones and moods as different lights fight for control.\nThe gentle warm lights are trying to defend against the bright white fluorescents in a final stand, while some colourful reds, blues, and greens look on from the sides, each protecting a small plot of their own.");
        wallDeco = new Room("in the wall decor section", "The walls are covered head to toe in mirrors, shelves and picture frames. Trying to find a single blank spot would be near impossible here.\nIn the corner you see a man in a cowboy hat who at first glance appears to be on a phone call, but you have a suspicion that he's not talking to anyone at all.");
        homeDeco = new Room("in the home decor section", "Plastic plants and various knick-knacks adorn the shelves, you find a little wooden dog figure particularly amusing.\nThere's a massive opening into the warehouse with a burly grey-haired security guard talking to customers as they go by.");
        warehouse1 = new Room("in the upper part of the warehouse", "You stare down the middle of a massive, echoing warehouse, aisles on either side are seperated by shelves of palettes and unbuilt furniture that stretch from floor to ceiling.");
        warehouse2 = new Room("in the middle part of the warehouse", "The rows and rows of shelves continues onwards, you see a display of a shed with astroturf, and an employee with a palatte mover beside it.");
        warehouse3 = new Room("in the lower part of the warehouse", "After what felt like months of walking, you finally see the checkouts in the distance, victory is within reach.");
        aisle1 = new Room("in the first aisle", "Amidst the shelves of disassembled bed frames, you see what looks like a grown man hiding in one of the shelves in a fort of wood planks and colourful wrapping paper, complete with some fairy lights.");
        aisle2 = new Room("in the second aisle", "Crates of wardrobes and dressers fill the shelves.");
        aisle3 = new Room("in the third aisle", "Cute dog kennels and sheds are on display.");
        aisle4 = new Room("in the fourth aisle", "Lots of tables can be seen all around the aisle.");
        aisle5 = new Room("in the fifth aisle", "Lawn furniture is abound here.");
        aisle6 = new Room("in the sixth aisle", "Planks of wood are piled high. In the middle of the aisle you notice a handyman looking around intently.");
        checkouts = new Room("at the checkouts", "The beeps of registers and murmur of tired customers play like a triumphant victory march, heralding your soon approaching victory. You see a kassör with dark hair and a bob at an empty checkout");
        bistro = new Room("in the bistro", "The illuminated signs of hotdogs and ice cream at low prices tries to lure you in, a final challenge before you reach the exit.");
        foodMark = new Room("in the Food Market", "Swedish delicacies and foods you've never heard of before fill the shelves.");
        customerServ = new Room("at the customer service desk", "Two older women man the service desk, ready and waiting with a forced smile and tired eyes to help customers solve their issues.");
        exit = new Room("at the exit", "the escape from this dreaded place");

        List<Room> allRooms = List.of(
                entrance, living, elevator1, kitchen, dining, bedroom, wardrobes, kids,
                restaurant, stairs1, stairs2, cookingUten, textiles, bathroom, homeOrg,
                lighting, wallDeco, homeDeco, warehouse1, warehouse2, warehouse3,
                aisle1, aisle2, aisle3, aisle4, aisle5, aisle6, checkouts, bistro,
                foodMark, customerServ, exit
        );

        this.rooms = allRooms;   // add a field “rooms” to IKEORK


        // initialise room exits
        entrance.setExit("north", living);

        living.setExit("east", kitchen);
        living.setExit("west", elevator1);

        elevator1.setExit("east", living);
        elevator1.setExit("north", restaurant);
        elevator1.setExit("west", stairs1);

        kitchen.setExit("west", living);
        kitchen.setExit("south", dining);

        dining.setExit("north", kitchen);
        dining.setExit("east", bedroom);

        bedroom.setExit("north", wardrobes);
        bedroom.setExit("west", dining);
        bedroom.setExit("south",bathroom);

        wardrobes.setExit("south", bedroom);
        wardrobes.setExit("west", kids);

        kids.setExit("west", restaurant);
        kids.setExit("east", wardrobes);

        restaurant.setExit("south", elevator1);
        restaurant.setExit("east", kids);

        stairs1.setExit("east", elevator1);
        stairs1.setExit("south", stairs2);

        stairs2.setExit("north", stairs1);
        stairs2.setExit("east", cookingUten);

        cookingUten.setExit("west", stairs2);
        cookingUten.setExit("south", textiles);

        textiles.setExit("north", cookingUten);
        textiles.setExit("east", bathroom);

        bathroom.setExit("west", textiles);
        bathroom.setExit("east", homeOrg);
        bathroom.setExit("north",bedroom);

        homeOrg.setExit("west", bathroom);
        homeOrg.setExit("north", lighting);

        lighting.setExit("north", wallDeco);
        lighting.setExit("south", homeOrg);

        wallDeco.setExit("south", lighting);
        wallDeco.setExit("east", homeDeco);

        homeDeco.setExit("west", wallDeco);
        homeDeco.setExit("south", warehouse1);

        warehouse1.setExit("north", homeDeco);
        warehouse1.setExit("west", aisle1);
        warehouse1.setExit("east", aisle4);
        warehouse1.setExit("south", warehouse2);

        warehouse2.setExit("north", warehouse1);
        warehouse2.setExit("west", aisle2);
        warehouse2.setExit("east", aisle5);
        warehouse2.setExit("south", warehouse3);

        warehouse3.setExit("north", warehouse2);
        warehouse3.setExit("west", aisle3);
        warehouse3.setExit("east", aisle6);
        warehouse3.setExit("south", checkouts);

        aisle1.setExit("east", warehouse1);

        aisle2.setExit("east", warehouse2);

        aisle3.setExit("east", warehouse3);

        aisle4.setExit("west", warehouse1);

        aisle5.setExit("west", warehouse2);

        aisle6.setExit("west", warehouse3);

        checkouts.setExit("north", warehouse3);
        checkouts.setExit("south",bistro);

        bistro.setExit("west", foodMark);
        bistro.setExit("east", customerServ);
        bistro.setExit("south", exit);

        foodMark.setExit("east", bistro);

        customerServ.setExit("west", bistro);


        // create the player character and start outside
        player = new Character("player", entrance);

        //create NPCs
        NPC matt = new NPC("Matt", living,"\"Couches can hide lots of things, you never know what some kid might've left behind, I already scored 50 cent and a piece of gum!\"",
                "nothin", "null", "null", "null", false);
            matt.getCurrentRoom().addNPC(matt);

        NPC chase = new NPC("Chase", kitchen, "\"G'day and welcome to IKEA mate! If there's anything I can help you with please let me know! Although I can't write anything down for you without a pencil unfortunately.\"",
                "pencil", "\"Oh, you're looking for the Bergsbo Åheim? Yeah I can write down the reference number for you if you let me borrow that pencil.\"", "\"Alright then, just let me know if you need anything else.\"",
                "\"Thank you very much, I hope you find that Bergsbo Åheim.\"", false);
            chase.getCurrentRoom().addNPC(chase);

        NPC moonie = new NPC("Moonie", kids, "\"You want my blahaj? No way >:( I would never gwive up my Sharky! He helps me sleep when I don't have any white Monster.\"",
                "monster", "\"Is that a white Monster? Uhhhmmmm... could I have it pweeeeaaaase. I'll gwive you my blahaj :(\"", "\"Awwwwwww, are you sure? :(\"",
                "\"Tank chuuuu I wub youuuuuu :)))\"", false);
            moonie.getCurrentRoom().addNPC(moonie);

        NPC katie = new NPC("Katie", bedroom, "\"Have you seen any shark plushies? They're called blahajs and are suuuper cute!\nMy mommy is too busy looking for bed sheets to help, I'm so boooooored, even after finding that secret staircase.\"",
                "blahaj","\"Is that blahaj for me?! Thank you so much! I'll love him forever!\"", "\"Aww, well I hope you love him as much as I would.\"", "I'm gonna name him Sharkolomew :)\"", false);
            katie.getCurrentRoom().addNPC(katie);

        NPC edd = new NPC("Edd", wallDeco, "\"Oh sorry, I was talking with my partner, Sol. She's much more than an AI, she's like a real person.\nI'm trying to find something nice to liven up my bedroom but have had no luck so far. Hey Sol can you think of anything that would be nice?\"",
                "lamp", "\"Ooh that looks nice, what do you think Sol?\"" +
                "\n\"I think it works great! The energies of the warm light brighten your chakras, which would help your anxiety, and the the pleasing angles of the cube based shade tell your guests that you appreciate modern design, while having bold, unconventional tastes. It's not just a lamp, it's a statement." +
                "\nI think this lamp would fit your bedroom perfectly. Let me know if you want my help picking out anything else\"\n\"Thank you darling. We'll take it! If you'll let us. I can give you this charger in exchange.\"",
                "\"Damn, I guess me and Sol would have to keep looking then\"", "\"Hey Sol where in my bedroom should we put this?\"", false);
            edd.getCurrentRoom().addNPC(edd);

        NPC brett = new NPC("Brett", dining, "\"Ooohhhh, sorry bro I'd love to help but my stomach is kiiiiilling me. I could really use some grub y'know?\"",
                "meatballs", "\"Yoooooo are those some Swedish meatballs? Could I maybe grab em off ya? I don't have much but uuhhh... I got some spare lil' pencils if you want one?\"",
                "\"Awwwww come on bro you're kiiiiiillin' meeeee. I don't know how much longer I can go\"", "\"Thank you so much brooooo, my stomach has been tamed. I hope you put that pencil to good use\"",false);
            brett.getCurrentRoom().addNPC(brett);

        NPC jay = new NPC("Jay", aisle1, "\"Hey quiet down! If I get caught this 24 hour challenge is cooked! I'll get no views and have to go back to doing TikTok lives smh.\nIt already took me ages to move these things with the palette mover I 'borrowed' from that employee.\"",
                "null", "null", "null", "null", false);
            jay.getCurrentRoom().addNPC(jay);

        NPC doris = new NPC("Doris", restaurant, "\"Sorry hun but we're all outta meatballs. You didn't hear this from me but if you still want some there's some day old ones in the tub at the end of the buffet we're meant to throw out.\nI'll look the other way if you decide to take some.\"",
                "null", "null", "null", "null", false);
            doris.getCurrentRoom().addNPC(doris);

        NPC branca = new NPC("Branca", stairs1, "\"You can't go this way I'm afraid ehh, theres been a spillage. I'll get it cleaned up as soon as I find where I left my mop, hmmmm...\"",
                "mop", "\"Is that my mop? Oh could I please have it... ehhh... back?\"", "\"Oh why not eh?\"", "\"Mmmm... It's all cleaned up now ehhh.\"", false);
            branca.getCurrentRoom().addNPC(branca);

        NPC callie = new NPC("Callie", warehouse2, "\"Hey do you happen to have a spare phone charger? Mine's completely dead right now and I neeeed it to get through this shift\"",
                "charger", "\"Yooooooo, could I borrow that charger? I can help with anything you need like uhhh, I got a palette mover if you need to move anything?\"", "\"Damn, I knew I should've kept some gum for bribes.\"",
                "\"Thank you so much, you're a lifesaver. Here's the mover if you want it\"", false);
            callie.getCurrentRoom().addNPC(callie);

        NPC manny = new NPC("Manny", aisle6, "\"I've been lookin' for a table, the uhhh, the Knarrevik! Been lookin' all over but can't find it. If you see it somewhere gimme a shout.\"",
                "knarrevik", "\"Oh did you find one? Nice! Would I be able to grab it off you please?\"", "\"Alright, I'll keep looking then.\"", "\"Thank you so much! Here you can borrow my screwdiver if you want.\"", false);
            manny.getCurrentRoom().addNPC(manny);

        NPC gwynn = new NPC("Gwynn", checkouts, "\"What items are you buying?\"", "bergsboaheim", "\"The Bergsbo Åheim? That'll be \u20AC565. Cash or card?\"", "\"You have to pay...\"", "\"Thank you. Please come back again.\"", false);
            gwynn.getCurrentRoom().addNPC(gwynn);

        NPC greg = new NPC("Greg", homeDeco, "\"Sorry, but due to new security measures I'm going to need to see proof of the item you are going to buy.\"", "slip", "\"Do you have proof?\"", "\"Sorry, can't let you through then.\"", "\"Ah the Bergsbo Åheim, one of our top sellers. You can find it in aisle 2.\"", false);
            greg.getCurrentRoom().addNPC(greg);

        //create Objects
        Objects couch = new Objects("Couch", living, "A retro couch with bright neon colours and funky shapes all over.","In between the cushions you notice a \u20AC2 coin.", "null", false);
            couch.getCurrentRoom().addObject(couch);
        Objects vendingMachine = new Objects("VendingMachine", elevator1, "A vending machine with crisps, jellies and energy drinks.", "You notice in C4 a can of white Monster for \u20AC2.", "coin", true);
            vendingMachine.getCurrentRoom().addObject(vendingMachine);
        Objects drawers = new Objects("Drawers", homeOrg, "A small set of wooden bedside drawers.", "There is a modern black lamp with a cubic white lamp shade, it appears to be screwed into the top of the drawers and won't budge.", "screwdriver", true);
            drawers.getCurrentRoom().addObject(drawers);
        Objects buffet = new Objects("Buffet", restaurant, "A buffet full of swedish delicacies, and fish and chips.", "At the end you notice the tub of day old meatballs", "null", false);
            buffet.getCurrentRoom().addObject(buffet);
        Objects wardrobe = new Objects("Wardrobe", wardrobes, "A lovely wooden wardrobe made with dark oak.", "You open it up and notice a mop inside.", "null", false);
            wardrobe.getCurrentRoom().addObject(wardrobe);
        Objects shelves = new Objects("Shelves", aisle2, "The tall shelves stretch on for eons filled with massive wooden furniture sets to build like Lego", "Finally you see it, you're quest for the holy grail is finally over, the Bergsbo Åheim is here. It's too heavy to bring to the checkouts, you'll need a palette mover.", "mover", true);
            shelves.getCurrentRoom().addObject(shelves);
        Objects tables = new Objects("Tables", aisle4, "Sets of tables line the shelves.", "At the back you notice a small black table, the Knarrevik.", "null", false);
            tables.getCurrentRoom().addObject(tables);

        //create items
        Item textMessage = new Item("Message", "\"We need more furniture for the bedroom, could you get a Bergsbo Åheim from Ikea on your way home please? Love you honey <3\"");
            player.addInventory(textMessage);
        Item creditCard = new Item("CreditCard", "\"Spend what you don't have today, worry tomorrow!\"");
            player.addInventory(creditCard);
        Item coin = new Item("Coin", "A \u20AC2 coin you found in a couch. Any amount of money comes in handy.");
            couch.addInventory(coin);
        Item blahaj = new Item("Blahaj", "\"A cuddly friend with a fearsome appearance!\"");
            moonie.addInventory(blahaj);
        Item monster = new Item("Monster", "A white energy drink that's safe-ish for human consumption.");
            vendingMachine.addInventory(monster);
        Item lamp = new Item("Lamp", "A modern lamp with a cubic lampshade, the unique shape is quite appealing.");
            drawers.addInventory(lamp);
        Item pencil = new Item("Pencil", "A little pencil for jotting down notes, or a furniture wishlist.");
            brett.addInventory(pencil);
        Item meatballs = new Item("Meatballs", "A delicious (day-old) Swedish staple, although some skeptics will claim it's made of horse meat, but who cares if it tastes good right?");
            buffet.addInventory(meatballs);
        Item mop = new Item("Mop", "\"Gotta mop mop mop!\"");
            wardrobe.addInventory(mop);
        Item screwdriver = new Item("Screwdriver", "\"Flat-head or Philips?\"");
            manny.addInventory(screwdriver);
        Item mover = new Item("Mover", "A palette mover, the fourth most fun thing to ride!");
            callie.addInventory(mover);
        Item charger = new Item("Charger", "\"Charge they phone, eat hot chip and lie.\"");
            edd.addInventory(charger);
        Item bergsboAheim = new Item("BergsboAheim", "The fated holy grail you've been looking for.");
            shelves.addInventory(bergsboAheim);
        Item knarrevik = new Item("Knarrevik", "\"Good things come in small packages.\"");
            tables.addInventory(knarrevik);
        Item receipt = new Item("Receipt", "Proof you are the proud owner of a Bergsbo Åheim.");
            gwynn.addInventory(receipt);
        Item slip = new Item("Slip", "A slip of paper with the reference number of the Bergsbo Åheim.");
            chase.addInventory(slip);
    }

//    public void play() {
//        printWelcome();
//
//        boolean finished = false;
//        while (!finished) {
//            Command command = parser.getCommand("");
//            //finished = processCommand(command);
//        }
//        Printer.addText("Thank you for playing. Goodbye.");
//    }

    public void printWelcome() {
        Printer.addText("");
        Printer.addText("Your partner has sent you on a treacherous quest to retrieve an important artefact, the Bergsbo Åheim.");
        Printer.addText("This mythical object can only be obtained from a blue and yellow monolithic structure, \"IKEA\"");
        Printer.addText("This journey will be a perilous one, armed with naught but a message from your partner and a credit card, you steel your soul and proceed towards the imposing structure...");
        Printer.addText("Type 'help' if you need help.");
        Printer.addText("\n");

        Printer.addText(player.getCurrentRoom().getLongDescription());
        Printer.addText(player.getCurrentRoom().getLookDescription());
    }
    public void processCommand(Command command) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            Printer.addText("I don't understand your command...");
            return;
        }

        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "inventory":
                player.displayInventory();
                break;
            case "drop":
                dropItem(command);
                break;
            case "pickup":
                pickupItem(command);
                break;
            case "look":
                lookAround(command);
                break;
            case "talk":
                talkNPC(command);
                break;
            case "examine":
                examineObject(command);
                break;
            case "place":
                placeItem(command);
                break;
            case "save":
                saveGame("savefile.dat");
                return;
            case "load":
                loadGame("savefile.dat");
                Printer.addText(player.getCurrentRoom().getLongDescription());
                break;
            default:
                Printer.addText("I don't know what you mean...");
                break;
        }
        return;
    }

    private void printHelp() {
        Printer.addText("You are trying to find the Bergsbo Åheim in one of the showrooms.");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            Printer.addText("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            Printer.addText("You can't go that way.");
        } else if (player.getCurrentRoom().getDescription().equals("in the living room showroom") && direction.equals("west") && elevatorEnt == false) {
            Printer.addText("A horde of hungry customers block your way, try coming back later when it's less busy.");

        } else if (player.getCurrentRoom().getDescription().equals("in the bedroom showroom") && direction.equals("south") && bedShort == false) {
            Printer.addText("A bed on display blocks your path to what appears to be a staircase leading down.");

        } else if (player.getCurrentRoom().getDescription().equals("in the bathroom section") && direction.equals("north") && bedShort == false) {
            Printer.addText("A locked fire door that can only be opened from the other side blocks your path.");

        } else if (player.getCurrentRoom().getDescription().equals("at the top of the stairs") && direction.equals("south") && stairsShort == false) {
            Printer.addText("A massive puddle on the ground and a wet floor sign are in your way.");

        } else if (player.getCurrentRoom().getDescription().equals("at the bottom of the stairs") && direction.equals("north") && stairsShort == false) {
            Printer.addText("A massive puddle on the ground and a wet floor sign are in your way.");

        } else if (player.getCurrentRoom().getDescription().equals("at the checkouts") && direction.equals("south") && exitShort == false) {
            Printer.addText("You can't make it past the checkouts without buying the Bergsbo Åheim!");

        } else if (player.getCurrentRoom().getDescription().equals("in the bistro") && direction.equals("south") && exitShort == false) {
            Printer.addText("You can't leave without buying the Bergsbo Åheim!");

        } else if (player.getCurrentRoom().getDescription().equals("in the home decor section") && direction.equals("south") && wareShort == false) {
            Printer.addText("A massive, if old, security guard blocks your path.");
        } else if (player.getCurrentRoom().getDescription().equals("at the exit")) {
            ending();
        } else {
            player.setCurrentRoom(nextRoom);
            Printer.addText(player.getCurrentRoom().getLongDescription());
            Printer.addText(player.getCurrentRoom().getLookDescription());
            moved++;
            if (moved >= 30) {
                time = TimeDay.NOON;
            }
            if (moved >= 60) {
                time = TimeDay.EVENING;
            }
        }
        if (player.getCurrentRoom().getDescription().equals("in the restaurant")) {
            elevatorEnt = true;
        }
    }

    private void dropItem(Command command) {
        String droppedItem = command.getSecondWord();

        if (!command.hasSecondWord()) {
            Printer.addText("Drop what?");
            return;
        }
        for (int i = 0; i < player.inventory.size(); i++) {
            String itemInvCheck = player.inventory.get(i).getName().toLowerCase();
                if (itemInvCheck.equals(droppedItem)) {
                    player.getCurrentRoom().addRoomInventory(player.inventory.get(i));
                    Printer.addText("Dropped: " + player.inventory.get(i).getName());
                    player.inventory.remove(i);
                    break;
                }
        }
    }

    public void pickupItem(Command command) {
        String pickItem = command.getSecondWord();

        if (!command.hasSecondWord()) {
            Printer.addText("Pickup what?");
            return;
        }
        for (int i = 0; i < player.getCurrentRoom().roomsInventory.size(); i++) {
            String itemFloorCheck = player.getCurrentRoom().roomsInventory.get(i).getName().toLowerCase();
            //Printer.addText(itemFloorCheck);
            //Printer.addText(pickItem);
            if (itemFloorCheck.equals(pickItem)) {
                player.inventory.add(player.getCurrentRoom().roomsInventory.get(i));
                Printer.addText("Picked up: " + player.getCurrentRoom().roomsInventory.get(i).getName());
                player.getCurrentRoom().removeRoomInventory(player.getCurrentRoom().roomsInventory.get(i));
                break;
            }
        } //Printer.addText("whoops");
    }

    private void lookAround(Command command) {
        Printer.addText(player.getCurrentRoom().getLongDescription());
        Printer.addText(player.getCurrentRoom().getLookDescription());
    }

    private void talkNPC(Command command) {
        String talkNPC = command.getSecondWord();

        if (!command.hasSecondWord()) {
            Printer.addText("Talk to who?");
            return;
        }
        for (int i = 0; i < player.getCurrentRoom().roomsNPCs.size(); i++) {
            String talkNPCCheck = player.getCurrentRoom().roomsNPCs.get(i).getName().toLowerCase();
            if (talkNPCCheck.equals(talkNPC)) {
                if (player.getCurrentRoom().roomsNPCs.get(i).getHadQuest() == true) { //checks for quest done dialogue
                    Printer.addText(player.getCurrentRoom().roomsNPCs.get(i).getQuestDone());
                    } else {
                    Printer.addText(player.getCurrentRoom().roomsNPCs.get(i).getDialogue());
                    for (int j = 0; j < player.inventory.size(); j++) { //quest item check
                        String questCheck = player.inventory.get(j).getName().toLowerCase();
                        if (questCheck.equals(player.getCurrentRoom().roomsNPCs.get(i).getQuestItem())) {
                            Printer.addText(player.getCurrentRoom().roomsNPCs.get(i).getQuestAsk());
                            String response = player.getCurrentRoom().roomsNPCs.get(i).takeQuest();
                            switch (response) {
                                case "Y":
                                    player.getCurrentRoom().roomsNPCs.get(i).addInventory(player.inventory.get(j));
                                    Printer.addText("Gave: " + player.inventory.get(j).getName());
                                    player.inventory.remove(j);
                                    if (player.getCurrentRoom().roomsNPCs.get(i).inventory.size() > 1) { //check if give item
                                        player.inventory.add(player.getCurrentRoom().roomsNPCs.get(i).inventory.getFirst());
                                        Printer.addText("Obtained: " + player.getCurrentRoom().roomsNPCs.get(i).inventory.getFirst().getName());
                                        player.getCurrentRoom().roomsNPCs.get(i).inventory.removeFirst();
                                        if (player.getCurrentRoom().roomsNPCs.get(i).getName().equals("Gwynn")) {
                                            exitShort = true;
                                            Printer.addText("\"We'll help bring this out for you.\"");
                                        }
                                    } else if(player.getCurrentRoom().roomsNPCs.get(i).inventory.size() == 1) { //check if reveal path
                                        if (player.getCurrentRoom().roomsNPCs.get(i).getName().equals("Katie")) {
                                            bedShort = true;
                                            Printer.addText("\"If you crawl under the bed to the south you can make it to the bathroom section in no time!\"");
                                        } else if (player.getCurrentRoom().roomsNPCs.get(i).getName().equals("Branca")) {
                                            stairsShort = true;
                                            Printer.addText("\"Thank the heavens you found my beaut. I'll get this cleaned up in ehh... no time at all!\"");
                                        } else if (player.getCurrentRoom().roomsNPCs.get(i).getName().equals("Greg")) {
                                            wareShort = true;
                                            Printer.addText("\"Hmm, this will work.\"");
                                        }
                                    }
                                    Printer.addText(player.getCurrentRoom().roomsNPCs.get(i).getQuestDone());
                                    player.getCurrentRoom().roomsNPCs.get(i).setHadQuest(true);
                                    break;
                                case "N":
                                    Printer.addText(player.getCurrentRoom().roomsNPCs.get(i).getQuestNo());
                                    break;
                                default:
                                    Printer.addText("error");
                            }
                            Parser.reader.nextLine();
                        }

                    }
                }
                break;
            }
        }
    }

    private void examineObject(Command command) {
        String examineObject = command.getSecondWord();
        if (!command.hasSecondWord()) {
            Printer.addText("Examine what?");
            return;
        }
        for (int i = 0; i < player.getCurrentRoom().roomsObjects.size(); i++) {
            String examineObjectCheck = player.getCurrentRoom().roomsObjects.get(i).getName().toLowerCase();
            if (examineObjectCheck.equals(examineObject)) {
                Printer.addText(player.getCurrentRoom().roomsObjects.get(i).getDescription());
                if(!player.getCurrentRoom().roomsObjects.get(i).inventory.isEmpty()) {
                    if (player.getCurrentRoom().roomsObjects.get(i).getExchange() == true) {
                        Printer.addText(player.getCurrentRoom().roomsObjects.get(i).getItemText());
                        for (int j = 0; j < player.inventory.size(); j++) { //exchange item check
                            String itemCheck = player.inventory.get(j).getName().toLowerCase();
                            if (itemCheck.equals(player.getCurrentRoom().roomsObjects.get(i).getItemExchange())) {
                                String choice = player.getCurrentRoom().roomsObjects.get(i).exchangeObject();
                                switch (choice) {
                                    case "Y":
                                        Printer.addText("Used: " + player.inventory.get(j).getName());
                                        player.inventory.remove(j);
                                        player.inventory.add(player.getCurrentRoom().roomsObjects.get(i).inventory.get(0));
                                        Printer.addText("Obtained: " + player.getCurrentRoom().roomsObjects.get(i).inventory.get(0).getName());
                                        player.getCurrentRoom().roomsObjects.get(i).removeInventory(player.getCurrentRoom().roomsObjects.get(i).inventory.get(0));
                                        break;
                                    case "N":
                                        Printer.addText("Did not take the " + player.getCurrentRoom().roomsObjects.get(i).inventory.get(0).getName());
                                        break;
                                    default:
                                        Printer.addText("error");
                                }
                                Parser.reader.nextLine();
                            }
                        }
                    } else {
                        Printer.addText(player.getCurrentRoom().roomsObjects.get(i).getItemText());
                        String response = player.getCurrentRoom().roomsObjects.get(i).takeObject();
                        switch (response) {
                            case "Y":
                                player.inventory.add(player.getCurrentRoom().roomsObjects.get(i).inventory.get(0));
                                Printer.addText("Obtained: " + player.getCurrentRoom().roomsObjects.get(i).inventory.get(0).getName());
                                player.getCurrentRoom().roomsObjects.get(i).removeInventory(player.getCurrentRoom().roomsObjects.get(i).inventory.get(0));
                                break;
                            case "N":
                                Printer.addText("Did not take the " + player.getCurrentRoom().roomsObjects.get(i).inventory.get(0).getName());
                                break;
                            default:
                                Printer.addText("error");
                        }
                        Parser.reader.nextLine();
                    }
                }
            }
        }
    }

    private void placeItem(Command command) {
        String placeItem = command.getSecondWord();
        if (!command.hasSecondWord()) {
            Printer.addText("Place what?");
            return;
        }
        if (player.getCurrentRoom().getDescription().equals("in the textiles section")) {
            Printer.addText("What would you like to put in the box?");
            String item = Parser.reader.next().toLowerCase();
            for (int i = 0; i < player.inventory.size(); i++) {
                Printer.addText("Test");
                if (item.equals(player.inventory.get(i).getName().toLowerCase())) {
                    box.setValue(player.inventory.get(i));
                    player.inventory.remove(i);
                }
            }
        } else if (box.getValue() != null) {
            Printer.addText("Has: " + box.info() + "\nWould you like to take the item?\nY or N");
            String answer = "null";
            boolean ans = false;
            while (ans == false) {
                answer = Parser.reader.next().toUpperCase();
                switch (answer) {
                    case "Y":
                        player.addInventory(box.getValue());
                        box.setValue(null);
                        ans = true;
                        break;
                    case "N":
                        Printer.addText("Did not take the item.");
                        ans = true;
                        break;
                    default:
                        Printer.addText("Make up your mind!\n");
                        break;
                }
            }
            Parser.reader.nextLine();
        }
    }

    public void ending() {
        switch (time) {
            case MORNING:
                Printer.addText("You blazed through the maze that is Ikea and made it out with your treasure before noon!");
                break;
            case NOON:
                Printer.addText("Through trials and tribulations you made it out victorious! The sun high in the sky, you begin the journey home with your treasure.");
                break;
            case EVENING:
                Printer.addText("You make it out just before closing with your treasure. Over the horizon you see the sun slowly disappearing, the light won't last long. You hastily run to your car and prepare for the road ahead.");
                break;
        }
        Printer.addText("Thank you for playing! To quit close the window.");
    }
    //Save
    public void saveGame(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {

            GameState state = new GameState(
                    player,
                    rooms,
                    elevatorEnt,
                    bedShort,
                    stairsShort,
                    exitShort,
                    wareShort,
                    moved,
                    time,
                    box
            );

            out.writeObject(state);
            Printer.addText("Game saved to " + filename);

        } catch (IOException e) {
            Printer.addText("Error saving: " + e.getMessage());
        }
    }

    public void loadGame(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {

            GameState state = (GameState) in.readObject();

            this.player = state.player;
            this.rooms = state.rooms;

            this.elevatorEnt = state.elevatorEnt;
            this.bedShort = state.bedShort;
            this.stairsShort = state.stairsShort;
            this.exitShort = state.exitShort;
            this.wareShort = state.wareShort;

            this.moved = state.moved;
            this.time = state.time;

            this.box = state.box;

            Printer.addText("Game loaded!");

        } catch (IOException | ClassNotFoundException e) {
            Printer.addText("Error loading: " + e.getMessage());
        }
    }




//    public static void main(String[] args) {
//        new MyFrame();
//    }
}
