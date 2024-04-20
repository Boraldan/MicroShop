package boraldan.front.rest_client;

import boraldan.entitymicro.account.dto.SingUpDto;
import boraldan.entitymicro.account.entity.person.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class AuthorizeRestClient {

    private final RestClient restClient;

    public Customer singUpCustomer(SingUpDto singUpDto) {
        return this.restClient.post()
                .uri("/account/authorize/singup")
                .contentType(MediaType.APPLICATION_JSON)
                .body(singUpDto)
                .retrieve()
                .body(Customer.class);
    }
}
