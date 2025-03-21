package hello.itemservice.domain.item;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer integer;
    private Integer pk;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer integer) {
        this.itemName = itemName;
        this.price = price;
        this.integer = integer;
    }
}
