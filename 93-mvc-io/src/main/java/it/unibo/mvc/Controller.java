package it.unibo.mvc;
import java.util.List;
/**
 *Interface Controller`: it must model a simple controller responsible of I/O access.
 */
public interface Controller {
    /**
     * This method set the next string to print.
     * @param stringToPrint
     * @throws NullPointerException if stringToPrint is null
     */
    public void nextStringToPrint(final String stringToPrint);
    /**
     * This method gets the next string to print.
     * @return the next string to print 
     * @throws IllegalStateException if there isn't a string to print
     */
    public String getNextStringToPrint();
    /**
     * This method returns a list of strings representing those previously printed.
     * @return a list of string 
     */
    public List<String> getHistoryOfPrintedString();
    /**
     * This method prints the current string.
     * @throws IllegalStateException if the current string is unset
     */
    public void printCurrentString();
}
