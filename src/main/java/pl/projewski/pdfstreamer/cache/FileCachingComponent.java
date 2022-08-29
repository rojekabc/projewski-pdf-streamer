package pl.projewski.pdfstreamer.cache;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
public class FileCachingComponent implements CachingComponent {
    private final File cacheFolder;

    @Override
    public String put(InputStream inputStream) throws IOException {
        final UUID uuid = UUID.randomUUID();
        cacheFolder.mkdirs();
        IOUtils.copyLarge(inputStream, new FileOutputStream(new File(cacheFolder, uuid.toString())));
        return uuid.toString();
    }

    @Override
    public InputStream get(String id) throws IOException {
        final UUID uuid = UUID.fromString(id);
        return new FileInputStream(new File(cacheFolder, uuid.toString()));
    }
}
