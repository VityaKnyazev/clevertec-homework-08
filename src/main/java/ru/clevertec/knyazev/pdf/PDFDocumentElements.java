package ru.clevertec.knyazev.pdf;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

/**
 * Represents simple methods for creating pdf elements (paragraph, table, etc.)
 * and filling it with data
 */
public sealed interface PDFDocumentElements permits AbstractPDFDocument {

    /**
     *
     * Create paragraph
     *
     * @param text paragraph text
     * @return paragraph that contains given text
     */
    default Paragraph createParagraph(String text, float marginTop) {
        Paragraph paragraph = new Paragraph(text);
        paragraph.setMarginTop(marginTop);
        paragraph.setFontSize(14f);
        return paragraph;
    }

    /**
     *
     * Create table with column width
     *
     * @param columnWidth column width
     * @return table with columns
     */
    default Table createTable(float[] columnWidth) {
        Table table = new Table(columnWidth);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.setVerticalAlignment(VerticalAlignment.BOTTOM);
        table.setMarginTop(3f);
//        table.setBorder(Border.NO_BORDER);

        return table;
    }

    /**
     *
     * Create table cell with text and text alignment and add it to table
     *
     * @param cellText cell text
     * @param textAlignment text alignment
     * @param table table
     * @return table with cell that contains text
     */
    default Table addCell(String cellText,
                          TextAlignment textAlignment,
                          Table table) {
        Cell cell = new Cell();
        cell.add(new Paragraph(cellText).setTextAlignment(textAlignment).setFontSize(10));
//        cell.setBorder(Border.NO_BORDER);

        table.addCell(cell);
        return table;
    }
}
