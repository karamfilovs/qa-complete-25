package api;

import api.dto.LoginCredentials;
import api.dto.LoginResp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import javax.print.DocFlavor;

public class LoginAPI {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String BASE_URI = "https://api.inv.bg";
    private static final String BASE_PATH = "/v3";
    private static final String LOGIN_URI = "/login/token";

    public static String getBearerToken(String email, String password, String domain){
        LoginCredentials credentials = new LoginCredentials(email, password, domain);
        RestAssured.baseURI = BASE_URI; //Sets the base uri
        RestAssured.basePath = BASE_PATH; //Sets the base path
        Response loginResp = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(GSON.toJson(credentials))
                .when()
                .post(LOGIN_URI)
                .prettyPeek();
        String body = loginResp.body().asString();
        return GSON.fromJson(body, LoginResp.class).token;
    }

}
