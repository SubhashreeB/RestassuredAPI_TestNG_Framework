package api.test;

import api.endpoints.userEndpoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {

    Faker faker;
    User userPayload;

    public Logger logger;

    @BeforeClass
    public void setup(){
        //Generate data using Faker Library
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());//hashcode will generate uniq number
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //Initiate Logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPOSTUser(){
        //calling userEndpoints directly as it is static method
        logger.info("********Creating User*******");
        Response response = userEndpoints2.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

    }
    @Test(priority = 2)
    public void testGETUser(){
        logger.info("********Reading User Info*******");
        Response response=userEndpoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName(){

            //update data using below payloads
        logger.info("********Updating User Info*******");
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = userEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        //Checking data after update
        Response responseAfterUpdate=userEndpoints2.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

        }

        @Test(priority = 4)
        public void testDeleteUserByName(){
        logger.info("********Deleting User*******");
        Response response= userEndpoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("********Deleted User*******");
        }

}
