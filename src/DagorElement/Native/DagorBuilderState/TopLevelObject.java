package DagorElement.Native.DagorBuilderState;

import DagorElement.Native.DagorNativeBuilder;
import DagorElement.Native.DagorValueNative;

public class TopLevelObject extends BuilderState {

    private String valuesBuffer;

    /**
     * constructor for a state
     *
     * @param text the full file text
     */
    public TopLevelObject(DagorNativeBuilder builder, String text, String valuesBuffer) {
        super(builder, text);
        this.valuesBuffer = valuesBuffer;
    }

    @Override
    public void parseChar(int currentIndex) {
        char currentChar = text.charAt(currentIndex);

        switch (currentChar){
            case '{':
                if (isEscaped(currentIndex)){
                    //not the start of an object
                    valuesBuffer += currentChar;
                }else{
                    //start of object
                    builder.changeState(new SubObject(builder, text, 1 , "" , valuesBuffer)); //TODO pass buffer?
                    valuesBuffer += currentChar;
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
        try {
            DagorValueNative value = new DagorValueNative(valuesBuffer);
            builder.addElement(value);
        }catch (IllegalStateException ignored){

        }
        valuesBuffer = "";
    }

}
