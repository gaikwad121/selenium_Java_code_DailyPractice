package pages;
import base.BaseTest;
import utils.ExtentManager;
import utils.TestListener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.util.ArrayList;
import java.util.List;
@Listeners({TestListener.class})

public class DynamicWebTablesHandle extends BaseTest {

@Test

void staticWebElement() {
    ExtentTest test = ExtentManager.getInstance().createTest("staticWebElement");

driver.get("https://testautomationpractice.blogspot.com/");
// Find total number of row in a table
/*List<WebElement> rowList=driver.findElements(By.xpath("//table[@name='BookTable']//tr")); // for multiple table
System.out.println(rowList.size());
test.log(Status.INFO, "The size of a row is "+rowList.size());
for(int i=0;i<rowList.size();i++) {
	System.out.println(rowList.get(i).getText());
    test.log(Status.INFO, "It will print all the rowListin table");
   List<WebElement> tagNames=driver.findElements(By.tagName("tr"));
   System.out.println(tagNames.size());
   for(WebElement list:tagNames) {
   System.out.println(list.getText());
   test.log(Status.INFO, "It will print all the matching tagName 'tr' in table");*/
   
/****int row=driver.findElements(By.xpath("//table[@name='BookTable']//tr")).size(); // this is for a single table in the Webpage
//System.out.println(row);
int coloumNums=driver.findElements(By.xpath("//table[@name='BookTable']//th")).size();// multiple coloum
//System.out.println(coloumNums);
//int columN=driver.findElements(By.tagName("th")).size();
//System.out.println(columN);
//WebElement text=driver.findElement(By.xpath("//table[@name=\"BookTable\"]//tr[5]//td[1]")); // read data from row 5 and coloum 1
//System.out.println("print the single value form table : "+text.getText());
//WebElement text2=driver.findElement(By.xpath("//table[@name=\"BookTable\"]//tr[7]//td[3]")); // read data from row 7 and coloum 3
//System.out.println(text2.getText());
// read a data from row and coloum
List<WebElement> header=driver.findElements(By.xpath("//table[@name='BookTable']//th"));
for(WebElement tx:header)
	System.out.print(tx.getText()+" ");

for(int i=2;i<=row;i++) {
for(int j=1;j<=coloumNums;j++) {
WebElement txt=driver.findElement(By.xpath("//table[@name='BookTable']//tr[" + i + "]//td[" + j +"]"));
//System.out.println(txt.getText());
	System.out.print(txt.getText()+" | "+"  ");
}
System.out.println();
}}

}****/
//driver.get("https://testautomationpractice.blogspot.com/");

/*Find number of rows
int rowCount = driver.findElements(By.xpath("//table[@name='BookTable']//tr")).size();
System.out.println("Total rows: " + rowCount);

//Find number of columns
int colCount = driver.findElements(By.xpath("//table[@name='BookTable']//tr[1]/th")).size();
System.out.println("Total columns: " + colCount);

//Read all data from table
for (int i = 2; i <= rowCount; i++) { // start from 2 to skip header row
 for (int j = 1; j <= colCount; j++) {
     WebElement cell = driver.findElement(
         By.xpath("//table[@name='BookTable']//tr[" + i + "]//td[" + j + "]")
     );
     System.out.print(cell.getText() + "   ");
 }
 System.out.println();
}
}}*/




//Get headers
List<WebElement> headers = driver.findElements(By.xpath("//table[@name='BookTable']//th"));

//Get rows
List<WebElement> rows = driver.findElements(By.xpath("//table[@name='BookTable']//tr"));

//Store table in 2D list
List<List<String>> tableData = new ArrayList<>();

//Add headers first
List<String> headerTexts = new ArrayList<>();
for (WebElement head : headers) {
 headerTexts.add(head.getText());
}
tableData.add(headerTexts);

//Add row data
for (int i = 1; i < rows.size(); i++) { // skip header row
 List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
 List<String> rowData = new ArrayList<>();

 for (int j = 0; j < cells.size(); j++) {
     String xpath = "//table[@name='BookTable']//tr[" + (i+1) + "]//td[" + (j+1) + "]";
     String cellText = cells.get(j).getText();

     // 👇 Print the xpath being created
     System.out.println(xpath + " --> " + cellText);

     rowData.add(cellText);
 }
 tableData.add(rowData);
}

//Find max width for each column
int[] colWidths = new int[tableData.get(0).size()];
for (List<String> row : tableData) {
 for (int i = 0; i < row.size(); i++) {
     colWidths[i] = Math.max(colWidths[i], row.get(i).length());
 }
}

//Print table with proper alignment
System.out.println("\nFormatted Table:\n");
for (List<String> row : tableData) {
 for (int i = 0; i < row.size(); i++) {
     System.out.printf("%-" + (colWidths[i] + 3) + "s", row.get(i));
 }
 System.out.println();
}
}}