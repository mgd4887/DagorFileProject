package ExampleProgram;

import DagorElement.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ValuePane extends JPanel {
    private boolean expandable;
    private DagorValue.ValueType dataType;
    private String dataValue;
    private DagorElement element;
    private BlockFile blockFile;
    private TextField nameField;
    private TextField dataTypeField;
    private TextField valueField;
    private JButton applyButton;

    public ValuePane(BlockFile blockFile) {
        super();
        this.blockFile = blockFile;
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(boxLayout);
        nameField = new TextField();
        dataTypeField = new TextField();
        valueField = new TextField();
        nameField.setText(null);
        dataTypeField.setText(null);
        valueField.setText(null);
    }

    public void setElement(DagorElement element){
        this.element = element;
        ArrayList <DagorElement> elements = element.getElements();
        expandable = elements == null;

        //reset
        this.removeAll();
        nameField.setText(null);
        dataTypeField.setText(null);
        valueField.setText(null);


        String name = element.getName();
        nameField = new TextField(name);
        this.add(nameField);

        if (expandable){
            DagorValue elementValue = (DagorValue) element;
            this.dataValue = elementValue.getValue();
            this.dataType = elementValue.getType();

            dataTypeField = new TextField(dataType.toString());
            this.add(dataTypeField);

            valueField = new TextField(dataValue);
            this.add(valueField);
        }

        applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> applyElementChanges());
        this.add(applyButton);

        this.updateUI();
    }

    private void applyElementChanges() {
        String name = nameField.getText();
        String type = dataTypeField.getText();
        String value = valueField.getText();

        ArrayList<Component> components = new ArrayList<Component>(Arrays.asList( this.getComponents()));
        if(!components.contains(dataTypeField)){
            type = null;
        }
        if(!components.contains(valueField)){
            value = null;
        }

        blockFile.applyElementChanges(name,type,value);
    }
}
