package api;

import api.dto.Item;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.BaseTest;

public class ItemAPITest extends BaseTest {
    ItemAPI itemAPI = new ItemAPI(TOKEN);

    @Test
    @Tag("api")
    @DisplayName("Can get all items")
    void canGetAllItems() {
        Response getAllResp = itemAPI.getAllItems();
        Assertions.assertEquals(200, getAllResp.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can create item")
    void canCreateItem() {
        Item beer = Item.builder()
                .name("Coffee")
                .currency("EUR")
                .price(20.50)
                .quantity_unit("kg.")
                .build();
        Response createResp = itemAPI.createItem(beer);
        Assertions.assertEquals(201, createResp.statusCode());
    }

    @Test
    @DisplayName("Can delete item")
    @Tag("api")
    void canDeleteItem(){
        Response deleteResp = itemAPI.deleteItem(7158);
        itemAPI.getAllItems();
    }

    @Test
    void canDeleteAllItems(){
        itemAPI.deleteAllItems();
    }
}
