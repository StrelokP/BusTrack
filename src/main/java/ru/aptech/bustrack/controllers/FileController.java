package ru.aptech.bustrack.controllers;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class FileController {

    @PostMapping("/file")
    public void upload(@RequestParam("file") MultipartFile file) {

        try (InputStream is = file.getInputStream()) {
            //TODO: проверять расширение файлов
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
