package DagorElement;

import DagorElement.DagorElement;
import DagorElement.DagorValue;

import java.util.ArrayList;

public class DagorObject extends DagorElement {
    private String name;
    private ArrayList<DagorElement> elements;

    protected DagorObject(String name, ArrayList<DagorElement> elements) {
        this.name = name;
        this.elements = elements;
    }

    @Override
    public void addElement(DagorElement element) {

    }
}
