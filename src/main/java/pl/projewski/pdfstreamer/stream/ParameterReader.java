package pl.projewski.pdfstreamer.stream;

import java.io.ByteArrayOutputStream;

class ParameterReader extends ParentReader {
    char BEGIN_CHAR = '/';
    boolean begin = false;

    ByteArrayOutputStream parameterName = new ByteArrayOutputStream();
    ByteArrayOutputStream parameterValue = new ByteArrayOutputStream();
    boolean parameterNamePart = true;

    ParameterReader(ParentReader parent) {
        super(parent);
    }

    @Override
    public void put(ParserContext context, int r) {
        if (begin) {
            if (parameterNamePart) {
                if (r == ' ') {
                    parameterNamePart = false;
                    System.out.println("Parameter name: " + parameterName.toString());
                    return; // ignore
                }
                if (r == '/') {
                    parameterNamePart = false;
                    System.out.println("Parameter name: " + parameterName.toString());
                } else if (r == '(') {
                    parameterNamePart = false;
                    System.out.println("Parameter name: " + parameterName.toString());
                    redirect(new StringReader(this), context, r);
                } else if (r == '[') {
                    parameterNamePart = false;
                    System.out.println("Parameter name: " + parameterName.toString());
                    redirect(new ArrayReader(this), context, r);
                } else if (r == '<') {
                    parameterNamePart = false;
                    System.out.println("Parameter name: " + parameterName.toString());
                    redirect(new DirectoryReader(this), context, r);
                } else {
                    parameterName.write(r);
                }
            } else {
                if ((parameterValue.size() == 0) && (r == '(' || r == ' ')) {
                    return;
                }
                if (r == '<') {
                    redirect(new DirectoryReader(this), context, r);
                } else if (r == '[') {
                    redirect(new ArrayReader(this), context, r);
                } else if (r == '(') {
                    redirect(new StringReader(this), context, r);
                } else if (r == '/' || r == '>') {
                    // System.out.println("Parameter name: " + parameterName.toString());
                    System.out.println("Parameter value: " + parameterValue.toString());

                    parent.complete(context);
                    context.phaseReader.put(context, r);
                } else if (r == ')' || r == ']') {
                    // System.out.println("Parameter name: " + parameterName.toString());
                    System.out.println("Parameter value: " + parameterValue.toString());

                    parent.complete(context);
                } else {
                    parameterValue.write(r);
                }
            }
        } else {
            if (r == BEGIN_CHAR) {
                begin = true;
                return;
            }
            throw new IllegalStateException("Parameter should begin");
        }

    }

    @Override
    public void nextStage(ParserContext context, PhaseReader endingObject) {
        parent.complete(context); // Finish the value - for example directory
    }

}
