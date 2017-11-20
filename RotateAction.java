import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 * {@link javax.swing.Action} for implementing "Rotate" functionality.
 *<p>
 * based on code written by David Lamb
 * last modified by David Seekatz and Julia Yach
 */
public class RotateAction extends ImageAction {

    /**
     * Constructs a rotation action - rotates the image
     */
    public RotateAction() {
		super("Rotate");
    } // end constructor RotateAction

    /**
     * Rotate the image clockwise by 90 degrees. 
     * Some code in this section is based on an example in the following link:
     * https://stackoverflow.com/questions/8639567/java-rotating-images
     * Note that the example in the link only works for perfectly square images,
     * so a non-trivial amount of additional work was needed.
     * @param con The contents of the image document to be rotated.
     */
    protected void changeImage(ImageContents con) {
        try {
            BufferedImage image = con.getImg();
            double h = image.getHeight();
            double w = image.getWidth();
            
            BufferedImage newImg = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
            
            double locX = w / 2;
            double locY = h / 2;
            double translateX = (h - w) / 2;
            double translateY = (w - h) / 2;

            // Rotate the image around the center of the NEW image, and then
            // translate by the distance between the old image center and the
            // new image center.
            AffineTransform tx = new AffineTransform();
            tx.rotate(Math.PI / 2, locY, locX);
            tx.translate(translateX, translateY);
    
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            
            // Drawing the rotated image
            newImg = op.filter(image, null);

            con.setImg(newImg);
        } catch(Exception e) {
            e.printStackTrace();
        } //end try-catch
    } // end changeImage

} // end class RotateAction
