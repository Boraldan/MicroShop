package boraldan.front.rest_client;

import boraldan.entitymicro.account.dto.PersonDTO;
import boraldan.entitymicro.account.entity.person.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
public class AccountRestClient {

    private final RestClient restClient;

    public Customer getCustomerByUsername(String username) {
        return this.restClient.post()
                .uri("/account/getbyusername")
                .contentType(MediaType.APPLICATION_JSON)
                .body(username)
                .retrieve()
                .body(Customer.class);
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
