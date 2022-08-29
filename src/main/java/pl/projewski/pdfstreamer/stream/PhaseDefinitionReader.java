package pl.projewski.pdfstreamer.stream;

import java.io.ByteArrayOutputStream;

public class PhaseDefinitionReader extends ChildReader {
    private final static byte END_CHARACTER = '\n';
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public PhaseDefinitionReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (r == END_CHARACTER || r == '\r') {
            System.out.println("Configuration: [" + baos.toString() + "]");
            parent.complete(context);
        } else {
            baos.write(r);
        }
    }

}
