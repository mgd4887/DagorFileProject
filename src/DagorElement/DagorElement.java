package DagorElement;

import java.io.BufferedWriter;

public abstract class DagorElement {
    private String name;

    public String getName(){
        return this.name;
    }

    public abstract void addElement(DagorElement element);

    /*
    public abstract void compare(BufferedWriter writer);
    public abstract void equals();
    */
}
