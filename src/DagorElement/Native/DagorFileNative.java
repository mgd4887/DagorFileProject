package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorFile;

import java.util.ArrayList;

public class DagorFileNative implements DagorFile {
    private ArrayList<DagorElement> elements;
    private String name;

    @Override
    public String toNative(int depth) {

        StringBuilder stringBuilder = new StringBuilder();
        for (DagorElement element: elements) {
            stringBuilder.append(element.toNative(depth + 1));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public DagorFileNative(String name) {
        this.name = name;
        DagorNativeBuilder builder = new DagorNativeBuilder();
        elements = builder.CreateObjectFromFile(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList <DagorElement> getElements() {
        return elements;
    }

    @Override
    public void addElement(DagorElement element) {
        elements.add(element);
    }

    @Override
    public void removeElement(DagorElement element) {
        elements.remove(element);
    }

    @Override
    public String toString() {
        return name;
    }
}
