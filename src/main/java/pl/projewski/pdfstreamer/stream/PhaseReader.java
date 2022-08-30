package pl.projewski.pdfstreamer.stream;

interface PhaseReader {
    void put(ParserContext context, int r);

    default void redirect(PhaseReader phaseReader, ParserContext context, int r) {
        context.phaseReader = phaseReader;
        phaseReader.put(context, r);
    }


}
