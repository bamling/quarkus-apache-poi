package com.github.bamling.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@ApplicationScoped
public class ExcelService {

    public ByteArrayInputStream createExcelSheet() {
        final XSSFWorkbook workbook = new XSSFWorkbook();
        final XSSFSheet sheet = workbook.createSheet("Java Books");

        final Object[][] bookData = {
            {"Head First Java", "Kathy Serria", 79},
            {"Effective Java", "Joshua Bloch", 36},
            {"Clean Code", "Robert martin", 42},
            {"Thinking in Java", "Bruce Eckel", 35},
        };

        int rowCount = 0;

        for (final Object[] bookFields : bookData) {
            final Row row = sheet.createRow(rowCount++);

            int columnCount = 0;

            for (final Object field : bookFields) {
                final Cell cell = row.createCell(columnCount++);

                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            workbook.write(output);

            output.close();
            workbook.close();

            return new ByteArrayInputStream(output.toByteArray());
        } catch (final IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Report generation issue");
        }
    }

}
