package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
//Created for CRUD operations on user API

public class userEndpoints {

    public static Response createUser(User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(Routes.post_url);//post_url is Static variable so directly we can access
        return response;
    }

    public static Response readUser(String userName){
        System.out.println(Routes.get_url);
        Response response= given()
                .pathParam("username",userName)
                .when()
                .get(Routes.get_url);
        return response;
    }

    public static Response updateUser(String userName, User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(Routes.put_url);
        return response;
    }
    public static Response deleteUser(String userName){
        Response response = given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.delete_url);
        return response;
    }
}
