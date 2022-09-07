package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfElement;
import pl.projewski.pdfstreamer.structure.PdfString;

import java.io.ByteArrayOutputStream;

class StringReader extends ChildReader {
    boolean begin = false;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    boolean escaped = false;

    StringReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (begin) {
            if (escaped) {
                baos.write(r);
                escaped = false;
            } else if (r == '\\') {
                escaped = true;
            } else if (r == ')') {
                if (ParserContext.OUT) {
                    System.out.println("String " + baos.toString());
                }
                parent.complete(context);
            } else {
                baos.write(r);
            }
        } else if (r == '(') {
            begin = true;
        } else {
            throw new IllegalStateException("Wrong string begin");
        }

    }

    @Override
    public PdfElement getResult() {
        return new PdfString(baos.toString());
    }
}
