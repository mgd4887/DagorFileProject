package DagorElement;

import java.util.ArrayList;

public interface DagorBuilder {
    ArrayList <DagorElement> CreateObjectFromFile(String fileName);
    ArrayList<DagorElement> CreateObjectFromString(String text);
    void addElement(DagorElement element);
    void changeState(DagorBuilderState state);
}
