package pl.projewski.pdfstreamer.stream;

class PdfReader extends ParentReader {
    public static byte DEFINITION_CHARACTER = '%';

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
    public void nextStage(ParserContext context, PhaseReader endingObject) {
    }

}
