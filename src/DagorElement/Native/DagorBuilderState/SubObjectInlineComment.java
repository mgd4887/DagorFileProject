package DagorElement.Native.DagorBuilderState;

import DagorElement.Native.DagorNativeBuilder;

public class SubObjectInlineComment extends BuilderState{
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
    public SubObjectInlineComment(DagorNativeBuilder builder, String text, String subObjectBuffer, int depth, String name) {
        super(builder, text);
        this.subObjectBuffer = subObjectBuffer;
        this.depth = depth;
        this.name = name;
    }

    @Override
    public void parseChar(int currentIndex) {
        char currentChar = text.charAt(currentIndex);

        switch (currentChar){
            case('\r'):
                builder.changeState(new SubObject(builder, text, depth, subObjectBuffer, name));
            default:
                subObjectBuffer += currentChar;
                break;
        }
    }
}
