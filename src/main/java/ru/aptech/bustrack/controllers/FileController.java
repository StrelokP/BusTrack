package ru.aptech.bustrack.controllers;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.aptech.bustrack.config.Constants;
import ru.aptech.bustrack.entities.Station;
import ru.aptech.bustrack.repositories.StationRepository;
import ru.aptech.bustrack.services.FileService;
import ru.aptech.bustrack.services.StationService;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/file")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
                                    @RequestParam("type") String type) {
        try {
            return fileService.parseEntity(file, type);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Constants.ERROR_PACKAGE_SAVING);
        }
    }
}
