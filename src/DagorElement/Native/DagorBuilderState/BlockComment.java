package DagorElement.Native.DagorBuilderState;

import DagorElement.Native.DagorNativeBuilder;

public class BlockComment extends BuilderState {


    private String valuesBuffer;

    public BlockComment(DagorNativeBuilder builder, String text, String valuesBuffer) {
        super(builder, text);
        this.valuesBuffer = valuesBuffer;
    }

    @Override
    public void parseChar(int currentIndex) {
        char currentChar = text.charAt(currentIndex);

        switch (currentChar){
            case('/'):
                if (text.charAt(currentChar-1) == '*'){
                    // if there is ending to the current block comment
                    builder.changeState(new TopLevelObject(builder, text, valuesBuffer));
                }else {
                    // if there is no * before the /
                    valuesBuffer += currentChar;
                }
            default:
                //all other cases
                valuesBuffer += currentChar;
                break;
        }
    }
}
