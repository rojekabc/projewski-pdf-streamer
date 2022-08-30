package pl.projewski.pdfstreamer.cache;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MemoryCachingComponent implements CachingComponent {
    Map<String, byte[]> cacheMap = new HashMap<>();

    @Override
    public String put(InputStream inputStream) throws IOException {
        final String uuid = UUID.randomUUID().toString();
        cacheMap.put(uuid, IOUtils.toByteArray(inputStream));
        return uuid;
    }

    @Override
    public InputStream get(String id) throws IOException {
        return new ByteArrayInputStream(cacheMap.get(id));
    }
}
