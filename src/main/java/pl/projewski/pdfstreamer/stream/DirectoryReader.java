package pl.projewski.pdfstreamer.stream;

import java.util.HashMap;
import java.util.Map;

public class DirectoryReader extends ParentReader {
    String START_STRING = "<<";
    String END_STRING = ">>";

    int startPos = 0;
    int endPos = 0;

    Map<String, String> parameters = new HashMap<>();

    public DirectoryReader(ParentReader parent) {
        super(parent);
    }


    @Override
    public void put(ParserContext context, int r) {
        if (startPos == START_STRING.length()) {
            if (r == '/') {
                redirect(new ParameterReader(this), context, r);
            } else if (r == '>') {
                endPos++;
                if (endPos == END_STRING.length()) {
                    parent.complete(context);
                    System.out.println("END DIRECTORY");
                }
            }
        } else if (START_STRING.charAt(startPos) == r) {
            startPos++;
        } else if (startPos == 1) {
            // Change to Content <Base64>
            redirect(new ContentReader(parent), context, '<');
            context.phaseReader.put(context, r);
        } else {
            throw new IllegalStateException("Wrong structure");
        }

    }

    @Override
    public void nextStage(ParserContext context, PhaseReader endingObject) {

    }

}