package Config.General;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Viral on 11/26/2016.
 */
public class ExcelFileConfig {


    PropertiesConfig prop = new PropertiesConfig();
    
    /**
     * Read Entered Sheet All Data.
     *
     * @param SheetNo Enter Your Selected Sheet number For Read Your Data.
     * @return Return Sheet All Data In String Array.
     */
    public ArrayList<String> readExcelSheetAllData(int SheetNo) {

        String ReadExalFilePath = (String) prop.getValue("general", "ReadExalFilePath");

        ArrayList<String> sheetData = new ArrayList<String>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(ReadExalFilePath));

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(SheetNo);
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    DataFormatter formatter = new DataFormatter();

                    String cellData = formatter.formatCellValue(cell);

                    sheetData.add(cellData);
                }
            }
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return sheetData;
    }

    /**
     * Read Only Enter Row Data.
     *
     * @param SheetNo Enter Your Selected Sheet number For Read Your Data.
     * @param RowNo   Enter Your Selected Row Number.
     * @return Return Selected Row Data In String Array.
     */
    public ArrayList<String> readSelectedRow(int SheetNo, int RowNo) {

        String ReadExalFilePath = (String) prop.getValue("general", "ReadExalFilePath");

        ArrayList<String> sheetData = new ArrayList<String>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(ReadExalFilePath));

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(SheetNo);
            Iterator<Row> iterator = firstSheet.iterator();


            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == RowNo) {

                    Iterator<Cell> cellIterator = nextRow.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        DataFormatter formatter = new DataFormatter();

                        String CellData = formatter.formatCellValue(cell);

                        sheetData.add(CellData);
                    }
                }

            }
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return sheetData;
    }

    /**
     * Read Only Enter Column Data.
     *
     * @param SheetNo  Enter Your Selected Sheet number For Read Your Data.
     * @param ColumnNo Enter Your Selected Column Number.
     * @return Return Selected Column Data In String Array.
     */
    public ArrayList<String> readselectColumn(int SheetNo, int ColumnNo) {

        String ReadExalFilePath = (String) prop.getValue("general", "ReadExalFilePath");

        ArrayList<String> SheetData = new ArrayList<String>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(ReadExalFilePath));

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(SheetNo);
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cell.getColumnIndex() == ColumnNo) {
                        DataFormatter formatter = new DataFormatter();

                        String CellData = formatter.formatCellValue(cell);

                        SheetData.add(CellData);
                    }
                }
            }
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return SheetData;
    }

    /**
     * Read Only Single Cell.
     *
     * @param SheetNo  Enter Your Selected Sheet number For Read Your Data.
     * @param RowNo    Enter Your Selected Row Number.
     * @param ColumnNo Enter Your Selected Column Number.
     * @return Selected Cell Value In String.
     */
    public String readSelectedCell(int SheetNo, int RowNo, int ColumnNo) {

        String ReadExalFilePath = (String) prop.getValue("general", "ReadExalFilePath");

        String CellValue = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File(ReadExalFilePath));

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(SheetNo);
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();

                if (nextRow.getRowNum() == RowNo) {
                    Iterator<Cell> cellIterator = nextRow.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        if (cell.getColumnIndex() == ColumnNo) {
                            DataFormatter formatter = new DataFormatter();

                            CellValue = formatter.formatCellValue(cell);

                        }
                    }
                }
            }
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return CellValue;
    }

    /**
     * Write String Data On Selected Cell.
     *
     * @param SheetNumber Enter Your Selected Sheet number For Read Your Data.
     * @param Row         Enter Your Selected Row Number.
     * @param Column      Enter Your Selected Column Number.
     * @param Text        Enter String Text.
     */
    public void writeStringDataOnCell(int SheetNumber, int Row, int Column, String Text) {

        String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

        try {
            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            // Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Cell cell = null;

            cell = sheet.getRow(Row).createCell(Column);

            cell.setCellValue(Text);

            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write Integer Data On Selected Cell.
     *
     * @param SheetNumber Enter Your Selected Sheet number For Read Your Data.
     * @param Row         Enter Your Selected Row Number.
     * @param Column      Enter Your Selected Column Number.
     * @param Number      Enter Numeric Number.
     */
    public void writeIntDataOnCell(int SheetNumber, int Row, int Column, int Number) {
        try {

            String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            // Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Cell cell = null;

            cell = sheet.getRow(Row).createCell(Column);

            cell.setCellValue(Number);

            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write Float Data On Selected Cell.
     *
     * @param SheetNumber Enter Your Selected Sheet number For Read Your Data.
     * @param Row         Enter Your Selected Row Number.
     * @param Column      Enter Your Selected Column Number.
     * @param FloatNumber Enter Float Value.
     */
    public void writeIntDataOnCell(int SheetNumber, int Row, int Column, float FloatNumber) {

        String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

        try {
            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            // Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Cell cell = null;

            cell = sheet.getRow(Row).createCell(Column);

            cell.setCellValue(FloatNumber);

            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write Boolean Data On Selected Cell.
     *
     * @param SheetNumber  Enter Your Selected Sheet number For Read Your Data.
     * @param Row          Enter Your Selected Row Number.
     * @param Column       Enter Your Selected Column Number.
     * @param BooleanValue Enter Boolean Value.
     */
    public void writeBooleanDataOnCell(int SheetNumber, int Row, int Column, Boolean BooleanValue) {

        String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

        try {
            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            // Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            // Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Cell cell = null;

            cell = sheet.getRow(Row).createCell(Column);

            cell.setCellValue(BooleanValue);

            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write String Data On Multiple Cell.
     * Enter Stating Row Number For Start Writing On That Row.
     * Enter Stating and Ending Cell Number.
     *
     * @param SheetNumber Enter Your Selected Sheet number For Read Your Data.
     * @param StartRow    Enter Your Selected Start Row Number.
     * @param StartColumn Enter Your Selected Start Column Number.
     * @param EndCell     Enter Your Selected End Column Number.
     * @param Text        Enter String Text.
     */
    public void writeStringDataOnMultipalCell(int SheetNumber, int StartRow, int StartColumn, int EndCell, String Text) {

        String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

        try {

            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            HSSFWorkbook workbook = new HSSFWorkbook(file);

            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                if (row.getRowNum() >= StartRow) {

                    int row1 = row.getRowNum();

                    Cell cell = null;

                    for (int i = StartColumn; i <= EndCell; i++) {
                        cell = sheet.getRow(row1).createCell(i);

                        cell.setCellValue(Text);

                    }
                }

            }
            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write Integer Data On Multiple Cell.
     * Enter Stating Row Number For Start Writing On That Row.
     * Enter Stating and Ending Cell Number.
     *
     * @param SheetNumber   Enter Your Selected Sheet number For Read Your Data.
     * @param StartRow      Enter Your Selected Start Row Number.
     * @param StartColumn   Enter Your Selected Start Column Number.
     * @param EndCell       Enter Your Selected End Column Number.
     * @param IntegerNumber Enter Numeric Number.
     */
    public void writeIntDataOnMultipalCell(int SheetNumber, int StartRow, int StartColumn, int EndCell, int IntegerNumber) {

        String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

        try {

            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            HSSFWorkbook workbook = new HSSFWorkbook(file);

            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                if (row.getRowNum() >= StartRow) {

                    int row1 = row.getRowNum();

                    Cell cell = null;

                    for (int i = StartColumn; i <= EndCell; i++) {
                        cell = sheet.getRow(row1).createCell(i);

                        cell.setCellValue(IntegerNumber);

                    }
                }

            }
            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write Float Data On Multiple Cell.
     * Enter Stating Row Number For Start Writing On That Row.
     * Enter Stating and Ending Cell Number.
     *
     * @param SheetNumber Enter Your Selected Sheet number For Read Your Data.
     * @param StartRow    Enter Your Selected Start Row Number.
     * @param StartColumn Enter Your Selected Start Column Number.
     * @param EndCell     Enter Your Selected End Column Number.
     * @param FloatNumber Enter Float Number.
     */
    public void writeFloatDataOnMultipalCell(int SheetNumber, int StartRow, int StartColumn, int EndCell, float FloatNumber) {

        String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

        try {

            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            HSSFWorkbook workbook = new HSSFWorkbook(file);

            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                if (row.getRowNum() >= StartRow) {

                    int row1 = row.getRowNum();

                    Cell cell = null;

                    for (int i = StartColumn; i <= EndCell; i++) {
                        cell = sheet.getRow(row1).createCell(i);

                        cell.setCellValue(FloatNumber);

                    }
                }

            }
            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write String Data On Multiple Cell.
     * Enter Row Number For Selecting Row in Multiple Cell Enter Text.
     * Enter Stating and Ending Cell Number.
     *
     * @param SheetNumber Enter Your Selected Sheet number For Read Your Data.
     * @param RowNumber   Enter Your Selected Row Number.
     * @param StartColumn Enter Your Selected Start Column Number.
     * @param EndCell     Enter Your Selected End Column Number.
     * @param Text        Enter String Text.
     */
    public void writeStringDataOnSingleRowMultipalCell(int SheetNumber, int RowNumber, int StartColumn, int EndCell, String Text) {

        String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

        try {

            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            HSSFWorkbook workbook = new HSSFWorkbook(file);

            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                if (row.getRowNum() == RowNumber) {

                    int row1 = row.getRowNum();

                    Cell cell = null;

                    for (int i = StartColumn; i <= EndCell; i++) {
                        cell = sheet.getRow(row1).createCell(i);

                        cell.setCellValue(Text);

                    }
                }

            }
            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write Integer Data On Multiple Cell.
     * Enter Row Number For Selecting Row in Multiple Cell Enter Text.
     * Enter Stating and Ending Cell Number.
     *
     * @param SheetNumber   Enter Your Selected Sheet number For Read Your Data.
     * @param RowNumber     Enter Your Selected Row Number.
     * @param StartColumn   Enter Your Selected Start Column Number.
     * @param EndCell       Enter Your Selected End Column Number.
     * @param IntegerNumber Enter Numeric Number.
     */
    public void writeIntDataOnSingleRowMultipalCell(int SheetNumber, int RowNumber, int StartColumn, int EndCell, int IntegerNumber) {

        String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

        try {

            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            HSSFWorkbook workbook = new HSSFWorkbook(file);

            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                if (row.getRowNum() == RowNumber) {

                    int row1 = row.getRowNum();

                    Cell cell = null;

                    for (int i = StartColumn; i <= EndCell; i++) {
                        cell = sheet.getRow(row1).createCell(i);

                        cell.setCellValue(IntegerNumber);

                    }
                }

            }
            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write Float Data On Multiple Cell.
     * Enter Row Number For Selecting Row in Multiple Cell Enter Text.
     * Enter Stating and Ending Cell Number.
     *
     * @param SheetNumber Enter Your Selected Sheet number For Read Your Data.
     * @param RowNumber   Enter Your Selected Row Number.
     * @param StartColumn Enter Your Selected Start Column Number.
     * @param EndCell     Enter Your Selected End Column Number.
     * @param FloatNumber Enter Float Number.
     */
    public void writeFloatDataOnSingleRowMultipalCell(int SheetNumber, int RowNumber, int StartColumn, int EndCell, float FloatNumber) {

        String ReadExalFilePath = (String) prop.getValue("general", "WriteExalFilePath");

        try {

            FileInputStream file = new FileInputStream(new File(ReadExalFilePath).getAbsolutePath());

            HSSFWorkbook workbook = new HSSFWorkbook(file);

            HSSFSheet sheet = workbook.getSheetAt(SheetNumber);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                if (row.getRowNum() == RowNumber) {

                    int row1 = row.getRowNum();

                    Cell cell = null;

                    for (int i = StartColumn; i <= EndCell; i++) {
                        cell = sheet.getRow(row1).createCell(i);

                        cell.setCellValue(FloatNumber);

                    }
                }

            }
            file.close();

            FileOutputStream outFile = new FileOutputStream(new File(ReadExalFilePath));
            workbook.write(outFile);
            outFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
