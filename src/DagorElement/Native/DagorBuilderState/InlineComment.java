package DagorElement.Native.DagorBuilderState;

import DagorElement.Native.DagorNativeBuilder;

public class InlineComment extends BuilderState {

    private String valuesBuffer;

    public InlineComment(DagorNativeBuilder builder, String text, String valuesBuffer) {
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
