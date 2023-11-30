package ru.clevertec.knyazev;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.clevertec.knyazev.config.AppConfig;
import ru.clevertec.knyazev.dao.exception.DAOException;
import ru.clevertec.knyazev.pdf.exception.PDFDocumentException;
import ru.clevertec.knyazev.service.GovernmentService;
import ru.clevertec.knyazev.service.exception.ServiceException;

import java.util.UUID;

@Slf4j
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        log.debug("Spring context started");

        GovernmentService governmentServiceImpl = context.getBean("governmentServiceImpl",
                                                                  GovernmentService.class);

        UUID personId = UUID.fromString("f0a7c9a8-0a0a-4f1a-9c0d-9a0f9a0f9a0f");

        try {
            String savedPDFAbsolutePath = governmentServiceImpl.getAbsolutePathByPersonId(personId);

            log.info("Saved pdf check about person services with id={} here:{}",
                    personId,
                    savedPDFAbsolutePath);
        } catch (DAOException | ServiceException | PDFDocumentException | IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
    }

}
