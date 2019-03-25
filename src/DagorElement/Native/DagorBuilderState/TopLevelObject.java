package DagorElement.Native.DagorBuilderState;

import DagorElement.Native.DagorNativeObjectBuilder;
import DagorElement.Native.DagorValueNative;

public class TopLevelObject extends BuilderState {

    private String valuesBuffer;

    /**
     * constructor for a state
     *
     * @param text the full file text
     */
    public TopLevelObject(DagorNativeObjectBuilder builder, String text) {
        super(builder, text);
    }

    @Override
    public void parseChar(int currentIndex) {
        char currentChar = text.charAt(currentIndex);

        switch (currentChar){
            case '{':
                if (isEscaped(currentIndex)){
                    valuesBuffer += currentChar;
                }else{
                    valuesBuffer += currentChar;
                    builder.changeState(new SubObject(builder, text, 1 , "" , valuesBuffer));
                    endValue();
                }
                break;
            case '"':
                if (isEscaped(currentIndex)){
                    valuesBuffer += currentChar;
                }else{
                    valuesBuffer += currentChar;
                    builder.changeState(new InString(builder, text, valuesBuffer));
                }
                break;
            case '/':
                if (text.charAt(currentIndex-1) == '/'){
                    valuesBuffer += currentChar;
                    builder.changeState(new InlineComment(builder, text, valuesBuffer));
                }else{
                    valuesBuffer += currentChar;
                }
                break;
            case '*':
                if (text.charAt(currentIndex-1) == '/'){
                    valuesBuffer += currentChar;
                    builder.changeState(new BlockComment(builder, text, valuesBuffer));
                }else{
                    valuesBuffer += currentChar;
                }
                break;
            case ';':
                endValue();
                break;
            case '\n':
                endValue();
                break;
            default:
                valuesBuffer += currentChar;
                break;
        }


    }

    private void endValue() {
        builder.addElement(new DagorValueNative(valuesBuffer));
        valuesBuffer = "";
    }

}
