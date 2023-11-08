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
import ru.aptech.bustrack.services.StationService;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private StationService stationService;
    @Autowired
    private StationRepository stationRepository;

    @PostMapping("/file")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {

        try (InputStream is = file.getInputStream()) {
            //TODO: проверять расширение файлов
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            List<Station> stations = new ArrayList<>();
            Set<Integer> errorRows = new HashSet<>();

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                try {
                    String stationName = sheet.getRow(i).getCell(0).getStringCellValue();
                    Double stationLng = Double.valueOf(sheet.getRow(i).getCell(1).getStringCellValue());
                    Double stationLat = Double.valueOf(sheet.getRow(i).getCell(2).getStringCellValue());

                    Station station = Station.builder()
                            .lng(stationLng)
                            .name(stationName)
                            .lat(stationLat).build();
                    stations.add(station);
                } catch (IllegalStateException | NumberFormatException e) {
                    errorRows.add(i + 1);
                }
            }

            List<String> savedNames = stationRepository
                    .findAlreadySavedStationNames(stations.stream()
                            .map(Station::getName).collect(Collectors.toList()));

            stations = stations.stream()
                    .filter(e -> !savedNames.contains(e.getName()))
                    .collect(Collectors.toList());

            int savedCount = stationService.saveAllStations(stations);
            StringBuilder sb = new StringBuilder();
            
            if (savedCount != 0) {
                sb.append(String.format(Constants.PACKAGE_SAVING_RESULT_MSG, 
                        savedCount, 
                        errorRows.isEmpty() ? StringUtils.EMPTY : String.format(Constants.ERRORS_IN_ROWS, errorRows)));
            } else {
                sb.append(String.format(Constants.PACKAGE_SAVING_NO_RESULT_MSG,
                        errorRows.isEmpty() ? StringUtils.EMPTY : String.format(Constants.ERRORS_IN_ROWS, errorRows)));
            }
            
            return ResponseEntity.ok(sb.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.badRequest().body(Constants.ERROR_PACKAGE_SAVING);
    }
}
