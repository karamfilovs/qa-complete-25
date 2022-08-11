package api;

import api.dto.Item;
import api.dto.ItemsResp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

//This class will contain all the HTTP requests related to items endpoint
public class ItemAPI {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String ITEMS_URL = "/items";
    private String token;


    public ItemAPI(String token) {
        this.token = token;
    }

    public RequestSpecification baseRequest() {
        RestAssured.baseURI = "https://api.inv.bg"; //Default value is localhost
        RestAssured.basePath = "/v3"; //Default value /
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .auth().oauth2(token);
    }

    public Response getAllItems() {
        return baseRequest().when()
                .get(ITEMS_URL)
                .prettyPeek();
    }

    public void deleteAllItems() {
        //Get all item
        Response getAllItems = getAllItems();
        ItemsResp itemsResp = GSON.fromJson(getAllItems.body().asString(), ItemsResp.class);
        //Extract al item ids
        List<Item> items = itemsResp.getItems();
        //Delete items one by one
        items.forEach(item -> deleteItem(item.getId()));

    }

    public Response deleteItem(int id) {
        return baseRequest()
                .when()
                .delete(ITEMS_URL + "/" + id)
                .prettyPeek();
    }

    public Response createItem(Item item) {
        return baseRequest()
                .body(GSON.toJson(item))
                .when()
                .post(ITEMS_URL)
                .prettyPeek();
    }


}
