import java.awt.event.ActionEvent;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.DefaultAction;
import ca.queensu.cs.dal.edfmwk.win.CommonWindow;
import ca.queensu.cs.dal.flex.log.Log;

/**
 * Parent for {@link javax.swing.Action Actions} for implementing changes to
 * the current image. Subclasses need only implement the
 * {@link #changeImage} method.
 * <p>
 * Based on code written by David Lamb
 * Last modified by David Seekatz
 */
public abstract class ImageAction extends DefaultAction {
    /**
     * Constructs an image manipulation action
     *
     * @param name Name of the action.
     */
    protected ImageAction(String name) {
        super(name);
    } // end constructor ImageAction

    /**
     * Perform some appropriate change on an image;
     * subclasses must implement this method.
     */
    protected abstract void changeImage(ImageContents con);

    /**
     * Perform the appropriate action (defined by {@link #changeImage}) on the
     * image.
     */
    public void actionPerformed(ActionEvent ae) {
        try {
            Application app = Application.getApplication();
            CommonWindow win = app.getActiveWindow();
            JLabel label = (JLabel) ((JScrollPane) win.getContentPane()).getViewport().getView();
            ImageDocument doc = (ImageDocument) app.getActiveDocument();
            ImageContents con = doc.getContents();
            changeImage(con);

            // Update the image to be displayed
            label.setIcon(new ImageIcon(con.getImg()));
            label.revalidate();

            // This method apparently re-sizes the display window to fit the contents.
            win.pack();

        } catch (Exception ex) {
            Log.error("Image action error: " + ex.getLocalizedMessage());
        } // end try-catch
    } // end actionPerformed method

} // end class ImageAction
