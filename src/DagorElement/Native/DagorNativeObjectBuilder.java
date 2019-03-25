package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.Native.DagorBuilderState.*;
import DagorElement.*;

import java.io.*;
import java.util.ArrayList;

public class DagorNativeObjectBuilder {

    private String name;
    private String text;

    private BuilderState state;
    private int currentIndex = 0;
    private ArrayList<DagorElement> elements;



    public void addElement(DagorElement element) {
        if (elementsContains(element.getName())){
            elementsGet(element.getName()).addElement(element);
        }else{
            elements.add(element);
        }

    }

    private boolean elementsContains(String name) {
        for (DagorElement element: elements) {
            if (element.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    private DagorElement elementsGet(String name) {
        for (DagorElement element: elements) {
            if (element.getName().equals(name)){
                return element;
            }
        }
        return null;
    }


    public DagorNativeObjectBuilder(){
    }

    private String getText(String file) {
        StringBuilder text = new StringBuilder();
        String currentLine;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                text.append(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public DagorObject parse(String name, String text){
        //this.name = fileName;
        //this.text = getText(fileName);
        this.elements = new ArrayList<DagorElement>();
        this.state = new TopLevelObject(this ,text);

        while (!isFinished()) {
            state.parseChar(currentIndex);
            currentIndex++;
        }
        resetBuilder();
        return new DagorObjectNative(name, elements);
    }

    private void resetBuilder() {
        this.name = null;
        this.text = null;
        this.state = null;
        this.elements = null;
    }

    private boolean isFinished() {
        return currentIndex == text.length();
    }

    public void changeState(BuilderState newState){
        this.state = newState;
    }

}
