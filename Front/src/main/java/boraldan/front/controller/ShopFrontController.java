package boraldan.front.controller;


import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.cart.entity.Cart;
import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.entitymicro.shop.dto.SpecificationDto;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.toolbox.builder.SpecificationDtoBuilder;
import boraldan.front.rest_client.ShopRestClient;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
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
@RequiredArgsConstructor
public class ShopFrontController {

    private final ShopRestClient restClient;
    private final HttpSession httpSession;
    private final ModelMapper modelMapper;

    @GetMapping("/carttest")
    public String test(Model model, @AuthenticationPrincipal Principal principal, @ModelAttribute("cart") Cart cart) {
        return "cart";
    }

    @GetMapping("/catalog")
    public String getCatalog(Model model, @ModelAttribute("cart") CartDto cartDto) {
        model.addAttribute("category", new Category());
        model.addAttribute("cart", cartDto);
        model.addAttribute("lotDto", new LotDto());
        return "catalog";
    }

    @PostMapping("/catalog")
    public String postCatalog(Model model, @ModelAttribute("category") Category category) {
        List<Item> itemList = this.restClient.findByCategory(category);
        model.addAttribute("items", itemList);
        model.addAttribute("lotDto", new LotDto());
        return "catalog";
    }

    /**
     * Вариант поиска используя interface Specification<T>
     * @return Page<T>
     */

    @GetMapping("/shop/items/spec")
    public String getPageBySpecification(Model model,
                                         @RequestParam(name = "category_name", required = false) CategoryName categoryName,
                                         @RequestParam(name = "name_part", required = false) String namePart,
                                         @RequestParam(name = "min_price", required = false) Long minPrice,
                                         @RequestParam(name = "max_price", required = false) Long maxPrice,
                                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                                         @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize) {

        SpecificationDto spec = SpecificationDtoBuilder.creat()
                                        .setPage(page)
                                        .setPageSize(pageSize)
                                        .setMinScore(minPrice)
                                        .setMaxScore(maxPrice)
                                        .setPartName(namePart)
                                        .setCategoryName(categoryName)
                                        .build();

        Page<Item> itemsPage = this.restClient.getAllBySpecification(spec);
        model.addAttribute("items", itemsPage);
        model.addAttribute("spec", spec);

        return "catalog_spec";
    }

    /**
     * Вариант поиска @Query запроса по параметрам
     * @return Page<T>
     */
    @GetMapping("/shop/items/param")
    public String getPageByParam(Model model,
                                         @RequestParam(name = "category_name", required = false) CategoryName categoryName,
                                         @RequestParam(name = "name_part", required = false) String namePart,
                                         @RequestParam(name = "min_price", required = false) Long minPrice,
                                         @RequestParam(name = "max_price", required = false) Long maxPrice,
                                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                                         @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize) {

        SpecificationDto spec = SpecificationDtoBuilder.creat()
                                        .setPage(page)
                                        .setPageSize(pageSize)
                                        .setMinScore(minPrice)
                                        .setMaxScore(maxPrice)
                                        .setPartName(namePart)
                                        .setCategoryName(categoryName)
                                        .build();

        Page<Item> itemsPage = this.restClient.getAllBySpecification(spec);
        model.addAttribute("items", itemsPage);
        model.addAttribute("spec", spec);

        return "catalog_param";
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

    // технический метод по добавлению товара Car Bike ------------------
    @GetMapping("/shop/addcar")
    public String addCar(Model model) {
        Car car = restClient.addCar();
        model.addAttribute("item", car);
        List<String> fieldNames = getFieldNames(car, car.getItemClazz());
        model.addAttribute("fieldNames", fieldNames);
        return "item";
    }

    @GetMapping("/shop/addbike")
    public String addBike(Model model) {
        Bike bike = restClient.addBike();
        model.addAttribute("item", bike);
        List<String> fieldNames = getFieldNames(bike, bike.getItemClazz());
        model.addAttribute("fieldNames", fieldNames);
        return "item";
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