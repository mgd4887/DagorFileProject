package DagorElement;

import DagorElement.DagorElement;

import java.util.ArrayList;

public abstract class DagorValue extends DagorElement {

    protected ValueType valueType;
    protected String value;
    protected ArrayList <String> values;
    protected String name;

    protected enum ValueType {
        REAL, INT, BOOL, P4, P3, P2, COLOR, M, STRING, ERROR
    }
    public DagorValue(String value, String name) {
        this.value = value;
        this.name = name;

    }

    protected ValueType valueTypeStringToEnum(String valueType){

        switch (valueType.toLowerCase()){
            case "r":
                return ValueType.REAL;
            case "i":
                return ValueType.INT;
            case "b":
                return ValueType.BOOL;
            case "p4":
                return ValueType.P4;
            case "p3":
                return ValueType.P3;
            case "p2":
                return ValueType.P2;
            case "c":
                return ValueType.COLOR;
            case "m":
                return ValueType.M;
            case "t":
                return ValueType.STRING;
        }
        return ValueType.ERROR;
    }
}
