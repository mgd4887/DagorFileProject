package ExampleProgram;

import DagorElement.*;
import DagorElement.Native.DagorFileNative;
import DagorElement.Native.DagorNativeBuilder;
import DagorElement.Native.DagorObjectNative;
import DagorElement.Native.DagorValueNative;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;
import java.util.ArrayList;

public class DagorElementTreePane extends JPanel{
    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel treeModel;
    private JTree tree;

    public DagorElementTreePane(BlockFile blockFile, DagorElement data){
        super(new GridLayout(1,0));

        createTree(data);
        //rootNode = new DefaultMutableTreeNode(data);
        treeModel = new DefaultTreeModel(rootNode);
        tree = new JTree(treeModel);
        tree.addTreeSelectionListener(blockFile);
        tree.setEditable(false);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);
    }

    private void createTree(DagorElement data){
        this.rootNode = new DagorNode(data);
        ArrayList<DagorElement> elements = data.getElements();
        if (elements != null) {
            for (DagorElement dagorElement : elements){
                createNode(dagorElement, this.rootNode);
            }
        }
    }

    private void createNode(DagorElement element, DefaultMutableTreeNode rootNode) {
        DefaultMutableTreeNode newRoot = new DagorNode(element);
        rootNode.add(newRoot);
        ArrayList<DagorElement> elements = element.getElements();
        if (elements != null) {
            for (DagorElement dagorElement : elements) {
                createNode(dagorElement, newRoot);
            }
        }
    }

    public void applyElementChanges(String newName, String newType, String newValue) {
        TreePath currentPath = tree.getSelectionPath();
        DagorNode currentNode = (DagorNode) currentPath.getLastPathComponent();
        DagorElement currentElement = currentNode.getDagorElement();
        if (newType != null && newValue != null){
            DagorValue currentValue = (DagorValue) currentElement;
            currentValue.setType(DagorValue.ValueType.valueOf(newType));
            currentValue.setValue(newValue);
        }
        currentElement.setName(newName);
        currentNode.setUserObject(currentElement);
    }

    public void removeElement() {
        TreePath currentPath = tree.getSelectionPath();
        DagorNode currentNode = (DagorNode) currentPath.getLastPathComponent();
        DagorElement currentElement = currentNode.getDagorElement();
        DagorNode rootNode = (DagorNode) treeModel.getRoot();
        if (rootNode == currentNode){
            rootNode.removeAllChildren();
            DagorFile file = (DagorFile) currentElement;
            ArrayList <DagorElement> elements = file.getElements();
            ArrayList <DagorElement> nonConcurrentElements = (ArrayList <DagorElement>) elements.clone();
            for (DagorElement element: nonConcurrentElements){
                file.removeElement(element);
            }
            file.setName("blank file");
            tree.updateUI();

        }else {

            DagorNode parentNode = (DagorNode) currentNode.getParent();
            DagorObject parentObject = (DagorObject) parentNode.getDagorElement();

            parentObject.removeElement(currentElement);
            parentNode.remove(currentNode);
        }

        tree.updateUI();

    }

    public void addObject() {
        DagorObject newObject = new DagorObjectNative("newObject", "");
        addToCurrentOrParent(newObject);
    }

    public void addValue() {
        DagorValue newValue = new DagorValueNative("newValue:t=\"\"");
        addToCurrentOrParent(newValue);
    }

    private void addToCurrentOrParent(DagorElement element){
        TreePath currentPath = tree.getSelectionPath();
        DagorNode currentNode = (DagorNode) currentPath.getLastPathComponent();
        DagorElement currentElement = currentNode.getDagorElement();
        if (currentElement.getElements() != null){
            DagorObject currentObject = (DagorObject) currentElement;
            currentObject.addElement(element);
            currentNode.add(new DagorNode(element));
        }else{
            DagorNode parentNode = (DagorNode) currentNode.getParent();
            DagorObject parentObject = (DagorObject) parentNode.getDagorElement();
            parentObject.addElement(element);
            parentNode.add(new DagorNode(element));
        }
        tree.updateUI();
    }

    class DagorNode extends DefaultMutableTreeNode{
        private DagorElement dagorElement;
        public DagorNode(DagorElement dagorElement) {
            super(dagorElement.toString());
            this.dagorElement = dagorElement;
        }

        public DagorElement getDagorElement() {
            return dagorElement;
        }

    }
}
