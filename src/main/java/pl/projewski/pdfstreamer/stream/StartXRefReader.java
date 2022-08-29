package pl.projewski.pdfstreamer.stream;

import java.io.ByteArrayOutputStream;

public class StartXRefReader extends ChildReader {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public StartXRefReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (omitNewLines(r)) {
            return;
        }
        if (r == '\r' || r == '\n') {
            System.out.println("StartXRef " + baos.toString());
            parent.complete(context);
        } else {
            baos.write(r);
        }
    }
}
