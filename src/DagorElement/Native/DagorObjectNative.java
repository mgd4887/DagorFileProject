package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorObject;

import java.util.ArrayList;
import java.util.Collection;

public class DagorObjectNative extends DagorObject {
    private ArrayList<DagorElement> elements;


    public String toNative(int depth) {

        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++){
            indent.append("  ");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (DagorElement element: elements) {
            //TODO add depth to toString?
            stringBuilder.append(element.toNative(depth + 1));
            stringBuilder.append("\n");
        }
        return indent + name + "{\n" +
                    stringBuilder.toString() +
                    indent + "}";

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
