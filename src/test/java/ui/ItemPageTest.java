package ui;

import api.ItemAPI;
import api.dto.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ItemPageTest extends BaseTest {
    private static final String NO_ITEMS_MESSAGE = "Все още нямате добавени артикули.";
    private ItemAPI itemAPI = new ItemAPI(TOKEN);

    @Test
    @Tag("items")
    @DisplayName("Can navigate to Item page")
    void canNavigateToItemPage() {
        //Login
        login();
        //Navigate to items page
        WebElement itemsMenuTab = driver.findElement(By.id("tabs_objects"));
        itemsMenuTab.click();
        //Check item page heading
        WebElement itemHeading = driver.findElement(By.xpath("//div[@id='headline2']/h2"));
        Assertions.assertEquals("Артикули", itemHeading.getText());
    }

    @Test
    @DisplayName("Correct message is displayed when no items exist in the system")
    void correctMessageIsDisplayedWhenNoItemsExist() {
        //Login
        login();
        //Navigate to items page via main menu
        WebElement itemsMenuTab = driver.findElement(By.id("tabs_objects"));
        itemsMenuTab.click();
        //Delete all items via API
        itemAPI.deleteAllItems();
        //Reload the page
        driver.navigate().refresh();
        //Check the message
        WebElement zeroItemsMessage = driver.findElement(By.id("emptylist"));
        Assertions.assertEquals(NO_ITEMS_MESSAGE, zeroItemsMessage.getText());
    }

    @Test
    @DisplayName("Can export multiple items")
    @Tag("items")
    void canExportMultipleItems() {
        itemAPI.deleteAllItems();
        //Login
        login();
        //Create few items via API
        Item item = Item.builder()
                .name("Export 1")
                .currency("BGN")
                .price(23.34)
                .quantity_unit("kg")
                .build();
        itemAPI.createItem(item);
        item.setName("Export item 2");
        itemAPI.createItem(item);
        item.setName("Export item 3");
        itemAPI.createItem(item);
        //Navigate to items page via main menu
        WebElement itemsMenuTab = driver.findElement(By.id("tabs_objects"));
        itemsMenuTab.click();
        //Select all items to be exported
        WebElement selectAllCheckbox = driver.findElement(By.id("handle_all"));
        selectAllCheckbox.click();
        //Click Export button
        WebElement exportButton = driver.findElement(By.id("expbtn"));
        exportButton.click();
    }
}
