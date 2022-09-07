package pl.projewski.pdfstreamer.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PdfStream extends PdfElement {
    int count;
}
