package boraldan.gateway.controller;

//
//import boraldan.gateway.service.HeaderService;
//import lombok.AllArgsConstructor;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.web.bind.annotation.RestController;
//
///**
//
// * Контроллер клиента. Заходить на http://127.0.0.1:8080/
// */
//@RestController
//@AllArgsConstructor
//public class ClientController {
//    //  Объект для получения токена авторизации.
//    private final OAuth2AuthorizedClientService authorizedClientService;
//    private final HeaderService headerService;

//    @GetMapping
//    public String getCharacters(Model model, @RequestParam(value = "page", required = false) Integer pageNumber,
//                                Principal principal) {
//        // Получение access токена. Создаем заголовок и помещаем в него токен авторизации
//        headers = headerService.addAuthHeader(principal);
//        if (pageNumber == null) {
//            Characters allCharacters = serviceApi.getAllCharacters(headers);
//            model.addAttribute("characters", allCharacters.getResults());
//            model.addAttribute("info", allCharacters.getInfo());
//            model.addAttribute("currentPage", 5);
//        } else {
//            Characters allCharacters = serviceApi.getAllCharacters(headers, pageNumber);
//            model.addAttribute("characters", allCharacters.getResults());
//            model.addAttribute("info", allCharacters.getInfo());
//            model.addAttribute("currentPage", pageNumber);
//        }
//        return "index";
//    }

//    @GetMapping("/{id}")
//    public String getChar(Model model, @PathVariable("id") int id, Principal principal) {
//        // Получение access токена. Создаем заголовок и помещаем в него токен авторизации
//        headers = headerService.addAuthHeader(principal);
//        model.addAttribute("chars", serviceApi.getCharById(headers, id));
//        return "character";
//    }
//}
