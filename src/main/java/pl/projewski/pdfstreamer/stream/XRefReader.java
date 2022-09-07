package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfElement;
import pl.projewski.pdfstreamer.structure.PdfXRef;

import java.io.ByteArrayOutputStream;

class XRefReader extends ChildReader {
    int type = -1;
    ByteArrayOutputStream line = new ByteArrayOutputStream();
    PdfXRef pdfXRef = new PdfXRef();

    XRefReader(ParentReader parent) {
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
                if (ParserContext.OUT) {
                    System.out.println("trailer");
                }
                parent.complete(context);
            } else {
                final String[] strings = linestr.split(" ");
                if (strings.length == 2) {
                    type = Integer.parseInt(strings[0]);
                } else if (strings.length == 3) {
                    if (ParserContext.OUT) {
                        System.out.println("" + type + " " + linestr);
                    }
                    pdfXRef.addLine("" + type + " " + linestr);
                } else {
                    throw new IllegalStateException("Unrecognized xref");
                }
            }
            line.reset();
        } else {
            line.write(r);
        }
    }

    @Override
    public PdfElement getResult() {
        return pdfXRef;
    }
}
