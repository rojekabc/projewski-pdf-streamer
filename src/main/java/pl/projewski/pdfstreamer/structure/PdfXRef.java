package pl.projewski.pdfstreamer.structure;

import java.util.ArrayList;
import java.util.List;

public class PdfXRef extends PdfElement {
    final List<String> lines = new ArrayList<>();

    public void addLine(String line) {
        lines.add(line);
    }
}
