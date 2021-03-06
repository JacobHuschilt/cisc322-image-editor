import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

/**
 * {@link javax.swing.Action} for implementing "Resize" functionality.
 * <p>
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
     *
     * @param con The contents of the image document to be resized
     */
    protected void changeImage(ImageContents con) {
        try {
            int[] input = getUserInput();
            if (input == null) {
                return;
            }

            BufferedImage image = con.getImg();
            int width = input[0];
            int height = input[1];

            Image tmp = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics g = newImg.createGraphics();
            g.drawImage(tmp, 0, 0, null);
            g.dispose();

            con.setImg(newImg);

        } catch (Exception e) {
            e.printStackTrace();
        } // end try-catch
    } // end changeImage

    /**
     * Presents a JOptionPane with inputs needed for the resize action.
     *
     * @return int[] results from user input, width, height
     */
    private int[] getUserInput() {
        JTextField width = new JTextField();
        JTextField height = new JTextField();
        int[] resultArray = new int[2];
        final JComponent[] inputs = new JComponent[]{
                new JLabel("Re-sized image width"),
                width,
                new JLabel("Re-sized image height"),
                height
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "Please enter the dimensions", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                resultArray[0] = Integer.parseInt(width.getText());
                resultArray[1] = Integer.parseInt(height.getText());
            } catch (Exception e) {
                // Make a dialog box here if we have time
                System.out.println("Invalid input!");
                return null;
            }
        } else {
            return null;
        }
        return resultArray;
    }

} // end class CropAction
