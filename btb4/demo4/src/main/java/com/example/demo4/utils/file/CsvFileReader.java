package com.example.demo4.utils.file;

import com.example.demo4.model.People;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component("csvFileReader")
public class CsvFileReader implements IFileReader {
    private static final DateTimeFormatter CSV_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter TARGET_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<People> readFile(String filePath) {
        List<People> peopleList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // Để bỏ qua dòng tiêu đề

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");

                if (values.length < 7) {
                    continue; // Bỏ qua dòng không đủ dữ liệu
                }

                try {
                    People person = People.builder()
                            .id(Integer.parseInt(values[0].trim()))
                            .fullname(values[1].trim())
                            .job(values[2].trim())
                            .gender(values[3].trim())
                            .city(values[4].trim())
                            .salary(Integer.parseInt(values[5].trim()))
                            .birthday(convertDate(values[6].trim()))
                            .build();

                    peopleList.add(person);
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.err.println("Lỗi khi chuyển đổi dữ liệu: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }

        return peopleList;
    }

    private LocalDate convertDate(String dateStr) {
        return LocalDate.parse(dateStr, CSV_DATE_FORMAT);
    }
}

