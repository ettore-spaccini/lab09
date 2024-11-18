package it.unibo.mvc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String SEP = File.separator;
    private File file;

    /**
     * Controller constructor.
     */
    public Controller() {
        this.file = new File(System.getProperty("user.home") + SEP + "output.txt"); 
    }
    /**
     * This function set the field file to the current file passed as argument.
     * 
     * @param newFile new file to assign
     */
    public void setFileAsCurrent(final File newFile) {
        this.file = newFile; 
    }
    /**
     * This function return the current file.
     * 
     * @return the current file associated to the field file
     */
    public File getCurrentFile() {
        return this.file;
    }
    /**
     * This function return the path of the current file.
     * 
     * @return the path of the current file
     */
    public String getFilePath() {
        return this.file.getPath();
    }
    /**
     * This function write the argument passed as parameter on the file.
     * 
     * @param stringToWrite the string to write on the file 
     * @throws IOException 
     */
    public void writeOnFile(final String stringToWrite) throws IOException {
        try (
            BufferedWriter bStream = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(this.getCurrentFile()), StandardCharsets.UTF_8));
        ) {
            bStream.write(stringToWrite);
        }
    }
}
