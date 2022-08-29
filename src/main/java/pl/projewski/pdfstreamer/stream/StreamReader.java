package pl.projewski.pdfstreamer.stream;

public class StreamReader extends ChildReader {
    static String END_STRING = "\nendstream\n";
    int endpos = 0;
    int counter = 0;

    public StreamReader(ParentReader parent) {
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
}
