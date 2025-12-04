import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    IKEORK game = new IKEORK();

    JButton west;
    JButton north;
    JButton east;
    JButton south;
    JButton input;
    JTextArea textBox;
    JScrollPane scrollBar;
    JTextField textInput;

    MyFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("IKEORK");
        frame.setLayout(new BorderLayout());
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);

        //Panels
        JPanel panelLeft = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelBottom = new JPanel();
        JPanel panelCentre = new JPanel();
        JPanel panelCentreBottom = new JPanel();
        JPanel panelBottomBottom = new JPanel();


//        panelLeft.setBackground(Color.red);
//        panelTop.setBackground(Color.blue);
//        panelRight.setBackground(Color.green);
//        panelBottom.setBackground(Color.pink);
//        panelCentre.setBackground(Color.black);
//        panelCentreBottom.setBackground(Color.cyan);
        panelBottomBottom.setBackground(Color.YELLOW);


        panelLeft.setPreferredSize(new Dimension(90,100));
        panelTop.setPreferredSize(new Dimension(100,30));
        panelRight.setPreferredSize(new Dimension(90,100));
        panelBottom.setPreferredSize(new Dimension(100,250));
        panelCentre.setPreferredSize(new Dimension(100,100));
        panelCentreBottom.setPreferredSize(new Dimension(100,30));
        panelBottomBottom.setPreferredSize(new Dimension(30,30));

        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelRight, BorderLayout.EAST);
        frame.add(panelBottom, BorderLayout.SOUTH);
        frame.add(panelCentre, BorderLayout.CENTER);

        panelLeft.setLayout(new BorderLayout());
        panelTop.setLayout(new BorderLayout());
        panelRight.setLayout(new BorderLayout());
        panelCentre.setLayout(new BorderLayout());
        panelBottom.setLayout(new BorderLayout());
        panelCentreBottom.setLayout(new BorderLayout());
        panelBottomBottom.setLayout(new BorderLayout());

        //Sub-panels (for buttons)
        JPanel panelNorth = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelNorth2 = new JPanel();
        JPanel panelWest2 = new JPanel();
        JPanel panelEast2 = new JPanel();
        JPanel panelSouth2 = new JPanel();
        JPanel panelBottomRight = new JPanel();

        panelNorth.setBackground(Color.blue);
        panelWest.setBackground(Color.YELLOW);
        panelEast.setBackground(Color.YELLOW);
        panelSouth.setBackground(Color.blue);
        panelNorth2.setBackground(Color.BLUE);
        panelWest2.setBackground(Color.blue);
        panelEast2.setBackground(Color.blue);
        panelSouth2.setBackground(Color.blue);
        panelBottomRight.setBackground(Color.green);

        panelNorth.setPreferredSize(new Dimension(50,80));
        panelWest.setPreferredSize(new Dimension(300,25));
        panelEast.setPreferredSize(new Dimension(300,80));
        panelSouth.setPreferredSize(new Dimension(25,80));
        panelNorth2.setPreferredSize(new Dimension(50,80));
        panelWest2.setPreferredSize(new Dimension(200,25));
        panelEast2.setPreferredSize(new Dimension(200,80));
        panelSouth2.setPreferredSize(new Dimension(25,80));
        panelBottomRight.setPreferredSize(new Dimension(80,50));
        panelBottomRight.setLayout(new BorderLayout());

        panelTop.add(panelWest, BorderLayout.WEST);
        panelTop.add(panelEast, BorderLayout.EAST);
        panelLeft.add(panelSouth, BorderLayout.SOUTH);
        panelLeft.add(panelNorth, BorderLayout.NORTH);
        panelRight.add(panelNorth2, BorderLayout.NORTH);
        panelRight.add(panelSouth2, BorderLayout.SOUTH);
        panelCentre.add(panelCentreBottom, BorderLayout.SOUTH);
        panelCentreBottom.add(panelWest2, BorderLayout.WEST);
        panelCentreBottom.add(panelEast2, BorderLayout.EAST);
        panelBottom.add(panelBottomBottom, BorderLayout.SOUTH);
        panelBottomBottom.add(panelBottomRight, BorderLayout.EAST);

        //Buttons
        west = new JButton();
        west.setSize(90,25);
        west.addActionListener(this);
        west.setText("Go West");
        west.setFocusable(false);
        west.setVisible(true);

        north = new JButton();
        north.setSize(90,25);
        north.addActionListener(this);
        north.setText("Go North");
        north.setFocusable(false);
        north.setVisible(true);

        east = new JButton();
        east.setSize(90,25);
        east.addActionListener(this);
        east.setText("Go East");
        east.setFocusable(false);
        east.setVisible(true);

        south = new JButton();
        south.setSize(90,25);
        south.addActionListener(this);
        south.setText("Go South");
        south.setFocusable(false);
        south.setVisible(true);

        input = new JButton("Input");
        input.addActionListener(this);
        input.setSize(100,100);
        input.setFocusable(false);

        panelLeft.add(west);
        panelTop.add(north);
        panelRight.add(east);
        panelCentreBottom.add(south);
        panelBottomRight.add(input);

        //Text Output
        textBox = new JTextArea();
        textBox.setSize(600,300);
        //textBox.setBackground(Color.red);
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);
        textBox.setVisible(true);
        textBox.setEditable(false);
        Printer printer = new Printer(textBox);

        //Text Input
        textInput = new JTextField();
        panelBottomBottom.add(textInput);

        //Scroll bar
        scrollBar = new JScrollPane(textBox);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelBottom.add(scrollBar);

        //Room Images
        JLabel images = new JLabel();
        ImageIcon roomImage = new ImageIcon("kitchen.png");
        images.setIcon(roomImage);

        panelCentre.add(images);

        frame.setVisible(true);

        game.printWelcome();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==west) {
            Printer.addText("go west");
            game.parserInput("go west");
        }
        if(e.getSource()==north) {
            Printer.addText("go north");
            game.parserInput("go north");
        }
        if(e.getSource()==east) {
            Printer.addText("east");
            game.parserInput("go east");
        }
        if(e.getSource()==south) {
            Printer.addText("go south");
            game.parserInput("go south");
        }
        if(e.getSource()==input) {
            Printer.addText(textInput.getText());
            game.parserInput(textInput.getText());
            textInput.setText("");
        }
        JScrollBar vertical = scrollBar.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
}
