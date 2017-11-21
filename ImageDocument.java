import java.io.*;
import javax.swing.*;
// Import only those classes from edfmwk that are essential, for documentation purposes
import ca.queensu.cs.dal.edfmwk.doc.AbstractDocument;
import ca.queensu.cs.dal.edfmwk.doc.DocumentType;
import ca.queensu.cs.dal.edfmwk.doc.DocumentException;

/**
 * Implementation of an image document
 * <p>
 * Based on code written by David Lamb
 * Last modified by David Seekatz
 */
public class ImageDocument extends AbstractDocument implements javax.swing.event.DocumentListener {

    private ImageContents contents;
    private JLabel imgLabel;

    /**
     * Constructs an image document representation.
     * We are using an ImageIcon set in a JLabel.
     *
     * @param type The type of the document.
     */
    public ImageDocument(DocumentType type) {
        super(type);
        contents = new ImageContents();
        imgLabel = new JLabel(new ImageIcon(contents.getImg()));
        window = new JScrollPane(imgLabel);
    } // end ImageDocument

    /**
     * Gets the document's image label.
     */
    public JLabel getImageLabel() {
        return imgLabel;
    } // end getImageLabel

    // Image document change listeners: all invoke the framework's own document
    // change listeners.
    // We are unsure which of these are applicable/necessary, but everything
    // works fine so we have left them in.

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
     * It then updates the image label so that the image is displayed.
     *
     * @param in Where to read the document from.
     * @throws IOException if any I/O errors occur, in which case it will have
     *                     closed the stream.
     */
    public void open(InputStream in) throws IOException {
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
} // end class ImageDocument

