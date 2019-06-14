package DagorElement.Native.DagorBuilderState;

import DagorElement.DagorBuilderState;
import DagorElement.Native.DagorNativeBuilder;

public abstract class BuilderState implements DagorBuilderState {
    protected final String text;
    protected final DagorNativeBuilder builder;

    /**
     * constructor for a state
     * @param text the full file text
     */
    public BuilderState(DagorNativeBuilder builder, String text){
        this.text = text;
        this.builder = builder;
    }

    /**
     * Read the next char and do whatever with it
     * @param currentIndex
     */
    public abstract void parseChar(int currentIndex);

    /**
     * returns if a character at a given index is esacped
     * @param currentIndex the index of the caharacter you want to know if is escaped
     * @return if the char is escaped
     */
    protected boolean isEscaped(int currentIndex) {
        boolean finished = false;
        int reverseIndex = currentIndex-1;
        int count = 0;
        while (!finished){
            if (text.charAt(reverseIndex) == '\\'){
                count++;
                reverseIndex--;
            }else{
                finished = true;
            }
        }
        //if an even number of '/' then it is not escaped
        int i = count % 2;
        return (i == 1);
    }

}
