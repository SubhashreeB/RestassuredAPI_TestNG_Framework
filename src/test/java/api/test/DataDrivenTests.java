package api.test;

import api.endpoints.userEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTests {
    /**
     * 1. Pass entire data from xl sheet one by one - POST
     * 2. Pass only Username from utility and Delete
     */

    @Test(priority =1, dataProvider="Data", dataProviderClass = DataProviders.class)
    public void testPOSTUser(String userID, String userName, String fName, String lName, String userEmail, String pwd, String ph){

        User userPayload = new User();
        //Capture data from xl sheet
        userPayload.setId(Integer.parseInt(userID));//hashcode will generate uniq number
        userPayload.setUsername(userName);
        userPayload.setFirstname(fName);
        userPayload.setLastname(lName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(ph);

        Response response = userEndpoints.createUser(userPayload);

        Assert.assertEquals(response.getStatusCode(), 200);

    }
    //Delete the user created above

    @Test(priority =2, dataProvider="UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName){

        Response response = userEndpoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);

    }


}
