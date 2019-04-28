package DagorElement.Native.DagorBuilderState;

import DagorElement.DagorElement;
import DagorElement.DagorObject;
import DagorElement.Native.DagorNativeObjectBuilder;

import java.util.Collection;

public class SubObject extends BuilderState{

    private int depth;
    private String subObjectBuffer;
    private String name;

    /**
     * constructor for a state
     *
     * @param text the full file text
     */
    public SubObject(DagorNativeObjectBuilder builder, String text, int depth, String subObjectBuffer, String name) {
        super(builder, text);
        this.depth = depth;
        this.subObjectBuffer = subObjectBuffer;
        this.name = name;
    }

    @Override
    public void parseChar(int currentIndex) {
        char currentChar = text.charAt(currentIndex);

        switch (currentChar){
            case '{':
                if (isEscaped(currentIndex)){
                    subObjectBuffer += currentChar;
                }else{
                    subObjectBuffer += currentChar;
                    depth++;
                }
                break;

            case '}':
                if (isEscaped(currentIndex)){
                    subObjectBuffer += currentChar;
                }else if(depth > 1) {
                    subObjectBuffer += currentChar;
                    depth--;
                }else if(depth == 1){
                    endObject();
                    subObjectBuffer += currentChar;
                }
                break;

            case '"':
                if (isEscaped(currentIndex)){
                    subObjectBuffer += currentChar;
                }else{
                    subObjectBuffer += currentChar;
                    builder.changeState(new SubObjectInString(builder, text, subObjectBuffer, depth, name));
                }
                break;

            case '/':
                if (text.charAt(currentIndex-1) == '/'){
                    subObjectBuffer += currentChar;
                    builder.changeState(new SubObjectInlineComment(builder, text, subObjectBuffer, depth, name));
                }else{
                    subObjectBuffer += currentChar;
                }
                break;

            case '*':
                if (text.charAt(currentIndex-1) == '/'){
                    subObjectBuffer += currentChar;
                    builder.changeState(new SubObjectBlockComment(builder, text, subObjectBuffer, depth, name));
                }else{
                    subObjectBuffer += currentChar;
                }
                break;

            default:
                subObjectBuffer += currentChar;
                break;
        }


    }

    private void endObject() {
        DagorNativeObjectBuilder selfBuilder = new DagorNativeObjectBuilder();
        DagorObject self = selfBuilder.CreateObjectFromString(name, subObjectBuffer);
        builder.addElement(self);
    }

}
