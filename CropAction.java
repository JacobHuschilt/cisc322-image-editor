// $Id: CapitalizeAction.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Graphics;
import java.awt.Color;

// For documentation purposes, import only edfmwk classes actually used.
import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.DefaultAction;
import ca.queensu.cs.dal.edfmwk.win.CommonWindow;
import ca.queensu.cs.dal.flex.log.Log;
/**
 * {@link javax.swing.Action} for implementing "Crop" functionality.
 *<p>
 * based on code written by David Lamb
 * last modified by David Seekatz
 */
public class CropAction extends ImageAction {

    /**
     * Constructs a rotation action - rotates the image
     */
    public CropAction() {
		super("Crop");
    } // end constructor RotateAction

    /**
     * Capitalize the text in a given range of the document -- that is,
     * capitalize each "word" (string of letters or digits). Does nothing if
     * the start and end indices are equal. 
     * Some code in this section is based on an example in the following link:
     * https://stackoverflow.com/questions/8639567/java-rotating-images
     * @param con Text to change.
     * @param start Index of the first character to change (the one to be
     *  capitalized).
     * @param end Index one beyond the last character to change.
     */
    protected void changeImage(ImageContents con) {
	try {
        BufferedImage image = con.getImg();
        int width = 300;
        int height = 300;
        int xStart = 100;
        int yStart = 100;

        BufferedImage newImg = image.getSubimage(xStart, yStart, width, height);
        con.setImg(newImg);

	} catch(Exception e) {
	    e.printStackTrace();
	}
    } // end changeImage
} // end class CropAction
