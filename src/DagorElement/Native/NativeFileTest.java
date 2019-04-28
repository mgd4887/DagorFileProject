package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorObject;

import java.util.ArrayList;
import java.util.Arrays;

public class NativeFileTest {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        if (args.length == 1){
            DagorNativeObjectBuilder builder = new DagorNativeObjectBuilder();
            DagorObject dagorObject = builder.CreateObjectFromFile(args[0]);
            ArrayList <DagorElement> subobjects = dagorObject.getElements();
            for (DagorElement object: subobjects){
                System.out.println(object.toNative(0));
            }

        }
    }
}
