package pl.projewski.pdfstreamer.stream;

import java.io.ByteArrayOutputStream;

public class XRefReader extends ChildReader {
    int type = -1;
    ByteArrayOutputStream line = new ByteArrayOutputStream();

    public XRefReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (omitNewLines(r)) {
            return;
        }
        // TODO: Try separate
        if (r == '\n') {
            String linestr = line.toString();
            if ("trailer".equals(linestr)) {
                System.out.println("trailer");
                parent.complete(context);
            } else {
                final String[] strings = linestr.split(" ");
                if (strings.length == 2) {
                    type = Integer.parseInt(strings[0]);
                } else if (strings.length == 3) {
                    System.out.println("" + type + " " + linestr);
                } else {
                    throw new IllegalStateException("Unrecognized xref");
                }
            }
            line.reset();
        } else {
            line.write(r);
        }
    }
}
