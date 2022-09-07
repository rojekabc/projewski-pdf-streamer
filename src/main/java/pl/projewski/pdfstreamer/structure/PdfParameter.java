package pl.projewski.pdfstreamer.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PdfParameter extends PdfElement {
    private String name;
    private PdfElement value;
}
