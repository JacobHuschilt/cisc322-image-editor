// $Id: TextType.java,v 1.3 2012/10/24 17:06:40 dalamb Exp $
// Import only those classes from edfmwk that are essential, for documentation purposes

import java.awt.Component;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import java.awt.image.*;
// import javax.swing.KeyStroke;
import ca.queensu.cs.dal.edfmwk.act.DefaultAction;
import ca.queensu.cs.dal.edfmwk.doc.Document;
import ca.queensu.cs.dal.edfmwk.doc.DocumentType;
import ca.queensu.cs.dal.edfmwk.doc.DocumentManager;
import ca.queensu.cs.dal.edfmwk.menu.MenuDescriptor;
import ca.queensu.cs.dal.data.tree.TreeException;
import ca.queensu.cs.dal.edfmwk.menu.MenuElement;
import ca.queensu.cs.dal.flex.log.Log;

/**
 * <a href="http://en.wikipedia.org/wiki/Factory_(software_concept)">Factory</a>
 * for representations of image files.
 * <p>
 * Last modified by Julia Yach.
 */
public class ImageType implements DocumentType {
    /**
     * Construct a new factory for Image
     * objects.
     */
    public ImageType() {
    }

    public String getName() {
        return "Image file";
    }

    /**
     * Create and initialize a new representation for an image document.
     *
     * @return the new document contents.
     */
    public ImageDocument newDocument() {
        return new ImageDocument(this);
    }

    /**
     * Get the descriptor for the menu items appropriate for this type of
     * document.  For example, <code>"Image/Resize"</code> could be one such
     * menu element for an image processing program.  The descriptor must
     * not include type-independent menu items, such as
     * <code>"File/Exit"</code>
     * Normally the actual {@link ca.queensu.cs.dal.edfmwk.menu.MenuDescriptor}
     * would have been static, but specific actions such as Cut and Paste
     * must be fetched from the {@link javax.swing.JTextArea} embedded in
     * the frame displaying the document.
     *
     * @param doc Document whose state or GUI representation might influence
     *            the initial state of the menu.
     */
    public MenuDescriptor getMenu(Document doc) {
        MenuDescriptor desc = getStaticMenu().copy();
        Component comp = doc.getWindow();
        JLabel image = null;
        if (comp instanceof JScrollPane) {
            JScrollPane scroll = (JScrollPane) comp;
            comp = scroll.getViewport().getView();
        }
        if (comp instanceof JLabel) image = (JLabel) comp;
        if (image == null) {
            // an internal error
            System.out.println("Got unexpected " + comp);
            return desc;
        }

        // JTextArea-specific actions
        // System.out.println("Got JTextArea "+txt);
       setActions(image);
       // Keymap km = txt.getKeymap();
       // if (km==null) System.out.println("No keymap");
       //for (int i = 0; i < actionPairs.length; i++) {
           //String menuName = actionPairs[i][0];
           //String actionName = actionPairs[i][1];
           //try {
               //Action ac = new RotateAction();
               //Action ac = getNamedAction(actionName);
               // System.out.print(menuName+"=");
               // debugAction(actionName, km, ac);
               //desc.addElement(new MenuElement(menuName, ac));
           //} catch (TreeException e) {
               //System.out.println("Path error " + menuName + "=" + actionName +
               //        ":" + e);
           //}
       //} // for

        return desc;
    } // getMenu

    /*
    static void debugAction(Object name, Keymap km, Action ac) {
	System.out.print(name);
	if (km!=null) {
	    KeyStroke strokes[] = km.getKeyStrokesForAction(ac);
	    if (strokes == null) {
		System.out.print(" no keystrokes");
	    } else if (strokes.length==0) {
		System.out.print(" zero-length keystrokes");
	    } else {
		for(int j=0; j<strokes.length; j++) {
		    System.out.print(" "+strokes[j]);
		}
	    }
	}
	System.out.println();
    }
    */

    //Not sure what to do here...helpp?? -Julia
    // Some error here... What do these "actions" do here?
    private static String[][] actionPairs = {
	{ "Edit/Rotate", "rotate-clockwise"} ,
//	{ "Edit/Cut", DefaultEditorKit.cutAction},
            // { "", DefaultEditorKit.cutAction}, // test error check
//	{ "Edit/Paste", DefaultEditorKit.pasteAction}
    };


    /**
     * Map from action names to text-component-specific actions.
     * It should only be considered valid within a single call to
     * {@link #getMenu}
     */
    private HashMap<Object, Action> actions;

    /**
     * Gets the action with a specific name.
     */
    private Action getNamedAction(Object name) {
        return actions.get(name);
    }

    /**
     * Set the {@link #actions} member to contain a list of the actions
     * allowed on the current text component. The actions (might?) embed
     * references to the specific text component, which is why we have to do
     * it over again for each document.
     *
     * @param txt The text component from which to retrieve actions.
     */
   private void setActions(JLabel image) {
       actions = new HashMap<Object, Action>();
       ActionMap actionMap = image.getActionMap();
       Object[] keys = actionMap.allKeys();
       for (int i = 0; i < actionMap.size(); i++) {
           Action a = actionMap.get(keys[i]);
           actions.put(a.getValue(Action.NAME), a);
       }
   } // setActions

    /**
     * Get the descriptor for the menu items appropriate for this type of
     * document.  For example, <code>"Image/Resize"</code> could be one such
     * menu element for an image processing program.  The descriptor must
     * not include type-independent menu items, such as
     * <code>"File/Exit"</code>
     */
    private MenuDescriptor getStaticMenu() {
        if (menu == null) {
            menu = new MenuDescriptor();
            try {
		        menu.addElement(new MenuElement("Edit/Rotate", new RotateAction()));
                menu.addElement(new MenuElement("Edit/Crop", new CropAction()));
                menu.addElement(new MenuElement("Edit/Resize", new ResizeAction()));
                menu.addElement(new MenuElement("Edit/Adjust Brightness", new BrightnessAction()));
            } catch (Exception e) {
                Log.internalError("Menu element error " + e.getLocalizedMessage());
            }
        }
        return menu;
    }

    /**
     * The descriptor for the editor's main menu.
     */
    private static MenuDescriptor menu;

    /**
     * Get the filename extensions appropriate for this kind of document.
     * For example,
     * <code>{ ".html", ".htm" }</code>
     * for HyperText Markup Language documents.
     */
    public String[] getExtensions() {
        return extensions;
    }

    /**
     * The expected extensions for files the application can edit.
     */
    private static String[] extensions = {"jpg", "JPG", "jpeg", "JPEG", "png"};

} // end class TextType
