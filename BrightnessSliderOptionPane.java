import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Custom Class to show a dialog with a JSlider for the Image Brightness Action.
 *
 * Created by jacobhuschilt on 11/20/17.
 */
public class BrightnessSliderOptionPane {

    private float brightnessLevel;
    private int sliderValue;

    /**
     * Constructor for the brightness slider class, sets the default brightnessLevel to 1 (normal)
     */
    public BrightnessSliderOptionPane() {
        brightnessLevel = 1;
    }

    /**
     * Displays the custom JOptionPane with a JSlider, and saves the brightness level upon dialog confirmation.
     */
    public void show() {
        int result = JOptionPane.showConfirmDialog(null, getPanel(), "Adjust Brightness",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                brightnessLevel = ((float) sliderValue) / 100;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Brightness value!");
            }
        }
    }

    /**
     * Helper method to construct a container with JLabel and JSlider to be used in a JOptionPane.
     *
     * @return JPanel with JLabel and JSlider
     */
    private JPanel getPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel promptLabel = new JLabel("Drag the slider to adjust image brightness (%):");
        JSlider brightnessSlider = new JSlider();

        brightnessSlider.setMaximum(200);
        brightnessSlider.setMinimum(0);
        brightnessSlider.setValue(100);
        brightnessSlider.setMajorTickSpacing(50);
        brightnessSlider.setPaintLabels(true);
        brightnessSlider.setSnapToTicks(true);
        brightnessSlider.setPaintTicks(true);

        brightnessSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderValue = brightnessSlider.getValue();
            }
        });

        panel.add(promptLabel, BorderLayout.NORTH);
        panel.add(brightnessSlider, BorderLayout.SOUTH);

        return panel;
    }

    public float getBrightnessLevel() {
        return brightnessLevel;
    }
}
