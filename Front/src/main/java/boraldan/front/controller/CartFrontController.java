package boraldan.front.controller;

import boraldan.front.rest_client.CartRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartFrontController {

    private final CartRestClient restClient;




}
