package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfElement;
import pl.projewski.pdfstreamer.structure.PdfObject;

import java.io.ByteArrayOutputStream;

class ObjectReader extends ParentReader {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfObject pdfObject;

    ObjectReader(ParentReader parentReader) {
        super(parentReader);
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
            if ("xref".equals(name)) {
                System.out.println("START XREF");
                redirect(new XRefReader(this), context, r);
            } else if ("endobj".equals(name)) {
                if (ParserContext.OUT) {
                    System.out.println("END OBJECT");
                }
                parent.complete(context);
            } else if (name.endsWith(" obj")) {
                if (ParserContext.OUT) {
                    System.out.println("Object id: " + name);
                }
                pdfObject = new PdfObject(name);
            } else if ("stream".equals(name)) {
                redirect(new StreamReader(this), context, r);
            } else if ("startxref".equals(name)) {
                redirect(new StartXRefReader(this), context, r);
            } else if ("%%EOF".equals(name)) {
                System.out.println("END REVISION");
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
