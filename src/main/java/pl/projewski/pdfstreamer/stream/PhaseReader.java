package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfElement;

interface PhaseReader {
    void put(ParserContext context, int r);

    PdfElement getResult();

    default void redirect(PhaseReader phaseReader, ParserContext context, int r) {
        context.phaseReader = phaseReader;
        phaseReader.put(context, r);
    }


}
