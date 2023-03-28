package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
//Created for CRUD operations on user API

public class userEndpoints2 {

    static ResourceBundle getUrl(){
        //Load the property file from resouces folder
         ResourceBundle routes=ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(User payload){
        String post_url = getUrl().getString("post_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(post_url);//post_url is Static variable so directly we can access
        return response;
    }

    public static Response readUser(String userName){
        String get_url = getUrl().getString("get_url");
        Response response= given()
                .pathParam("username",userName)
                .when()
                .get(get_url);
        return response;
    }

    public static Response updateUser(String userName, User payload){
        String put_url = getUrl().getString("put_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(put_url);
        return response;
    }
    public static Response deleteUser(String userName){
        String delete_url = getUrl().getString("delete_url");
        Response response = given()
                .pathParam("username", userName)
                .when()
                .delete(delete_url);
        return response;
    }
}
