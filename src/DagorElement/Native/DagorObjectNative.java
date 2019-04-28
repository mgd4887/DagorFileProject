package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorObject;

import java.util.ArrayList;

public class DagorObjectNative extends DagorObject {
    private ArrayList<DagorElement> elements;


    public String toNative(int depth) {

        StringBuilder indent = new StringBuilder();
        String nameIndent = "";
        for (int i = 0; i < depth; i++){
            indent.append("  ");
            if (i == depth-3){
                nameIndent = indent.toString();
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (DagorElement element: elements) {
            //TODO add depth to toString?
            stringBuilder.append(element.toNative(depth + 1));
            stringBuilder.append("\n");
        }
        return "\n" + nameIndent + name + "{\n" +
                    stringBuilder.toString() +
                    indent + "}\n";

    }

    protected DagorObjectNative(String name, ArrayList<DagorElement> elements) {
        this.name = name;
        this.elements = elements;
    }

    @Override
    public ArrayList <DagorElement> getElements() {
        return elements;
    }

}
