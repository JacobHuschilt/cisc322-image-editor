import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

// For documentation purposes, import only edfmwk classes actually used.
import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.DefaultAction;
import ca.queensu.cs.dal.edfmwk.win.CommonWindow;
import ca.queensu.cs.dal.flex.log.Log;
/**
 * {@link javax.swing.Action} for implementing "Resize" functionality.
 *<p>
 * based on code written by David Lamb
 * last modified by David Seekatz and Julia Yach
 */
public class ResizeAction extends ImageAction {

    /**
     * Constructs a resize action - resizes the image
     */
    public ResizeAction() {
		super("Resize");
    } // end constructor ResizeAction

    /**
     * Resize the image to the specified X and Y values - allows the 
     * image to be stretched/distorted from its original proportions.
     * @param con The contents of the image document to be resized
     */
    protected void changeImage(ImageContents con) {
        try {
            BufferedImage image = con.getImg();
            int width = 100;
            int height = 100;

            Image tmp = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics g = newImg.createGraphics();
            g.drawImage(tmp,0,0,null);
            g.dispose();

            con.setImg(newImg);

        } catch(Exception e) {
            e.printStackTrace();
        } // end try-catch
    } // end changeImage
    
} // end class CropAction
