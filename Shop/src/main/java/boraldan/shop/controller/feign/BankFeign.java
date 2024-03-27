package boraldan.shop.controller.feign;



import boraldan.entitymicro.bank.dto.PayDTO;
import boraldan.entitymicro.bank.entity.BankAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bank")
public interface BankFeign {

    @GetMapping
    String getMany();

    @PostMapping("/transfershop")
    ResponseEntity<?> transferShop(@RequestBody PayDTO payDTO);

    @GetMapping("/accounts")
    List<BankAccount> getAllAccounts();

    @PostMapping("/card")
    BankAccount getByCard(@RequestBody Long card);
}
