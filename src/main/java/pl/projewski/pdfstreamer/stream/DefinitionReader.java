package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfDefinition;
import pl.projewski.pdfstreamer.structure.PdfElement;

import java.io.ByteArrayOutputStream;

class DefinitionReader extends ChildReader {
    private final static byte END_CHARACTER = '\n';
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    DefinitionReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (begin) {
            if (r != '%') {
                throw new IllegalStateException("Wrong definition begin");
            }
            begin = false;
            return;
        }
        if (r == END_CHARACTER || r == '\r') {
            if (ParserContext.OUT) {
                System.out.println("Configuration: [" + baos.toString() + "]");
            }

            parent.complete(context);
        } else {
            baos.write(r);
        }
    }

    @Override
    public PdfElement getResult() {
        return new PdfDefinition(baos.toString());
    }

}
