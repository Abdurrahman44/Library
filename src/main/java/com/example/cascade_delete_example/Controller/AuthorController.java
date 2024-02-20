package com.example.cascade_delete_example.Controller;

import com.example.cascade_delete_example.Entities.Author;
import com.example.cascade_delete_example.Entities.AuthorBookDetails;
import com.example.cascade_delete_example.Entities.Book;
import com.example.cascade_delete_example.Service.AuthorService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("a/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping("/create")
    public void saveAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }

    @PutMapping("m/{id}")
    public void updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        authorService.updateAuthor(id, updatedAuthor);
    }

    @DeleteMapping("d/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping("/exportToExcel")
    public String exportToExcel() {
        try {
            List<Author> authors = authorService.getAllAuthors();//Bütün dosyayı çekiyorki Rahat bir şekilde verilerin hepsi elinde olsun
            String excelFilePath = "D:/AuthorsData.xlsx";//Göndelileck dosyanın yolu ve ismi oluşturmasını sağlar

            exportAuthorsToExcel(authors, excelFilePath);

            return "Excel dosyası başarıyla oluşturuldu: " + excelFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "Excel dosyası oluşturulurken bir hata oluştu.";
        }
    }


    public static void exportAuthorsToExcel(List<Author> authors, String excelFilePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Authors" );//İlk başta excel dosyası oluşturuluyor ve sayfa ksımına isim veriliyor.

            // Başlık satırı
            Row headerRow = sheet.createRow(0);//bailangıç satrının ne olması gerektiğine karar kılmış durumda
            headerRow.createCell(0).setCellValue("Author Name");//başlangıç ismi
            //headerRow.createCell(1).setCellValue("Author Name");//Başlangıç ism

            // Veri satırları
            int rowNum = 1;

            for (Author author : authors) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(author.getAuthorName());
             //   row.createCell(1).setCellValue(author.getAuthorName());

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