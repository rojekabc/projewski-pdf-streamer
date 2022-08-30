package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfStructure;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfStructureParserInputStream extends FilterInputStream {
    ParserContext parserContext = new ParserContext();

    public PdfStructureParserInputStream(InputStream in) {
        super(in);
        parserContext.pdfStructure.startNextRevision();
    }

    public PdfStructure getPdfStructure() {
        return parserContext.pdfStructure;
    }

    @Override
    public int read() throws IOException {
        final int r = super.read();
        if (parserContext.phaseReader == null) {
            return r;
        }
        parserContext.phaseReader.put(parserContext, r);
        return r;
    }

    @Override
    public int read(byte[] b) throws IOException {
        final int c = super.read(b);
        for (int i = 0; i < c; i++) {
            if (parserContext.phaseReader == null) {
                return c;
            }
            parserContext.phaseReader.put(parserContext, b[i]);
        }
        return c;
    }
}
