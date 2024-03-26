package boraldan.bank.service;


import boraldan.bank.repository.AccountRepository;
import boraldan.entitymicro.bank.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public Account getByCard(Long card){
        return accountRepository.findByCard(card);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Account sender = accountRepository.findById(idSender).get();
        Account receiver = accountRepository.findById(idReceiver).get();

        sender.setAmount(sender.getAmount().subtract(amount));
        receiver.setAmount(receiver.getAmount().add(amount));

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }

    @Transactional
    public ResponseEntity<?> transferShop(long bayerCard, long sellerCard, BigDecimal amount) {
        try {
            Account sender = accountRepository.findByCard(bayerCard);
            Account receiver = accountRepository.findByCard(sellerCard);

            sender.setAmount(sender.getAmount().subtract(amount));
            receiver.setAmount(receiver.getAmount().add(amount));

            accountRepository.save(sender);
            accountRepository.save(receiver);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: ");
        }
        return ResponseEntity.ok().build();
    }
}
