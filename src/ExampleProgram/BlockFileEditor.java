package ExampleProgram;

import DagorElement.DagorFile;
import DagorElement.Native.DagorFileNative;
import DagorElement.Native.DagorNativeBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

public class BlockFileEditor extends JFrame {

    private JDesktopPane desktop;
    private DagorNativeBuilder builder;
    private ArrayList<BlockFile> blockFiles;

    public BlockFileEditor() {
        super("BLK Explorer");

        //create menu bar
        setJMenuBar(createMenuBar());

        //create and add desktop
        desktop = new JDesktopPane();
        setContentPane(desktop);

        blockFiles = new ArrayList <>();

    }

    //Create a new internal frame.
    protected void createFrame() {
        JInternalFrame frame = new JInternalFrame("test",true,true,true,true);
        frame.setSize(300,300);
        frame.setLocation(10,10);
        frame.setVisible(true);
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();

        //create creation menu
        JMenu createMenu = new JMenu("Create");
        JMenuItem cloneNew = new JMenuItem("Clone");
        JMenuItem createNew = new JMenuItem("New");
        createMenu.add(createNew);
        createMenu.add(cloneNew);

        //create exit menu
        JMenu ExitMenu = new JMenu("exit");
        JMenuItem exitProgram = new JMenuItem("Exit");
        ExitMenu.add(exitProgram);

        //add menus to bar
        bar.add(createMenu);
        bar.add(ExitMenu);

        //create button click events
        exitProgram.addActionListener(e -> System.exit(0));
        createNew.addActionListener(e -> createNewObject());
        cloneNew.addActionListener(e -> cloneObject());

        return bar;
    }

    private void createNewObject() {
        JInternalFrame frame = new JInternalFrame("Create a new object",true,true,true,true);
        frame.setSize(500,90);
        frame.setLocation(10,10);
        frame.setVisible(true);

        JLabel label = new JLabel("BlockFile to open: ");
        frame.add(label, BorderLayout.WEST);

        JTextField field = new JTextField(15);
        frame.add(field, BorderLayout.CENTER);

        JButton browse = new JButton("Browse");
        browse.addActionListener(e -> createFileChooser(field));
        frame.add(browse, BorderLayout.EAST);

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(e -> createNewObjectPanel(field.getText(), frame));
        frame.add(confirm, BorderLayout.SOUTH);

        try {
            frame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        desktop.add(frame);

    }

    private void createNewObjectPanel(String filePath, JInternalFrame newObjectFrame) {

        DagorFile file = new DagorFileNative(filePath);

        BlockFile blockFile = new BlockFile(file, this);
        blockFiles.add(blockFile);


        desktop.add(blockFile);
        desktop.remove(newObjectFrame);
        desktop.updateUI();
        try {
            blockFile.setSelected(true);
        } catch (java.beans.PropertyVetoException ignored) {}
    }

    private void createFileChooser(JTextField field) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new blkFileFilter());
        int returnVal = fileChooser.showDialog(desktop, "open");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            field.setText(file.getAbsolutePath());
            field.updateUI();
            field.setVisible(true);
        }

    }

    private void cloneObject() {
        JInternalFrame frame = new JInternalFrame("Clone an object",true,true,true,true);
        frame.setSize(500,90);
        frame.setLocation(10,10);
        frame.setVisible(true);

        JLabel label = new JLabel("BlockFile to clone: ");
        frame.add(label, BorderLayout.NORTH);


        DefaultListModel<BlockFile> listModel = new DefaultListModel <>();
        for (BlockFile blockFile: blockFiles){
            listModel.addElement(blockFile);
        }

        JList list = new JList<>(listModel);
        frame.add(list, BorderLayout.CENTER);

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(e -> cloneObjectAction(list.getSelectedValue(), frame));
        frame.add(confirm, BorderLayout.SOUTH);

        try {
            frame.setSelected(true);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        desktop.add(frame);
    }

    private void cloneObjectAction(Object selectedValue, JInternalFrame frame) {
        BlockFile file = (BlockFile) selectedValue;

        BlockFile blockFile = new BlockFile(file.getDagorElement(), this);
        blockFiles.add(blockFile);

        desktop.add(blockFile);
        desktop.remove(frame);
        desktop.updateUI();
    }

    public ArrayList <String> getObjectUIDs() {
        ArrayList<String> UIDs = new ArrayList <>();
        for (BlockFile blockFile: blockFiles){
               UIDs.add(blockFile.getUID());
        }
        return UIDs;
    }

    private class blkFileFilter extends FileFilter {
        @Override
        public boolean accept(java.io.File file) {
            if (file.isDirectory()) {
                return true;
            }
            String fileName = file.getName();
            int index = fileName.lastIndexOf('.');
            String extension = fileName.substring(index+1);
            if (extension.equals("blk")
            || extension.equals("blkx")){
                return true;
            }else {
                return false;
            }
        }

        @Override
        public String getDescription() {
            return "block blockFiles";
        }
    }
}
