package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfStructure;

class ParserContext {
    final static boolean OUT = false;
    PhaseReader phaseReader = new PdfReader(this);
    PdfStructure pdfStructure = new PdfStructure();
}
