package boraldan.front.controller;


import boraldan.entitymicro.cart.entity.Cart;
import boraldan.entitymicro.shop.dto.ListItemDto;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.front.rest_client.ShopRestClient;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller

public class ShopFrontController {
    private final ShopRestClient restClient;
    private final HttpSession httpSession;
    private final ModelMapper modelMapper;

    @Autowired
    public ShopFrontController(ShopRestClient restClient, HttpSession httpSession, ModelMapper modelMapper) {
        this.restClient = restClient;
        this.httpSession = httpSession;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/carttest")
    public String test(Model model, @AuthenticationPrincipal Principal principal, @ModelAttribute("cart") Cart cart) {
        return "cart";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("category", new Category());
        return "catalog";
    }

    @PostMapping("/catalog")
    public String getCatalog(Model model, @ModelAttribute("category") Category category) {
        ListItemDto listItemDto = this.restClient.findByCategory(category);
        model.addAttribute("items", listItemDto.getItemList());
        return "catalog";
    }

    @GetMapping("/shop/item")
    public String getItem(Model model,
                          @RequestParam(value = "itemId", required = false) UUID itemId,
                          @RequestParam(value = "itemClassName", required = false) String itemClassName) {
        Class<? extends Item> clazz = null;
        try {
            clazz = (Class<? extends Item>) Class.forName(itemClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Item item = restClient.getItem(itemId, clazz);
        model.addAttribute("item", item);
        List<String> fieldNames = getFieldNames(item, clazz);
        model.addAttribute("fieldNames", fieldNames);
        return "item";
    }

    @PostMapping("/shop/item/delete{itemId}")
    public String deleteItem(@PathVariable("itemId") UUID itemId) {
        System.out.println(itemId);
        restClient.deleteItem(itemId);
        return "redirect:/catalog";
    }

    // технический метод по добавлению товара Car
    @GetMapping("/shop/addcar")
    public String addCar(Model model) {
        model.addAttribute("item", restClient.addCar());
        return "item";
    }

    @GetMapping
    public String index(Model model,
                        Principal principal,
                        @AuthenticationPrincipal Principal principal2,
                        Authentication authentication) {
        System.out.println("index  principal  -->  " + principal);
        System.out.println("index  principal2  -->  " + principal2);
        System.out.println("index  authentication  -->  " + authentication);
        System.out.println("index  httpSession  -->  " + httpSession.getId());
        return "index";
    }

    private <T extends Item> List<String> getFieldNames(T obj, Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                fieldNames.add(field.getName() + ": " + value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldNames;
    }

    private <T extends Item> T convertToNeedItem(Object item, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return modelMapper.map(item, targetType);
    }

// region -->  рабочие методы другой реализации

//    @ResponseBody
//    @GetMapping("/shop/item")
//    public ResponseEntity<?> getItem(@RequestParam(value = "itemId", required = false) UUID itemId,
//                                     @RequestParam(value = "itemClassName", required = false) String itemClassName) {
//        Item item = new Item();
//        try {
//            Class<?> clazz = Class.forName(itemClassName);
//            Object instance = clazz.getDeclaredConstructor().newInstance();
//            item = convertToNeedItem(instance, clazz);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok(restClient.getItem(itemId, item.getItemClazz()));
//    }

// endregion

}