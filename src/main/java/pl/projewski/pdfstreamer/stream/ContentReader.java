package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfContent;
import pl.projewski.pdfstreamer.structure.PdfElement;

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
                if (ParserContext.OUT) {
                    System.out.println("Content: " + baos.toString());
                }
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
    public PdfElement getResult() {
        return new PdfContent(baos.toString());
    }

    @Override
    public void nextStage(ParserContext context, PhaseReader endingObject) {

    }

}
