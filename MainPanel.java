// $Id: MainPanel.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.Writer;
import java.nio.Buffer;
import javax.swing.*;
// Import only those classes from edfmwk that are actually used, for
// documentation purposes.
import ca.queensu.cs.dal.edfmwk.win.TextAreaWriter;

/**
 * Main panel for the editor's user inferface.
 * <p>
 * Copyright 2010-2011 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class MainPanel extends JPanel {

    /*
     * The initial GUI component to display.
     */
    private JLabel mainArea;
    private BufferedImage imageToDisplay;

    /**
     * Constructs the main panel.
     */
    public MainPanel(BufferedImage imageToDisplay) {
        super();
        this.imageToDisplay = imageToDisplay;

        mainArea = new JLabel(new ImageIcon(imageToDisplay));

        JScrollPane sc = new JScrollPane(mainArea);

        // Add a border to the panel so we can see its boundaries
        sc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(sc, BorderLayout.CENTER);
    }

}
