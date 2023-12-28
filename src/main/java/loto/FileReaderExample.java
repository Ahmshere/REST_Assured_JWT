package loto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.exceptions.CsvException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
// Ya dovbavi kakuyto hren'
public class FileReaderExample {
    private static Map<Integer, Integer> valueCounts = new HashMap<>();
    private static Map<Integer, List<Integer>> valuePositions = new HashMap<>();
    static int endRow = 250;
    public static void main(String[] args) {
        // Укажите путь к файлу
        String filePath = "E:\\Lotto\\Lotto.csv";

        // с какой строки и столбца начинать чтение
        int startRow = 2; // начальная строка. В екселе первая строка имеет индекс 1
        int startColumn = 3; //начальная колонка
        int endColumn = 8; // последняя колонка
        int strongNumberColumn = 9;
        // какой строкой заканчивать чтение


        try {
            if (filePath.endsWith(".csv")) {
                readCSV(filePath, startRow, startColumn, endRow, endColumn, strongNumberColumn);
            } else if (filePath.endsWith(".xlsx")) {
                readExcel(filePath, startRow, startColumn, endRow, strongNumberColumn);
            } else {
                System.out.println("Неподдерживаемый формат файла.");
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        calculateFrequencyForTopNumbers(12); // true/false - выводить ли среднее значение каждого числа.
    }

    private static void readCSV(String filePath, int startRow, int startColumn, int endRow, int endColumn, int strongNumberColumn)
            throws IOException, CsvException {
        Map<Integer, Integer> strongNumberCounts = new HashMap<>();

        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(filePath)))) {
            List<String[]> lines = reader.readAll();

            for (int i = startRow - 1; i < Math.min(endRow, lines.size()); i++) {
                String[] line = lines.get(i);
                for (int j = startColumn - 1; j < Math.min(endColumn, line.length); j++) {
                    if (!line[j].isEmpty()) {
                        int value = Integer.parseInt(line[j]);
                        valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
                    }
                }

                // Обработка strongNumberColumn
                if (strongNumberColumn - 1 < line.length && !line[strongNumberColumn - 1].isEmpty()) {
                    int strongNumber = Integer.parseInt(line[strongNumberColumn - 1]);
                    strongNumberCounts.put(strongNumber, strongNumberCounts.getOrDefault(strongNumber, 0) + 1);
                    valuePositions.computeIfAbsent(strongNumber, k -> new ArrayList<>()).add(i + 1);
                }
            }
        }

        printValueCounts(valueCounts);
        System.out.println("Встречаемость значений в столбце " + strongNumberColumn + " : ");
        printValueCounts(strongNumberCounts);
    }

// test

    private static void readExcel(String filePath, int startRow, int startColumn, int endRow, int strongNumberColumn)
            throws IOException {
        Map<Integer, Integer> strongNumberCounts = new HashMap<>();

        try (Workbook workbook = new XSSFWorkbook(new File(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = startRow - 1; i < Math.min(endRow, sheet.getPhysicalNumberOfRows()); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    for (int j = startColumn - 1; j < row.getPhysicalNumberOfCells(); j++) {
                        Cell cell = row.getCell(j);
                        int value = getNumericCellValue(cell);
                        valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
                    }

                    // Обработка strongNumberColumn
                    Cell strongNumberCell = row.getCell(strongNumberColumn - 1);
                    int strongNumber = getNumericCellValue(strongNumberCell);
                    strongNumberCounts.put(strongNumber, strongNumberCounts.getOrDefault(strongNumber, 0) + 1);
                    valuePositions.computeIfAbsent(strongNumber, k -> new ArrayList<>()).add(i + 1);
                }
            }
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

        printValueCounts(valueCounts);
        System.out.println("Встречаемость значений в столбце " + strongNumberColumn + " : ");
        printValueCounts(strongNumberCounts);
    }
    private static int getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0;
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            default:
                return 0;
        }
    }

    private static void printValueCounts(Map<Integer, Integer> valueCounts) {
        System.out.println("Встречаемость значений : ");
        valueCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue() + " раз"));
    }
    private static void calculateFrequencyForTopNumbers(int topCount) {
        List<Map.Entry<Integer, List<Integer>>> sortedEntries = new ArrayList<>(valuePositions.entrySet());
        sortedEntries.sort(Comparator.comparingInt(entry -> entry.getValue().size()));

        System.out.println("Отношение количества дней к вхождениям для первых " + topCount + " чисел: ");
        for (Map.Entry<Integer, List<Integer>> entry : sortedEntries.subList(0, Math.min(topCount, sortedEntries.size()))) {
            List<Integer> positions = entry.getValue();
            if (positions.size() > 0) {
                double ratio = (double) endRow / positions.size();
                System.out.println(entry.getKey() + " : " + ratio);
            } else {
                System.out.println(entry.getKey() + " : Недостаточно данных для вычисления");
            }
        }
    }




}
