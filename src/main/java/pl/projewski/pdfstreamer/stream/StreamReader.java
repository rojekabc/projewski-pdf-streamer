package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfElement;
import pl.projewski.pdfstreamer.structure.PdfStream;

class StreamReader extends ChildReader {
    static String END_STRING = "\nendstream\n";
    int endpos = 0;
    int counter = 0;

    StreamReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (omitNewLines(r)) {
            return;
        }

        if (END_STRING.charAt(endpos) == r) {
            endpos++;
            if (END_STRING.length() == endpos) {
                System.out.println("Stream Length : " + counter);
                parent.complete(context);
            }
        } else {
            counter += endpos;
            counter++;
            endpos = 0;
        }

    }

    @Override
    public PdfElement getResult() {
        return new PdfStream(counter);
    }
}
