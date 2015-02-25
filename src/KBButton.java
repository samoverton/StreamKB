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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

public class KBButton extends JButton{
	private static final long serialVersionUID = 2036873143L;
	protected int kbHeight;
	protected int kbWidth;
	protected int myVal;
	
	public KBButton(int val){
		this(val, new Color(238,238,238));
	}

	public KBButton(int val, Color bg){
		super(KeyEvent.getKeyText(val));
		setFont(new Font("sansserif",Font.PLAIN,12));
		setMargin(new Insets(0, 0, 0, 0));
		setBackground(bg);
		kbHeight = 24;
		kbWidth = 24;
		myVal = val;
		
		switch(val){
			//ESC
			case 27:	setText("Esc");
						kbHeight = 17;
						kbWidth = 21;
						setFont(new Font("sansserif",Font.PLAIN,8));
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
			case 123:	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("sansserif",Font.PLAIN,8));
						break;
			//PrtScr	
			case 44:	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("sansserif",Font.PLAIN,8));
						setText("Prt");
						break;
			//Pause
			case 19:	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("Arial Black",Font.BOLD,8));
						setText("| |");
						break;
			//Insert
			case 45:	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("sansserif",Font.PLAIN,8));
						setText("Ins");
						break;
			//Del
			case 46: 	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("sansserif",Font.PLAIN,8));
						setText("Del");
						break;
			//tilde
			case 192: 	kbWidth = 12;
						setText("`");
						break;
			case 189: 	setText("-");
						break;
			case 187: 	setText("=");
						break;	
			//backspace
			case 8:	 	kbWidth = 44;
						setText("BS");
						break;
			//Tab
			case 9: 	kbWidth = 27;
						break;
			// [
			case 219: 	setText("[");
						break;
			// ]
			case 221: 	setText("]");
						break;
			// \
			case 220: 	kbWidth = 29;
						setText("\\");
						break;
			//CAPSLOCK
			case 20:	kbWidth = 40;
						setText("CAPS");
						break;
			// ;
			case 186: 	setText(";");
						break;
			// "
			case 222: 	setText("\"");
						break;			
			//enter
			case 13: 	setText("Enter");
						kbWidth = 41;
						break;								
			//left shift
			case 160:	setText("Shift");
						kbWidth = 53;
						break;
			// ,
			case 188: 	setText(",");
						break;
			// .
			case 190: 	setText(".");
						break;			
			// /
			case 191:	setText("/");
						break;
			//right shift
			case 161:	setText("Shift");
						kbWidth = 53;
						break;
			//left ctrl
			case 162: 	kbWidth = 29;
						setText("Ctrl");
						setFont(new Font("sansserif",Font.PLAIN,12));
						break;
			//Windows key
			case 91: 	kbWidth = 29;
						setText("Win");
						setFont(new Font("sansserif",Font.PLAIN,12));
						break;
			//left alt
			case 164:	setText("Alt");
						kbWidth = 29;
						break;
			//space
			case 32: 	kbWidth = 102;
						break;
			//Alt Gr
			case 165:	kbWidth = 29;
						setText("AltGr");
						break;
			// Context Menu
			case 93:	kbWidth = 29;
						setText("Mnu");
						setFont(new Font("sansserif",Font.PLAIN,12));
						break;
			//right ctrl
			case 163:	kbWidth = 29;
						setText("Ctrl");
						setFont(new Font("sansserif",Font.PLAIN,12));
						break;
			//left arrow			
			case 37:	kbHeight = 12;
						setFont(new Font("sansserif",Font.PLAIN,8));
						setText("L");
						break;
			//up arrow
			case 38:	kbHeight = 12;
						setFont(new Font("sansserif",Font.PLAIN,8));
						setText("U");
						break;
			//right arrow
			case 39:	kbHeight = 12;
						setFont(new Font("sansserif",Font.PLAIN,8));
						setText("R");
						break;
			//down arrow
			case 40: 	kbHeight = 12;
						setFont(new Font("sansserif",Font.PLAIN,8));
						setText("D");
						break;
		}
		setPreferredSize(new Dimension(kbWidth, kbHeight));
	}
	
	public void enlarge(){
		setFont(new Font("sansserif",Font.PLAIN,12));
		setMargin(new Insets(0, 0, 0, 0));
		kbHeight = 24;
		kbWidth = 24;
		
		switch(myVal){
			//ESC
			case 27:	kbHeight = 17;
						kbWidth = 21;
						setFont(new Font("sansserif",Font.PLAIN,8));
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
			case 123:	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("sansserif",Font.PLAIN,8));
						break;
			//PrtScr	
			case 44:	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("sansserif",Font.PLAIN,8));
						break;
			//Pause
			case 19:	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("Arial Black",Font.BOLD,8));
						setText("| |");
						break;
			//Insert
			case 45:	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("sansserif",Font.PLAIN,8));
						break;
			//Del
			case 46: 	kbHeight = 17;
						kbWidth = 20;
						setFont(new Font("sansserif",Font.PLAIN,8));
						break;
			//tilde
			case 192: 	kbWidth = 12;
						break;
			//backspace
			case 8:	 	kbWidth = 44;
						break;
			//Tab
			case 9: 	kbWidth = 27;
						break;
			// \
			case 220: 	kbWidth = 29;
						break;
			//CAPSLOCK
			case 20:	kbWidth = 40;
						break;		
			//enter
			case 13: 	kbWidth = 41;
						break;								
			//left shift
			case 160:	kbWidth = 53;
						break;
			//right shift
			case 161:	kbWidth = 53;
						break;
			//left ctrl
			case 162: 	kbWidth = 29;
						setFont(new Font("sansserif",Font.PLAIN,12));
						break;
			//Windows key
			case 91: 	kbWidth = 29;
						setFont(new Font("sansserif",Font.PLAIN,12));
						break;
			//left alt
			case 164:	kbWidth = 29;
						break;
			//space
			case 32: 	kbWidth = 102;
						break;
			//Alt Gr
			case 165:	kbWidth = 29;
						break;
			// Context Menu
			case 93:	kbWidth = 29;
						setFont(new Font("sansserif",Font.PLAIN,12));
						break;
			//right ctrl
			case 163:	kbWidth = 29;
						setFont(new Font("sansserif",Font.PLAIN,12));
						break;
			//left arrow			
			case 37:	kbHeight = 12;
						setFont(new Font("sansserif",Font.PLAIN,8));
						break;
			//up arrow
			case 38:	kbHeight = 12;
						setFont(new Font("sansserif",Font.PLAIN,8));
						break;
			//right arrow
			case 39:	kbHeight = 12;
						setFont(new Font("sansserif",Font.PLAIN,8));
						break;
			//down arrow
			case 40: 	kbHeight = 12;
						setFont(new Font("sansserif",Font.PLAIN,8));
						break;
		}
		setPreferredSize(new Dimension(kbWidth, kbHeight));
	}
	public void shrink(){
		setFont(new Font("sansserif",Font.PLAIN,10));
		kbHeight = 20;
		kbWidth = 20;
		
		switch(myVal){
		//ESC
		case 27:	kbHeight = 15;
					kbWidth = 21;
					setFont(new Font("sansserif",Font.PLAIN,7));
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
		case 123:	kbHeight = 15;
					kbWidth = 17;
					setFont(new Font("sansserif",Font.PLAIN,6));
					break;
		//PrtScr	
		case 44:	kbHeight = 15;
					kbWidth = 17;
					setFont(new Font("sansserif",Font.PLAIN,7));
					break;
		//Pause
		case 19:	kbHeight = 15;
					kbWidth = 17;
					setFont(new Font("Arial Black",Font.BOLD,7));
					setText("||");
					break;
		//Insert
		case 45:	kbHeight = 15;
					kbWidth = 17;
					setFont(new Font("sansserif",Font.PLAIN,7));
					break;
		//Del
		case 46: 	kbHeight = 15;
					kbWidth = 17;
					setFont(new Font("sansserif",Font.PLAIN,7));
					break;
		//tilde
		case 192: 	kbWidth = 12;
					break;
		//backspace
		case 8:	 	kbWidth = 44;
					break;
		//Tab
		case 9: 	kbWidth = 27;
					break;
		// \
		case 220: 	kbWidth = 29;
					break;
		//CAPSLOCK
		case 20:	kbWidth = 36;
					break;		
		//enter
		case 13: 	kbWidth = 41;
					break;								
		//left shift
		case 160:	kbWidth = 49;
					break;
		//right shift
		case 161:	kbWidth = 49;
					break;
		//left ctrl
		case 162: 	kbWidth = 25;
					setFont(new Font("sansserif",Font.PLAIN,7));
					break;
		//Windows key
		case 91: 	kbWidth = 25;
					setFont(new Font("sansserif",Font.PLAIN,7));
					break;
		//left alt
		case 164:	kbWidth = 25;
					break;
		//space
		case 32: 	kbWidth = 87;
					break;
		//Alt Gr
		case 165:	kbWidth = 28;
					break;
		// Context Menu
		case 93:	kbWidth = 25;
					setFont(new Font("sansserif",Font.PLAIN,12));
					break;
		//right ctrl
		case 163:	kbWidth = 25;
					setFont(new Font("sansserif",Font.PLAIN,12));
					break;
		//left arrow			
		case 37:	kbHeight = 9;
					setFont(new Font("sansserif",Font.PLAIN,7));
					break;
		//up arrow
		case 38:	kbHeight = 9;
					setFont(new Font("sansserif",Font.PLAIN,7));
					break;
		//right arrow
		case 39:	kbHeight = 9;
					setFont(new Font("sansserif",Font.PLAIN,7));
					break;
		//down arrow
		case 40: 	kbHeight = 9;
					setFont(new Font("sansserif",Font.PLAIN,7));
					break;
	}
		setPreferredSize(new Dimension(kbWidth, kbHeight));
	}
}