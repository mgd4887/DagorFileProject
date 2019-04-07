package DagorElement;

import java.util.Collection;

public abstract class DagorObject extends DagorElement {


    protected DagorObject() {
    }

    @Override
    public abstract Collection <DagorElement> getElements();
}
