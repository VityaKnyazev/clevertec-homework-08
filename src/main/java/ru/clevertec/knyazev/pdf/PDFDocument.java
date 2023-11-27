package ru.clevertec.knyazev.pdf;


import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import ru.clevertec.knyazev.pdf.exception.PDFDocumentException;

import java.io.File;
import java.io.IOException;

/**
 * Interface for working with pdf document (creating, adding data, closing)
 */
public non-sealed interface PDFDocument extends PDFDocumentElements {

    /**
     *
     * Create document using existing pdf document with template and
     * result pdf document that storing data
     *
     * @param pdfTemplatePath existing pdf template path to document
     * @param pdfPath result pdf document path
     * @param pdfFontPath path to font of new pdf document
     * @return Document document
     * @throws PDFDocumentException when pdf creating error
     */
    default Document createDocument(String pdfTemplatePath,
                                      String pdfPath,
                                      String pdfFontPath) {
        try {
            Document document = new Document(new PdfDocument(new PdfReader(pdfTemplatePath),
                    new PdfWriter(new File(pdfPath))));

            FontProgram fontProgram = FontProgramFactory.createFont(pdfFontPath);
            PdfFont font = PdfFontFactory.createFont(fontProgram, "CP1251");
            document.setFont(font);

            return document;
        } catch (IOException e) {
            throw new PDFDocumentException(e);
        }
    }

    /**
     * Close pdf document. Should call close method on document
     */
    void closeDocument();
}
