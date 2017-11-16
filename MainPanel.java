// $Id: MainPanel.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.Buffer;
import javax.imageio.ImageIO;
import javax.swing.*;
// Import only those classes from edfmwk that are actually used, for
// documentation purposes.
import ca.queensu.cs.dal.edfmwk.win.TextAreaWriter;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

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

    /**
     * Constructs the main panel.
     */
    public MainPanel() {
        super();

        mainArea = new JLabel();

        ImageIcon imageIcon = new ImageIcon(new BufferedImage(600, 400, TYPE_INT_ARGB));

        // try {
        //     BufferedImage img = ImageIO.read(new File("Earth-2560x1600-wallpapershd.org.jpg"));
        //     imageIcon = new ImageIcon(img);
        // } catch (Exception e) {
        //     //	throw new IOException(e);
        //     System.out.println("Error");
        // }


        mainArea.setIcon(imageIcon);

        JScrollPane sc = new JScrollPane(mainArea);

        // Add a border to the panel so we can see its boundaries
        sc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(sc, BorderLayout.CENTER);
    }
}
