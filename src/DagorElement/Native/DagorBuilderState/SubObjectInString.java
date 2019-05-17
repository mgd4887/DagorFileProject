package DagorElement.Native.DagorBuilderState;

import DagorElement.Native.DagorNativeBuilder;

public class SubObjectInString extends BuilderState {
    private String subObjectBuffer;
    private int depth;
    private String name;

    /**
     * constructor for a state
     *
     * @param text the full file text
     * @param depth
     * @param name
     */
    public SubObjectInString(DagorNativeBuilder builder, String text, String subObjectBuffer, int depth, String name) {
        super(builder, text);
        this.subObjectBuffer = subObjectBuffer;
        this.depth = depth;
        this.name = name;
    }

    @Override
    public void parseChar(int currentIndex) {
        char currentChar = text.charAt(currentIndex);

        switch (currentChar){
            case('"'):
                if (isEscaped(currentIndex)){
                    subObjectBuffer += currentChar;
                }else{
                    subObjectBuffer += currentChar;
                    builder.changeState(new SubObject(builder, text, depth, subObjectBuffer, name));
                }

            default:
                subObjectBuffer += currentChar;
                break;
        }
    }
}
