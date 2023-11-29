package ru.clevertec.knyazev.pdf.impl;

import com.itextpdf.layout.Document;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.clevertec.knyazev.pdf.AbstractPDFDocument;
import ru.clevertec.knyazev.pdf.exception.PDFDocumentException;

@RequiredArgsConstructor
public final class PDFDocumentImpl extends AbstractPDFDocument {

    private final String pdfTemplatePath;

    @Getter
    private final String pdfPath;

    private final String pdfFontPath;

    private final String pdfFontEncoding;

    /**
     * Create pdf document for working with
     *
     * @return pdf document
     * @throws PDFDocumentException
     */
    public Document createDocument() throws PDFDocumentException {

        return super.createDocument(pdfTemplatePath,
                                    pdfPath,
                                    pdfFontPath,
                                    pdfFontEncoding);
    }
}
