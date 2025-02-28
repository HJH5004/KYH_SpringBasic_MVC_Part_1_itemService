package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> allItems = itemRepository.findAll();
        model.addAttribute("items", allItems);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model) {
        Item items = itemRepository.findById(itemId);
        model.addAttribute("item", items);

        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String saveV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model
    ) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setInteger(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String saveV2(@ModelAttribute("item") Item item, Model model) {

        log.info("save item: {}", item);
        itemRepository.save(item);

        //@ModelAttribute를 통해 model에 item이 자동으로 들어가서 생략이 가능 하다.
//        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    //ModelAttribute에서 value가 생략되어 있으면 관례적으로 모델명 앞글자를 소문자로 하여 던져 준다.
    //ex Item -> item
    public String saveV3(@ModelAttribute Item item) {

        itemRepository.save(item);

        return "basic/item";
    }

    //ModelAttribute가 없이 쓰기도 하지만 그리 권장하진 않음
//    @PostMapping("/add")
    public String saveV4(Item item) {

        itemRepository.save(item);

        //바로 뷰 템플릿을 호출하는 형태
        return "basic/item";
    }

    //ModelAttribute가 없이 쓰기도 하지만 그리 권장하진 않음
//    @PostMapping("/add")
    public String saveV5(Item item) {

        itemRepository.save(item);

        return "redirect:/basic/items/"+item.getId();
    }

    //ModelAttribute가 없이 쓰기도 하지만 그리 권장하진 않음
    @PostMapping("/add")
    public String saveV6(Item item, RedirectAttributes redirectAttributes) {

        itemRepository.save(item);
        //ㄴ
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {

        Item byId = itemRepository.findById(itemId);
        model.addAttribute("item", byId);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        //@PathVariable의 값은 리다이렉트에서도 된다.
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

    }

}
