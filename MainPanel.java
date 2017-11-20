import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.*;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

/**
 * Main panel for the editor's user inferface.
 * <p>
 * Based on code written by David Lamb.
 * Last modified by Jacob Huschilt and David Seekatz.
 */
public class MainPanel extends JPanel {

    /*
     * The initial GUI component to display.
     */
    private JLabel mainArea;

    /**
     * Constructs the main panel.
     */
    public MainPanel() {
        super();

        mainArea = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new BufferedImage(600, 400, TYPE_INT_ARGB));
        mainArea.setIcon(imageIcon);
        JScrollPane sc = new JScrollPane(mainArea);

        // Add a border to the panel so we can see its boundaries
        sc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(sc, BorderLayout.CENTER);
    }
}
