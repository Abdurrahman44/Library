package com.example.cascade_delete_example.Entities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelExporter {

    public static void exportAuthorsToExcel(List<Author> authors, String excelFilePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Authors");

            // Başlık satırı
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Author ID");
            headerRow.createCell(1).setCellValue("Author Name");

            // Veri satırları
            int rowNum = 1;
            for (Author author : authors) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(author.getAuthorId());
                row.createCell(1).setCellValue(author.getAuthorName());

                // Author ile ilişkili Book verilerini ekle
                List<Book> books = author.getBooks();
                for (Book book : books) {
                    Row bookRow = sheet.createRow(rowNum++);
                    bookRow.createCell(2).setCellValue(book.getBookId());
                    bookRow.createCell(3).setCellValue(book.getBookTitle());
                    // Diğer book sütunlarını eklemeye devam et
                }

                // Author ile ilişkili AuthorBookDetails verilerini ekle
                List<AuthorBookDetails> authorDetails = author.getAuthorDetails();
                for (AuthorBookDetails details : authorDetails) {
                    Row detailsRow = sheet.createRow(rowNum++);
                    detailsRow.createCell(4).setCellValue(details.getId());
                    detailsRow.createCell(5).setCellValue(details.getAdditionalDetails());
                    // Diğer details sütunlarını eklemeye devam et
                }
            }

            // Excel dosyasını kaydet
            try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
                workbook.write(fileOut);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
