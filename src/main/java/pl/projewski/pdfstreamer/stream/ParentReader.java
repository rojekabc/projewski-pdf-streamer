package pl.projewski.pdfstreamer.stream;

abstract class ParentReader extends ChildReader {
    int stage = 0;

    ParentReader(ParentReader parentReader) {
        super(parentReader);
    }

    abstract void nextStage(ParserContext context, PhaseReader endingObject);

    void complete(ParserContext context) {
        final PhaseReader endingObject = context.phaseReader;
        stage++;
        context.phaseReader = this;
        nextStage(context, endingObject);
    }

}
