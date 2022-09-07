package pl.projewski.pdfstreamer.structure;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@RequiredArgsConstructor
@ToString
public class PdfObject extends PdfDirectory {
    private final static String OBJECT_ID_SEPARATOR = " ";
    private final int id;
    private final int rev;

    public PdfObject(String objectId) {
        final String[] strings = objectId.split(OBJECT_ID_SEPARATOR);
        if (strings.length != 3 || !Objects.equals("obj", strings[2])) {
            throw new IllegalStateException("Wrong Object ID");
        }
        id = Integer.parseInt(strings[0]);
        rev = Integer.parseInt(strings[1]);
    }

}
