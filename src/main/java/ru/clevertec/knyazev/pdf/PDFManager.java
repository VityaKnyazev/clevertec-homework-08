package ru.clevertec.knyazev.pdf;

/**
 * Represents manager that working with pdf document
 *
 * @param <T> data object type that should be added to pdf
 *            document
 */
public interface PDFManager<T> {

    /**
     * Manage given pdf document (create pdf, add object data to it
     * than save and close document).
     *
     * @param objectData data for adding to pdf document
     */
    void manage(T objectData);
}
