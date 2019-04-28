package DagorElement.Native;

import DagorElement.DagorElement;
import DagorElement.DagorValue;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DagorValueNative extends DagorValue {

    private ValueType valueType;
    private String value;

    @Override
    public String toNative(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++){
            indent.append("  ");
        }
        return indent + name + ":" + valueType.toNative() + "=" + value;
    }

    public DagorValueNative(String value) throws IllegalStateException {
        //create matcher
        String workValue = value.strip();
        String regex = "(.+):(p4|p3|p2|c|i|r|t|m|b|ip2)=(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(workValue);

        //set values
        if(matcher.matches()){
            this.valueType = valueTypeStringToEnum(matcher.group(2));
            this.value = matcher.group(3);
            this.name = matcher.group(1);
        }else{
            throw new IllegalStateException();
        }
    }

    @Override
    public ArrayList <DagorElement> getElements() {
        return null;
    }

    @Override
    public String getValue() {
        return value;
    }
}
