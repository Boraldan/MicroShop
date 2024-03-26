package boraldan.front.service;

import boraldan.entitymicro.cart.dto.CartsDTO;
import boraldan.entitymicro.shop.dto.CarDto;
import boraldan.entitymicro.shop.dto.ItemsDto;
import boraldan.entitymicro.test.Fly;
import boraldan.entitymicro.test.Lot;
import boraldan.front.api.UrlApi;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ServiceApi implements IServiceApi {

    private RestTemplate template;
    private HttpHeaders headers;
    private final UrlApi URLApi;

    @Override
    public Lot getLot() {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<Lot> response = template.exchange(URLApi.getLot_api(), HttpMethod.GET, entity, Lot.class);
        return response.getBody();
    }

    @Override
    public CarDto getCar() {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<CarDto> response = template.exchange(URLApi.getCar_api(), HttpMethod.GET, entity, CarDto.class);
        CarDto car = response.getBody();
        System.out.println(car.toString());
        return car;
    }

    @Override
    public Fly getFly() {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<Fly> response = template.exchange(URLApi.getFly_api(), HttpMethod.GET, entity, Fly.class);
        Fly fly = response.getBody();
        System.out.println(fly.toString());
        return fly;
    }

    @Override
    public ItemsDto getItems() {
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ItemsDto> responce = template.exchange(URLApi.getItems_api(), HttpMethod.GET, entity, ItemsDto.class);
        return responce.getBody();
    }


    @Override
    public CartsDTO getCarts() {
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<CartsDTO> responce = template.exchange(URLApi.getCarts_api(), HttpMethod.GET, entity, CartsDTO.class);
        return responce.getBody();
    }

    @Override
    public String getCard() {
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responce = template.exchange(URLApi.getCard_api(), HttpMethod.GET, entity, String.class);
        return responce.getBody();
    }

    @Override
    public String getSession(String sessionId) {
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set("IdSession", sessionId);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responce = template.exchange(URLApi.getSession_shop_api(), HttpMethod.GET, entity, String.class);
        return responce.getBody();
    }


    //    @Override
//    public Characters getAllCharacters(HttpHeaders headers, int page) {
//        String urlPage = "?page=" + page;
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        ResponseEntity<Characters> responce = template.exchange(URL.getCHARACTER_API() + urlPage, HttpMethod.GET, entity, Characters.class);
//        return responce.getBody();
//    }

}
