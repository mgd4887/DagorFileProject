package DagorElement.Native.DagorBuilderState;

import DagorElement.Native.DagorNativeObjectBuilder;

public class BlockComment extends BuilderState {


    private String valuesBuffer;

    public BlockComment(DagorNativeObjectBuilder builder, String text, String valuesBuffer) {
        super(builder, text);
        this.valuesBuffer = valuesBuffer;
    }

    @Override
    public void parseChar(int currentIndex) {
        char currentChar = text.charAt(currentIndex);

        switch (currentChar){
            case('/'):
                if (text.charAt(currentChar-1) == '*'){
                    builder.changeState(new TopLevelObject(builder, text));
                }else {
                    valuesBuffer += currentChar;
                }
            default:
                valuesBuffer += currentChar;
                break;
        }
    }
}
