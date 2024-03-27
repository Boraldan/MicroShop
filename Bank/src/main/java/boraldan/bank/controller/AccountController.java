package boraldan.bank.controller;


import boraldan.bank.util.ValidPayDTO;
import boraldan.entitymicro.bank.dto.PayDTO;
import boraldan.entitymicro.bank.dto.TransferDTO;
import boraldan.entitymicro.bank.entity.BankAccount;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import boraldan.bank.service.TransferService;

import java.util.List;

@AllArgsConstructor
@RestController
public class AccountController {

    private final TransferService transferService;
    private final ValidPayDTO validPayDTO;



    @GetMapping
    public String getMany(){
        System.out.println("Банк пишет нам.");
        return "Give me your many!";
    }

    @PostMapping("/transfershop")
    public ResponseEntity<?> transferShop(@RequestBody PayDTO payDTO) {
        if (validPayDTO.falsePayDTO(payDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: ");
        }
        return transferService.transferShop(payDTO.getBayerCard(), payDTO.getSellerCard(), payDTO.getAmount());
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferDTO request) {
        transferService.transferMoney(request.getSenderAccountId(), request.getReceiverAccountId(), request.getAmount());
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAllAccounts() {
        return transferService.getAllAccounts();
    }
}
