package pl.projewski.pdfstreamer.cache;

import java.io.IOException;
import java.io.InputStream;

public interface CachingComponent {
    String put(InputStream inputStream) throws IOException;

    InputStream get(String id) throws IOException;
}
