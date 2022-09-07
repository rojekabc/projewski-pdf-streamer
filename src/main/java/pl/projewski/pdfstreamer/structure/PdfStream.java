package pl.projewski.pdfstreamer.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PdfStream extends PdfElement {
    int count;
}
