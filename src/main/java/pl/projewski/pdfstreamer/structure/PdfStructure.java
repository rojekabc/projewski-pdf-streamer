package pl.projewski.pdfstreamer.structure;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString(exclude = {"lastPdfRevision", "lastPdfObject"})
public class PdfStructure {
    private final List<PdfRevision> pdfRevisionList = new ArrayList<>();
    private PdfRevision lastPdfRevision;
    private PdfObject lastPdfObject;

    public void startNextRevision() {
        lastPdfRevision = new PdfRevision();
        pdfRevisionList.add(lastPdfRevision);
    }

    public void addDefinition(String definition) {
        lastPdfRevision.definitionList.add(new PdfDefinition(definition));
    }

    public void nextObject(String objectId) {
        lastPdfObject = new PdfObject(objectId);
        lastPdfRevision.objectList.add(lastPdfObject);
    }

}
