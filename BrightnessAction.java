import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import javax.swing.*;

/**
 * {@link javax.swing.Action} for implementing "Adjust Brightness" functionality.
 *<p>
 * based on code written by David Lamb
 * Created by jacobhuschilt on 11/20/17.
 */
public class BrightnessAction extends ImageAction {

    /**
     * Constructor for a Brightness action - Adjust the image brightness
     */
    public BrightnessAction() {
        super("Adjust Brightness");
    }

    /**
     * Adjust the image brightness, by a scale factor, values < 1 darken the image, while values > 1 brighten it.
     *
     * @param contents current contents of the image document
     */
    protected void changeImage(ImageContents contents) {
        try {
            // Display a custom prompt with a JSlider to adjust brightness
            BrightnessSliderOptionPane prompt = new BrightnessSliderOptionPane();
            prompt.show();

            float brightnessFactor = prompt.getBrightnessLevel();

            BufferedImage image = contents.getImg();

            // Multiply each pixel by the scaling factor from the slider
            RescaleOp brightnessScaler = new RescaleOp(brightnessFactor, 0, null);

            image = brightnessScaler.filter(image, image);

            contents.setImg(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
