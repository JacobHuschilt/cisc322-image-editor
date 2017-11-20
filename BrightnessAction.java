import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 * Created by jacobhuschilt on 11/20/17.
 */
public class BrightnessAction extends ImageAction {

    /**
     * Constructor for a Brightness action - Adjust the image brightness
     */
    public BrightnessAction() {
        super("Adjust Brightness");
    }

    protected void changeImage(ImageContents contents) {
        try {
            BufferedImage image = contents.getImg();
            float brightnessFactor = (float) 0.75;

            RescaleOp brightnessScaler = new RescaleOp(brightnessFactor, 0, null);

            image = brightnessScaler.filter(image, image);

            contents.setImg(image);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot get pixel values for image: " + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}
