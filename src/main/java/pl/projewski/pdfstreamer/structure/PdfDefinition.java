package pl.projewski.pdfstreamer.structure;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class PdfDefinition extends PdfElement {
    final String data;
}
