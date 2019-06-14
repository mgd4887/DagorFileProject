package DagorElement;

import java.util.ArrayList;

public interface DagorElement {

    String getName();

    ArrayList <DagorElement> getElements();

    String toNative(int depth);

    void setName(String name);

    /*
    public abstract void compare(BufferedWriter writer);
    public abstract void equals();
    */
}
