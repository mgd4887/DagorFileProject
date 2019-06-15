package ExampleProgram;

import DagorElement.*;
import javax.swing.*;
import java.util.ArrayList;

public class BlockElement extends JPanel {
    private JButton expandButton;
    private JTextField nameField;
    private JTextField valueField;
    private JButton createObjectButton;
    private JButton createValueButton;
    private boolean expandable;
    private final int height = 55;
    private final int width = 400;
    private String name;
    private final String createObjectText = "Create Object";
    private final String createValueText = "Create Value";

    public BlockElement(DagorElement data, int number) {
        super();

        ArrayList<DagorElement> elements = data.getElements();
        expandable = elements == null;
        name = data.getName();

        //set location
        this.setBounds(0,number*height, width, height);

        //expand button
        if (expandable) {
            expandButton = new JButton("+");
            expandButton.addActionListener(e -> expand());
            this.add(expandButton);
        }

        //name
        nameField = new JTextField(name);
        this.add(nameField);

        //text
        if(!expandable){
            DagorValue value = (DagorValue) data;
            valueField = new JTextField(value.getValue());
            this.add(valueField);
        }



        //create buttons
        if (expandable){
            createObjectButton = new JButton(createObjectText);
            createObjectButton.addActionListener(e -> createObject());
            this.add(createObjectButton);

            createValueButton = new JButton(createValueText);
            createValueButton.addActionListener(e -> createValue());
            this.add(createValueButton);
        }

    }

    private void expand() {

    }

    private void createValue() {

    }

    private void createObject() {

    }

    public boolean isExpandable(){
        return expandable;
    }
}
