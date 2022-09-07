package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfDefinition;
import pl.projewski.pdfstreamer.structure.PdfElement;
import pl.projewski.pdfstreamer.structure.PdfEndRevision;
import pl.projewski.pdfstreamer.structure.PdfStructure;

import java.io.ByteArrayOutputStream;

class PdfReader extends ParentReader {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public static byte DEFINITION_CHARACTER = '%';
    PdfStructure pdfStructure = new PdfStructure();

    PdfReader(ParserContext context) {
        super(null);
        context.phaseReader = this;
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
            if (name.startsWith("%")) {
                if ("%%EOF".equals(name)) {
                    pdfStructure.addElement(new PdfEndRevision());
                } else {
                    pdfStructure.addElement(new PdfDefinition(name.substring(1)));
                }
            } else if ("xref".equals(name)) {
                if (ParserContext.OUT) {
                    System.out.println("START XREF");
                }
                redirect(new XRefReader(this), context, r);
            } else if (name.endsWith(" obj")) {
                if (ParserContext.OUT) {
                    System.out.println("Object id: " + name);
                }
                context.phaseReader = new ObjectReader(this, name);
            } else if ("startxref".equals(name)) {
                redirect(new StartXRefReader(this), context, r);
            }
        } else {
            baos.write(r);
        }
    }

    @Override
    public PdfElement getResult() {
        return pdfStructure;
    }

    @Override
    public void nextStage(ParserContext context, PhaseReader endingObject) {
        pdfStructure.addElement(endingObject.getResult());
    }

}
