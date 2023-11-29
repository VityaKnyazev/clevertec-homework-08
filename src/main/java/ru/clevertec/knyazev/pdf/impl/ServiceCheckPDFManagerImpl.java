package ru.clevertec.knyazev.pdf.impl;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.RequiredArgsConstructor;
import ru.clevertec.knyazev.data.ServiceDTO;
import ru.clevertec.knyazev.pdf.AbstractPDFDocument;
import ru.clevertec.knyazev.pdf.PDFManager;

import java.io.File;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class ServiceCheckPDFManagerImpl extends PDFManager<List<ServiceDTO>> {

    private final String pdfTemplatePath;
    private final String pdfPath;
    private final String pdfFontPath;
    private final String pdfFontEncoding;

    private AbstractPDFDocument pdfDocumentImpl;

    /**
     * @implSpec
     */
    @Override
    protected Document create() {
        String pdfPathName = pdfPath + File.separator + "service-check-" +
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH-mm-ss-SSS", Locale.ROOT)) +
                ".pdf";

        pdfDocumentImpl = new PDFDocumentImpl(pdfTemplatePath,
                pdfPathName,
                pdfFontPath,
                pdfFontEncoding);

        return pdfDocumentImpl.createDocument();
    }

    /**
     * @implSpec
     */
    @Override
    protected Document add(List<ServiceDTO> objectData, Document document) {
        Paragraph titleParagraph = pdfDocumentImpl.createParagraph(
                String.format("Person services check #%s%n", objectData.get(0).personId().toString()),
                85f);
        Paragraph dateTimeParagraph = pdfDocumentImpl.createParagraph(
                String.format("Date time: %s%n", objectData.get(0).localDateTime()
                        .format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss"))),
                3f);
        Paragraph checkParagraph = pdfDocumentImpl.createParagraph(
                String.format("For person: %s %s%n", objectData.get(0).personName().toString(),
                        objectData.get(0).personSurname().toString()), 3f);

        Table dataTable = pdfDocumentImpl.createTable(new float[]{150f, 400f, 45f});
        pdfDocumentImpl.addCell("service name",
                TextAlignment.CENTER,
                dataTable);
        pdfDocumentImpl.addCell("service description",
                TextAlignment.CENTER,
                dataTable);
        pdfDocumentImpl.addCell("price",
                TextAlignment.CENTER,
                dataTable);

        objectData.stream().forEach(serviceDTO -> {
            pdfDocumentImpl.addCell(serviceDTO.serviceName(),
                    TextAlignment.LEFT,
                    dataTable);
            pdfDocumentImpl.addCell(serviceDTO.description(),
                    TextAlignment.CENTER,
                    dataTable);
            pdfDocumentImpl.addCell(DecimalFormat.getCurrencyInstance().format(serviceDTO.price()),
                    TextAlignment.CENTER,
                    dataTable);
        });

        Table sumTable = pdfDocumentImpl.createTable(new float[]{495f, 35f});
        pdfDocumentImpl.addCell("total",
                TextAlignment.LEFT,
                sumTable);
        pdfDocumentImpl.addCell(DecimalFormat.getCurrencyInstance()
                .format(objectData.stream()
                        .mapToDouble(serviceDTO -> serviceDTO.price().doubleValue())
                        .sum()),
                TextAlignment.CENTER,
                sumTable);

        document.add(titleParagraph);
        document.add(dateTimeParagraph);
        document.add(checkParagraph);
        document.add(dataTable);
        document.add(sumTable);

        return document;
    }

    /**
     * @implSpec
     */
    @Override
    protected String saveAndClose(Document document) {
        String absolutePDFPath = Path.of(pdfDocumentImpl.getPdfPath())
                .toFile().getAbsolutePath();

        PDFDocumentImpl.closeDocument(document);
        pdfDocumentImpl = null;

        return absolutePDFPath;
    }
}
