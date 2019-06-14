package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.Native.DagorBuilderState.*;
import DagorElement.*;

import java.io.*;
import java.util.ArrayList;

public class DagorNativeBuilder {

    private String text;
    private BuilderState state;
    private int currentIndex = 0;
    private ArrayList<DagorElement> elements;

    /*
     * PUBLIC METHODS
     */

    /**
     * constructs the DagorNativeBuilder
     */
    public DagorNativeBuilder(){
    }

    /**
     * creates a array list of dagor elements from the given file
     * @param fileName the name of the file to create a dagorFile from
     * @return a DagorFileNative described in the file.
     */
    public ArrayList<DagorElement> CreateObjectFromFile(String fileName){
        String text = getText(fileName);
        ArrayList<DagorElement> elements = parse(text);
        return elements;
    }

    /**
     * creates an array list of dagor elements from the given text
     * @param text the text of the object
     * @return a nativeDagorObject described in the text
     */
    public ArrayList<DagorElement> CreateObjectFromString(String text){
        //this.text = text;
        ArrayList<DagorElement> elements = parse(text);
        return elements;
    }

    /**
     * changes the state of the state machine
     * @param newState
     */
    public void changeState(BuilderState newState){
        this.state = newState;
    }

    /**
     * adds a given element to the builder
     * @param element the element to add
     */
    public void addElement(DagorElement element) {
        elements.add(element);
    }

    /*
     * PRIVATE METHODS
     */

    /**
     * gets the text from a given file as a string
     * @param file the name of the file to open, or the file path
     * @return a string containing the text of a file.
     */
    private String getText(String file) {
        StringBuilder text = new StringBuilder();
        String currentLine;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                text.append(currentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    /**
     * parces the given text
     * @param text the text to parse
     * @return an arraylist of elements in the object described in the text.
     */
    private ArrayList<DagorElement> parse(String text){
        this.text = text;
        this.elements = new ArrayList<DagorElement>();
        this.state = new TopLevelObject(this ,text, "");

        while (!isFinished()) {
            state.parseChar(currentIndex);
            currentIndex++;
        }
        return elements;
    }

    /**
     * resets all values used in object creation to null.
     */
    private void resetBuilder() {
        this.text = null;
        this.state = null;
        this.elements = null;
    }

    /**
     * returns if the parceing is finished.
     * @return if the parceing is finished.
     */
    private boolean isFinished() {
        if (text == null){
            System.out.println("null");
        }
        return currentIndex == text.length();
    }



}
