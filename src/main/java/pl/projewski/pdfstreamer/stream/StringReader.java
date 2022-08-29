package pl.projewski.pdfstreamer.stream;

import java.io.ByteArrayOutputStream;

public class StringReader extends ChildReader {
    boolean begin = false;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    boolean escaped = false;

    public StringReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (begin) {
            if (escaped) {
                baos.write(r);
                escaped = false;
            } else if (r == '\\') {
                escaped = true;
            } else if (r == ')') {
                System.out.println("String " + baos.toString());
                parent.complete(context);
            } else {
                baos.write(r);
            }
        } else if (r == '(') {
            begin = true;
        } else {
            throw new IllegalStateException("Wrong string begin");
        }

    }
}
