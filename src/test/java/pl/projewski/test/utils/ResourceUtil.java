package pl.projewski.test.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class ResourceUtil {
    public static InputStream getResourceStream(String resourcename) {
        InputStream stream = null;
        File file = new File(resourcename);
        if (file.exists()) {
            try {
                stream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                // do nothing
            }
        }

        if (stream == null) {
            stream = ResourceUtil.class.getResourceAsStream(resourcename);
            if (stream == null) {
                stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcename);
            }
        }
        return stream;
    }

    public static byte[] getResourceBytes(String resourceName) {
        try (final InputStream resourceStream = getResourceStream(resourceName)) {
            return IOUtils.toByteArray(resourceStream);
        } catch (IOException e) {
            return null;
        }
    }

}
