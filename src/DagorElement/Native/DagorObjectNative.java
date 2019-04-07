package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorObject;

import java.util.ArrayList;
import java.util.Collection;

public class DagorObjectNative extends DagorObject {
    private String name;
    private ArrayList<DagorElement> elements;

    protected DagorObjectNative(String name, ArrayList<DagorElement> elements) {
        this.name = name;
        this.elements = elements;
    }

    @Override
    public Collection <DagorElement> getElements() {
        return elements;
    }
}
