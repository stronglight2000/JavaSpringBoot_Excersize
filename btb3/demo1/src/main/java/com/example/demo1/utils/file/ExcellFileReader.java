package com.example.demo1.utils.file;

import com.example.demo1.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Slf4j
@Component("excelFileReader")
public class ExcellFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String filePath) {
        log.info("Reading Excel file: {}", filePath);
        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            List<Book> books = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Bỏ qua dòng tiêu đề

                Book book = new Book();

                // Đọc ID (nếu là số, chuyển sang chuỗi)
                Cell idCell = row.getCell(0);
                if (idCell != null) {
                    if (idCell.getCellType() == CellType.NUMERIC) {
                        book.setId(String.valueOf((int) idCell.getNumericCellValue()));
                    } else {
                        book.setId(idCell.getStringCellValue());
                    }
                }

                // Đọc Title
                Cell titleCell = row.getCell(1);
                book.setTitle(titleCell != null ? titleCell.getStringCellValue() : "");

                // Đọc Author
                Cell authorCell = row.getCell(2);
                book.setAuthor(authorCell != null ? authorCell.getStringCellValue() : "");

                // Đọc Year (nếu là số)
                Cell yearCell = row.getCell(3);
                if (yearCell != null) {
                    if (yearCell.getCellType() == CellType.NUMERIC) {
                        book.setYear((int) yearCell.getNumericCellValue());
                    } else {
                        log.warn("Invalid year format in row {}: {}", row.getRowNum(), yearCell);
                        book.setYear(0); // Mặc định 0 nếu lỗi
                    }
                }

                books.add(book);
            }
            return books;
        } catch (IOException e) {
            log.error("Error reading Excel file: {}", filePath, e);
            return Collections.emptyList();
        }
    }
}
