package ExampleProgram;

import javax.swing.JFrame;

public class Runner {
    public static void main(String[] args) {
        JFrame dFrame = new BlockFileEditor();
        dFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        dFrame.setSize( 800, 600 ); // set frame size
        dFrame.setVisible( true ); // display frame
    }

}
