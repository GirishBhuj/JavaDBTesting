package GBSoft.DatabaseTesting;

import java.util.Hashtable;

public class ExcelFunction {
	
	/*ReadExcel - return two dimensional object array because this function has to be passed in
	Data provider annotation which also takes two dimensional object array*/
	
	//public static Object[][] ReadExcel(String testCase, Xls_Reader xls)
	public static Hashtable<String,String> ReadExcel(String testCase, Xls_Reader xls)
	{
		
		//1. First find out the row number in which test case is present - Send Mail and Save mail
		//Test case starts from the first row
		//Column number starts from 0 and row number starts from 1 - Xls_Reader class file
		int testCaseStartRowNumber = 1;
		//While loop will stop running when its find the test case 
		while(!xls.getCellData("Data", 0, testCaseStartRowNumber).equals(testCase)) {
			testCaseStartRowNumber++;
		}
		System.out.println(testCase+" is present in row number "+testCaseStartRowNumber);
		
		
		//2.To find number of column for each test case
		//ROw number in which column name is present for each test case = testCaseStartRowNumber+1
		int rowNumberfromWhichColumnNameisStarting =  testCaseStartRowNumber+1;
		int startingColumn = 1;
		while(!xls.getCellData("Data", startingColumn, rowNumberfromWhichColumnNameisStarting).equals("")) {
			startingColumn++;
		}
		System.out.println(testCase+" has "+startingColumn+ " columns filled with data");
		
		//3. Need to find how many rows are filled with data for a particular test case.
		//Test data for a particular test case = testCaseStartRowNumber +2
		int rowNumberFromWhichTestDataStarts = testCaseStartRowNumber+2;
		int columnInWhichDataIsPresent = 0;
		int counterRowfilledWithData = 0;//TO count how many times the while loop is run. Assume one row is filled with data
		while(!xls.getCellData("Data",columnInWhichDataIsPresent, rowNumberFromWhichTestDataStarts+counterRowfilledWithData).equals("")) {
			counterRowfilledWithData++;
		}
		System.out.println(testCase+" has "+counterRowfilledWithData+ " rows filled with data");
		
		
		//4. Pick up the data from excel sheet and put it to hashtable . Create a function for it.
		Object obj [][] = new Object[counterRowfilledWithData][1];
		Hashtable<String, String>table = null;
		int rNum;
		int cNum;
		for(rNum = rowNumberFromWhichTestDataStarts; rNum<rowNumberFromWhichTestDataStarts+counterRowfilledWithData; rNum++) {
			table = new Hashtable<String, String>();
			for(cNum = 0; cNum<startingColumn; cNum++) {
				table.put(xls.getCellData("Data", cNum, rowNumberfromWhichColumnNameisStarting), xls.getCellData("Data", cNum, rNum));	
			}
			//Put the hashtable to the Object array
			obj[rNum-rowNumberFromWhichTestDataStarts][0] = table;//rNum = 3, 3-3 = 0, rNum = 4, 4-3 = 1,rNum = 5, 5-3= =3
		}
		return table;
		//return obj;
		
		//return null;		
	}

}
