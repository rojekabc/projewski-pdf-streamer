package pl.projewski.pdfstreamer.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PdfParameter extends PdfElement {
    private String name;
    private PdfElement value;
}
