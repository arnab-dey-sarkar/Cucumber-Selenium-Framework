package framework.stepdefinitions;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import framework.utilities.AbstractCucumberTestNG;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultStepDefinition extends AbstractCucumberTestNG {
    private static String sheetName = "";// sheetname
    private static String workBook = "";// workbookPath
    private static final Map<String, HashMap<String, String>> dataMap = new HashMap<>();
    private static Scenario scenario;

    @Given("A WorkBook Named \"([^\"]*)\" with SheetName \"([^\"]*)\" is Loaded")
    public synchronized void setWorkBookConfiguration(String workbookName, String sheetName) {
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
                HashMap<String, String> columnMap = new HashMap<>();

                for (String column : tcData.getFieldNames()) {
                    String columnValue = tcData.getField(column);
                    columnMap.put(column, columnValue);
                }
                dataMap.put(tcName, columnMap);
            }
        } catch (Exception ignored) {

        }
        if (dataMap.containsKey(testCase)) {
            return dataMap.get(testCase).get(columnName);
        } else
            throw new Exception("No Testdata Found With Relevance To Scenario Name");
    }

    public static String findFile(String fileName, String searchDirectory) {
        String filePath = "";
        File directory = new File(searchDirectory);
        Optional<File[]> files = Optional.ofNullable(directory.listFiles());
        if (files.isPresent())
            for (File f : files.get()) {
                String fname = f.getName();
                if (fname.split("\\.")[0].equalsIgnoreCase(fileName) && fname.contains("xls") || fname.contains("csv")) {
                    filePath = f.getAbsolutePath();
                    break;
                }
            }
        return filePath;
    }
}
