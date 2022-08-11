package api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private Integer id;
    private String name;
    private String currency;
    private String quantity_unit;
    private Double price;

}
