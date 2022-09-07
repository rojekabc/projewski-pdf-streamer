package pl.projewski.pdfstreamer;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Test;
import pl.projewski.pdfstreamer.cache.CachingComponent;
import pl.projewski.pdfstreamer.cache.NoneCachingComponent;
import pl.projewski.pdfstreamer.structure.PdfStructure;
import pl.projewski.test.utils.ResourceUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class PdfStreamerTest {
    static File cacheFolder = new File("cache");
    // CachingComponent cachingComponent = new FileCachingComponent(cacheFolder);
    CachingComponent cachingComponent = new NoneCachingComponent();
    PdfStreamer pdfStreamer = new PdfStreamer(cachingComponent);

    @Test
    public void test_parse_pdf_001() throws Exception {
        // given
        final InputStream resourceStream = ResourceUtil.getResourceStream("001.pdf");

        // when
        final PdfStructure pdfStructure = pdfStreamer.parse(resourceStream);
        System.out.println(pdfStructure);

        // then

    }

    @Test
    public void test_parse_pades_001() throws Exception {
        // given
        final InputStream resourceStream = ResourceUtil.getResourceStream("001.pades");

        // when
        final PdfStructure pdfStructure = pdfStreamer.parse(resourceStream);
        System.out.println(pdfStructure);

        // then

    }

    @Test
    public void test_parse_P_HR_AKD_10() throws Exception {
        // given
        final InputStream resourceStream = ResourceUtil.getResourceStream("Signature-P-HR_AKD-10.pdf");

        // when
        pdfStreamer.parse(resourceStream);

        // then

    }

    @Test(expected = IllegalStateException.class)
    public void test_parse_txt() throws Exception {
        // given
        final InputStream resourceStream = ResourceUtil.getResourceStream("any.txt");

        // when
        pdfStreamer.parse(resourceStream);

        // then

    }

    @AfterClass
    public static void after() {
        try {
            FileUtils.deleteDirectory(cacheFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}