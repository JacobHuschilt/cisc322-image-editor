// Import only those classes from edfmwk that are essential, for documentation purposes

import java.awt.Component;
import java.util.HashMap;
import javax.swing.*;
import ca.queensu.cs.dal.edfmwk.doc.Document;
import ca.queensu.cs.dal.edfmwk.doc.DocumentType;
import ca.queensu.cs.dal.edfmwk.menu.MenuDescriptor;
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
    } // end contructor

    /**
     * Returns the name for this type of document.
     */
    public String getName() {
        return "Image file";
    } // end getName

    /**
     * Create and initialize a new representation for an image document.
     *
     * @return the new document representation.
     */
    public ImageDocument newDocument() {
        return new ImageDocument(this);
    } // end newDocument

    /**
     * Get the descriptor for the menu items appropriate for this type of
     * document.  For example, <code>"Image/Resize"</code> could be one such
     * menu element for an image processing program.  The descriptor must
     * not include type-independent menu items, such as
     * <code>"File/Exit"</code>
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

        setActions(image);
        return desc;
    } // getMenu


    /**
     * Map from action names to image actions.
     * It should only be considered valid within a single call to
     * {@link #getMenu}
     */
    private HashMap<Object, Action> actions;

    /**
     * Gets the action with a specific name.
     */
    private Action getNamedAction(Object name) {
        return actions.get(name);
    } // end getNamedAction

    /**
     * Set the {@link #actions} member to contain a list of the actions
     * allowed on the current image.
     *
     * @param image the JLabel from which to retrieve the actions.
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

    // Not super sure if we actually need this part...
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

} // end class ImageType
