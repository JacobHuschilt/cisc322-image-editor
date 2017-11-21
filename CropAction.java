import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

import javax.swing.*;

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
            int[] input = getUserInput();
            if (input == null) {
                return;
            }

            BufferedImage image = con.getImg();
            int xStart = input[0];
            int yStart = input[1];
            int width = input[2];
            int height = input[3];

            BufferedImage newImg = image.getSubimage(xStart, yStart, width, height);
            con.setImg(newImg);
        } catch (RasterFormatException rfe) {
            // If we have time, maybe show a dialog box here displaying the error
            System.out.println("Invalid Crop Area!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end changeImage

    // Really bad quick and dirty dialog box to get user input
    private int[] getUserInput() {
        JTextField xStart = new JTextField();
        JTextField yStart = new JTextField();
        JTextField width = new JTextField();
        JTextField height = new JTextField();
        int[] resultArray = new int[4];
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Starting x location"),
                xStart,
                new JLabel("Starting y location"),
                yStart,
                new JLabel("Cropped image width"),
                width,
                new JLabel("Cropped image height"),
                height
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "Please define the crop area", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                resultArray[0] = Integer.parseInt(xStart.getText());
                resultArray[1] = Integer.parseInt(yStart.getText());
                resultArray[2] = Integer.parseInt(width.getText());
                resultArray[3] = Integer.parseInt(height.getText());
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
