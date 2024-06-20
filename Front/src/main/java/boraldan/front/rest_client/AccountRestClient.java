package boraldan.front.rest_client;

import boraldan.entitymicro.account.dto.PersonDTO;
import boraldan.entitymicro.account.entity.order.Order;
import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
public class AccountRestClient {

    private final RestClient restClient;

    public Customer getCustomerAccountWhitOrder() {
        return this.restClient.get()
                .uri("/account/customer/orders")
                .retrieve()
                .body(Customer.class);
    }


    public Customer getCustomerAccount() {
        return this.restClient.get()
                .uri("/account/customer")
                .retrieve()
                .body(Customer.class);
    }

    //    public Customer getCustomerByUsername(String username) {
//        return this.restClient.post()
//                .uri("/account/getbyusername")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(username)
//                .retrieve()
//                .body(Customer.class);
//    }

    public Order creatOrder(CartDto cartDto) {
        return this.restClient.post()
                .uri("/account/order/creat")
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartDto)
                .retrieve()
                .body(Order.class);
    }


    public PersonDTO addAccount() {
        return this.restClient.get()
                .uri("/account/kc/add")
                .retrieve()
                .body(PersonDTO.class);
    }

    public List<String> getAccount() {
        return this.restClient.get()
                .uri("/account/kc/all")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

}
