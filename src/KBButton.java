/**
 * KBButton 1.2
 * ============
 * Patch Notes:
 * 1.2 - Updated some setText so new hook displays correctly
 * 1.1 - Changed key event references from KeyCode to RawCode.
 *       Updated case structure to follow keyboard left to right and up to down.
 */

/**
 * @author Mike
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class KBButton extends JButton {
    private static final long serialVersionUID = 2036873143L;
    protected int kbHeight;
    protected int kbWidth;
    protected int myVal;

    public KBButton(int val) {
        this(val, new Color(238, 238, 238));
    }

    public KBButton(int val, Color bg) {
        super(KeyEvent.getKeyText(val));
        setBackground(bg);
        myVal = val;

        initKeyAppearance(12, 24, 24);
    }

    public void shrink() {
        initKeyAppearance(10, 20, 20);
    }

    public void initKeyAppearance(int fontSize, int defaultHeight, int defaultWidth) {
        setMargin(new Insets(0, 0, 0, 0));
        setFont(new Font("sansserif", Font.PLAIN, fontSize));
        kbHeight = defaultHeight;
        kbWidth = defaultWidth;

        switch (myVal) {
            //ESC
            case 27:
                setText("Esc");
                kbHeight = defaultHeight - 7;
                kbWidth = defaultWidth - 3;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                break;
            //F1-F12
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
                kbHeight = defaultHeight - 7;
                kbWidth = defaultWidth - 4;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                break;
            //PrtScr
            case 44:
                kbHeight = defaultHeight - 7;
                kbWidth = defaultWidth - 4;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText("Prt");
                break;
            //Pause
            case 19:
                kbHeight = defaultHeight - 7;
                kbWidth = defaultWidth - 4;
                setFont(new Font("Arial Black", Font.BOLD, fontSize - 4));
                setText("| |");
                break;
            //Insert
            case 45:
                kbHeight = defaultHeight - 7;
                kbWidth = defaultWidth - 4;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText("Ins");
                break;
            //Del
            case 46:
                kbHeight = defaultHeight - 7;
                kbWidth = defaultWidth - 4;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText("Del");
                break;
            //tilde
            case 223:
                kbWidth = defaultWidth - 12;
                setText("`");
                break;
            case 189:
                setText("-");
                break;
            case 187:
                setText("=");
                break;
            //backspace
            case 8:
                kbWidth = defaultWidth + 20;
                setText("BS");
                break;
            //Tab
            case 9:
                kbWidth = defaultWidth + 3;
                break;
            // [
            case 219:
                setText("[");
                break;
            // ]
            case 221:
                setText("]");
                break;
            // \
            case 220:
                kbWidth = defaultWidth + 9;
                setText("\\");
                break;
            //CAPSLOCK
            case 20:
                kbWidth = defaultWidth + 4;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText("CAPS");
                break;
            // ;
            case 186:
                setText(";");
                break;
            // "
            case 222:
                setText("\"");
                break;
            //tilde
            case 192:
                setText("'");
                break;
            //enter
            case 13:
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText("Enter");
                kbWidth = defaultWidth + 4;
                break;
            case 255:
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText(" ");
                kbWidth = defaultWidth + 5;
                break;
            //left shift
            case 160:
                setText("Shift");
                kbWidth = defaultWidth + 12;
                break;
            // ,
            case 188:
                setText(",");
                break;
            // .
            case 190:
                setText(".");
                break;
            // /
            case 191:
                setText("/");
                break;
            //right shift
            case 161:
                setText("Shift");
                kbWidth = defaultWidth + 12;
                break;
            //left ctrl
            case 162:
                kbWidth = defaultWidth + 5;
                setText("Ctrl");
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                break;
            //Windows key
            case 91:
                kbWidth = defaultWidth + 5;
                setText("Win");
                setFont(new Font("sansserif", Font.PLAIN, fontSize));
                break;
            //left alt
            case 164:
                setText("Alt");
                kbWidth = defaultWidth + 5;
                break;
            //space
            case 32:
                kbWidth = defaultWidth + 78;
                break;
            //Alt Gr
            case 165:
                kbWidth = defaultWidth + 5;
                setText("AltGr");
                break;
            // Context Menu
            case 93:
                kbWidth = defaultWidth + 5;
                setText("Mnu");
                setFont(new Font("sansserif", Font.PLAIN, fontSize));
                break;
            //right ctrl
            case 163:
                kbWidth = defaultWidth + 5;
                setText("Ctrl");
                setFont(new Font("sansserif", Font.PLAIN, fontSize));
                break;
            //left arrow
            case 37:
                kbHeight = defaultHeight - 12;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText("L");
                break;
            //up arrow
            case 38:
                kbHeight = defaultHeight - 12;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText("U");
                break;
            //right arrow
            case 39:
                kbHeight = defaultHeight - 12;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText("R");
                break;
            //down arrow
            case 40:
                kbHeight = defaultHeight - 12;
                setFont(new Font("sansserif", Font.PLAIN, fontSize - 4));
                setText("D");
                break;
        }
        setPreferredSize(new Dimension(kbWidth, kbHeight));
    }
}