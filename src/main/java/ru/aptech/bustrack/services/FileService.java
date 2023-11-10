package ru.aptech.bustrack.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aptech.bustrack.config.Constants;
import ru.aptech.bustrack.entities.Station;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private StationService stationService;

    public ResponseEntity<?> parseEntity(MultipartFile file, String type) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook;
            if (FilenameUtils.getExtension(file.getOriginalFilename())
                    .equals(Constants.Extensions.XLSX)) {
                workbook = new XSSFWorkbook(is);
            } else if (FilenameUtils.getExtension(file.getOriginalFilename())
                    .equals(Constants.Extensions.XLS)) {
                workbook = new HSSFWorkbook(is);
            } else {
                throw new IllegalStateException("Данный парсер ещё не реализован/неизвестный парсер");
            }

            Sheet sheet = workbook.getSheetAt(0);
            List<Object> entities = new ArrayList<>();
            Set<Integer> errorRows = new HashSet<>();

            switch (type) {
                case "station":
                    return parseStationXlsx(sheet, entities, errorRows);
                default:
                    throw new IllegalStateException("Данный парсер ещё не реализован/неизвестный парсер");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка при обработке XLSX файла для остановок " +
                    file.getName());
        }
    }

    public ResponseEntity<?> parseStationXlsx(Sheet sheet,
                                              List<Object> stations,
                                              Set<Integer> errorRows) {
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

        List<String> savedNames = stationService
                .findAlreadySavedStationNames(stations.stream()
                        .map(e -> (Station) e)
                        .map(Station::getName).collect(Collectors.toList()));

        stations = stations.stream()
                .filter(e -> !savedNames.contains(((Station) e).getName()))
                .collect(Collectors.toList());

        int savedCount = stationService.saveAllStations(stations.stream()
                .map(e -> (Station) e).collect(Collectors.toList()));
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
    }

}
