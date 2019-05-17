package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorFile;

import java.util.ArrayList;

public class DagorFileNative extends DagorFile {
    private ArrayList<DagorElement> elements;

    @Override
    public String toNative(int depth) {

        StringBuilder stringBuilder = new StringBuilder();
        for (DagorElement element: elements) {
            stringBuilder.append(element.toNative(depth + 1));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();

    }

    protected DagorFileNative(String name, ArrayList<DagorElement> elements) {
        this.name = name;
        this.elements = elements;
    }

    @Override
    public ArrayList <DagorElement> getElements() {
        return elements;
    }

}
