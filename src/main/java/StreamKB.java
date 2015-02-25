/**
 * StreamKB 1.3
 * ============
 * Patch Notes:
 * 1.3 - Added Always On Top option
 * 1.2 - Using a different keyboard hook that uses less memory.
 *       Fixed minor issue with color choosing.
 * 	     Reduced CPU used when keys are held down.
 * 1.1 - Changed key event references from KeyCode to RawCode.
 * 		 Updated method for highlighting/reverting button backgrounds to prevent errors when not using standard US keyboard.
 */

/**
 * @author Mike
 *
 */

import de.ksquared.system.keyboard.GlobalKeyListener;
import de.ksquared.system.keyboard.KeyAdapter;
import de.ksquared.system.keyboard.KeyEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class StreamKB extends JPanel {
    private static final long serialVersionUID = 2036873143L;
    protected static Color bgC, btC, hiC, txtC;
    //HashMap to contain all buttons
    protected Map<Integer, KBButton> KB = Collections.synchronizedMap(new HashMap<Integer, KBButton>());
    protected Set<Integer> pressed = Collections.synchronizedSet(new HashSet<Integer>());
    protected Point clickPos;
    protected String mySize;
    protected boolean isOnTop;

    public StreamKB(final JFrame frame) {
        super(new GridLayout(6, 1));

        //Loads saved colors from file, otherwise sets initial Colors
        try {
            FileInputStream loadColors = new FileInputStream("StreamKB.Settings");
            ObjectInputStream load = new ObjectInputStream(loadColors);
            bgC = (Color) load.readObject();
            btC = (Color) load.readObject();
            hiC = (Color) load.readObject();
            txtC = (Color) load.readObject();
            frame.setLocation((Point) load.readObject());
            mySize = (String) load.readObject();
            isOnTop = (Boolean) load.readObject();
            load.close();
        } catch (Exception e) {
            bgC = new Color(200, 200, 200);
            btC = new Color(238, 238, 238);
            hiC = new Color(0, 255, 0);
            txtC = new Color(0, 0, 0);
            mySize = "Big";
            isOnTop = false;
        }

        //Set AlwayOnTop state.
        frame.setAlwaysOnTop(isOnTop);

        //Declare panels for each row on keyboard
        final JPanel fKeys = new JPanel();
        fKeys.setLayout(new FlowLayout(FlowLayout.TRAILING, 1, 1));
        final JPanel numKeys = new JPanel();
        numKeys.setLayout(new FlowLayout(FlowLayout.TRAILING, 1, 1));
        final JPanel tabLine = new JPanel();
        tabLine.setLayout(new FlowLayout(FlowLayout.TRAILING, 1, 1));
        final JPanel capsLine = new JPanel();
        capsLine.setLayout(new FlowLayout(FlowLayout.TRAILING, 1, 1));
        final JPanel shiftLine = new JPanel();
        shiftLine.setLayout(new FlowLayout(FlowLayout.TRAILING, 1, 1));
        final JPanel ctrlLine = new JPanel();
        ctrlLine.setLayout(new FlowLayout(FlowLayout.TRAILING, 1, 1));
        final JPanel upDown = new JPanel();
        upDown.setLayout(new BorderLayout(0, 0));

        //set background color for each panel
        fKeys.setBackground(bgC);
        numKeys.setBackground(bgC);
        tabLine.setBackground(bgC);
        capsLine.setBackground(bgC);
        shiftLine.setBackground(bgC);
        ctrlLine.setBackground(bgC);
        upDown.setBackground(bgC);
        setBackground(bgC);
        //frame.setBackground(bgC);

        //List of key values for each row
        Integer[] fKeyList = {27, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 44, 19, 45, 46};
        Integer[] numKeyList = {223, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 189, 187, 8};
        Integer[] tabLineList = {9, 81, 87, 69, 82, 84, 89, 85, 73, 79, 80, 219, 221, 255};
        Integer[] capsLineList = {20, 65, 83, 68, 70, 71, 72, 74, 75, 76, 186, 192, 222, 13};
        Integer[] shiftLineList = {160, 220, 90, 88, 67, 86, 66, 78, 77, 188, 190, 191, 161};
        Integer[] ctrlLineList = {162, 91, 164, 32, 165, 93, 163};

        //Put all buttons into KB HashMap so they can be accessed later by key value
        makeButtons(fKeyList);
        makeButtons(numKeyList);
        makeButtons(tabLineList);
        makeButtons(capsLineList);
        makeButtons(shiftLineList);
        makeButtons(ctrlLineList);

        //Put all keys into keyboard
        addButtons(fKeys, fKeyList);
        addButtons(numKeys, numKeyList);
        addButtons(tabLine, tabLineList);
        addButtons(capsLine, capsLineList);
        addButtons(shiftLine, shiftLineList);
        addButtons(ctrlLine, ctrlLineList);

        //manually adding arrow keys for formatting
        KB.put((Integer) 37, new KBButton(37));
        KB.put((Integer) 38, new KBButton(38));
        KB.put((Integer) 39, new KBButton(39));
        KB.put((Integer) 40, new KBButton(40));
        ctrlLine.add(KB.get((Integer) 37));
        upDown.add(KB.get((Integer) 38), BorderLayout.NORTH);
        upDown.add(KB.get((Integer) 40), BorderLayout.SOUTH);
        ctrlLine.add(upDown);
        ctrlLine.add(KB.get((Integer) 39));

        //adds all button rows to main panel
        add(fKeys);
        add(numKeys);
        add(tabLine);
        add(capsLine);
        add(shiftLine);
        add(ctrlLine);

        //Allow window to be dragged
        //Get start point
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                clickPos = e.getPoint();
                getComponentAt(clickPos);
            }
        });

        //If drag, move
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                // get location of Window
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = (thisX + e.getX()) - (thisX + clickPos.x);
                int yMoved = (thisY + e.getY()) - (thisY + clickPos.y);

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);
            }
        });

        //add right click menu
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem optionsMenuItem = new JMenuItem("Color Options");
                    optionsMenuItem.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    //Make frame for pop-up window
                                    JPanel OptionsPane = new JPanel();
                                    OptionsPane.setLayout(new GridLayout(4, 0));

                                    //Make panels for each color change option
                                    JPanel bgColor = new JPanel();
                                    JPanel btColor = new JPanel();
                                    JPanel hiColor = new JPanel();
                                    JPanel txtColor = new JPanel();

                                    //generate options for each pane and add them to pop-up window
                                    OptionsPane.add(colorOption(bgColor, bgC, "Background"));
                                    OptionsPane.add(colorOption(btColor, btC, "Button"));
                                    OptionsPane.add(colorOption(hiColor, hiC, "Highlight"));
                                    OptionsPane.add(colorOption(txtColor, txtC, "Text"));

                                    //Pop-up!
                                    int result = JOptionPane.showConfirmDialog(null, OptionsPane, "Select Desired Colors", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                                    //Apply color changes if OK button hit.
                                    if (result == JOptionPane.OK_OPTION) {
                                        bgC = bgColor.getBackground();
                                        btC = btColor.getBackground();
                                        hiC = hiColor.getBackground();
                                        txtC = txtColor.getBackground();
                                        fKeys.setBackground(bgC);
                                        numKeys.setBackground(bgC);
                                        tabLine.setBackground(bgC);
                                        capsLine.setBackground(bgC);
                                        shiftLine.setBackground(bgC);
                                        ctrlLine.setBackground(bgC);
                                        upDown.setBackground(bgC);
                                        setBackground(bgC);
                                        frame.setBackground(bgC);
                                        for (KBButton b : KB.values()) {
                                            b.setForeground(txtC);
                                            b.setBorder(BorderFactory.createLineBorder(txtC));
                                            b.setBackground(btC);
                                            b.repaint();
                                        }
                                        repaint();

                                        //save changes to settings
                                        try {
                                            FileOutputStream saveColors = new FileOutputStream("StreamKB.settings", false);
                                            ObjectOutputStream save = new ObjectOutputStream(saveColors);
                                            save.writeObject(bgC);
                                            save.writeObject(btC);
                                            save.writeObject(hiC);
                                            save.writeObject(txtC);
                                            save.writeObject(frame.getLocation());
                                            save.writeObject(mySize);
                                            save.writeObject(isOnTop);
                                            save.close();
                                        } catch (Exception e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                }
                            });

                    JCheckBoxMenuItem AlwaysOnTop = new JCheckBoxMenuItem("Always On Top");
                    AlwaysOnTop.setState(isOnTop);
                    AlwaysOnTop.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    if (isOnTop) {
                                        frame.setAlwaysOnTop(false);
                                        isOnTop = false;
                                    } else {
                                        frame.setAlwaysOnTop(true);
                                        isOnTop = true;
                                    }
                                }
                            });

                    JMenuItem Close = new JMenuItem("Exit");
                    Close.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    //saves window variables before closing
                                    try {
                                        FileOutputStream saveColors = new FileOutputStream("StreamKB.settings", false);
                                        ObjectOutputStream save = new ObjectOutputStream(saveColors);
                                        save.writeObject(bgC);
                                        save.writeObject(btC);
                                        save.writeObject(hiC);
                                        save.writeObject(txtC);
                                        save.writeObject(frame.getLocation());
                                        save.writeObject(mySize);
                                        save.writeObject(isOnTop);
                                        save.close();
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                    }
                                    System.exit(0);
                                }
                            });
                    JMenuItem sizeMenuItem = new JMenuItem();
                    if (mySize.equals("Big")) {
                        sizeMenuItem.setText("Shrink Keyboard");
                    } else {
                        sizeMenuItem.setText("Enlarge Keyboard");
                    }
                    sizeMenuItem.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    if (mySize.equals("Big")) {
                                        mySize = "Small";
                                        for (KBButton b : KB.values()) {
                                            b.shrink();
                                        }
                                        frame.pack();
                                    } else {
                                        mySize = "Big";
                                        for (KBButton b : KB.values()) {
                                            b.initKeyAppearance(12, 24, 24);
                                        }
                                        frame.pack();
                                    }
                                }
                            });


                    menu.add(optionsMenuItem);
                    menu.add(sizeMenuItem);
                    menu.add(AlwaysOnTop);
                    menu.add(Close);
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        //Change color of all of the buttons and add copy mouse listeners from main window to each button
        for (KBButton b : KB.values()) {
            b.setForeground(txtC);
            b.setBorder(BorderFactory.createLineBorder(txtC));
            b.setBackground(btC);
            for (MouseListener m : this.getMouseListeners()) {
                b.addMouseListener(m);
            }
            for (MouseMotionListener m : this.getMouseMotionListeners()) {
                b.addMouseMotionListener(m);
            }
            if (!mySize.equals("Big")) {
                b.shrink();
            }
        }

        //Add global key listeners
        new GlobalKeyListener().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent KBe1) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (pressed.add((Integer) KBe1.getVirtualKeyCode()) && KB.containsKey((Integer) KBe1.getVirtualKeyCode())) {
                            KB.get((Integer) KBe1.getVirtualKeyCode()).setBackground(hiC);
                            revalidate();
                        }
                    }
                });
            }

            @Override
            public void keyReleased(final KeyEvent KBe2) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        pressed.remove((Integer) KBe2.getVirtualKeyCode());
                        if (KB.containsKey((Integer) KBe2.getVirtualKeyCode())) {
                            KB.get((Integer) KBe2.getVirtualKeyCode()).setBackground(btC);
                            revalidate();
                        }
                    }
                });
            }
        });
    }

    //Format frame and display
    private static void makeStreamKB() {
        //Create and set up the window.
        JFrame frame = new JFrame("StreamKB 1.3 EN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new StreamKB(frame));
        frame.setUndecorated(true);
        frame.setResizable(false);

        //Display the window.
        frame.pack();
        frame.setBackground(bgC);
        frame.setVisible(true);
    }

    //call makeStreamKB() in runnable thread
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                makeStreamKB();
            }
        });
    }

    //adds buttons to KB HashMap
    protected void makeButtons(Integer[] keyList) {
        for (Integer I : keyList) {
            KB.put(I, new KBButton(I.intValue()));
        }
    }

    //adds buttons from KB HashMap to pane
    protected void addButtons(JPanel pane, Integer[] keyList) {
        for (Integer I : keyList) {
            pane.add(KB.get(I));
        }
    }

    //generate options for color changes
    protected JPanel colorOption(final JPanel inner, final Color c, final String name) {
        JPanel outer = new JPanel();
        outer.setLayout(new BorderLayout(0, 0));
        TitledBorder ColorTitle = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), name + " Color");
        ColorTitle.setTitlePosition(TitledBorder.ABOVE_TOP);
        outer.setBorder(ColorTitle);
        inner.setBorder(BorderFactory.createLineBorder(Color.black));
        inner.setBackground(c);
        inner.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Color changed = JColorChooser.showDialog(null, "Pick " + name + " Color", inner.getBackground());
                if (changed != null) {
                    inner.setBackground(changed);
                }
            }
        });
        outer.add(inner);
        return outer;
    }
}