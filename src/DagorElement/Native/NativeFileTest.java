package DagorElement.Native;

import DagorElement.DagorObject;

import java.util.Arrays;

public class NativeFileTest {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        if (args.length == 1){
            DagorNativeObjectBuilder builder = new DagorNativeObjectBuilder();
            DagorObject dagorObject = builder.CreateObjectFromFile(args[0]);
            System.out.println(dagorObject.toNative(0));
        }
    }
}
