import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
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
