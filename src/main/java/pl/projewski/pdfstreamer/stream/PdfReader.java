package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfElement;
import pl.projewski.pdfstreamer.structure.PdfStructure;

class PdfReader extends ParentReader {
    public static byte DEFINITION_CHARACTER = '%';
    PdfStructure pdfStructure = new PdfStructure();

    PdfReader(ParserContext context) {
        super(null);
        context.phaseReader = this;
    }

    @Override
    public void put(ParserContext context, int r) {
        if (r == DEFINITION_CHARACTER) {
            redirect(new DefinitionReader(this), context, r);
        } else {
            redirect(new ObjectReader(this), context, r);
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
