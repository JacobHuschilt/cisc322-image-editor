import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
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
 * last modified by David Seekatz and Julia Yach
 */
public class CropAction extends ImageAction {

    /**
     * Constructs a crop action - crops the image
     */
    public CropAction() {
		super("Crop");
    } // end constructor CropAction

    /**
     * Crop the image, returning a subimage of the original.
     * @param con The contents of the image document to be cropped
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
        } catch (RasterFormatException rfe) {
            // If we have time, maybe show a dialog box here displaying the error
            System.out.println("Invalid Crop Area!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end changeImage
    
} // end class CropAction
