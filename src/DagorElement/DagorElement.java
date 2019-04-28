package DagorElement;

import java.io.BufferedWriter;
import java.util.Collection;

public abstract class DagorElement {
    protected String name;

    public String getName(){
        return this.name;
    }

    public abstract Collection<DagorElement> getElements();

    public abstract String toNative(int depth);

    /*
    public abstract void compare(BufferedWriter writer);
    public abstract void equals();
    */
}
