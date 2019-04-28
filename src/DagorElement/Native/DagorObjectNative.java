package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorObject;

import java.util.ArrayList;
import java.util.Collection;

public class DagorObjectNative extends DagorObject {
    private ArrayList<DagorElement> elements;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (DagorElement element: elements) {
            stringBuilder.append("\t");
            stringBuilder.append(element.toString());
            stringBuilder.append("\n");
        }
        return name + "{\n" +
                    stringBuilder.toString() +
                    "}";

    }

    protected DagorObjectNative(String name, ArrayList<DagorElement> elements) {
        this.name = name;
        this.elements = elements;
    }

    @Override
    public Collection <DagorElement> getElements() {
        return elements;
    }

}
