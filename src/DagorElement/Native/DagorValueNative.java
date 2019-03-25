package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorValue;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DagorValueNative extends DagorValue {

    public DagorValueNative(String value, String name) {
        super(value, name);

        values = new ArrayList <>();

        String workValue = value.strip();
        String regex = "(.+):(p4|p3|p2|c|i|r|t|m|b)=(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(workValue);

        this.valueType = valueTypeStringToEnum(matcher.group(2));

        values.add(matcher.group(3));

    }


    @Override
    public void addElement(DagorElement value) {


        String workValue = value.strip();
        String regex = "(.+):(p4|p3|p2|c|i|r|t|m|b)=(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(workValue);

        this.valueType = valueTypeStringToEnum(matcher.group(2));

        values.add(matcher.group(3));
    }


}
