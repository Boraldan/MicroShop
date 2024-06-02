package boraldan.front.controller;


import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.entitymicro.shop.dto.SpecificationDto;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.toolbox.builder.LotDtoBuilder;
import boraldan.entitymicro.toolbox.builder.SpecificationDtoBuilder;
import boraldan.front.rest_client.ShopRestClient;
import boraldan.front.service.ItemFrontServiceV_1;
import boraldan.front.utilit.LotdtoValidator;
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

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ShopFrontController {

    private final ShopRestClient restClient;
    private final HttpSession httpSession;
    private final ModelMapper modelMapper;
    private final ItemFrontServiceV_1 itemFrontService;

    private final LotdtoValidator lotDtoValidator;

//    @GetMapping("/carttest")
//    public String test(Model model, @AuthenticationPrincipal Principal principal, @ModelAttribute("cart") Cart cart) {
//        return "cart";
//    }

    @GetMapping("/catalog")
    public String getCatalog(Model model, @ModelAttribute("cartDto") CartDto cartDto) {
        model.addAttribute("category", new Category());
        model.addAttribute("cartDto", cartDto); // добавляем для удобства работы с полями cartDto
//        model.addAttribute("lotDto", new LotDto());
        return "catalog";
    }

    @PostMapping("/catalog")
    public String postCatalog(Model model, @ModelAttribute("category") Category category) {
        List<Item> itemList = this.restClient.findByCategory(category);
        model.addAttribute("items", itemList);
//        model.addAttribute("lotDto", new LotDto());
        return "catalog";
    }

    /**
     * Вариант поиска используя interface Specification<T>
     *
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
     *
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
        Item item = itemFrontService.getAndConvertItem(itemId, itemClassName);
        model.addAttribute("item", item);
        List<String> fieldNames = itemFrontService.getFieldNames(item, item.getItemClazz());
        model.addAttribute("fieldNames", fieldNames);
        model.addAttribute("lotDto", LotDtoBuilder.creat().setQuantity(1).build());
        return "item";
    }

//    @PostMapping("/item/add")
//    public String addItemToCart(Model model, BindingResult bindingResult,
//                                @ModelAttribute("lotDto") @Valid LotDto lotDto) {
//        Item item = itemFrontService.getAndConvertItem(lotDto.getItemId(), lotDto.getItemClassName());
//        lotDtoValidator.validate(lotDto, bindingResult);
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("item", item);
//            List<String> fieldNames = itemFrontService.getFieldNames(item, item.getItemClazz());
//            model.addAttribute("fieldNames", fieldNames);
//            return "item";
//        }
//
//        System.out.println(lotDto);
//
//        return "redirect:/shop/item?itemId=%s&itemClassName=%s".formatted(lotDto.getItemId().toString(), lotDto.getItemClassName());
//    }

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


    // технический метод по добавлению товара Car Bike ------------------
    @GetMapping("/shop/addcar")
    public String addCar(Model model) {
        Car car = restClient.addCar();
        model.addAttribute("item", car);
        List<String> fieldNames = itemFrontService.getFieldNames(car, car.getItemClazz());
        model.addAttribute("fieldNames", fieldNames);
        model.addAttribute("lotDto", new LotDto());
        return "item";
    }

    @GetMapping("/shop/addbike")
    public String addBike(Model model) {
        Bike bike = restClient.addBike();
        model.addAttribute("item", bike);
        List<String> fieldNames = itemFrontService.getFieldNames(bike, bike.getItemClazz());
        model.addAttribute("fieldNames", fieldNames);
        model.addAttribute("lotDto", new LotDto());
        return "item";
    }

}