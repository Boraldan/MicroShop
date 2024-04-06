package boraldan.front.controller;


import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.front.service.IServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class FrontController {

    private final IServiceApi serviceApi;
    private final RestClient restClient;
    private final RestTemplate restTemplate;


    public FrontController(IServiceApi serviceApi, RestTemplate restTemplate) {
        this.serviceApi = serviceApi;
        this.restTemplate = restTemplate;
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8765/shop")
                .build();
    }

    //
//    @GetMapping
//    public String start(Model model) {
//        Lot lot = new Lot();
//        try {
//            lot = IServiceApi.getLot();
//            model.addAttribute("lot", lot);
//        } catch (Exception e) {
//            model.addAttribute("lot", lot);
//            System.out.println(e.getMessage());
//        }
//        return "index";
//    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("category", new Category());
        return "catalog";
    }

    @PostMapping("/catalog")
    public String getCatalog(@ModelAttribute("category") Category category, Model model) {
        System.out.println(category);
//        ListItemDto listItemDto = serviceApi.getItems(category);
//        model.addAttribute("items", listItemDto.getItemList());
        List<Car> carList= serviceApi.getItems(category);
        model.addAttribute("items", carList);
        System.out.println(carList);
        return "catalog";
    }


//    @GetMapping("/")
//    public String getGreetingsPage(Model model) {
//
//
//        model.addAttribute("items",
//                this.restClient.post()
//                        .uri("/greetings-api/greetings")
//                        .body( )
//                        .retrieve()
//                        .body(Greetings.class));
//        return "catalog";
//    }

}