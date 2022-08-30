package pl.projewski.pdfstreamer.cache;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class NoneCachingComponent implements CachingComponent {

    private static final NullOutputStream NULL_OUTPUT_STREAM = new NullOutputStream();

    @Override
    public String put(InputStream inputStream) throws IOException {
        IOUtils.copyLarge(inputStream, NULL_OUTPUT_STREAM);
        return UUID.randomUUID().toString();
    }

    @Override
    public InputStream get(String id) throws IOException {
        return null;
    }
}
