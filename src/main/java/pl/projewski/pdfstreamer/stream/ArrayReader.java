package pl.projewski.pdfstreamer.stream;

import java.io.ByteArrayOutputStream;

public class ArrayReader extends ChildReader {
    boolean begin = false;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public ArrayReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (begin) {
            if (r == ']') {
                System.out.println("Array " + baos.toString());
                parent.complete(context);
            } else {
                baos.write(r);
            }
        } else if (r == '[') {
            begin = true;
        } else {
            throw new IllegalStateException("Wrong array begin");
        }

    }
}
