package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfElement;
import pl.projewski.pdfstreamer.structure.PdfStartXRef;

import java.io.ByteArrayOutputStream;

class StartXRefReader extends ChildReader {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    StartXRefReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (omitNewLines(r)) {
            return;
        }
        if (r == '\r' || r == '\n') {
            if (ParserContext.OUT) {
                System.out.println("StartXRef " + baos.toString());
            }
            parent.complete(context);
        } else {
            baos.write(r);
        }
    }

    @Override
    public PdfElement getResult() {
        return new PdfStartXRef();
    }
}
