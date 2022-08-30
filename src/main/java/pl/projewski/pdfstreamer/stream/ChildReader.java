package pl.projewski.pdfstreamer.stream;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class ChildReader implements PhaseReader {
    protected final ParentReader parent;
    boolean begin = true;

    protected boolean omitNewLines(int r) {
        if (begin) {
            begin = r == '\n' || r == '\r';
        }
        return begin;

    }

}
