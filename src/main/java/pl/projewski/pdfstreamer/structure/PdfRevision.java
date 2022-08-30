package pl.projewski.pdfstreamer.structure;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class PdfRevision {
    final List<PdfDefinition> definitionList = new ArrayList<>();
    final List<PdfObject> objectList = new ArrayList<>();

}
