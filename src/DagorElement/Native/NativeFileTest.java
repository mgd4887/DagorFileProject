package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class NativeFileTest {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        if (args.length == 1){
            DagorNativeObjectBuilder builder = new DagorNativeObjectBuilder();
            DagorObject dagorObject = builder.CreateObjectFromFile(args[0]);
            ArrayList <DagorElement> subobjects = dagorObject.getElements();


            try {
                FileWriter output = new FileWriter("output.blkx");
                for (DagorElement object: subobjects){
                    System.out.println(object.toNative(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
