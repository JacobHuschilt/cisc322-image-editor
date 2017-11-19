// $Id: TextAction.java,v 1.1 2012/10/24 17:06:40 dalamb Exp $
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
// import javax.swing.Action;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
// import javax.swing.text.Keymap;
// import javax.swing.text.DefaultEditorKit;
// import javax.swing.KeyStroke;
import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.DefaultAction;
import ca.queensu.cs.dal.edfmwk.win.CommonWindow;
import ca.queensu.cs.dal.flex.log.Log;
/**
 * Parent for {@link javax.swing.Action Actions} for implementing changes to
 * the current image. Subclasses need only implement the
 * {@link #changeImage} method.
 *<p>
 * Based on code written by David Lamb
 * Last modified by David Seekatz
 */
public abstract class ImageAction extends DefaultAction {
    /**
     * Constructs a text manipulation action
     */
    private ImageAction() {
	super("Image");
    } // end constructor ImageAction

    /**
     * Constructs an image manipulation action
     * @param name Name of the action.
     */
    protected ImageAction(String name) {
	super(name);
    } // end constructor ImageAction
    
    /**
     * Perform some appropriate change on an image;
     *    subclasses must implement this method.
     */
    protected abstract void changeImage(ImageContents con);

    /**
     * Perform the appropriate action (defined by {@link #changeText}) on the
     *    image.
     */
    public void actionPerformed(ActionEvent ae) {
	try {
	    Application app = Application.getApplication();
	    CommonWindow win = app.getActiveWindow();
	    JLabel label = (JLabel) ((JScrollPane) win.getContentPane()).getViewport().getView();
	    // if (firstArea==null) setArea(area);
		ImageDocument doc = (ImageDocument) app.getActiveDocument();
		ImageContents con = doc.getContents();
	    //ImageContents con = (ImageContents) doc.getContents();
		changeImage(con);
		label.setIcon(new ImageIcon(con.getImg()));
		label.revalidate();
	} catch (Exception ex) {
	    Log.error("Image action error: "+ex.getLocalizedMessage());
	}
    }

    // debugging
    /*
    private static JTextArea firstArea = null;
    private static void setArea(JTextArea area) {
	TextType.setActions(area);
	Keymap km = area.getKeymap();
	if (km==null) {System.out.println("No keymap"); return; }
	String actionName=DefaultEditorKit.pasteAction;
	Action ac = TextType.getNamedAction(actionName);
	TextType.debugAction(actionName, km, ac);
	debugStroke("ctrl pressed V",km);
	debugStroke("ctrl X",km);
	debugStroke("ctrl pressed C",km);
	firstArea = area;
    }
    private static void debugStroke(String stroke, Keymap km) {
	KeyStroke testChar = KeyStroke.getKeyStroke(stroke);
	if (testChar!=null) {
	    System.out.print("test='"+stroke+"' '"+testChar+"'");
	    while (km != null) {
		if (km.isLocallyDefined(testChar)) {
		    Action ac = km.getAction(testChar);
		    if (ac==null) System.out.print(" no action");
		    else System.out.print(" action "+ac);
		    break;
		} else {
		    System.out.println(" not in "+km);
		    km = km.getResolveParent();
		}
	    } // while 
	    System.out.println();
	} else System.out.println("No ctl-C keystroke");
    } // debugStroke
    */
} // end class ImageAction
