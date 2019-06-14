package DagorElement.Native;

import DagorElement.DagorObject;

import java.util.Arrays;

public class NativeFileTest {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        if (args.length == 1) {
            DagorNativeBuilder builder = new DagorNativeBuilder();
            DagorFileNative dagorFileNative = new DagorFileNative(args[0]);
            System.out.println("test");
            System.out.println(dagorFileNative.toNative(0));
        }
    }
}
