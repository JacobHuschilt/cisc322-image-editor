import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

import ca.queensu.cs.dal.edfmwk.doc.DocumentException;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

/**
 * Internal representation of an image document.
 * <p>
 * Adapted from work by David Alex Lamb
 * Last modified by David Seekatz
 */
public class ImageContents {

    private BufferedImage img;

    /**
     * Constructs a default image file contents of size 500x800 px.
     */
    public ImageContents() {
        img = new BufferedImage(500, 800, TYPE_INT_ARGB);
    } // end constructor

    /**
     * Reads the image, and closes the stream from which it is read.
     *
     * @param in Where to read the image from.
     * @throws IOException if any I/O errors occur
     */
    public void open(InputStream in) throws IOException {
        try {
            img = ImageIO.read(in);
        } catch (Exception e) {
            //	throw new IOException(e);
            throw new IOException(e.getLocalizedMessage());
        }
    } // end method open

    /**
     * Saves the image document.  Currently we only support saving images as png files.
     *
     * @param out Where to write the document.
     * @throws IOException if any I/O errors occur, in which case it will have
     *                     closed the stream.
     */
    public void save(OutputStream out) throws IOException {
        try {
            // TODO: change this to allow saving with other supported file extensions such as gif and jpg
            ImageIO.write(img, "png", out);
        } catch (Exception e) {
            out.close();
            throw new IOException(e.getLocalizedMessage());
        }
    } // end save

    /**
     * Gets an input stream from which the document contents can be read as a
     * stream of bytes.  This is required when running in a sandbox, where
     * {@link javax.jnlp.FileSaveService#saveAsFileDialog} does not provide a
     * means of supplying an output stream to which to write the internal
     * representation. Document managers should avoid using this method
     * wherever possible, preferring {@link #save} instead.
     *
     * @throws DocumentException if such a stream cannot be created.
     */
    // I'm not sure if this method is completely necessary for us.  I've tried to implement it anyway,
    // and it may or may not work properly.  If this method causes any errors down the line,
    // I think we should be alright to remove it.
    // Code to represent BufferedImage as ByteArrayInputStream from:
    // https://stackoverflow.com/questions/649186/how-to-get-an-inputstream-from-a-bufferedimage
    // -David S
    public InputStream getContentsStream() throws DocumentException {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } catch (Exception e) {
            throw new DocumentException(e);
        }
    } // end getContentStream

    /**
     * Gets the BufferedImage attribute.
     */
    public BufferedImage getImg() {
        return img;
    } // end getImg

    /**
     * Sets the BufferedImage attribute.  This method is used to
     * update the internal representation of an image after an action
     * has been performed.
     *
     * @param img the BufferedImage to be set as the new attribute
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    } // end setImg

} // end ImageContents
