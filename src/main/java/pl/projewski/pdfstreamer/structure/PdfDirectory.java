package pl.projewski.pdfstreamer.structure;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class PdfDirectory extends PdfElement {
    private List<PdfElement> elements = new ArrayList<>();

    public void addElement(PdfElement element) {
        elements.add(element);
    }
}
