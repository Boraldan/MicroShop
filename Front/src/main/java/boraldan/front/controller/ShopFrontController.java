package boraldan.front.controller;


import boraldan.entitymicro.cart.entity.Cart;
import boraldan.entitymicro.shop.dto.ListItemDto;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
//        this.modelMapper.addMappings(new PropertyMap<Item, Item>() {
//            @Override
//            protected void configure() {
////                map().setStorageId(source.getStorage() != null ? source.getStorage().getId() : null);
//                skip(destination.getStorageId());
//            }
//        });
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
    public String getCatalog(@ModelAttribute("category") Category category, Model model) {
        ListItemDto listItemDto = this.restClient.findByCategory(category);
        model.addAttribute("items", listItemDto.getItemList());
        return "catalog";
    }


    @GetMapping("/shop/item")
    public String getItem(Model model, @RequestParam(value = "itemId", required = false) Long itemId) {

        Car item = restClient.getItem(itemId);
        model.addAttribute("item", item);

        List<String> fieldNames = getFieldNames(item.getItemClazz(), item);
        model.addAttribute("fieldNames", fieldNames);

        System.out.println(fieldNames);


        return "item";
    }

//    @ResponseBody
//    @GetMapping("/shop/item")
//    public Item getItem(Model model, @RequestParam(value = "itemId", required = false) Long itemId) {
//
//        Item item = restClient.getItem(itemId);
////        List<String> fieldNames = getFieldNames(item.getItemClazz(), item);
////        System.out.println(fieldNames);
//
//        System.out.println(item);
//
//        return item;
//    }


    private List<String> getFieldNames(Class<?> clazz, Car obj) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true); // Разрешаем доступ к закрытым полям
                Object value = field.get(obj); // Получаем значение поля для конкретного объекта
                fieldNames.add(field.getName() + ": " + value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldNames;
    }

    private <T extends Item> T convertToNeedItem(Item item, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
//        modelMapper.getConfiguration().setSkipNullEnabled(true);
//        PropertyMap<Item, T> propertyMap = new PropertyMap<Item, T>() {
//            protected void configure() {
//                map().setStorageId(source.getStorage() != null ? source.getStorage().getId() : null);
//                map().setStorage(source.getStorage());
//            }
//        };
//        modelMapper.addMappings(propertyMap);
        return modelMapper.map(item, targetType);
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

}