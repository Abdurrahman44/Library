package com.example.cascade_delete_example.Controller;

import com.example.cascade_delete_example.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/upFile")
public class FileController {

    @Autowired
    private FileService fileService;
    /**
     * Verilen dosyanın türünü kontrol eder ve uygunsa kaydetme işlemi yapar.
     *
     * @param file Yüklenen dosya
     * @return Dosya başarıyla yüklendiyse 200 OK, aksi takdirde uygun değilse 400 Bad Request veya 500 Internal Server Error
     */

    @PostMapping("/uploadFile")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (isValidFileType(file.getContentType())) {
                fileService.saveFile(file);
                return ResponseEntity.ok("File uploaded successfully!");
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading file");
        }
        return null;
    }


    /**
     * Verilen dosya türünün geçerli olup olmadığını kontrol eder.
     *
     * @param fileType Kontrol edilecek dosya türü
     * @return Dosya türü geçerli ise true, aksi takdirde false döner
     */
    private Boolean isValidFileType(String fileType) {
        try {
            if (fileType != null) {
                // MIME türlerini kontrol et
                if (fileType.equals(MediaType.IMAGE_JPEG_VALUE) ||
                        fileType.equals(MediaType.APPLICATION_PDF_VALUE) ||
                        fileType.equals("application/msword") ||
                        fileType.equals(MediaType.IMAGE_PNG_VALUE) ||
                        fileType.equals("application/vnd.ms-excel") ||
                        fileType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                    return true;
                }
            }
        } catch (Exception e) {
            // Hata durumunda false döndür
            return false;
        }

        // Hiçbir koşul karşılanmazsa false döndür
        return false;
    }



}

