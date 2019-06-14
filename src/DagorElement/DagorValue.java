package DagorElement;

public abstract class DagorValue implements DagorElement {

    public abstract String getValue();

    public abstract ValueType getType();

    public abstract void setType(ValueType newType);

    public abstract void setValue(String newValue);

    public enum ValueType {
        REAL, INT, BOOL, P4, P3, P2, COLOR, M, STRING, ERROR, IP2;

        public String toNative() {
            switch (this){
                case REAL:
                    return "r";
                case INT:
                    return "i";
                case BOOL:
                    return "b";
                case P4:
                    return "p4";
                case P3:
                    return "p3";
                case P2:
                    return "p2";
                case COLOR:
                    return "c";
                case M:
                    return "m";
                case STRING:
                    return "t";
                case IP2:
                    return "ip2";
            }
            return "ERROR";
        }
    }

    protected DagorValue() {
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
            case "ip2":
                return ValueType.IP2;
        }
        return ValueType.ERROR;
    }
}
