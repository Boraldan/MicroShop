package boraldan.front.rest_client;

import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class CartRestClient {

    private final RestClient restClient;

    public CartDto getCart(Customer customer) {
        return this.restClient.post()
                .uri("/cart/get")
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer)
                .retrieve()
                .body(CartDto.class);
    }

    public ResponseEntity<Void> saveCart(CartDto cartDto) {
        return this.restClient.patch()
                .uri("/cart/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartDto)
                .retrieve()
                .toBodilessEntity();

    }


}
