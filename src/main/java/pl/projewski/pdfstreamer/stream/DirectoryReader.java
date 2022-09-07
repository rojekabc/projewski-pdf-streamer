package pl.projewski.pdfstreamer.stream;

import pl.projewski.pdfstreamer.structure.PdfDirectory;
import pl.projewski.pdfstreamer.structure.PdfElement;

public class DirectoryReader extends ParentReader {
    final PdfDirectory pdfDirectory = new PdfDirectory();
    final static String START_STRING = "<<";
    final static String END_STRING = ">>";

    int startPos = 0;
    int endPos = 0;

    DirectoryReader(ParentReader parent) {
        super(parent);
    }


    @Override
    public void put(ParserContext context, int r) {
        if (startPos == START_STRING.length()) {
            if (r == '/') {
                redirect(new ParameterReader(this), context, r);
            } else if (r == '>') {
                endPos++;
                if (endPos == END_STRING.length()) {
                    parent.complete(context);
                    if (ParserContext.OUT) {
                        System.out.println("END DIRECTORY");
                    }
                }
            }
        } else if (START_STRING.charAt(startPos) == r) {
            startPos++;
        } else if (startPos == 1) {
            // Change to Content <Base64>
            redirect(new ContentReader(parent), context, '<');
            context.phaseReader.put(context, r);
        } else {
            throw new IllegalStateException("Wrong structure");
        }

    }

    @Override
    public PdfElement getResult() {
        return pdfDirectory;
    }

    @Override
    public void nextStage(ParserContext context, PhaseReader endingObject) {
        pdfDirectory.addElement(endingObject.getResult());
    }

}
