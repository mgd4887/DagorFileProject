package DagorElement.Native.DagorBuilderState;

import DagorElement.Native.DagorNativeObjectBuilder;

public class InlineComment extends BuilderState {

    private String valuesBuffer;

    public InlineComment(DagorNativeObjectBuilder builder, String text, String valuesBuffer) {
        super(builder, text);
        this.valuesBuffer = valuesBuffer;
    }

    @Override
    public void parseChar(int currentIndex) {
        char currentChar = text.charAt(currentIndex);

        switch (currentChar){
            case('\r'):
                builder.changeState(new TopLevelObject(builder, text, valuesBuffer));
            default:
                valuesBuffer += currentChar;
                break;
        }
    }
}
