package c0321g1_pawnshop_backend.controller.security;

<<<<<<< HEAD
import c0321g1_pawnshop_backend.entity.security.Account;
import c0321g1_pawnshop_backend.service.security.AccountService;
import c0321g1_pawnshop_backend.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;
=======
import c0321g1_pawnshop_backend.service.security.AccountService;
import c0321g1_pawnshop_backend.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping
public class AccountRestController {
<<<<<<< HEAD

=======
>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
<<<<<<< HEAD

    @GetMapping("/account/setPass")
    public ResponseEntity<Void> setNewPassword(@RequestParam String username,
                                               @RequestParam String newPassword) throws MessagingException {
        Optional<Account> account = accountService.findByUserNames(username);
        if (account.isPresent()) {
            accountService.changePassWord( newPassword,account.get().getAccountId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/account/{username}")
    public ResponseEntity<Account> getAccountByUserName(@PathVariable(name = "username") String username) {
        Optional<Account> account = accountService.findByUserNames(username);
        if (account.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
=======
>>>>>>> b4282ffe7b00faa377054aa2730be124489f086d
}
