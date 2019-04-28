package DagorElement;

import java.util.ArrayList;

public abstract class DagorElement {
    protected String name;

    public String getName(){
        return this.name;
    }

    public abstract ArrayList <DagorElement> getElements();

    public abstract String toNative(int depth);

    /*
    public abstract void compare(BufferedWriter writer);
    public abstract void equals();
    */
}
