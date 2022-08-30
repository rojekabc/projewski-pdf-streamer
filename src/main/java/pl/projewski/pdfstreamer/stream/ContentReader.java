package pl.projewski.pdfstreamer.stream;

import java.io.ByteArrayOutputStream;

class ContentReader extends ParentReader {
    String START_STRING = "<";

    int startPos = 0;

    ByteArrayOutputStream baos = new ByteArrayOutputStream();


    ContentReader(ParentReader parent) {
        super(parent);
    }


    @Override
    public void put(ParserContext context, int r) {
        if (startPos == START_STRING.length()) {
            if (r == '>') {
                System.out.println("Content: " + baos.toString());
                parent.complete(context);
            } else {
                baos.write(r);
            }
        } else if (START_STRING.charAt(startPos) == r) {
            startPos++;
        } else {
            throw new IllegalStateException("Wrong structure");
        }
    }

    @Override
    public void nextStage(ParserContext context, PhaseReader endingObject) {

    }

}
