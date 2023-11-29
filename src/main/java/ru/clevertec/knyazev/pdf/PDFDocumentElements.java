package ru.clevertec.knyazev.pdf;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

/**
 * Represents simple methods for creating pdf elements (paragraph, table, etc.)
 * and filling it with data
 */
public sealed interface PDFDocumentElements permits AbstractPDFDocument {

    default Paragraph createParagraph(String text) {
        Paragraph paragraph = new Paragraph(text);
        paragraph.setFontSize(14f);
        return paragraph;
    }

    default Table createTable(float[] columnWidth) {
        Table table = new Table(columnWidth);
        table.setFontSize(14f);
        return table;
    }

    default Table addCell(String cellText, Table table) {
        table.addCell(cellText);
        return table;
    }
}
