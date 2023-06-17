package com.munsun.hateoas_spring.hateoasspring.mapping;

import com.munsun.hateoas_spring.hateoasspring.dto.AccountDtoIn;
import com.munsun.hateoas_spring.hateoasspring.dto.AccountDtoOut;
import com.munsun.hateoas_spring.hateoasspring.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountDtoOut toDto(Account account) {
        AccountDtoOut accountDtoOut = new AccountDtoOut();
            accountDtoOut.setId(account.getId());
            accountDtoOut.setLogin(account.getLogin());
            accountDtoOut.setPassword(account.getPassword());
        return accountDtoOut;
    }

    public Account toEntity(AccountDtoIn accountDtoIn) {
        Account account = new Account();
            account.setId(0);
            account.setLogin(accountDtoIn.getLogin());
            account.setPassword(accountDtoIn.getPassword());
        return account;
    }
}
