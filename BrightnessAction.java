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
            float input = getUserInput();
            if (input < 0) {
                return;
            } // end if

            BufferedImage image = contents.getImg();

            RescaleOp brightnessScaler = new RescaleOp(input, 0, null);

            image = brightnessScaler.filter(image, image);

            contents.setImg(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Really bad quick and dirty dialog box to get user input
    private float getUserInput() {
        float input;
        JTextField brightnessFactor = new JTextField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Brightness Factor"),
                brightnessFactor
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "Please define the crop area", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                input = Float.parseFloat(brightnessFactor.getText());
            } catch (Exception e) {
                // Make a dialog box here if we have time
                System.out.println("Invalid input!");
                return -1;
            } // end try-catch
            if (input < 0) {
                input = 0;
            } // end if
        } else {
            return -1;
        } // end if-else
        return input;
}
}
