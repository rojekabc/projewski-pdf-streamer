package pl.projewski.pdfstreamer;

import lombok.RequiredArgsConstructor;
import pl.projewski.pdfstreamer.cache.CachingComponent;
import pl.projewski.pdfstreamer.stream.PdfStructureParserInputStream;
import pl.projewski.pdfstreamer.structure.PdfStructure;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class PdfStreamer {
    private final CachingComponent cachingComponent;

    public PdfStructure parse(InputStream inputStream) throws IOException {
        final PdfStructureParserInputStream pdfStructureParserInputStream = new PdfStructureParserInputStream(inputStream);
        cachingComponent.put(pdfStructureParserInputStream);
        return pdfStructureParserInputStream.getPdfStructure();
    }
}
