package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of a simple controller.
 */
public final class SimpleController implements Controller {
    private final List<String> stringHistory; 
    private String nextString;

    /**
     * Constructor.
     */
    public SimpleController() {
        this.stringHistory = new ArrayList<>();
    }
    @Override
    public void nextStringToPrint(final String stringToPrint) {
        Objects.requireNonNull(stringToPrint, "Null argument are not accepted");
        this.nextString = stringToPrint; 
    }

    @Override
    public String getNextStringToPrint() {
        if (Objects.isNull(nextString)) {
           throw new IllegalStateException("There isn't a string to print"); 
        }
        return this.nextString;
    }

    @Override
    public List<String> getHistoryOfPrintedString() {
        return List.copyOf(this.stringHistory);
    }

    @Override
    public void printCurrentString() {
        if (Objects.isNull(this.nextString)) {
            throw new IllegalStateException("The next string to print is unset");
        }
        this.stringHistory.add(this.nextString);
        System.out.println(this.nextString); // NOPMD: allowed in exercises
    }

}
