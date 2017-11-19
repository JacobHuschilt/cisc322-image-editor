import java.io.*;
import javax.swing.*;
// Import only those classes from edfmwk that are essential, for documentation purposes
import ca.queensu.cs.dal.edfmwk.doc.AbstractDocument;
import ca.queensu.cs.dal.edfmwk.doc.DocumentType;
import ca.queensu.cs.dal.edfmwk.doc.DocumentException;

/**
 * Implementation of an image document
 * <p>
 * Last modified 2017-10-26 by David Seekatz
 */
public class ImageDocument extends AbstractDocument implements javax.swing.event.DocumentListener {

    private ImageContents contents;
    private JLabel imgLabel;

    /**
     * Constructs a document representation.
     *
     * @param type The type of the document.
     */
    // Since I'm not very familiar with swing, I don't know if this is the best way
    // to convert the image to a swing component. We might have to re-work this constructor.
    // -David S.
    public ImageDocument(DocumentType type) {
        super(type);
        contents = new ImageContents();
//        contents.addDocumentListener(this);
        imgLabel = new JLabel(new ImageIcon(contents.getImg()));
        window = new JScrollPane(imgLabel);
    } // end ImageDocument

    // Getter for the JLabel
    public JLabel getImageLabel() {
        return imgLabel;
    }

    // Image document change listeners: all invoke the framework's own document
    // change listeners.

    /**
     * Gives notification that an attribute or set of attributes changed.
     */
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        setChanged();
    } // end changedUpdate

    /**
     * Gives notification that there was an insert into the document.
     */
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        setChanged();
    } // end insertUpdate

    /**
     * Gives notification that a portion of the document has been removed.
     */
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        setChanged();
    } // end removeUpdate

    /**
     * Saves the entire document.  After this operation completes
     * successfully, {@link #isChanged} returns <b>false</b>
     *
     * @param out Where to write the document.
     * @throws IOException if any I/O errors occur, in which case it will have
     *                     closed the stream; isChanged() is unchanged.
     */
    public void save(OutputStream out) throws IOException {
        contents.save(out);
        setChanged(false);
    } // save

    /**
     * Gets an input stream from which the document contents can be read as a
     * stream of bytes.  This is required when running in a sandbox, where
     * {@link javax.jnlp.FileSaveService#saveAsFileDialog} does not provide a
     * means of supplying an output stream to which to write the internal
     * representation. Document managers should avoid using this method
     * wherever possible, preferring {@link #save} instead.
     *
     * @throws IOException if such a stream cannot be created.
     */
    public InputStream getContentsStream() throws DocumentException {
        return contents.getContentsStream();
    } // getContentsStream

    /**
     * Reads the entire document, and closes the stream from which it is read.
     *
     * @param in Where to read the document from.
     * @throws IOException if any I/O errors occur, in which case it will have
     *                     closed the stream.
     */
    public void open(InputStream in)
            throws IOException {
        contents.open(in);
        imgLabel.setIcon(new ImageIcon(contents.getImg()));
        setChanged(false);
    } // open

    /**
     * Gets the contents of the image document, for those few methods within
     * this package that need direct access (such as actions).
     */
    ImageContents getContents() {
        return contents;
    }
} // end class TextDocument

