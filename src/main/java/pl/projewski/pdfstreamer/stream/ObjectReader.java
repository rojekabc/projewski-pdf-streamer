package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfElement;
import pl.projewski.pdfstreamer.structure.PdfObject;

import java.io.ByteArrayOutputStream;

class ObjectReader extends ParentReader {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfObject pdfObject;

    ObjectReader(ParentReader parentReader, String objectName) {
        super(parentReader);
        pdfObject = new PdfObject(objectName);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (baos.size() == 0) {
            if (r == '<') {
                if (ParserContext.OUT) {
                    System.out.println("START DIRECTORY");
                }
                baos.reset();
                redirect(new DirectoryReader(this), context, r);
            } else if (r != '\n' && r != '\r') {
                baos.write(r);
            }
        } else if (r == '\n' || r == '\r') {
            final String name = baos.toString();
            baos.reset();
            if ("endobj".equals(name)) {
                if (ParserContext.OUT) {
                    System.out.println("END OBJECT");
                }
                parent.complete(context);
            } else if ("stream".equals(name)) {
                redirect(new StreamReader(this), context, r);
            }
        } else {
            baos.write(r);
        }
    }

    @Override
    public PdfElement getResult() {
        return pdfObject;
    }

    @Override
    public void nextStage(ParserContext context, PhaseReader endingObject) {
        pdfObject.addElement(endingObject.getResult());
        baos.reset();
    }
}
