package hello.itemservice;

import hello.itemservice.domain.item.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KyhSpringMvcPart1ItemServiceApplicationTests {


    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void clearStore() {
        itemRepository.clearStore();
    }


    @Test
    void save(){
        as
    }

}
