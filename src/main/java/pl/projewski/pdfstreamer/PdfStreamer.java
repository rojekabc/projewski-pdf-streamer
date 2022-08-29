package pl.projewski.pdfstreamer;

import lombok.RequiredArgsConstructor;
import pl.projewski.pdfstreamer.cache.CachingComponent;
import pl.projewski.pdfstreamer.stream.PdfStructureParserInputStream;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class PdfStreamer {
    private final CachingComponent cachingComponent;

    public void parse(InputStream inputStream) throws IOException {
        cachingComponent.put(new PdfStructureParserInputStream(inputStream));


    }
}
