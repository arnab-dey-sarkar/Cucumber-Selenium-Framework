package framework.stepdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import framework.utilities.AbstractCucumberTestNG;

public class DefaultStepDefinition extends AbstractCucumberTestNG {
	private static String s = new String();// sheetname
	private static String s1 = new String();// workbookPath
	private static Map<String, String> map = new HashMap<>();
	private static Scenario scenario;

	@Given("A WorkBook Named \"([^\"]*)\" with SheetName \"([^\"]*)\" is Loaded")
	public void setWorkBookConfiguration(String workbookName, String sheetName) {
		s = sheetName;
		s1 = "./src/test/resources/data/"+ workbookName+".xls";
	}
	@Before
	public void Scenario(Scenario scenario) {
		DefaultStepDefinition.scenario = scenario;
	}

	public static String getCellData(String columnname) throws IOException {
		String testcasename=scenario.getName();
		FileInputStream fis;
		int k = 0;
		try {
			fis = new FileInputStream(s1);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet ws = wb.getSheet(s);
			int rows = ws.getPhysicalNumberOfRows();
			for (int i = 0; i < rows; i++) {
				int cols = ws.getRow(0).getPhysicalNumberOfCells();
				for (int j = 0; j < cols; j++) {
					if (ws.getRow(i).getCell(j, Row.CREATE_NULL_AS_BLANK).toString().replace(".0", "")
							.equalsIgnoreCase(columnname)) {
						k = j;
					}
					map.put(ws.getRow(i).getCell(0, Row.CREATE_NULL_AS_BLANK).toString().replace(".0", ""),
							ws.getRow(i).getCell(k, Row.CREATE_NULL_AS_BLANK).toString().replace(".0", ""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map.get(testcasename);
	}
}