package boraldan.front.rest_client;

import boraldan.entitymicro.cart.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@RequiredArgsConstructor
public class CartRestClient {

    private final RestClient restClient;

    public Cart getCart(UUID cartId) {
        String stringUuid = cartId.toString();
        return this.restClient.post()
                .uri("/cart/get")
                .contentType(MediaType.APPLICATION_JSON)
                .body(stringUuid)
                .retrieve()
                .body(Cart.class);
    }

    public ResponseEntity<Void> saveCartSession(Cart cart) {
        return this.restClient.post()
                .uri("/cart/savesession")
                .contentType(MediaType.APPLICATION_JSON)
                .body(cart)
                .retrieve()
                .toBodilessEntity();


    }


}
