package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class ItemRepositoryTest  {


    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void clearStore() {
        itemRepository.clearStore();
    }


    @Test
    void save(){
        //
        Item item = new Item("itemA", 10000, 10);

        Item save = itemRepository.save(item);

        Item findItem = itemRepository.findById(save.getId());


        Assertions.assertThat(findItem).isEqualTo(save);


    }

    @Test
    void findAll(){
        //
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 10000, 10);


        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> allItem = itemRepository.findAll();

        Assertions.assertThat(allItem.size()).isEqualTo(2);
        Assertions.assertThat(allItem).contains(item1, item2);
    }

    @Test
    void updateItem(){
        //
        Item item = new Item("item1", 10000, 10);

        Item saveItem = itemRepository.save(item);
        Long id = saveItem.getId();

        Item item2 = new Item("item2", 20000, 20);
        itemRepository.update(id, item2);


        Item findedItem = itemRepository.findById(id);
        Assertions.assertThat(findedItem.getItemName()).isEqualTo(item2.getItemName());
        Assertions.assertThat(findedItem.getPrice()).isEqualTo(item2.getPrice());
        Assertions.assertThat(findedItem.getQuantity()).isEqualTo(item2.getQuantity());
    }

}