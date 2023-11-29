package ru.clevertec.knyazev;

import ru.clevertec.knyazev.data.ServiceDTO;
import ru.clevertec.knyazev.pdf.PDFManager;
import ru.clevertec.knyazev.pdf.impl.ServiceCheckPDFManagerImpl;
import ru.clevertec.knyazev.util.YAMLParser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        YAMLParser yamlParser = new YAMLParser("application.yaml");

        String tmpl = yamlParser.getProperty("pdf", "templatePath");
        String pdfPath = yamlParser.getProperty("pdf", "resultPath");
        String fontPath = yamlParser.getProperty("pdf", "documentFontPath");
        String fontEncoding = yamlParser.getProperty("pdf", "documentFontEncoding");

        PDFManager<List<ServiceDTO>> pdfManager = new ServiceCheckPDFManagerImpl(tmpl, pdfPath, fontPath, fontEncoding);
        String result = pdfManager.manage(List.of(
                ServiceDTO.builder()
                        .id(UUID.randomUUID())
                        .personId(UUID.randomUUID())
                        .localDateTime(LocalDateTime.now())
                        .personName("Vano")
                        .personSurname("Ivano")
                        .serviceName("Справка о доходах")
                        .description("Доходы mou, doxodi")
                        .price(new BigDecimal("37.00"))
                        .build(),
                ServiceDTO.builder()
                        .id(UUID.randomUUID())
                        .personId(UUID.randomUUID())
                        .localDateTime(LocalDateTime.now())
                        .personName("Vano")
                        .personSurname("Ivano")
                        .serviceName("Справка o пароходах")
                        .description("paro xodi mou, paroxodi")
                        .price(new BigDecimal("18.50"))
                        .build()
        ));

        System.out.printf("Person service check created: %s%n", result);
    }
}
