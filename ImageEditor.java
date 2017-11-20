import javax.swing.JPanel;
import javax.swing.JMenuBar;
// Import only those classes from edfmwk that are essential, for documentation purposes
import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.*;
import ca.queensu.cs.dal.edfmwk.doc.DocumentType;
import ca.queensu.cs.dal.edfmwk.doc.DocumentManager;
import ca.queensu.cs.dal.edfmwk.i18n.Localizers;
import ca.queensu.cs.dal.edfmwk.menu.MenuDescriptor;
import ca.queensu.cs.dal.edfmwk.menu.MenuElement;
import ca.queensu.cs.dal.edfmwk.Menus;
import ca.queensu.cs.dal.flex.Register;
import ca.queensu.cs.dal.flex.i18n.Localizer;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

/**
 * A simple image editor using the document framework.
 *<p>
 * Based on code written by David Lamb
 * Last modified by Jacob Huschilt and David Seekatz.
 */

public class ImageEditor extends Application {
	private MenuDescriptor menu;
	/* package */ MainPanel mainPanel;
	private static String title = "Simple Image Editor";
	private static String copyright = "Jacob Huschilt, David Seekatz, and Julia Yach.";
	private static String adapted = "\nAdapted from work by David Lamb.";
	private static String aboutMsg = title + "\nWritten by" + copyright + adapted;
	private static String packageName = "ca.queensu.cs.dal.txt";
	private static String version = "0.01";
	private static Register register;
	//private final String helpURI = "http://cs.queensu.ca/home/dalamb/java/txt/help.html";

	/**
	 * Gets the menu descriptor for the main window, which contains only menu
	 *  items independent of the type of document to be displayed.
	 */
	public MenuDescriptor getMainMenu() {
		if (menu == null) {
			menu = new MenuDescriptor(Menus.getStandardMenu());
			//System.err.println("Got standard menu.");
			try {
//				menu.addElement(new MenuElement("Edit")); // position empty menu
				//menu.addElement(new MenuElement("View")); // position empty menu
				menu.addPath(Menus.getLanguageMenu());
				//menu.addElement(new MenuElement("Tools")); // position empty menu
				//menu.addElement(new MenuElement("Help/Help", new HelpAction(helpURI), "Help contents"));
				menu.addElement(new MenuElement("Help/About", new AboutAction(aboutMsg)));
				menu.addElement(new MenuElement("Help/Credits", new CreditAction()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return menu;
	} // end getMainMenu

	/*
	 * Constructs and initializes the editor.
	 */
	public ImageEditor() {
		super(title);
		mainPanel = new MainPanel();
		DocumentType fac = new ImageType();
		DocumentManager manager = getDocumentManager();
		if (manager != null) {
			manager.addExtension(fac);
		}
		// TODO: Fix localization for image-specific actions
		// Localizers.Menu.getLocalizer().addBaseName(packageName+".Text");
		// Localizers.Message.getLocalizer().addBaseName(packageName+".Text");
		addLocalizerBaseName(packageName + ".Text");
		MenuDescriptor mainMenu = getMainMenu();
		MenuElement newAction = mainMenu.getElement("New");

		if (newAction != null) {
			newAction.setAction(new NewAction(fac));
		}
		
		register = new Register(packageName, title, copyright, version);
		Register.setPrimaryRegister(register);
		setup(mainPanel, mainMenu);
		finishSetup();
	} // end constructor ImageEditor

	/**
	 * Editor main program.  Constructs an instance of ImageEditor and
	 * waits for user interface events.
	 */
	public static void main(String args[]) {
		new ImageEditor();
	} // end main

	/**
	 * Returns the main application, so that other classes can access some
	 *   global information.
	 */
	public static ImageEditor getApplication() {
		return (ImageEditor) Application.getApplication();
	}

} // end class ImageEditor
