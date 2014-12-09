package coded.others;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*
public class ExcelClass {

    public static void main(String[] args) {
        Sheet S = new Sheet();
        S.Import("book1.xlsx","book1`");
        S.export("book1", "output.xlsx");
        //S.importMethodRough("book1.xlsx");                                   //Call whatever method here
        
    }
}
*/


/**
 *
 * @author Dilushan
 */
public class ExcelClass {

    public void export(String database, String output) {
        Connection con;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String tableName;

        try {
            try (FileOutputStream fileOut = new FileOutputStream(output)) {
                XSSFWorkbook workbook = new XSSFWorkbook();

                Class.forName("com.mysql.jdbc.Driver");                                                                             // this will load the MySQL driver, each DB has its own driver
                // setup the connection with the DB.
                con = DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?" + "user=root");         // IF there is a password then  "user=root&password=1234"
                DatabaseMetaData DBmetadata = con.getMetaData();                                                                //getMetaData method returns a object that include important info about database
                ResultSet rs = DBmetadata.getTables(null, null, "%", null);                                                     //getting table names. 3rd column of the rs has table names
                XSSFRow row;                                                                                                                    //resultset is a table include results.
                XSSFCell cell;

                //while loop run for seperate sheets
                while (rs.next()) {                             //this while loop for creating sheets in the xlsx file

                    preparedStatement = con.prepareStatement("select * from " + rs.getString(3));
                    XSSFSheet worksheet = workbook.createSheet(rs.getString(3));
                    resultSet = preparedStatement.executeQuery();
                    ResultSetMetaData metadata = resultSet.getMetaData();

                    boolean resultsEmpty = true;
                    int rowNum = 1;

                    row = worksheet.createRow(0);
                    for (int i = 1; i < metadata.getColumnCount() + 1; i++) {                           //for loop for the set column names
                        cell = row.createCell(i - 1);
                        cell.setCellValue(metadata.getColumnName(i));
                        if (metadata.getColumnType(i) == java.sql.Types.DOUBLE) {
                            worksheet.setColumnWidth(i - 1, 12 * 256);
                        } else {
                            worksheet.setColumnWidth(i - 1, 25 * 256);                                //Better if added different column width for the formula cells
                        }
                    }

                    while (resultSet.next()) {                                                      // loop for  inserting rows to the sheet
                        row = worksheet.createRow(rowNum++);

                        for (int i = 1; i < metadata.getColumnCount() + 1; i++) {       //for loop for inserting cells in a row
                            switch (metadata.getColumnType(i)) {
                                case java.sql.Types.DATE:
                                    cell = row.createCell(i - 1, Cell.CELL_TYPE_STRING);          //not sure date should go to the string cell. If so this case not needed
                                    cell.setCellValue(resultSet.getDate(i));
                                    break;
                                case java.sql.Types.DOUBLE:
                                    cell = row.createCell(i - 1, Cell.CELL_TYPE_NUMERIC);
                                    cell.setCellValue(resultSet.getDouble(i));
                                    break;
                                case java.sql.Types.BOOLEAN:
                                    cell = row.createCell(i - 1, Cell.CELL_TYPE_BOOLEAN);
                                    cell.setCellValue(resultSet.getBoolean(i));
                                    break;
                                default:
                                    if (resultSet.getString(i).substring(0, 1).equalsIgnoreCase("=")) {         //check whether a formula or not and input string to cell
                                        cell = row.createCell(i - 1, Cell.CELL_TYPE_FORMULA);
                                        cell.setCellFormula(resultSet.getString(i).substring(1));
                                    } else {
                                        cell = row.createCell(i - 1, Cell.CELL_TYPE_STRING);
                                        cell.setCellValue(resultSet.getString(i));
                                    }
                            }
                            //System.out.print(resultSet.getString(i) + "\t");
                        }
                        //System.out.println("");
                        resultsEmpty = false;
                    }

                    if (resultsEmpty) {
                        //System.out.println("No record found for given table");
                    }
                }

                workbook.write(fileOut);                                                            //write ti the file
                fileOut.flush();
            }
            con.close();
           
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            //ex.printStackTrace();
        }
    }

    //Import method from a formal excel sheet with a table
    public void Import(String input, String database) {
        
        try {
            FileInputStream fileInputStream = new FileInputStream(input);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet;

            MySQLConnectionClass.getInstance().updateStatement("create database if not exists " + database);
            MySQLConnectionClass.getInstance().updateStatement("use " + database);
            
            //MySQLConnection.getInstance().updateStatement("create database if not exists " + input.split("\\.")[0].replaceAll("\\s", "_"));
           // MySQLConnection.getInstance().updateStatement("use " + input.split("\\.")[0].replaceAll("\\s", "_"));

            int index = 0;
            boolean first;
            String insert = "insert into " + workbook.getSheetName(0).replaceAll("\\s", "_") + " values (", createD;

            //Should fix issue when empty xlsx inputted
            do {
                worksheet = workbook.getSheetAt(index++);
                createD = "create table if not exists " + workbook.getSheetName(index - 1).replaceAll("\\s", "_") + " (";
                first = true;

                for (Row row : worksheet) {
                    for (Cell cell : row) {
                        if (first == true) {

                            switch (worksheet.getRow(1).getCell(cell.getColumnIndex()).getCellType()) {                                               //getting celltype of the top row cells. Could be errorness because they are usually strings 
                                case Cell.CELL_TYPE_NUMERIC:
                                    if (DateUtil.isCellDateFormatted(worksheet.getRow(1).getCell(cell.getColumnIndex()))) {
                                        createD += cell.getStringCellValue().replaceAll("[/\\\\\\s\\.]", "_") + " DATE,";                                   //replace all take regex values. Here replaced /,\,whitespace characters
                                    } else {
                                        createD += cell.getStringCellValue().replaceAll("[/\\\\\\s\\.]", "_") + " DOUBLE,";
                                    }
                                    break;
                                default:
                                    createD += cell.getStringCellValue().replaceAll("[/\\\\\\s\\.]", "_") + " VARCHAR(200),";
                            }

                        } else {

                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        insert += cell.getDateCellValue().toString() + "' , '";
                                    } else {
                                        insert += Double.toString(cell.getNumericCellValue()) + "' , '";
                                    }
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                                    insert += "=" + cell.getCellFormula() + "' , '";
                                    break;
                                default:
                                    insert += cell.getStringCellValue() + "' , '";
                            }
                        }
                        //System.out.print(cell.getStringCellValue() + " ");
                    }

                    if (first == true) {
                        createD = createD.substring(0, createD.length() - 1) + ")";
                        //System.out.println("\n" + createD);
                        MySQLConnectionClass.getInstance().updateStatement(createD);
                        insert = "insert into " + workbook.getSheetName(index - 1).replaceAll("[/\\\\\\s\\.]", "_") + " values ('";

                    } else {
                        insert = insert.substring(0, insert.length() - 4) + ")";
                        // System.out.println(insert);
                        MySQLConnectionClass.getInstance().updateStatement(insert);
                        insert = "insert into " + workbook.getSheetName(index - 1).replaceAll("[/\\\\\\s\\.]", "_") + " values ('";
                    }

                    //System.out.println("");
                    first = false;
                }
                //System.out.println("");

            } while (index < workbook.getNumberOfSheets());

        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }

    }

    //Method for rough extraction
    /* 
     public void importMethodRough(String input) {

     Connection con;
     Statement statement;
     ResultSet resultSet;

     try {
     FileInputStream fileInputStream = new FileInputStream(input);
     XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
     XSSFSheet worksheet;
     Class.forName("com.mysql.jdbc.Driver");                                                                             // this will load the MySQL driver, each DB has its own driver
     con = DriverManager.getConnection("jdbc:mysql://localhost/?user=root");         // IF there is a password then  "user=root&password=1234"
     statement = con.createStatement();
     statement.executeUpdate("create database if not exists " + input.split("\\.")[0].replaceAll("\\s", "_"));
     statement.executeUpdate("use " + input.split("\\.")[0].replaceAll("\\s", "_"));

     int index = 0;
     boolean first;
     String insert = "insert into " + workbook.getSheetName(0).replaceAll("\\s", "_") + " values (", createD;

     //Should fix issue when empty xlsx inputted
     do {
     worksheet = workbook.getSheetAt(index++);
     createD = "create table if not exists " + workbook.getSheetName(index - 1).replaceAll("\\s", "_") + " (";
     first = true;
     int ColmnNum = 0;
     Cell cell;

     for (Row row : worksheet) {
     if (first == true) {
     ColmnNum = row.getLastCellNum();
     }

     for (int i = 0; i < ColmnNum; i++) {

     cell = row.getCell(i);
     try {
     if(cell.getCellType()==Cell.CELL_TYPE_BLANK||cell.getCellType()==Cell.CELL_TYPE_ERROR){
     insert += "NULL"+"' , '";
     continue;
     }
     } catch (Exception e) {
     insert += "NULL"+"' , '";
     continue;
     }
     if (first == true) {
     createD += cell.getStringCellValue().replaceAll("[/\\\\\\s\\.]", "_") + " VARCHAR(200),";     //replace all take regex values. Here replaced /,\,whitespace characters
     } else {
     switch (cell.getCellType()) {
     case Cell.CELL_TYPE_NUMERIC:
     if (DateUtil.isCellDateFormatted(cell)) {
     insert += cell.getDateCellValue().toString() + "' , '";
     } else {
     insert += Double.toString(cell.getNumericCellValue()) + "' , '";
     }
     break;
     case Cell.CELL_TYPE_FORMULA:
     insert += "="+cell.getCellFormula() + "' , '";
     break;
     default:
     insert += cell.getStringCellValue() + "' , '";
     }
     }
     //System.out.print(cell.getStringCellValue() + " ");

     }

     if (first == true) {
     createD = createD.substring(0, createD.length() - 1) + ")";
     System.out.println("\n" + createD);
     statement.executeUpdate(createD);
     insert = "insert into " + workbook.getSheetName(index - 1).replaceAll("[/\\\\\\s\\.]", "_") + " values ('";
     } else {
     insert = insert.substring(0, insert.length() - 4) + ")";
     System.out.println(insert);
     statement.executeUpdate(insert);
     insert = "insert into " + workbook.getSheetName(index - 1).replaceAll("[/\\\\\\s\\.]", "_") + " values ('";
     }

     //System.out.println("");
     first = false;
     }
     //System.out.println("");

     } while (index < workbook.getNumberOfSheets());

     } catch (IOException | SQLException | ClassNotFoundException e) {
     e.printStackTrace();
     }

     }
     */
}
