package api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemsResp {
    private String next_page;
    private String prev_page;
    private Integer total;
    private List<Item> items;

}
