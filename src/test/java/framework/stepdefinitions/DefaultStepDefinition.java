package framework.stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import framework.utilities.AbstractCucumberTestNG;

public class DefaultStepDefinition extends AbstractCucumberTestNG {
    private static String sheetName = new String();// sheetname
    private static String workBook = new String();// workbookPath
    private static Map<String, HashMap> dataMap = new HashMap<>();
    private static Scenario scenario;

    @Given("A WorkBook Named \"([^\"]*)\" with SheetName \"([^\"]*)\" is Loaded")
    public synchronized void  setWorkBookConfiguration(String workbookName, String sheetName) throws IOException {
        DefaultStepDefinition.sheetName = sheetName;
        workBook = findFile(workbookName, "./src/test/resources/data");
    }

    @Before
    public void Scenario(Scenario scenario) {
        DefaultStepDefinition.scenario = scenario;
    }

    public static synchronized String getCellData(String columnName) throws Exception {
        String testCase = scenario.getName();
        try {
            Fillo fillo = new Fillo();
            Connection connection = fillo.getConnection(workBook);
            String strQuery = "Select * from " + sheetName;
            Recordset tcData = connection.executeQuery(strQuery);
            while (tcData.next()) {
                String tcName = tcData.getField("TestDataID");
                if (tcName == null || tcName.equalsIgnoreCase(""))
                    continue;
                HashMap<String, String> columnMap = new HashMap<String, String>();

                for (String column : tcData.getFieldNames()) {
                    String columnValue = tcData.getField(column);
                    columnMap.put(column, columnValue);
                }
                dataMap.put(tcName, columnMap);
            }
        } catch (Exception e) {

        }
        if (dataMap.containsKey(testCase)) {
            return (String) dataMap.get(testCase).get(columnName);
        }
        else
            throw new Exception("No Testdata Found With Relevance To Scenario Name");
    }

    public static String findFile(String fileName, String searchDirectory) throws IOException {
        String filePath = "";
        File directory = new File(searchDirectory);
        File[] files = directory.listFiles();
        for (File f : files) {
            String fname = f.getName();
            if (fname.split("\\.")[0].equalsIgnoreCase(fileName) && fname.contains("xls") || fname.contains("csv")) {
                filePath = f.getAbsolutePath();
                break;
            }
        }
        return filePath;
    }
}
