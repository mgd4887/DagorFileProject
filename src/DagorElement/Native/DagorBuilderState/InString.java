package DagorElement.Native.DagorBuilderState;

import DagorElement.Native.DagorNativeObjectBuilder;

public class InString extends BuilderState {


    private String valuesBuffer;

    /**
     * constructor for a state
     *
     * @param text the full file text
     * @param valuesBuffer
     */
    public InString(DagorNativeObjectBuilder builder, String text, String valuesBuffer) {
        super(builder, text);
        this.valuesBuffer = valuesBuffer;
    }

    @Override
    public void parseChar(int currentIndex) {
        char currentChar = text.charAt(currentIndex);

        switch (currentChar){
            case('"'):
                valuesBuffer += currentChar;
                if (!isEscaped(currentIndex)) {
                    builder.changeState(new TopLevelObject(builder, text, valuesBuffer));
                }

            default:
                valuesBuffer += currentChar;
                break;
        }
    }
}
