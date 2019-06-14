package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorObject;

import java.util.ArrayList;

public class DagorObjectNative implements DagorObject {
    private ArrayList<DagorElement> elements;
    private String name;


    public DagorObjectNative(String name, String text) {
        this.name = name;
        DagorNativeBuilder builder = new DagorNativeBuilder();
        elements = builder.CreateObjectFromString(text);
    }

    public String toNative(int depth) {

        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++){
            indent.append("  ");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (DagorElement element: elements) {
            stringBuilder.append(element.toNative(depth + 1));
            stringBuilder.append("\n");
        }
        return "\n"  + name + "{\n" +
                    stringBuilder.toString() +
                    indent + "}";

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    protected DagorObjectNative(String name, ArrayList<DagorElement> elements) {
        this.name = name;
        this.elements = elements;
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

}
