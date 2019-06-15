package ExampleProgram;

import javax.swing.*;

public class AddRemoveElementPanel extends JPanel {
    private BlockFile blockFile;

    public AddRemoveElementPanel(BlockFile blockFile) {
        this.blockFile = blockFile;

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(boxLayout);

        JButton newValueButton = new JButton("create new value");
        newValueButton.addActionListener(e -> addValue());
        this.add(newValueButton);

        JButton newObjectButton = new JButton("create new Object");
        newObjectButton.addActionListener(e -> addObject());
        this.add(newObjectButton);

        JButton removeElementButton = new JButton("remove element");
        removeElementButton.addActionListener(e -> removeElement());
        this.add(removeElementButton);
    }

    private void addObject() {
        blockFile.addObject();
    }

    private void addValue() {
        blockFile.addValue();
    }

    private void removeElement() {
        blockFile.removeElement();
    }
}
