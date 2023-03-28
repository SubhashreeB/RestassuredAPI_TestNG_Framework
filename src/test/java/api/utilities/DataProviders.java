package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="Data")
    public Object[][] getAllData() throws IOException {
        //provide xlsx path
        String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
        //pass the path to the utility function
        XLUtility xl = new XLUtility(path);

        //Read total row count from Sheet1
        int totalRows = xl.getRowCount("Sheet1");
        //Get total Column Count using sheet1 from row 1
        int totalCols = xl.getCellCount("Sheet1", 1);

        String apiData[][]=new String[totalRows][totalCols];

        for(int i=1; i<=totalRows; i++) //skip header and start from row so i=1
        {
            for (int j=0; j<totalCols; j++)
            {
                apiData[i-1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return(apiData);
    }

    @DataProvider(name="UserNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");

        String apiData[] = new String[rowNum];

        for(int i =1; i<rowNum; i++)
        {
            apiData[i-1] = xl.getCellData("Sheet1", i, 1);
        }
        return(apiData);

    }
}
