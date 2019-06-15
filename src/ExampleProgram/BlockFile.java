package ExampleProgram;

import DagorElement.DagorElement;
import DagorElement.DagorFile;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class BlockFile extends JInternalFrame implements TreeSelectionListener {
    private final BlockFileEditor blockFileEditor;
    private DagorElementTreePane tree;
    private ValuePane valuePane;
    private DagorFile file;
    private String name;
    private String UID;
    private ArrayList<BlockElement> blockElements;

    public BlockFile(DagorFile file, BlockFileEditor blockFileEditor) {
        super(file.getName(), true, true, true, true);
        this.blockFileEditor = blockFileEditor;
        this.file = file;

        this.setSize(300,335);
        this.setLocation(10,10);
        this.setVisible(true);

        createUID();

        ArrayList <DagorElement> elements = file.getElements();
        blockElements = new ArrayList <>();

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(1.0);

        this.tree = new DagorElementTreePane(this, file);
        tree.setMinimumSize(new Dimension(300, 300));
        splitPane.setTopComponent(tree);

        this.valuePane = new ValuePane(this);
        valuePane.setMinimumSize(new Dimension(300, 35));
        splitPane.setBottomComponent(valuePane);

        this.add(splitPane, BorderLayout.CENTER);


        this.add(new AddRemoveElementPanel(this), BorderLayout.SOUTH);

    }

    private void createUID(){
        ArrayList<String> UIDs = blockFileEditor.getObjectUIDs();
        name = file.getName();
        if (UIDs.contains(name)){
            int i = 1;
            do{
                this.UID = name + " (" + i + ")";
                i++;
            }while (UIDs.contains(this.UID));

        }else{
            this.UID = name;
        }

        this.title = UID;
    }

    public String getName() {
        return name;
    }

    public String getUID() {
        return UID;
    }

    @Override
    public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
        //treeSelectionEvent.getPath()[0].lastPathComponent.getDagorElement()
        DagorElement element = ((DagorElementTreePane.DagorNode) treeSelectionEvent.getPath().getLastPathComponent()).getDagorElement();;
        valuePane.setElement(element);
    }

    public void applyElementChanges(String newName, String newType, String newValue) {
        tree.applyElementChanges(newName, newType, newValue);
    }

    public void removeElement() {
        tree.removeElement();
    }

    public void addObject() {
        tree.addObject();
    }

    public void addValue() {
        tree.addValue();
    }

    public DagorFile getDagorElement() {
        return file;
    }
}
